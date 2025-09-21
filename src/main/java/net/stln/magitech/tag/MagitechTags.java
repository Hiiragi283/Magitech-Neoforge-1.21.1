package net.stln.magitech.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.stln.magitech.Magitech;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.item.tool.upgrade.Upgrade;

import org.jetbrains.annotations.NotNull;

public final class MagitechTags {
    private static ResourceLocation commonId(String path) {
        return ResourceLocation.fromNamespaceAndPath("c", path);
    }

    public static final class Blocks {
        public static final TagKey<Block> ORES_FLUORITE = create("ores/fluorite");
        public static final TagKey<Block> ORES_TOURMALINE = create("ores/tourmaline");

        private static @NotNull TagKey<Block> create(@NotNull String path) {
            return TagKey.create(Registries.BLOCK, commonId(path));
        }

        private static @NotNull TagKey<Block> createMod(@NotNull String path) {
            return TagKey.create(Registries.BLOCK, Magitech.id(path));
        }
    }

    public static final class Biomes {
        public static final TagKey<Biome> HAS_CELIFERN_FOREST = create("has_celifern_forest");
        public static final TagKey<Biome> HAS_CHARCOAL_BIRCH_FOREST =
                create("has_charcoal_birch_forest");
        public static final TagKey<Biome> HAS_MANA_BERRY_BUSH = create("has_mana_berry_bush");
        public static final TagKey<Biome> HAS_MISTALIA_PETALS = create("has_mistalia_petals");
        public static final TagKey<Biome> IS_MISTJADE = create("is_celifern");
        public static final TagKey<Biome> IS_SCORCHED = create("is_scorched");

        private static @NotNull TagKey<Biome> create(@NotNull String path) {
            return TagKey.create(Registries.BIOME, Magitech.id(path));
        }
    }

    public static final class Items {
        public static final TagKey<Item> ORES_FLUORITE = create("ores/fluorite");
        public static final TagKey<Item> ORES_TOURMALINE = create("ores/tourmaline");

        public static final TagKey<Item> GEMS_CITRINE = create("gems/citrine");
        public static final TagKey<Item> GEMS_FLUORITE = create("gems/fluorite");
        public static final TagKey<Item> GEMS_MANA_CHARGED_FLUORITE =
                create("gems/mana_charged_fluorite");
        public static final TagKey<Item> GEMS_REDSTONE_CRYSTAL = create("gems/redstone_crystal");
        public static final TagKey<Item> GEMS_SULFUR = create("gems/sulfur");
        public static final TagKey<Item> GEMS_TOURMALINE = create("gems/tourmaline");

        public static final TagKey<Item> ASPECT_CRYSTAL_BASE = createMod("aspect_crystal_base");

        public static final TagKey<Item> THREAD_BOUND = createMod("threadbound");
        public static final TagKey<Item> REPAIR_COMPONENT = createMod("repair_component");
        public static final TagKey<Item> UPGRADE_MATERIAL_0 = createMod("upgrade_material_0");
        public static final TagKey<Item> UPGRADE_MATERIAL_5 = createMod("upgrade_material_5");
        public static final TagKey<Item> UPGRADE_MATERIAL_10 = createMod("upgrade_material_10");
        public static final TagKey<Item> UPGRADE_MATERIAL_15 = createMod("upgrade_material_15");
        public static final TagKey<Item> UPGRADE_MATERIAL_20 = createMod("upgrade_material_20");

        private static @NotNull TagKey<Item> create(@NotNull String path) {
            return TagKey.create(Registries.ITEM, commonId(path));
        }

        private static @NotNull TagKey<Item> createMod(@NotNull String path) {
            return TagKey.create(Registries.ITEM, Magitech.id(path));
        }
    }

    public static final class Upgrades {
        public static final TagKey<Upgrade> IS_SPELL = createMod("is_spell");

        private static @NotNull TagKey<Upgrade> createMod(@NotNull String path) {
            return TagKey.create(MagitechRegistries.Keys.UPGRADE, Magitech.id(path));
        }
    }
}
