package net.stln.magitech.magic.mana;

import java.util.Map;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.stln.magitech.init.MagitechAttributes;
import net.stln.magitech.network.SyncManaPayload;

public class ManaUtil {

    public static void tick(Entity entity) {
        if (entity instanceof Player player && !player.level().isClientSide) {
            regenAllMana(player);
        }
    }

    private static void regenAllMana(Player player) {
        regenTickMana(player, ManaType.MANA);
        regenTickMana(player, ManaType.NOCTIS);
        regenTickMana(player, ManaType.LUMINIS);
        regenTickMana(player, ManaType.FLUXIA);
    }

    public static void regenMana(Player player, ManaType type) {
        double regenAmount = getManaRegen(player, type);
        setMana(
                player,
                type,
                Math.min(
                        regenAmount + ManaData.getCurrentMana(player, type),
                        getMaxMana(player, type)));
    }

    public static void regenTickMana(Player player, ManaType type) {
        double regenAmount = getManaRegen(player, type) / 20;
        if (type != ManaType.MANA
                && ManaData.getCurrentMana(player, ManaType.MANA)
                        < getMaxMana(player, ManaType.MANA) / 2) {
            regenAmount /= 2;
        }
        setMana(
                player,
                type,
                Math.min(
                        regenAmount + ManaData.getCurrentMana(player, type),
                        getMaxMana(player, type)));
    }

    public static boolean checkMana(Player player, Map<ManaType, Double> map) {
        boolean flag = true;
        if (map.getOrDefault(ManaType.MANA, 0.0) > ManaData.getPrevMana(player, ManaType.MANA)
                && !player.isCreative()) {
            flag = false;
            player.displayClientMessage(
                    Component.translatable("spell.magitech.hint.not_enough_mana")
                            .withColor(0xFF8080),
                    true);
        }
        return flag;
    }

    public static float checkStrandDamageMul(
            Player player, Map<ManaType, Double> map, float spellDamage) {
        for (Map.Entry<ManaType, Double> entry : map.entrySet()) {
            ManaType type = entry.getKey();
            Double value = entry.getValue();
            if (value > ManaData.getPrevMana(player, type)
                    && type != ManaType.MANA
                    && !player.isCreative()) {
                spellDamage /= 2;
                player.displayClientMessage(
                        Component.translatable("spell.magitech.hint.not_enough_" + type.getName())
                                .withColor(0xFF8080),
                        true);
            }
        }
        return spellDamage;
    }

    public static boolean useMana(Player player, Map<ManaType, Double> map) {
        boolean flag = true;
        if (map.getOrDefault(ManaType.MANA, 0.0) > ManaData.getPrevMana(player, ManaType.MANA)
                && !player.isCreative()) {
            flag = false;
        }
        if (flag) {
            if (!player.isCreative()) {
                for (Map.Entry<ManaType, Double> entry : map.entrySet()) {
                    ManaType type = entry.getKey();
                    Double value = entry.getValue();
                    setMana(player, type, ManaData.getCurrentMana(player, type) - value);
                }
            }
            return true;
        }
        return false;
    }

    public static boolean useManaServerOnly(Player player, Map<ManaType, Double> map) {
        boolean flag = true;
        if (map.getOrDefault(ManaType.MANA, 0.0) > ManaData.getPrevMana(player, ManaType.MANA)
                && !player.isCreative()) {
            flag = false;
        }
        if (flag) {
            if (!player.level().isClientSide && !player.isCreative()) {
                for (Map.Entry<ManaType, Double> entry : map.entrySet()) {
                    ManaType type = entry.getKey();
                    Double value = entry.getValue();
                    setMana(player, type, ManaData.getCurrentMana(player, type) - value);
                }
            }
            return true;
        }
        player.displayClientMessage(
                Component.translatable("spell.magitech.hint.not_enough_mana").withColor(0xFF8080),
                true);
        return false;
    }

    public static boolean useManaClientOnly(Player player, Map<ManaType, Double> map) {
        boolean flag = true;
        if (map.getOrDefault(ManaType.MANA, 0.0) > ManaData.getPrevMana(player, ManaType.MANA)
                && !player.isCreative()) {
            flag = false;
        }
        if (flag) {
            if (player.level().isClientSide && !player.isCreative()) {
                for (Map.Entry<ManaType, Double> entry : map.entrySet()) {
                    ManaType type = entry.getKey();
                    Double value = entry.getValue();
                    setMana(player, type, ManaData.getCurrentMana(player, type) - value);
                }
            }
            return true;
        }
        player.displayClientMessage(
                Component.translatable("spell.magitech.hint.not_enough_mana").withColor(0xFF8080),
                true);
        return false;
    }

    public static void setMana(Player player, ManaType type, double value) {
        ManaData.setPrevMana(player, type, ManaData.getCurrentMana(player, type));
        ManaData.setCurrentMana(player, type, value);
        if (player.level().isClientSide) {
            PacketDistributor.sendToServer(new SyncManaPayload(value, type.id, player.getUUID()));
        } else {
            PacketDistributor.sendToAllPlayers(
                    new SyncManaPayload(value, type.id, player.getUUID()));
        }
    }

    public static double getManaRegen(Player player, ManaType type) {
        var attributeHolder =
                switch (type) {
                    case MANA -> MagitechAttributes.MANA_REGEN;
                    case NOCTIS -> MagitechAttributes.NOCTIS_REGEN;
                    case LUMINIS -> MagitechAttributes.LUMINIS_REGEN;
                    case FLUXIA -> MagitechAttributes.FLUXIA_REGEN;
                };
        var attribute = player.getAttribute(attributeHolder);
        return attribute == null ? 0 : attribute.getValue();
    }

    public static double getMaxMana(Player player, ManaType type) {
        return switch (type) {
            case MANA ->
                    player.getAttribute(MagitechAttributes.MAX_MANA) == null
                            ? 0
                            : player.getAttribute(MagitechAttributes.MAX_MANA).getValue();
            case NOCTIS ->
                    player.getAttribute(MagitechAttributes.MAX_NOCTIS) == null
                            ? 0
                            : player.getAttribute(MagitechAttributes.MAX_NOCTIS).getValue();
            case LUMINIS ->
                    player.getAttribute(MagitechAttributes.MAX_LUMINIS) == null
                            ? 0
                            : player.getAttribute(MagitechAttributes.MAX_LUMINIS).getValue();
            case FLUXIA ->
                    player.getAttribute(MagitechAttributes.MAX_FLUXIA) == null
                            ? 0
                            : player.getAttribute(MagitechAttributes.MAX_FLUXIA).getValue();
        };
    }

    public static ManaType getManaType(int id) {
        for (ManaType manatype : ManaType.values()) {
            if (manatype.id == id) {
                return manatype;
            }
        }
        return null;
    }

    public enum ManaType {
        MANA(0, "mana"),
        NOCTIS(1, "noctis"),
        LUMINIS(2, "luminis"),
        FLUXIA(3, "fluxia");

        final int id;
        final String name;

        ManaType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
