package net.stln.magitech.network;

import java.util.Objects;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.stln.magitech.magic.mana.ManaData;
import net.stln.magitech.magic.mana.ManaUtil;

public class SyncManaPayLoadHandler {

    public static void handleDataOnMainS2C(
            final SyncManaPayload payload, final IPayloadContext context) {
        Player player = null;
        Level level = context.player().level();
        for (Player search : level.players()) {
            if (Objects.equals(search.getUUID(), payload.uuid())) {
                player = search;
                break;
            }
        }
        if (player == null) {
            return;
        }
        ManaData.setPrevMana(
                player,
                ManaUtil.getManaType(payload.manaType()),
                ManaData.getCurrentMana(player, ManaUtil.getManaType(payload.manaType())));
        ManaData.setCurrentMana(player, ManaUtil.getManaType(payload.manaType()), payload.value());
    }

    public static void handleDataOnMainC2S(
            final SyncManaPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        ManaData.setPrevMana(
                player,
                ManaUtil.getManaType(payload.manaType()),
                ManaData.getCurrentMana(player, ManaUtil.getManaType(payload.manaType())));
        ManaData.setCurrentMana(player, ManaUtil.getManaType(payload.manaType()), payload.value());
        MinecraftServer server =
                Objects.requireNonNull(
                        ServerLifecycleHooks.getCurrentServer(),
                        "Cannot send clientbound payloads on the client");
        for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers())
            if (player.getUUID() != serverPlayer.getUUID()) {
                PacketDistributor.sendToPlayer(serverPlayer, payload);
            }
    }
}
