package net.stln.magitech.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.stln.magitech.Magitech;

import org.jetbrains.annotations.NotNull;

public final class MagitechTags {

    public static final class Biomes {
        public static final TagKey<Biome> HAS_CELIFERN_FOREST = create("has_celifern_forest");
        public static final TagKey<Biome> HAS_CHARCOAL_BIRCH_FOREST =
                create("has_charcoal_birch_forest");
        public static final TagKey<Biome> HAS_MANA_BERRY_BUSH = create("has_mana_berry_bush");
        public static final TagKey<Biome> HAS_MISTALIA_PETALS = create("has_mistalia_petals");
        public static final TagKey<Biome> IS_SCORCHED = create("is_scorched");

        private static @NotNull TagKey<Biome> create(@NotNull String path) {
            return TagKey.create(Registries.BIOME, Magitech.id(path));
        }
    }

    public static final class Items {
        public static TagKey<Item> THREAD_BOUND = create("threadbound");
        public static TagKey<Item> REPAIR_COMPONENT = create("repair_component");
        public static TagKey<Item> UPGRADE_MATERIAL_0 = create("upgrade_material_0");
        public static TagKey<Item> UPGRADE_MATERIAL_5 = create("upgrade_material_5");
        public static TagKey<Item> UPGRADE_MATERIAL_10 = create("upgrade_material_10");
        public static TagKey<Item> UPGRADE_MATERIAL_15 = create("upgrade_material_15");
        public static TagKey<Item> UPGRADE_MATERIAL_20 = create("upgrade_material_20");

        private static @NotNull TagKey<Item> create(@NotNull String path) {
            return TagKey.create(Registries.ITEM, Magitech.id(path));
        }
    }
}
