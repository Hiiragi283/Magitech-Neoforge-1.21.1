package net.stln.magitech.init;

import java.util.List;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.stln.magitech.Magitech;

public final class MagitechDamageTypes {

    public static final ResourceKey<DamageType> EMBER = create("ember");
    public static final ResourceKey<DamageType> FLOW = create("flow");
    public static final ResourceKey<DamageType> GLACE = create("glace");
    public static final ResourceKey<DamageType> HOLLOW = create("hollow");
    public static final ResourceKey<DamageType> MAGIC = create("magic");
    public static final ResourceKey<DamageType> MANA = create("mana");
    public static final ResourceKey<DamageType> PHANTOM = create("phantom");
    public static final ResourceKey<DamageType> SURGE = create("surge");
    public static final ResourceKey<DamageType> TREMOR = create("tremor");

    public static final ResourceKey<DamageType> MANA_BERRY_BUSH = create("mana_berry_bush");

    public static final List<ResourceKey<DamageType>> MAGICAL =
            List.of(EMBER, FLOW, GLACE, HOLLOW, MAGIC, MANA, PHANTOM, SURGE, TREMOR);

    /*public static float getElementDamage(Player player, Entity target, ItemStack stack) {
        ToolStats stats =
                ((PartToolItem) stack.getItem()).getSumStats(player, player.level(), stack);
        float multiplier = DataMapHelper.getElementMultiplier(target, stats.getElement());
        return stats.getStats().get(ToolStats.ELM_ATK_STAT) * multiplier;
    }*/

    private static ResourceKey<DamageType> create(String path) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Magitech.id(path));
    }
}
