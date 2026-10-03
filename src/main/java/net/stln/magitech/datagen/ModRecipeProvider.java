package net.stln.magitech.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.stln.magitech.Magitech;
import net.stln.magitech.content.block.BlockInit;
import net.stln.magitech.content.field_effect.effect.FieldEffectInit;
import net.stln.magitech.content.fluid.FluidInit;
import net.stln.magitech.content.item.ItemInit;
import net.stln.magitech.content.item.ItemTagKeys;
import net.stln.magitech.content.item.component.ComponentInit;
import net.stln.magitech.content.item.component.MaterialComponent;
import net.stln.magitech.content.recipe.FieldEffectRecipe;
import net.stln.magitech.content.recipe.InfusionRecipe;
import net.stln.magitech.content.recipe.PartCuttingRecipe;
import net.stln.magitech.content.recipe.ToolMaterialRecipe;
import net.stln.magitech.content.recipe.builder.IngredientCreator;
import net.stln.magitech.content.recipe.builder.MagitechRecipeBuilders;
import net.stln.magitech.core.api.field_effect.FieldEffectTypeLike;
import net.stln.magitech.datagen.recipe.BlockSetRecipeGenerator;
import net.stln.magitech.datagen.recipe.StoneRecipeGenerator;
import net.stln.magitech.datagen.recipe.VanillaSimpleRecipeGenerator;
import net.stln.magitech.datagen.recipe.WoodRecipeGenerator;
import net.stln.magitech.feature.magic.spell.SpellInit;
import net.stln.magitech.feature.tool.material.MaterialInit;
import net.stln.magitech.feature.tool.material.ToolMaterialLike;
import net.stln.magitech.registry.DeferredToolMaterial;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    private static final IngredientCreator creator = IngredientCreator.INSTANCE;

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        StoneRecipeGenerator.buildStoneRecipesWithPolishedAndBrick(output,
                BlockInit.ALCHECRYSITE_ITEM, BlockInit.ALCHECRYSITE_SLAB_ITEM, BlockInit.ALCHECRYSITE_STAIRS_ITEM, BlockInit.ALCHECRYSITE_WALL_ITEM,
                BlockInit.POLISHED_ALCHECRYSITE_ITEM, BlockInit.POLISHED_ALCHECRYSITE_SLAB_ITEM, BlockInit.POLISHED_ALCHECRYSITE_STAIRS_ITEM, BlockInit.POLISHED_ALCHECRYSITE_WALL_ITEM,
                BlockInit.ALCHECRYSITE_BRICKS_ITEM, BlockInit.ALCHECRYSITE_BRICK_SLAB_ITEM, BlockInit.ALCHECRYSITE_BRICK_STAIRS_ITEM, BlockInit.ALCHECRYSITE_BRICK_WALL_ITEM);
        BlockSetRecipeGenerator.stonecutting(output, BlockInit.ALCHECRYSITE_ITEM, BlockInit.ALCHECRYSITE_TILES_ITEM, 1, "_from_alchecrysite_stonecutting");
        BlockSetRecipeGenerator.stonecutting(output, BlockInit.POLISHED_ALCHECRYSITE_ITEM, BlockInit.ALCHECRYSITE_TILES_ITEM, 1, "_from_polished_alchecrysite_stonecutting");
        StoneRecipeGenerator.buildStoneRecipesWithPolishedAndBrick(output,
                BlockInit.VESPERITE_ITEM, BlockInit.VESPERITE_SLAB_ITEM, BlockInit.VESPERITE_STAIRS_ITEM, BlockInit.VESPERITE_WALL_ITEM,
                BlockInit.POLISHED_VESPERITE_ITEM, BlockInit.POLISHED_VESPERITE_SLAB_ITEM, BlockInit.POLISHED_VESPERITE_STAIRS_ITEM, BlockInit.POLISHED_VESPERITE_WALL_ITEM,
                BlockInit.VESPERITE_BRICKS_ITEM, BlockInit.VESPERITE_BRICK_SLAB_ITEM, BlockInit.VESPERITE_BRICK_STAIRS_ITEM, BlockInit.VESPERITE_BRICK_WALL_ITEM);
        BlockSetRecipeGenerator.stonecutting(output, BlockInit.VESPERITE_ITEM, BlockInit.CUT_VESPERITE_ITEM, 1, "_from_vesperite_stonecutting");
        StoneRecipeGenerator.buildStoneRecipes(output, BlockInit.CUT_VESPERITE_ITEM, BlockInit.CUT_VESPERITE_SLAB_ITEM, BlockInit.CUT_VESPERITE_STAIRS_ITEM, BlockInit.CUT_VESPERITE_WALL_ITEM);
        StoneRecipeGenerator.buildStoneRecipesFromResourceBlock(output, BlockInit.FLUORITE_BLOCK_ITEM, BlockInit.FLUORITE_BRICKS_ITEM, BlockInit.FLUORITE_BRICK_SLAB_ITEM, BlockInit.FLUORITE_BRICK_STAIRS_ITEM, BlockInit.FLUORITE_BRICK_WALL_ITEM);
        StoneRecipeGenerator.buildStoneRecipesFromResourceBlock(output, BlockInit.TOURMALINE_BLOCK_ITEM, BlockInit.TOURMALINE_BRICKS_ITEM, BlockInit.TOURMALINE_BRICK_SLAB_ITEM, BlockInit.TOURMALINE_BRICK_STAIRS_ITEM, BlockInit.TOURMALINE_BRICK_WALL_ITEM);
        WoodRecipeGenerator.buildWoodRecipes(output, ItemTagKeys.CELIFERN_LOGS, BlockInit.CELIFERN_LOG_ITEM, BlockInit.CELIFERN_WOOD_ITEM, BlockInit.STRIPPED_CELIFERN_LOG_ITEM, BlockInit.STRIPPED_CELIFERN_WOOD_ITEM, BlockInit.CELIFERN_PLANKS_ITEM, BlockInit.CELIFERN_SLAB_ITEM, BlockInit.CELIFERN_STAIRS_ITEM, BlockInit.CELIFERN_FENCE_ITEM, BlockInit.CELIFERN_FENCE_GATE_ITEM, BlockInit.CELIFERN_DOOR_ITEM, BlockInit.CELIFERN_TRAPDOOR_ITEM, BlockInit.CELIFERN_PRESSURE_PLATE_ITEM, BlockInit.CELIFERN_BUTTON_ITEM, BlockInit.CELIFERN_SIGN_ITEM, BlockInit.CELIFERN_HANGING_SIGN_ITEM, ItemInit.CELIFERN_BOAT, ItemInit.CELIFERN_CHEST_BOAT);
        WoodRecipeGenerator.buildWoodRecipes(output, ItemTagKeys.CHARCOAL_BIRCH_LOGS, BlockInit.CHARCOAL_BIRCH_LOG_ITEM, BlockInit.CHARCOAL_BIRCH_WOOD_ITEM, BlockInit.STRIPPED_CHARCOAL_BIRCH_LOG_ITEM, BlockInit.STRIPPED_CHARCOAL_BIRCH_WOOD_ITEM, BlockInit.CHARCOAL_BIRCH_PLANKS_ITEM, BlockInit.CHARCOAL_BIRCH_SLAB_ITEM, BlockInit.CHARCOAL_BIRCH_STAIRS_ITEM, BlockInit.CHARCOAL_BIRCH_FENCE_ITEM, BlockInit.CHARCOAL_BIRCH_FENCE_GATE_ITEM, BlockInit.CHARCOAL_BIRCH_DOOR_ITEM, BlockInit.CHARCOAL_BIRCH_TRAPDOOR_ITEM, BlockInit.CHARCOAL_BIRCH_PRESSURE_PLATE_ITEM, BlockInit.CHARCOAL_BIRCH_BUTTON_ITEM, BlockInit.CHARCOAL_BIRCH_SIGN_ITEM, BlockInit.CHARCOAL_BIRCH_HANGING_SIGN_ITEM, ItemInit.CHARCOAL_BIRCH_BOAT, ItemInit.CHARCOAL_BIRCH_CHEST_BOAT);
        WoodRecipeGenerator.buildWoodRecipes(output, ItemTagKeys.MYSTWOOD_LOGS, BlockInit.MYSTWOOD_LOG_ITEM, BlockInit.MYSTWOOD_WOOD_ITEM, BlockInit.STRIPPED_MYSTWOOD_LOG_ITEM, BlockInit.STRIPPED_MYSTWOOD_WOOD_ITEM, BlockInit.MYSTWOOD_PLANKS_ITEM, BlockInit.MYSTWOOD_SLAB_ITEM, BlockInit.MYSTWOOD_STAIRS_ITEM, BlockInit.MYSTWOOD_FENCE_ITEM, BlockInit.MYSTWOOD_FENCE_GATE_ITEM, BlockInit.MYSTWOOD_DOOR_ITEM, BlockInit.MYSTWOOD_TRAPDOOR_ITEM, BlockInit.MYSTWOOD_PRESSURE_PLATE_ITEM, BlockInit.MYSTWOOD_BUTTON_ITEM, BlockInit.MYSTWOOD_SIGN_ITEM, BlockInit.MYSTWOOD_HANGING_SIGN_ITEM, ItemInit.MYSTWOOD_BOAT, ItemInit.MYSTWOOD_CHEST_BOAT);

        buildVanilla(output);
        buildCustom(output);
    }

    private static void buildVanilla(RecipeOutput output) {
        shaped(output, ItemInit.ALCHEMICAL_FLASK, 4, keys('#', i(BlockInit.MANA_INSULATING_GLASS_ITEM)), "# #", " # ");
        shaped(output, ItemInit.APPLIED_ARCANE_CIRCUITRY, 1, keys('Z', component(ItemInit.REINFORCED_ROD, MaterialInit.ZINC, false), 'B', i(Items.BOOK), 'N', i(ItemInit.AGGREGATED_NOCTIS), 'L', i(ItemInit.AGGREGATED_LUMINIS), 'F', i(ItemInit.AGGREGATED_FLUXIA)), "ZN ", "LBZ", " FZ");
        shaped(output, BlockInit.ASSEMBLY_WORKBENCH_ITEM, 1, keys('#', tag(Tags.Items.STRIPPED_LOGS), 'S', tag(Tags.Items.STONES), 'P', ItemTags.PLANKS), "SSS", "#P#", "SPS");
        shaped(output, BlockInit.ENGINEERING_WORKBENCH_ITEM, 1, keys('#', tag(Tags.Items.STRIPPED_LOGS), 'S', tag(Tags.Items.STONES), 'P', ItemTags.PLANKS), "SSS", "#P#", "#S#");
        shaped(output, BlockInit.REPAIRING_WORKBENCH_ITEM, 1, keys('#', tag(Tags.Items.STRIPPED_LOGS), 'S', tag(Tags.Items.STONES), 'P', ItemTags.PLANKS), "SSS", "#S#", "SPS");
        shaped(output, BlockInit.MYSTWOOD_ENCLOSURE_ITEM, 4, keys('#', i(BlockInit.MYSTWOOD_PLANKS_ITEM), 'V', i(BlockInit.VESPERITE_ITEM)), " # ", "#V#", " # ");
        shaped(output, BlockInit.FLUXIUM_ENCLOSURE_ITEM, 4, keys('#', i(BlockInit.MYSTWOOD_PLANKS_ITEM), 'F', i(ItemInit.FLUXIUM_INGOT)), " # ", "#F#", " # ");
        shaped(output, BlockInit.COMPRESSOR_ITEM, 1, keys('E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'I', i(ItemInit.FLUXIUM_NUGGET), 'C', i(Items.IRON_BLOCK), 'A', i(BlockInit.ALCHECRYSITE_ITEM)), " A ", "ICI", " E ");
        shaped(output, BlockInit.CRUSHER_ITEM, 1, keys('A', i(BlockInit.ALCHECRYSITE_ITEM), 'C', tag(Tags.Items.INGOTS_IRON), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM)), " A ", "CEC", " A ");
        shaped(output, BlockInit.HEAT_BURNER_ITEM, 1, keys('I', i(ItemInit.FLUXIUM_NUGGET), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'G', i(ItemInit.EMBER_CRYSTAL), 'A', i(BlockInit.ALCHECRYSITE_ITEM)), "GAG", "IEI", "IAI");
        shaped(output, BlockInit.CHILLER_ITEM, 1, keys('I', i(ItemInit.FLUXIUM_NUGGET), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'G', i(ItemInit.GLACE_CRYSTAL), 'A', i(BlockInit.ALCHECRYSITE_ITEM)), "GAG", "IEI", "IAI");
        shaped(output, BlockInit.ENVIROMETER_ITEM, 1, keys('C', tag(Tags.Items.INGOTS_COPPER), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'R', i(Items.REDSTONE), 'A', i(BlockInit.ALCHECRYSITE_ITEM), 'F', i(ItemInit.HIGH_PURITY_FLUORITE)), " F ", "CAC", "RER");
        shaped(output, BlockInit.THERMAL_MANA_FURNACE_ITEM, 1, keys('I', i(ItemInit.FLUXIUM_INGOT), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'A', i(BlockInit.ALCHECRYSITE_ITEM), 'F', i(Blocks.BLAST_FURNACE), 'L', i(ItemInit.HIGH_PURITY_FLUORITE)), "ILI", "AFA", "IEI");
        shaped(output, BlockInit.DETANGLER_ITEM, 1, keys('I', i(ItemInit.FLUXIUM_INGOT), 'U', i(ItemInit.FLUXIUM_NUGGET), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'R', i(Items.REDSTONE)), " U ", "UIU", "RER");
        shaped(output, BlockInit.ENTANGLER_ITEM, 1, keys('U', i(ItemInit.FLUXIUM_NUGGET), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'R', i(Items.REDSTONE)), " U ", "U U", "RER");
        shaped(output, BlockInit.INFUSION_ALTAR_ITEM, 1, keys('V', i(BlockInit.VESPERITE_ITEM), 'M', i(BlockInit.MYSTWOOD_PLANKS_ITEM), 'F', tag(ItemTagKeys.GEMS_FLUORITE)), "VFV", " M ", "MVM");
        shaped(output, BlockInit.ITEM_COLLECTOR_ITEM, 1, keys('N', i(Items.GOLD_NUGGET), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'C', tag(Tags.Items.INGOTS_COPPER), 'A', i(BlockInit.ALCHECRYSITE_ITEM), 'F', i(ItemInit.HIGH_PURITY_FLUORITE)), "NFN", "CEC", "NAN");
        shaped(output, BlockInit.MANA_COLLECTOR_ITEM, 1, keys('N', i(Items.GOLD_NUGGET), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'C', tag(Tags.Items.INGOTS_COPPER), 'A', i(BlockInit.ALCHECRYSITE_ITEM), 'F', i(ItemInit.HIGH_PURITY_FLUORITE), 'R', i(Items.REDSTONE)), "NCN", "FEF", "RAR");
        shaped(output, BlockInit.MANA_JUNCTION_ITEM, 1, keys('N', i(Items.GOLD_NUGGET), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'A', i(BlockInit.ALCHECRYSITE_ITEM), 'F', i(ItemInit.HIGH_PURITY_FLUORITE)), "NAN", "FEF", "NAN");
        shaped(output, BlockInit.MANA_NODE_ITEM, 1, keys('N', i(Items.GOLD_NUGGET), 'V', i(BlockInit.VESPERITE_ITEM), 'M', i(BlockInit.MYSTWOOD_PLANKS_ITEM), 'F', tag(ItemTagKeys.GEMS_FLUORITE)), " V ", "MFM", "VNV");
        shaped(output, BlockInit.MANA_PUMP_ITEM, 1, keys('U', i(ItemInit.FLUXIUM_NUGGET), 'I', i(ItemInit.FLUXIUM_INGOT), 'A', i(BlockInit.ALCHECRYSITE_ITEM), 'F', i(ItemInit.HIGH_PURITY_FLUORITE), 'R', i(Items.REDSTONE), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM)), "UFU", "RER", "IAI");
        shaped(output, BlockInit.MANA_RECEIVER_ITEM, 1, keys('N', i(Items.GOLD_NUGGET), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'A', i(BlockInit.ALCHECRYSITE_ITEM), 'F', i(ItemInit.HIGH_PURITY_FLUORITE), 'R', i(Items.REDSTONE)), " F ", "NEN", "RAR");
        shaped(output, BlockInit.MANA_RELAY_ITEM, 1, keys('N', i(BlockInit.MANA_NODE_ITEM), 'V', i(BlockInit.VESPERITE_ITEM), 'M', i(BlockInit.MYSTWOOD_PLANKS_ITEM)), " N ", "MVM");
        shaped(output, BlockInit.MANA_STRANDER_ITEM, 1, keys('N', i(Items.GOLD_NUGGET), 'E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'A', i(BlockInit.ALCHECRYSITE_ITEM), '#', i(BlockInit.ENHANCED_MANA_NODE_ITEM), 'R', i(Items.REDSTONE)), " # ", "NEN", "RAR");
        shaped(output, BlockInit.MANA_VESSEL_ITEM, 2, keys('N', i(Items.GOLD_NUGGET), 'E', i(BlockInit.MYSTWOOD_ENCLOSURE_ITEM), 'M', i(BlockInit.MYSTWOOD_PLANKS_ITEM), 'F', i(BlockInit.FLUORITE_BLOCK_ITEM)), "MEM", "NFN", "MEM");
        shaped(output, BlockInit.PEDESTAL_PYLON_ITEM, 1, keys('V', i(BlockInit.VESPERITE_ITEM), 'M', i(BlockInit.MYSTWOOD_PLANKS_ITEM)), "VMV", " M ", "MVM");
        shaped(output, ItemInit.MATERIALS_AND_TOOLCRAFT_DESIGN, 1, keys('B', i(Items.BOOK), 'S', component(ItemInit.REINFORCED_ROD, MaterialInit.IRON, false), 'P', component(ItemInit.PLATE, MaterialInit.STONE, false), 'L', tag(Tags.Items.STRIPPED_LOGS)), "PLP", "SBS", "PLP");
        shaped(output, ItemInit.SULFURIC_ACID_BATTERY, 1, keys('C', component(ItemInit.REINFORCED_ROD, MaterialInit.COPPER, true), 'Z', component(ItemInit.REINFORCED_ROD, MaterialInit.ZINC, true), 'S', i(ItemInit.SULFURIC_ACID_FLASK)), "C ", "SZ");
        shaped(output, ItemInit.TOOL_BELT, 1, keys('P', component(ItemInit.PLATE, MaterialInit.IRON, true), 'L', i(Items.LEATHER)), " LP", "L L", "PL ");
        shaped(output, BlockInit.TOOL_HANGER_ITEM, 2, keys('#', tag(Tags.Items.STRIPPED_LOGS), 'S', tag(Tags.Items.STONES), 'P', ItemTags.PLANKS), "SPS", "## ", "S  ");
        shaped(output, BlockInit.TRAP_HATCH_ITEM, 2, keys('E', i(BlockInit.FLUXIUM_ENCLOSURE_ITEM), 'I', i(ItemInit.FLUXIUM_INGOT)), "IEI");
        shaped(output, BlockInit.ZARDIUS_CRUCIBLE_ITEM, 1, RecipeCategory.REDSTONE, "wooden_door", keys('#', tag(ItemTagKeys.GEMS_TOURMALINE), 'I', i(Items.IRON_INGOT)), "I I", "I#I", "III");
        shaped(output, BlockInit.FLUORITE_BLOCK_ITEM, 1, keys('#', tag(ItemTagKeys.GEMS_FLUORITE)), "###", "###", "###");
        shaped(output, ItemInit.FLUORITE, "_from_fluorite_block", 9, keys('#', i(BlockInit.FLUORITE_BLOCK_ITEM)), "#");
        shaped(output, BlockInit.TOURMALINE_BLOCK_ITEM, 1, keys('#', tag(ItemTagKeys.GEMS_TOURMALINE)), "###", "###", "###");
        shaped(output, ItemInit.TOURMALINE, "_from_tourmaline_block", 9, keys('#', i(BlockInit.TOURMALINE_BLOCK_ITEM)), "#");
        shaped(output, ItemInit.FLUXIUM_INGOT, "_from_nugget", 1, keys('#', tag(ItemTagKeys.NUGGETS_FLUXIUM)), "###", "###", "###");
        shaped(output, ItemInit.FLUXIUM_NUGGET, 9, keys('#', tag(ItemTagKeys.INGOTS_FLUXIUM)), "#");
        shaped(output, ItemInit.FLUXIUM_RING, 1, keys('I', i(ItemInit.FLUXIUM_INGOT), 'N', i(ItemInit.FLUXIUM_NUGGET)), " IN", "I I", "NI ");
        shaped(output, BlockInit.RAW_ZINC_BLOCK_ITEM, 1, keys('#', tag(ItemTagKeys.RAW_MATERIALS_ZINC)), "###", "###", "###");
        shaped(output, ItemInit.RAW_ZINC, "_from_raw_zinc_block", 9, keys('#', i(BlockInit.RAW_ZINC_BLOCK_ITEM)), "#");
        shaped(output, BlockInit.ZINC_BLOCK_ITEM, 1, keys('#', tag(ItemTagKeys.INGOTS_ZINC)), "###", "###", "###");
        shaped(output, ItemInit.ZINC_INGOT, "_from_zinc_block", 9, keys('#', i(BlockInit.ZINC_BLOCK_ITEM)), "#");
        shaped(output, BlockInit.FLUXIUM_BLOCK_ITEM, 1, keys('#', tag(ItemTagKeys.INGOTS_FLUXIUM)), "###", "###", "###");
        shaped(output, ItemInit.FLUXIUM_INGOT, "_from_fluxium_block", 9, keys('#', i(BlockInit.FLUXIUM_BLOCK_ITEM)), "#");
        shaped(output, BlockInit.SULFUR_BLOCK_ITEM, 1, RecipeCategory.BUILDING_BLOCKS, "", keys('#', tag(ItemTagKeys.GEMS_SULFUR)), "##", "##");
        shapeless(output, stack(ItemInit.GLISTENING_LEXICON, 1), i(Items.BOOK), tag(ItemTagKeys.GEMS_FLUORITE));
        shapeless(output, stack(ItemInit.MANA_PIE, 1), i(Items.WHEAT), i(Items.WHEAT), i(Items.SUGAR), i(ItemInit.MANA_BERRIES));
        shapeless(output, stack(Items.GUNPOWDER, 2), "_from_sulfur", tag(ItemTagKeys.GEMS_SULFUR), i(Items.CHARCOAL), i(Items.BONE_MEAL));

        // Enercrux Thread Page
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.THREAD_PAGE)
                .requires(Items.PAPER)
                .requires(Tags.Items.STRINGS)
                .requires(Tags.Items.STRINGS)
                .requires(Tags.Items.GLASS_BLOCKS)
                .requires(ItemTagKeys.GEMS_FLUORITE)
                .unlockedBy("has_fluorite", has(ItemTagKeys.GEMS_FLUORITE))
                .save(output, Magitech.id("crafting/%s".formatted(SpellInit.ENERCRUX.getId().getPath())));
    }

    private static void buildCustom(RecipeOutput output) {
        cooking(output, ItemInit.CITRINE, i(Items.AMETHYST_SHARD), 0, 200, false, "", "misc");
        cooking(output, ItemInit.FLUORITE, i(BlockInit.FLUORITE_ORE_ITEM, BlockInit.DEEPSLATE_FLUORITE_ORE_ITEM), 1, 200, false, "_from_fluorite_ore_smelting", "");
        cooking(output, ItemInit.TOURMALINE, i(BlockInit.TOURMALINE_ORE_ITEM, BlockInit.DEEPSLATE_TOURMALINE_ORE_ITEM), 1, 200, false, "_from_tourmaline_ore_smelting", "");
        cooking(output, ItemInit.ZINC_INGOT, i(ItemInit.RAW_ZINC), 0, 200, false, "_from_raw_zinc_smelting", "misc");
        cooking(output, ItemInit.FLUORITE, i(BlockInit.FLUORITE_ORE_ITEM, BlockInit.DEEPSLATE_FLUORITE_ORE_ITEM), 1, 100, true, "_from_fluorite_ore_blasting", "");
        cooking(output, ItemInit.TOURMALINE, i(BlockInit.TOURMALINE_ORE_ITEM, BlockInit.DEEPSLATE_TOURMALINE_ORE_ITEM), 1, 100, true, "_from_tourmaline_ore_blasting", "");
        cooking(output, ItemInit.ZINC_INGOT, i(ItemInit.RAW_ZINC), 0, 200, true, "_from_raw_zinc_blasting", "misc");

        fieldEffect(output, s(Items.SHROOMLIGHT, 1), FieldEffectInit.FREEZING, stack(Items.OCHRE_FROGLIGHT, 1));
        fieldEffect(output, s(Items.STONE, 1), FieldEffectInit.SCORCHING, new FluidStack(Fluids.LAVA, 1000));
        fieldEffect(output, s(Items.MAGMA_BLOCK, 1), FieldEffectInit.FREEZING, stack(Items.OBSIDIAN, 1));
        fieldEffect(output, s(Items.MAGMA_BLOCK, 1), FieldEffectInit.COLD, stack(Items.BLACKSTONE, 1));
        fieldEffect(output, SizedFluidIngredient.of(Fluids.WATER, 1000), FieldEffectInit.COLD, stack(Items.ICE, 1));
        fieldEffect(output, s(Items.ICE, 1), FieldEffectInit.FREEZING, stack(Items.PACKED_ICE, 1));
        fieldEffect(output, s(Items.PACKED_ICE, 1), FieldEffectInit.FREEZING, stack(Items.BLUE_ICE, 1));
        fieldEffect(output, SizedFluidIngredient.of(Fluids.WATER, 1000), FieldEffectInit.THERMAL_SHOCK, stack(Items.SNOW_BLOCK, 1));
        fieldEffect(output, s(Items.SLIME_BALL, 1), FieldEffectInit.HEATED, stack(Items.GREEN_DYE, 1));
        fieldEffect(output, s(Items.DRIPSTONE_BLOCK, 1), FieldEffectInit.FREEZING, stack(Items.PRISMARINE, 1));
        fieldEffect(output, s(Items.POINTED_DRIPSTONE, 1), FieldEffectInit.COLD, stack(Items.PRISMARINE_SHARD, 1));
        fieldEffect(output, s(Items.BLAZE_ROD, 1), FieldEffectInit.FREEZING, stack(Items.BREEZE_ROD, 1));
        fieldEffect(output, s(Items.PRISMARINE_SHARD, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.PRISMARINE_CRYSTALS, 1));
        fieldEffect(output, s(Items.PRISMARINE_CRYSTALS, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.PRISMARINE_SHARD, 1));
        fieldEffect(output, s(Items.DIRT, 1), FieldEffectInit.COLD, stack(Items.CLAY, 1));
        fieldEffect(output, s(Items.REDSTONE, 4), FieldEffectInit.FREEZING, stack(ItemInit.REDSTONE_CRYSTAL, 1));
        fieldEffect(output, s(Items.MAGMA_CREAM, 1), FieldEffectInit.COLD, stack(Items.SLIME_BALL, 1));
        fieldEffect(output, s(Items.TUFF, 1), FieldEffectInit.FREEZING, stack(Items.DEEPSLATE, 1));
        fieldEffect(output, s(Items.BLAZE_POWDER, 1), FieldEffectInit.COLD, stack(Items.GUNPOWDER, 1));
        fieldEffect(output, s(Items.ANDESITE, 1), FieldEffectInit.FREEZING, stack(Items.CALCITE, 1));
        fieldEffect(output, s(Items.GLOWSTONE, 1), FieldEffectInit.COLD, stack(Items.AMETHYST_BLOCK, 1));
        fieldEffect(output, s(Items.MOSS_BLOCK, 1), FieldEffectInit.COLD, stack(Items.DIRT, 1));
        fieldEffect(output, s(Items.SOUL_SAND, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.SOUL_SOIL, 1));
        fieldEffect(output, s(Items.SOUL_SOIL, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.SOUL_SAND, 1));

//        ItemLike ash = externalItem("supplementaries", "ash");
//        custom(output, "compressing", Items.TUFF, new CompressingRecipe("", sized(i(ash), 4), stack(Items.TUFF, 1)));
//        fieldEffect(output, "tuff_to_ash", s(Items.TUFF, 1), FieldEffectInit.THERMAL_SHOCK, stack(ash, 4));

        fieldEffect(output, s(Items.OXIDIZED_COPPER, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.WEATHERED_COPPER, 1));
        fieldEffect(output, s(Items.WEATHERED_COPPER, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.EXPOSED_COPPER, 1));
        fieldEffect(output, s(Items.EXPOSED_COPPER, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.COPPER_BLOCK, 1));
        fieldEffect(output, s(Items.OXIDIZED_CUT_COPPER, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.WEATHERED_CUT_COPPER, 1));
        fieldEffect(output, s(Items.WEATHERED_CUT_COPPER, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.EXPOSED_CUT_COPPER, 1));
        fieldEffect(output, s(Items.EXPOSED_CUT_COPPER, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.CUT_COPPER, 1));
        fieldEffect(output, s(Items.OXIDIZED_CUT_COPPER_STAIRS, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.WEATHERED_CUT_COPPER_STAIRS, 1));
        fieldEffect(output, s(Items.WEATHERED_CUT_COPPER_STAIRS, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.EXPOSED_CUT_COPPER_STAIRS, 1));
        fieldEffect(output, s(Items.EXPOSED_CUT_COPPER_STAIRS, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.CUT_COPPER_STAIRS, 1));
        fieldEffect(output, s(Items.OXIDIZED_CUT_COPPER_SLAB, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.WEATHERED_CUT_COPPER_SLAB, 1));
        fieldEffect(output, s(Items.WEATHERED_CUT_COPPER_SLAB, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.EXPOSED_CUT_COPPER_SLAB, 1));
        fieldEffect(output, s(Items.EXPOSED_CUT_COPPER_SLAB, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.CUT_COPPER_SLAB, 1));
        fieldEffect(output, s(Items.OXIDIZED_CHISELED_COPPER, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.WEATHERED_CHISELED_COPPER, 1));
        fieldEffect(output, s(Items.WEATHERED_CHISELED_COPPER, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.EXPOSED_CHISELED_COPPER, 1));
        fieldEffect(output, s(Items.EXPOSED_CHISELED_COPPER, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.CHISELED_COPPER, 1));
        fieldEffect(output, s(Items.OXIDIZED_COPPER_GRATE, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.WEATHERED_COPPER_GRATE, 1));
        fieldEffect(output, s(Items.WEATHERED_COPPER_GRATE, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.EXPOSED_COPPER_GRATE, 1));
        fieldEffect(output, s(Items.EXPOSED_COPPER_GRATE, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.COPPER_GRATE, 1));
        fieldEffect(output, s(Items.OXIDIZED_COPPER_BULB, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.WEATHERED_COPPER_BULB, 1));
        fieldEffect(output, s(Items.WEATHERED_COPPER_BULB, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.EXPOSED_COPPER_BULB, 1));
        fieldEffect(output, s(Items.EXPOSED_COPPER_BULB, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.COPPER_BULB, 1));
        fieldEffect(output, s(Items.OXIDIZED_COPPER_DOOR, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.WEATHERED_COPPER_DOOR, 1));
        fieldEffect(output, s(Items.WEATHERED_COPPER_DOOR, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.EXPOSED_COPPER_DOOR, 1));
        fieldEffect(output, s(Items.EXPOSED_COPPER_DOOR, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.COPPER_DOOR, 1));
        fieldEffect(output, s(Items.OXIDIZED_COPPER_TRAPDOOR, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.WEATHERED_COPPER_TRAPDOOR, 1));
        fieldEffect(output, s(Items.WEATHERED_COPPER_TRAPDOOR, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.EXPOSED_COPPER_TRAPDOOR, 1));
        fieldEffect(output, s(Items.EXPOSED_COPPER_TRAPDOOR, 1), FieldEffectInit.THERMAL_SHOCK, stack(Items.COPPER_TRAPDOOR, 1));
        // Compressing
        MagitechRecipeBuilders.compressing(ItemInit.RESTRAINT_QUARTZ)
                .ingredient(creator.sizedItem(ItemInit.RESTRAINT_QUARTZ_DUST, 4))
                .save(output);
        // Crushing
        MagitechRecipeBuilders.crushing(Items.AMETHYST_SHARD, 4)
                .ingredient(creator.sizedItem(Items.AMETHYST_BLOCK, 1))
                .save(output);
        MagitechRecipeBuilders.crushing(Items.REDSTONE, 3)
                .ingredient(creator.sizedItem(ItemInit.REDSTONE_CRYSTAL))
                .save(output);
        MagitechRecipeBuilders.crushing(ItemInit.RESTRAINT_QUARTZ_DUST)
                .ingredient(creator.sizedItem(ItemInit.QUARTZ_PLANT, 4))
                .save(output);
        MagitechRecipeBuilders.crushing(ItemInit.SULFUR, 3)
                .ingredient(creator.sizedItem(BlockInit.SULFUR_BLOCK_ITEM))
                .save(output);
        // Infusion
        infusion(output, ItemInit.SPECTACLES_OF_INSPECTION, "_infuser", i(BlockInit.MANA_INSULATING_GLASS), 2, 500000, 1, s(ItemInit.HIGH_PURITY_FLUORITE, 4), s(Items.LEATHER, 4), s(ItemInit.FLUXIUM_INGOT, 4), s(ItemInit.PHANTOM_CRYSTAL, 1));
        infusion(output, ItemInit.AETHER_LIFTER, i(Items.IRON_BOOTS), 1, 500000, 1, s(ItemInit.HIGH_PURITY_FLUORITE, 16), s(ItemInit.HOLLOW_CRYSTAL, 16), s(ItemInit.PHANTOM_CRYSTAL, 16), s(ItemInit.AEGIS_WEAVE, 8), s(ItemInit.FLUXIUM_INGOT, 16));
        infusion(output, ItemInit.FLAMGLIDE_STRIDER, i(Items.LEATHER_BOOTS), 1, 500000, 1, s(ItemInit.HIGH_PURITY_FLUORITE, 16), s(ItemInit.EMBER_CRYSTAL, 16), s(ItemInit.FLOW_CRYSTAL, 16), s(ItemInit.AEGIS_WEAVE, 8), s(ItemInit.FLUXIUM_INGOT, 16));
        infusion(output, ItemInit.FLUXIUM_INGOT, i(Items.IRON_INGOT), 8, 40000, 16, s(tag(ItemTagKeys.AGGREGATED_STRAND), 1), s(tag(ItemTagKeys.INGOTS_ZINC), 8));
        infusion(output, ItemInit.HIGH_PURITY_FLUORITE, "_infuser", i(ItemInit.MANA_CHARGED_FLUORITE), 2, 10000, 1);
        infusion(output, ItemInit.MANA_CHARGED_FLUORITE, "_infuser", tag(ItemTagKeys.GEMS_FLUORITE), 1, 4000, 1);
        infusion(output, BlockInit.VESPERITE_ITEM, "_infuser", tag(Tags.Items.STONES), 1, 2000, 1);
        infusion(output, BlockInit.MYSTWOOD_LOG_ITEM, "_infuser", tag(ItemTags.LOGS), 1, 2000, 1);
        infusion(output, BlockInit.ALCHECRYSITE_ITEM, "_infuser", i(BlockInit.VESPERITE_ITEM), 8, 10000, 8, s(ItemInit.HIGH_PURITY_FLUORITE, 2), s(tag(ItemTagKeys.GEMS_CITRINE), 1), s(tag(ItemTagKeys.GEMS_TOURMALINE), 2), s(Items.GOLD_NUGGET, 6));

        infusion(output, "infusion/accesories", ItemInit.ARDOR_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.EMBER_CRYSTAL, 4));
        infusion(output, "infusion/accesories", ItemInit.CELERITAS_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.PHANTOM_CRYSTAL, 4));
        infusion(output, "infusion/accesories", ItemInit.CHARGEBIND_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.SURGE_CRYSTAL, 4));
        infusion(output, "infusion/accesories", ItemInit.CRACK_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.TREMOR_CRYSTAL, 4));
        infusion(output, "infusion/accesories", ItemInit.DAWN_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.AGGREGATED_LUMINIS, 4));
        infusion(output, "infusion/accesories", ItemInit.DISTORTION_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.HOLLOW_CRYSTAL, 4));
        infusion(output, "infusion/accesories", ItemInit.FLUXBOUND_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.AGGREGATED_FLUXIA, 4));
        infusion(output, "infusion/accesories", ItemInit.MANA_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.HIGH_PURITY_FLUORITE, 4));
        infusion(output, "infusion/accesories", ItemInit.PROTECTION_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.MAGIC_CRYSTAL, 4));
        infusion(output, "infusion/accesories", ItemInit.QUENCH_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.GLACE_CRYSTAL, 4));
        infusion(output, "infusion/accesories", ItemInit.UMBRAL_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.AGGREGATED_NOCTIS, 4));
        infusion(output, "infusion/accesories", ItemInit.UPDRAFT_RING, i(ItemInit.FLUXIUM_RING), 1, 40000, 1, s(ItemInit.FLOW_CRYSTAL, 4));

        Ingredient aspectCrystalBase = tag(ItemTagKeys.ASPECT_CRYSTAL_BASE);
        infusion(output, "infusion/aspect_crystal", ItemInit.EMBER_CRYSTAL, "_infuser_from_blaze", aspectCrystalBase, 1, 40000, 1, s(Items.BLAZE_ROD, 1));
        infusion(output, "infusion/aspect_crystal", ItemInit.EMBER_CRYSTAL, "_infuser_from_citrine", aspectCrystalBase, 1, 40000, 1, s(tag(ItemTagKeys.GEMS_CITRINE), 4));
        infusion(output, "infusion/aspect_crystal", ItemInit.EMBER_CRYSTAL, "_infuser_from_coal", aspectCrystalBase, 1, 40000, 1, s(ItemTags.COALS, 8));
        infusion(output, "infusion/aspect_crystal", ItemInit.EMBER_CRYSTAL, "_infuser_from_gunpowder", aspectCrystalBase, 1, 40000, 1, s(Items.GUNPOWDER, 2));
        infusion(output, "infusion/aspect_crystal", ItemInit.EMBER_CRYSTAL, "_infuser_from_lava", aspectCrystalBase, 2, 80000, 2, s(Items.LAVA_BUCKET, 1));
        infusion(output, "infusion/aspect_crystal", ItemInit.FLOW_CRYSTAL, "_infuser_from_flower", aspectCrystalBase, 1, 40000, 1, s(ItemTags.FLOWERS, 4));
        infusion(output, "infusion/aspect_crystal", ItemInit.FLOW_CRYSTAL, "_infuser_from_moss", aspectCrystalBase, 1, 40000, 1, s(Items.MOSS_BLOCK, 8));
        infusion(output, "infusion/aspect_crystal", ItemInit.FLOW_CRYSTAL, "_infuser_from_sapling", aspectCrystalBase, 1, 40000, 1, s(ItemTags.SAPLINGS, 8));
        infusion(output, "infusion/aspect_crystal", ItemInit.FLOW_CRYSTAL, "_infuser_from_water", aspectCrystalBase, 1, 40000, 1, s(Items.WATER_BUCKET, 1));
        infusion(output, "infusion/aspect_crystal", ItemInit.GLACE_CRYSTAL, "_infuser_from_calcite", aspectCrystalBase, 1, 40000, 1, s(Items.CALCITE, 2));
        infusion(output, "infusion/aspect_crystal", ItemInit.GLACE_CRYSTAL, "_infuser_from_ice", aspectCrystalBase, 1, 40000, 1, s(Items.ICE, 2));
        infusion(output, "infusion/aspect_crystal", ItemInit.GLACE_CRYSTAL, "_infuser_from_quartz", aspectCrystalBase, 1, 40000, 1, s(Items.QUARTZ, 4));
        infusion(output, "infusion/aspect_crystal", ItemInit.GLACE_CRYSTAL, "_infuser_from_snow", aspectCrystalBase, 1, 40000, 1, s(Items.SNOW_BLOCK, 4));
        infusion(output, "infusion/aspect_crystal", ItemInit.HOLLOW_CRYSTAL, "_infuser_from_chorus", aspectCrystalBase, 1, 40000, 1, s(Items.CHORUS_FRUIT, 4));
        infusion(output, "infusion/aspect_crystal", ItemInit.HOLLOW_CRYSTAL, "_infuser_from_end_stone", aspectCrystalBase, 1, 40000, 1, s(Items.END_STONE, 8));
        infusion(output, "infusion/aspect_crystal", ItemInit.HOLLOW_CRYSTAL, "_infuser_from_ender_pearl", aspectCrystalBase, 1, 40000, 1, s(Items.ENDER_PEARL, 2));
        infusion(output, "infusion/aspect_crystal", ItemInit.MAGIC_CRYSTAL, "_infuser_from_amethyst", aspectCrystalBase, 1, 40000, 1, s(Items.AMETHYST_SHARD, 4));
        infusion(output, "infusion/aspect_crystal", ItemInit.MAGIC_CRYSTAL, "_infuser_from_gold", aspectCrystalBase, 1, 40000, 1, s(Items.GOLD_INGOT, 1));
        infusion(output, "infusion/aspect_crystal", ItemInit.PHANTOM_CRYSTAL, "_infuser_from_glow_berries", aspectCrystalBase, 1, 40000, 1, s(Items.GLOW_BERRIES, 8));
        infusion(output, "infusion/aspect_crystal", ItemInit.PHANTOM_CRYSTAL, "_infuser_from_glowstone", aspectCrystalBase, 1, 40000, 1, s(Items.GLOWSTONE_DUST, 4));
        infusion(output, "infusion/aspect_crystal", ItemInit.PHANTOM_CRYSTAL, "_infuser_from_phantom", aspectCrystalBase, 1, 40000, 1, s(Items.PHANTOM_MEMBRANE, 2));
        infusion(output, "infusion/aspect_crystal", ItemInit.SURGE_CRYSTAL, "_infuser_from_copper", aspectCrystalBase, 1, 40000, 1, s(Items.COPPER_INGOT, 4));
        infusion(output, "infusion/aspect_crystal", ItemInit.SURGE_CRYSTAL, "_infuser_from_redstone", aspectCrystalBase, 1, 40000, 1, s(Items.REDSTONE, 4));
        infusion(output, "infusion/aspect_crystal", ItemInit.TREMOR_CRYSTAL, "_infuser_from_deepslate", aspectCrystalBase, 1, 40000, 1, s(Items.COBBLED_DEEPSLATE, 8));
        infusion(output, "infusion/aspect_crystal", ItemInit.TREMOR_CRYSTAL, "_infuser_from_lapis", aspectCrystalBase, 1, 40000, 1, s(Items.LAPIS_LAZULI, 4));

        infusion(output, "infusion/machines", BlockInit.ENHANCED_MANA_NODE_ITEM, i(BlockInit.MANA_NODE_ITEM), 2, 100000, 2, s(ItemInit.HIGH_PURITY_FLUORITE, 2), s(BlockInit.ALCHECRYSITE_ITEM, 1), s(ItemInit.FLUXIUM_INGOT, 2));
        infusion(output, "infusion/machines", BlockInit.ENHANCED_MANA_RELAY_ITEM, i(BlockInit.MANA_RELAY_ITEM), 2, 100000, 2, s(ItemInit.HIGH_PURITY_FLUORITE, 2), s(BlockInit.ALCHECRYSITE_ITEM, 1), s(ItemInit.FLUXIUM_INGOT, 2));
        infusion(output, "infusion/machines", BlockInit.ENHANCED_MANA_VESSEL_ITEM, i(BlockInit.MANA_VESSEL_ITEM), 1, 100000, 1, s(ItemInit.HIGH_PURITY_FLUORITE, 4), s(BlockInit.ALCHECRYSITE_ITEM, 4), s(ItemInit.FLUXIUM_INGOT, 2));
        infusion(output, "infusion/machines", BlockInit.INFUSER_ITEM, i(BlockInit.INFUSION_ALTAR_ITEM), 1, 40000, 1, s(ItemInit.HIGH_PURITY_FLUORITE, 2), s(BlockInit.ALCHECRYSITE_ITEM, 1), s(ItemInit.FLUXIUM_INGOT, 2));

        custom(output, "part_cutting", ItemInit.CATALYST, "_part_cutting", new PartCuttingRecipe("", 1, stack(ItemInit.CATALYST, 1)));
        custom(output, "part_cutting", ItemInit.CONDUCTOR, "_part_cutting", new PartCuttingRecipe("", 2, stack(ItemInit.CONDUCTOR, 1)));
        custom(output, "part_cutting", ItemInit.HANDGUARD, "_part_cutting", new PartCuttingRecipe("", 1, stack(ItemInit.HANDGUARD, 1)));
        custom(output, "part_cutting", ItemInit.HEAVY_BLADE, "_part_cutting", new PartCuttingRecipe("", 1, stack(ItemInit.HEAVY_BLADE, 1)));
        custom(output, "part_cutting", ItemInit.HEAVY_HANDLE, "_part_cutting", new PartCuttingRecipe("", 2, stack(ItemInit.HEAVY_HANDLE, 1)));
        custom(output, "part_cutting", ItemInit.LIGHT_BLADE, "_part_cutting", new PartCuttingRecipe("", 2, stack(ItemInit.LIGHT_BLADE, 1)));
        custom(output, "part_cutting", ItemInit.LIGHT_HANDLE, "_part_cutting", new PartCuttingRecipe("", 1, stack(ItemInit.LIGHT_HANDLE, 1)));
        custom(output, "part_cutting", ItemInit.PLATE, "_part_cutting", new PartCuttingRecipe("", 2, stack(ItemInit.PLATE, 1)));
        custom(output, "part_cutting", ItemInit.REINFORCED_ROD, "_part_cutting", new PartCuttingRecipe("", 2, stack(ItemInit.REINFORCED_ROD, 1)));
        custom(output, "part_cutting", ItemInit.SPIKE_HEAD, "_part_cutting", new PartCuttingRecipe("", 2, stack(ItemInit.SPIKE_HEAD, 1)));
        custom(output, "part_cutting", ItemInit.STRIKE_HEAD, "_part_cutting", new PartCuttingRecipe("", 3, stack(ItemInit.STRIKE_HEAD, 1)));
        custom(output, "part_cutting", ItemInit.TOOL_BINDING, "_part_cutting", new PartCuttingRecipe("", 1, stack(ItemInit.TOOL_BINDING, 1)));

        material(output, i(BlockInit.ALCHECRYSITE_ITEM), MaterialInit.ALCHECRYSITE);
        material(output, i(Items.AMETHYST_SHARD), MaterialInit.AMETHYST);
        material(output, i(Items.BASALT), MaterialInit.BASALT);
        material(output, i(Items.BONE), MaterialInit.BONE);
        material(output, i(Items.BRICK), MaterialInit.BRICK);
        material(output, i(Items.CALCITE), MaterialInit.CALCITE);
        material(output, i(ItemInit.CITRINE), MaterialInit.CITRINE);
        material(output, i(Items.COPPER_INGOT), MaterialInit.COPPER);
        material(output, i(Items.DEEPSLATE), MaterialInit.DEEPSLATE);
        material(output, i(Items.DIAMOND), MaterialInit.DIAMOND);
        material(output, i(Items.DRIPSTONE_BLOCK), MaterialInit.DRIPSTONE);
        material(output, i(Items.EMERALD), MaterialInit.EMERALD);
        material(output, i(ItemInit.ENDER_METAL_INGOT), MaterialInit.ENDER_METAL);
        material(output, i(Items.END_STONE), MaterialInit.END_STONE);
        material(output, tag(ItemTagKeys.GEMS_FLUORITE), MaterialInit.FLUORITE);
        material(output, i(ItemInit.FLUXIUM_INGOT), MaterialInit.FLUXIUM);
        material(output, i(Items.GLASS), MaterialInit.GLASS);
        material(output, i(Items.GLOWSTONE), MaterialInit.GLOWSTONE);
        material(output, i(Items.GOLD_INGOT), MaterialInit.GOLD);
        material(output, i(Items.HONEYCOMB), MaterialInit.HONEYCOMB);
        material(output, i(Items.IRON_INGOT), MaterialInit.IRON);
        material(output, i(Items.LAPIS_LAZULI), MaterialInit.LAPIS);
        material(output, i(Items.MOSS_BLOCK), MaterialInit.MOSS);
        material(output, i(Items.NETHERITE_INGOT), MaterialInit.NETHERITE);
        material(output, i(Items.NETHER_BRICK), MaterialInit.NETHER_BRICK);
        material(output, i(Items.OBSIDIAN), MaterialInit.OBSIDIAN);
        material(output, i(Items.PHANTOM_MEMBRANE), MaterialInit.PHANTOM_MEMBRANE);
        material(output, i(Items.QUARTZ), MaterialInit.QUARTZ);
        material(output, i(ItemInit.RADIANT_STEEL_INGOT), MaterialInit.RADIANT_STEEL);
        material(output, tag(ItemTagKeys.GEMS_REDSTONE_CRYSTAL), MaterialInit.REDSTONE);
        material(output, i(Items.SANDSTONE), MaterialInit.SANDSTONE);
        material(output, i(Items.SLIME_BALL), MaterialInit.SLIME);
        material(output, i(Items.SNOW_BLOCK), MaterialInit.SNOW);
        material(output, i(Items.STONE), MaterialInit.STONE);
        material(output, i(ItemInit.SULFURIC_ACID_BATTERY), MaterialInit.SULFURIC_ACID_BATTERY);
        material(output, tag(ItemTagKeys.GEMS_TOURMALINE), MaterialInit.TOURMALINE);
        material(output, tag(Tags.Items.STRIPPED_LOGS), MaterialInit.WOOD);
        material(output, tag(ItemTagKeys.INGOTS_ZINC), MaterialInit.ZINC);
        // Spell
        MagitechRecipeBuilders.spell(ItemInit.ALCHAEFABRIC)
                .ingredient(creator.item(Items.PHANTOM_MEMBRANE))
                .spells(SpellInit.ENERCRUX)
                .save(output);
        MagitechRecipeBuilders.spell(ItemInit.MANA_CHARGED_FLUORITE)
                .ingredient(creator.item(ItemTagKeys.GEMS_FLUORITE))
                .spells(SpellInit.ENERCRUX)
                .save(output);
        MagitechRecipeBuilders.spell(BlockInit.MYSTWOOD_LOG_ITEM)
                .ingredient(creator.item(ItemTags.LOGS))
                .spells(SpellInit.ENERCRUX)
                .save(output);
        MagitechRecipeBuilders.spell(BlockInit.VESPERITE_ITEM)
                .ingredient(creator.item(Tags.Items.STONES))
                .spells(SpellInit.ENERCRUX)
                .save(output);
        // Tool Assembly
        MagitechRecipeBuilders.toolAssembly(ItemInit.AXE)
                .ingredient(creator.item(ItemInit.TOOL_BINDING))
                .ingredient(creator.item(ItemInit.STRIKE_HEAD))
                .ingredient(creator.item(ItemInit.LIGHT_BLADE))
                .ingredient(creator.item(ItemInit.HEAVY_HANDLE))
                .save(output);
        MagitechRecipeBuilders.toolAssembly(ItemInit.DAGGER)
                .ingredient(creator.item(ItemInit.HANDGUARD))
                .ingredient(creator.item(ItemInit.LIGHT_BLADE))
                .ingredient(creator.item(ItemInit.LIGHT_HANDLE))
                .save(output);
        MagitechRecipeBuilders.toolAssembly(ItemInit.HAMMER)
                .ingredient(creator.item(ItemInit.TOOL_BINDING))
                .ingredient(creator.item(ItemInit.PLATE))
                .ingredient(creator.item(ItemInit.STRIKE_HEAD))
                .ingredient(creator.item(ItemInit.HEAVY_HANDLE))
                .save(output);
        MagitechRecipeBuilders.toolAssembly(ItemInit.HEAVY_SWORD)
                .ingredient(creator.item(ItemInit.HANDGUARD))
                .ingredient(creator.item(ItemInit.TOOL_BINDING))
                .ingredient(creator.item(ItemInit.HEAVY_BLADE))
                .ingredient(creator.item(ItemInit.LIGHT_HANDLE))
                .save(output);
        MagitechRecipeBuilders.toolAssembly(ItemInit.LIGHT_SWORD)
                .ingredient(creator.item(ItemInit.TOOL_BINDING))
                .ingredient(creator.item(ItemInit.HANDGUARD))
                .ingredient(creator.item(ItemInit.LIGHT_BLADE))
                .ingredient(creator.item(ItemInit.LIGHT_HANDLE))
                .save(output);
        MagitechRecipeBuilders.toolAssembly(ItemInit.PICKAXE)
                .ingredient(creator.item(ItemInit.TOOL_BINDING))
                .ingredient(creator.item(ItemInit.SPIKE_HEAD))
                .ingredient(creator.item(ItemInit.HEAVY_HANDLE))
                .save(output);
        MagitechRecipeBuilders.toolAssembly(ItemInit.SCYTHE)
                .ingredient(creator.item(ItemInit.TOOL_BINDING))
                .ingredient(creator.item(ItemInit.HEAVY_BLADE))
                .ingredient(creator.item(ItemInit.HEAVY_HANDLE))
                .ingredient(creator.item(ItemInit.REINFORCED_ROD))
                .save(output);
        MagitechRecipeBuilders.toolAssembly(ItemInit.SHOVEL)
                .ingredient(creator.item(ItemInit.TOOL_BINDING))
                .ingredient(creator.item(ItemInit.PLATE))
                .ingredient(creator.item(ItemInit.LIGHT_BLADE))
                .ingredient(creator.item(ItemInit.HEAVY_HANDLE))
                .save(output);
        MagitechRecipeBuilders.toolAssembly(ItemInit.STAFF)
                .ingredient(creator.item(ItemInit.TOOL_BINDING))
                .ingredient(creator.item(ItemInit.CONDUCTOR))
                .ingredient(creator.item(ItemInit.HEAVY_HANDLE))
                .ingredient(creator.item(ItemInit.CATALYST))
                .save(output);
        MagitechRecipeBuilders.toolAssembly(ItemInit.WAND)
                .ingredient(creator.item(ItemInit.TOOL_BINDING))
                .ingredient(creator.item(ItemInit.CONDUCTOR))
                .ingredient(creator.item(ItemInit.LIGHT_HANDLE))
                .ingredient(creator.item(ItemInit.CATALYST))
                .save(output);
        // Crucible
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.ALCHAEFABRIC))
                .ingredient(creator.sizedItem(Tags.Items.OBSIDIANS_NORMAL))
                .ingredient(creator.sizedItem(ItemInit.GLACE_CRYSTAL))
                .ingredient(creator.sizedItem(ItemInit.PHANTOM_CRYSTAL))
                .ingredient(creator.sizedItem(Items.CHAIN))
                .ingredient(creator.sizedItem(Tags.Items.INGOTS_GOLD, 2))
                .ingredient(creator.sizedItem(Tags.Items.GEMS_DIAMOND))
                .fluidIngredient(creator.sizedFluid(Tags.Fluids.LAVA, 2000))
                .mana(100000)
                .result(ItemInit.AEGIS_WEAVE, 3)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(Tags.Items.ENDER_PEARLS))
                .ingredient(creator.sizedItem(Tags.Items.INGOTS_IRON, 2))
                .ingredient(creator.sizedItem(Tags.Items.INGOTS_GOLD))
                .ingredient(creator.sizedItem(Tags.Items.INGOTS_COPPER))
                .ingredient(creator.sizedItem(ItemInit.HOLLOW_CRYSTAL))
                .fluidIngredient(creator.sizedFluid(FluidInit.HOLLOW_POTION, 250))
                .mana(50000)
                .result(ItemInit.ENDER_METAL_INGOT, 5)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemTagKeys.GEMS_MANA_CHARGED_FLUORITE, 2))
                .ingredient(creator.sizedItem(ItemTagKeys.GEMS_TOURMALINE, 4))
                .ingredient(creator.sizedItem(Tags.Items.GLASS_BLOCKS, 4))
                .fluidIngredient(creator.sizedFluid(FluidInit.MANA_POTION, 1000))
                .mana(10000)
                .result(BlockInit.MANA_INSULATING_GLASS_ITEM, 6)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.MANA_BERRIES, 2))
                .fluidIngredient(creator.sizedFluid(Tags.Fluids.WATER, 1000))
                .mana(20000)
                .result(FluidInit.MANA_POTION, 1000)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(Tags.Items.NETHER_STARS))
                .ingredient(creator.sizedItem(Tags.Items.GUNPOWDERS, 16))
                .ingredient(creator.sizedItem(ItemInit.EMBER_CRYSTAL, 8))
                .ingredient(creator.sizedItem(ItemInit.MAGIC_CRYSTAL, 8))
                .ingredient(creator.sizedItem(ItemInit.TREMOR_CRYSTAL, 8))
                .fluidIngredient(creator.sizedFluid(Tags.Fluids.LAVA, 2000))
                .mana(10000)
                .result(ItemInit.NETHER_STAR_BRILLIANCE, 4)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.NETHER_STAR_BRILLIANCE))
                .ingredient(creator.sizedItem(Tags.Items.DUSTS_GLOWSTONE))
                .ingredient(creator.sizedItem(Items.GLOW_INK_SAC, 8))
                .ingredient(creator.sizedItem(Tags.Items.INGOTS_GOLD))
                .ingredient(creator.sizedItem(Tags.Items.GEMS_DIAMOND, 4))
                .ingredient(creator.sizedItem(ItemInit.GLACE_CRYSTAL, 4))
                .fluidIngredient(creator.sizedFluid(Tags.Fluids.WATER, 2000))
                .mana(40000)
                .result(ItemInit.RADIANT_STEEL_INGOT, 2)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(BlockInit.CELIFERN_PLANKS_ITEM))
                .ingredient(creator.sizedItem(ItemTagKeys.GEMS_SULFUR, 2))
                .fluidIngredient(creator.sizedFluid(FluidInit.MANA_POTION, 1000))
                .mana(10000)
                .result(Items.CHARCOAL)
                .result(FluidInit.SULFURIC_ACID, 1000)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(Items.BOOK))
                .ingredient(creator.sizedItem(Tags.Items.INGOTS_GOLD))
                .ingredient(creator.sizedItem(ItemTagKeys.GEMS_FLUORITE, 8))
                .ingredient(creator.sizedItem(BlockInit.ALCHECRYSITE_ITEM, 8))
                .ingredient(creator.sizedItem(ItemTagKeys.GEMS_TOURMALINE))
                .fluidIngredient(creator.sizedFluid(Tags.Fluids.WATER, 2000))
                .mana(40000)
                .result(ItemInit.THE_FIRE_THAT_THINKS)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.AGGREGATED_FLUXIA))
                .ingredient(creator.sizedItem(ItemInit.EMBER_CRYSTAL))
                .ingredient(creator.sizedItem(ItemTags.COALS))
                .ingredient(creator.sizedItem(ItemTagKeys.GEMS_CITRINE))
                .fluidIngredient(creator.sizedFluid(FluidInit.MANA_POTION, 1000))
                .mana(10000)
                .result(FluidInit.EMBER_POTION, 1000)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.AGGREGATED_LUMINIS))
                .ingredient(creator.sizedItem(ItemInit.FLOW_CRYSTAL))
                .ingredient(creator.sizedItem(ItemTags.SAPLINGS))
                .ingredient(creator.sizedItem(Items.DRIPSTONE_BLOCK))
                .fluidIngredient(creator.sizedFluid(FluidInit.MANA_POTION, 1000))
                .mana(10000)
                .result(FluidInit.FLOW_POTION, 1000)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.AGGREGATED_FLUXIA))
                .ingredient(creator.sizedItem(ItemInit.GLACE_CRYSTAL))
                .ingredient(creator.sizedItem(Items.CALCITE))
                .ingredient(creator.sizedItem(Tags.Items.GEMS_QUARTZ))
                .fluidIngredient(creator.sizedFluid(FluidInit.MANA_POTION, 1000))
                .mana(10000)
                .result(FluidInit.GLACE_POTION, 1000)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.AGGREGATED_FLUXIA))
                .ingredient(creator.sizedItem(Tags.Items.FOODS_RAW_MEAT))
                .ingredient(creator.sizedItem(ItemInit.MANA_BERRIES))
                .fluidIngredient(creator.sizedFluid(Tags.Fluids.WATER, 1000))
                .mana(10000)
                .result(FluidInit.HEALING_POTION, 1000)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.AGGREGATED_NOCTIS))
                .ingredient(creator.sizedItem(ItemInit.HOLLOW_CRYSTAL))
                .ingredient(creator.sizedItem(Tags.Items.ENDER_PEARLS))
                .ingredient(creator.sizedItem(Items.WARPED_FUNGUS))
                .fluidIngredient(creator.sizedFluid(Tags.Fluids.WATER, 1000))
                .mana(10000)
                .result(FluidInit.HOLLOW_POTION, 1000)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.AGGREGATED_FLUXIA))
                .ingredient(creator.sizedItem(ItemInit.MAGIC_CRYSTAL))
                .ingredient(creator.sizedItem(Tags.Items.GEMS_AMETHYST))
                .ingredient(creator.sizedItem(Tags.Items.INGOTS_GOLD))
                .fluidIngredient(creator.sizedFluid(Tags.Fluids.WATER, 1000))
                .mana(10000)
                .result(FluidInit.MAGIC_POTION, 1000)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.AGGREGATED_LUMINIS))
                .ingredient(creator.sizedItem(ItemInit.PHANTOM_CRYSTAL))
                .ingredient(creator.sizedItem(Items.PHANTOM_MEMBRANE))
                .ingredient(creator.sizedItem(ItemTagKeys.GEMS_FLUORITE))
                .fluidIngredient(creator.sizedFluid(Tags.Fluids.WATER, 1000))
                .mana(10000)
                .result(FluidInit.PHANTOM_POTION, 1000)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.AGGREGATED_LUMINIS))
                .ingredient(creator.sizedItem(ItemInit.SURGE_CRYSTAL))
                .ingredient(creator.sizedItem(Tags.Items.INGOTS_COPPER))
                .ingredient(creator.sizedItem(ItemTagKeys.GEMS_REDSTONE_CRYSTAL))
                .fluidIngredient(creator.sizedFluid(Tags.Fluids.WATER, 1000))
                .mana(10000)
                .result(FluidInit.SURGE_POTION, 1000)
                .save(output);
        MagitechRecipeBuilders.crucible()
                .ingredient(creator.sizedItem(ItemInit.AGGREGATED_NOCTIS))
                .ingredient(creator.sizedItem(ItemInit.TREMOR_CRYSTAL))
                .ingredient(creator.sizedItem(Items.DEEPSLATE))
                .ingredient(creator.sizedItem(Tags.Items.GEMS_LAPIS))
                .fluidIngredient(creator.sizedFluid(Tags.Fluids.WATER, 1000))
                .mana(10000)
                .result(FluidInit.TREMOR_POTION, 1000)
                .save(output);
    }

    private static void shaped(RecipeOutput output, ItemLike result, int count, Map<Character, Ingredient> keys, String... pattern) {
        shaped(output, result, "", count, RecipeCategory.MISC, "", keys, pattern);
    }

    private static void shaped(RecipeOutput output, ItemLike result, String suffix, int count, Map<Character, Ingredient> keys, String... pattern) {
        shaped(output, result, suffix, count, RecipeCategory.MISC, "", keys, pattern);
    }

    private static void shaped(RecipeOutput output, ItemLike result, int count, RecipeCategory category, String group, Map<Character, Ingredient> keys, String... pattern) {
        shaped(output, result, "", count, category, group, keys, pattern);
    }

    private static void shaped(RecipeOutput output, ItemLike result, String suffix, int count, RecipeCategory category, String group, Map<Character, Ingredient> keys, String... pattern) {
        var builder = new ShapedRecipeBuilder(category, new ItemStack(result, count)).group(group);
        for (String row : pattern) builder.pattern(row);
        keys.forEach(builder::define);
        builder.unlockedBy("has_input", VanillaSimpleRecipeGenerator.has(keys.values().iterator().next())).save(output, recipeId("crafting", result, suffix));
    }

    private static void shapeless(RecipeOutput output, ItemStack result, Ingredient... ingredients) {
        shapeless(output, result, "", ingredients);
    }

    private static void shapeless(RecipeOutput output, ItemStack result, String suffix, Ingredient... ingredients) {
        var builder = new ShapelessRecipeBuilder(RecipeCategory.MISC, result);
        for (Ingredient ingredient : ingredients) builder.requires(ingredient);
        builder.unlockedBy("has_input", VanillaSimpleRecipeGenerator.has(ingredients[0])).save(output, recipeId("crafting", result.getItem(), suffix));
    }

    private static void cooking(RecipeOutput output, ItemLike result, Ingredient ingredient, float experience, int time, boolean blasting) {
        cooking(output, result, ingredient, experience, time, blasting, "", "");
    }

    private static void cooking(RecipeOutput output, ItemLike result, Ingredient ingredient, float experience, int time, boolean blasting, String group) {
        cooking(output, result, ingredient, experience, time, blasting, "", group);
    }

    private static void cooking(RecipeOutput output, ItemLike result, Ingredient ingredient, float experience, int time, boolean blasting, String suffix, String group) {
        var builder = blasting ? SimpleCookingRecipeBuilder.blasting(ingredient, RecipeCategory.MISC, result, experience, time) : SimpleCookingRecipeBuilder.smelting(ingredient, RecipeCategory.MISC, result, experience, time);
        builder.group(group);
        String folder = blasting ? "blasting" : "smelting";
        builder.unlockedBy("has_input", VanillaSimpleRecipeGenerator.has(ingredient)).save(output, recipeId(folder, result, suffix));
    }

    private static void infusion(RecipeOutput output, ItemLike result, Ingredient base, int baseCount, long mana, int resultCount, SizedIngredient... ingredients) {
        infusion(output, "infusion", result, "", base, baseCount, mana, resultCount, ingredients);
    }

    private static void infusion(RecipeOutput output, ItemLike result, String suffix, Ingredient base, int baseCount, long mana, int resultCount, SizedIngredient... ingredients) {
        infusion(output, "infusion", result, suffix, base, baseCount, mana, resultCount, ingredients);
    }

    private static void infusion(RecipeOutput output, String folder, ItemLike result, Ingredient base, int baseCount, long mana, int resultCount, SizedIngredient... ingredients) {
        infusion(output, folder, result, "", base, baseCount, mana, resultCount, ingredients);
    }

    private static void infusion(RecipeOutput output, String folder, ItemLike result, String suffix, Ingredient base, int baseCount, long mana, int resultCount, SizedIngredient... ingredients) {
        custom(output, recipeId(folder, result, suffix), new InfusionRecipe("", creator.sizedItem(base, baseCount), List.of(ingredients), mana, stack(result, resultCount)));
    }

    private static void material(RecipeOutput output, Ingredient ingredient, DeferredToolMaterial<?> material) {
        custom(output, Magitech.id("part_material/" + material.getId().getPath() + "_material"), new ToolMaterialRecipe("", ingredient, material.get()));
    }

    private static void fieldEffect(RecipeOutput output, SizedIngredient ingredient, FieldEffectTypeLike fieldEffect, ItemStack... results) {
        ItemStack input = ingredient.getItems()[0];
        custom(output, fieldEffectId(input, results), new FieldEffectRecipe("", ingredient, fieldEffect.asFieldEffectType(), List.of(results)));
    }

    private static void fieldEffect(RecipeOutput output, SizedIngredient ingredient, FieldEffectTypeLike fieldEffect, FluidStack result) {
        ItemStack input = ingredient.getItems()[0];
        custom(output, fieldEffectId(input, result), new FieldEffectRecipe("", ingredient, fieldEffect.asFieldEffectType(), result));
    }

    private static void fieldEffect(RecipeOutput output, SizedFluidIngredient ingredient, FieldEffectTypeLike fieldEffect, ItemStack... results) {
        FluidStack input = ingredient.getFluids()[0];
        custom(output, fieldEffectId(input, results), new FieldEffectRecipe("", ingredient, fieldEffect.asFieldEffectType(), List.of(results)));
    }

    private static ResourceLocation fieldEffectId(ItemStack input, ItemStack... results) {
        return Magitech.id("field_effect/" + itemId(input) + "_to_" + resultId(results));
    }

    private static ResourceLocation fieldEffectId(ItemStack input, FluidStack result) {
        return Magitech.id("field_effect/" + itemId(input) + "_to_" + fluidId(result));
    }

    private static ResourceLocation fieldEffectId(FluidStack input, ItemStack... results) {
        return Magitech.id("field_effect/" + fluidId(input) + "_to_" + resultId(results));
    }

    private static String itemId(ItemStack stack) {
        return stack.getItemHolder().unwrapKey().orElseThrow().location().getPath();
    }

    private static String fluidId(FluidStack stack) {
        return stack.getFluidHolder().unwrapKey().orElseThrow().location().getPath();
    }

    private static String resultId(ItemStack... results) {
        return Arrays.stream(results).map(ModRecipeProvider::itemId).distinct().collect(Collectors.joining("_and_"));
    }

    private static void custom(RecipeOutput output, String folder, ItemLike result, String suffix, Recipe<?> recipe) {
        custom(output, recipeId(folder, result, suffix), recipe);
    }

    private static void custom(RecipeOutput output, ResourceLocation id, Recipe<?> recipe) {
        output.accept(id, recipe, null);
    }

    private static ResourceLocation recipeId(String folder, ItemLike result) {
        return Magitech.id(folder + "/" + RecipeBuilder.getDefaultRecipeId(result).getPath());
    }

    private static ResourceLocation recipeId(String folder, ItemLike result, String suffix) {
        return recipeId(folder, result).withSuffix(suffix);
    }

    private static Ingredient i(ItemLike item) {
        return creator.item(item);
    }

    private static Ingredient i(ItemLike... items) {
        return creator.item(items);
    }

    private static Ingredient tag(TagKey<Item> tag) {
        return creator.item(tag);
    }

    private static SizedIngredient s(ItemLike item, int count) {
        return creator.sizedItem(item, count);
    }

    private static SizedIngredient s(Ingredient ingredient, int count) {
        return creator.sizedItem(ingredient, count);
    }

    private static SizedIngredient s(TagKey<Item> tag, int count) {
        return creator.sizedItem(tag, count);
    }

    private static Ingredient component(ItemLike item, ToolMaterialLike material, boolean strict) {
        return DataComponentIngredient.of(strict, ComponentInit.MATERIAL_COMPONENT, new MaterialComponent(material), item);
    }

    private static ItemStack stack(ItemLike item, int count) {
        return new ItemStack(item, count);
    }

    private static Map<Character, Ingredient> keys(Object... values) {
        var result = new java.util.HashMap<Character, Ingredient>();
        for (int index = 0; index < values.length; index += 2) {
            result.put((Character) values[index], ingredient(values[index + 1]));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private static Ingredient ingredient(Object value) {
        if (value instanceof Ingredient ingredient) return ingredient;
        if (value instanceof TagKey<?> tag) return Ingredient.of((TagKey<Item>) tag);
        if (value instanceof ItemLike item) return i(item);
        throw new IllegalArgumentException("Unsupported recipe ingredient: " + value);
    }
}
