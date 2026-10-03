package net.stln.magitech.content.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.stln.magitech.Magitech;
import org.jetbrains.annotations.NotNull;

public class ItemTagKeys {

    public static final TagKey<Item> THREAD_BOUND = create(Magitech.id("threadbound"));
    public static final TagKey<Item> SYNTHESISED_TOOL = create(Magitech.id("synthesised_tool"));
    public static final TagKey<Item> REPAIR_COMPONENT = create(Magitech.id("repair_component"));
    public static final TagKey<Item> UPGRADE_MATERIAL_0 = create(Magitech.id("upgrade_material_0"));
    public static final TagKey<Item> UPGRADE_MATERIAL_5 = create(Magitech.id("upgrade_material_5"));
    public static final TagKey<Item> UPGRADE_MATERIAL_10 = create(Magitech.id("upgrade_material_10"));
    public static final TagKey<Item> UPGRADE_MATERIAL_15 = create(Magitech.id("upgrade_material_15"));
    public static final TagKey<Item> UPGRADE_MATERIAL_20 = create(Magitech.id("upgrade_material_20"));

    public static final TagKey<Item> CELIFERN_LOGS = create(Magitech.id("celifern_logs"));
    public static final TagKey<Item> CHARCOAL_BIRCH_LOGS = create(Magitech.id("charcoal_birch_logs"));
    public static final TagKey<Item> MYSTWOOD_LOGS = create(Magitech.id("mystwood_logs"));

    public static final TagKey<Item> ENCHANTABLE_ARMOR = external("minecraft", "enchantable/armor");
    public static final TagKey<Item> ENCHANTABLE_DURABILITY = external("minecraft", "enchantable/durability");
    public static final TagKey<Item> ENCHANTABLE_EQUIPPABLE = external("minecraft", "enchantable/equippable");
    public static final TagKey<Item> ENCHANTABLE_FOOT_ARMOR = external("minecraft", "enchantable/foot_armor");
    public static final TagKey<Item> ENCHANTABLE_VANISHING = external("minecraft", "enchantable/vanishing");
    public static final TagKey<Item> FOODS_BERRIES = external("c", "foods/berries");
    public static final TagKey<Item> INGOTS_ZINC = external("c", "ingots/zinc");
    public static final TagKey<Item> INGOTS_FLUXIUM = external("c", "ingots/fluxium");
    public static final TagKey<Item> INGOTS_ENDER_METAL = external("c", "ingots/ender_metal");
    public static final TagKey<Item> NUGGETS_FLUXIUM = external("c", "nuggets/fluxium");
    public static final TagKey<Item> GEMS_CITRINE = external("c", "gems/citrine");
    public static final TagKey<Item> GEMS_FLUORITE = external("c", "gems/fluorite");
    public static final TagKey<Item> GEMS_MANA_CHARGED_FLUORITE = external("c", "gems/mana_charged_fluorite");
    public static final TagKey<Item> GEMS_REDSTONE_CRYSTAL = external("c", "gems/redstone_crystal");
    public static final TagKey<Item> GEMS_SULFUR = external("c", "gems/sulfur");
    public static final TagKey<Item> GEMS_TOURMALINE = external("c", "gems/tourmaline");
    public static final TagKey<Item> RAW_MATERIALS_ZINC = external("c", "raw_materials/zinc");
    public static final TagKey<Item> STORAGE_BLOCKS_RAW_ZINC = external("c", "storage_blocks/raw_zinc");
    public static final TagKey<Item> ORES_FLUORITE = external("c", "ores/fluorite");
    public static final TagKey<Item> ORES_TOURMALINE = external("c", "ores/tourmaline");
    public static final TagKey<Item> ORES_ZINC = external("c", "ores/zinc");
    public static final TagKey<Item> CURIOS_BELT = external("curios", "belt");
    public static final TagKey<Item> CURIOS_HEAD = external("curios", "head");
    public static final TagKey<Item> CURIOS_RING = external("curios", "ring");
    public static final TagKey<Item> AGGREGATED_STRAND = create(Magitech.id("aggregated_strand"));
    public static final TagKey<Item> TOOL_PART = create(Magitech.id("tool_part"));
    public static final TagKey<Item> ASPECT_CRYSTAL_BASE = create(Magitech.id("aspect_crystal_base"));

    private static @NotNull TagKey<Item> create(@NotNull ResourceLocation id) {
        return ItemTags.create(id);
    }

    private static @NotNull TagKey<Item> external(@NotNull String namespace, @NotNull String path) {
        return create(ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}
