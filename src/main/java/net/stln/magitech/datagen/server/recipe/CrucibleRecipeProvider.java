package net.stln.magitech.datagen.server.recipe;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.stln.magitech.data.recipe.ZardiusCrucibleRecipeBuilder;
import net.stln.magitech.init.MagitechBlocks;
import net.stln.magitech.item.ItemInit;
import net.stln.magitech.tag.MagitechTags;

import org.jetbrains.annotations.NotNull;

class CrucibleRecipeProvider {
    static void register(@NotNull RecipeOutput recipeOutput) {
        // Aegis Weave
        ZardiusCrucibleRecipeBuilder.create(ItemInit.AEGIS_WEAVE.toStack(3), null)
                .addIngredient(ItemInit.ALCHAEFABRIC)
                .addIngredient(Tags.Items.OBSIDIANS)
                .addIngredient(ItemInit.GLACE_CRYSTAL)
                .addIngredient(ItemInit.PHANTOM_CRYSTAL)
                .addIngredient(Items.CHAIN)
                .addIngredient(Tags.Items.INGOTS_GOLD)
                .addIngredient(Tags.Items.INGOTS_GOLD)
                .addIngredient(Tags.Items.GEMS_DIAMOND)
                .setFluidIngredient(Tags.Fluids.LAVA, 2000)
                .save(recipeOutput);
        // Alchecrystal
        ZardiusCrucibleRecipeBuilder.create(MagitechBlocks.ALCHECRYSITE.toStack(4), null)
                .addIngredient(Items.CALCITE)
                .addIngredient(MagitechTags.Items.GEMS_FLUORITE)
                .addIngredient(Items.DEEPSLATE)
                .addIngredient(Tags.Items.INGOTS_GOLD)
                .addIngredient(ItemInit.MAGIC_CRYSTAL)
                .setFluidIngredient(Tags.Fluids.WATER, 1000)
                .save(recipeOutput);
        // Ember Crystal
        ZardiusCrucibleRecipeBuilder.create(ItemInit.EMBER_CRYSTAL.toStack(), null)
                .addIngredient(MagitechTags.Items.GEMS_FLUORITE)
                .addIngredient(MagitechTags.Items.ASPECT_CRYSTAL_BASE)
                .addIngredient(ItemTags.COALS)
                .addIngredient(ItemTags.COALS)
                .setFluidIngredient(Tags.Fluids.WATER, 250)
                .save(recipeOutput);
        // Ender Metal
        ZardiusCrucibleRecipeBuilder.create(ItemInit.ENDER_METAL_INGOT.toStack(5), null)
                .addIngredient(Tags.Items.ENDER_PEARLS)
                .addIngredient(Tags.Items.INGOTS_IRON)
                .addIngredient(Tags.Items.INGOTS_IRON)
                .addIngredient(Tags.Items.INGOTS_GOLD)
                .addIngredient(Tags.Items.INGOTS_COPPER)
                .addIngredient(ItemInit.HOLLOW_CRYSTAL)
                .setFluidIngredient(Tags.Fluids.WATER, 500)
                .save(recipeOutput);
        // Flow Crystal
        createGem(ItemInit.FLOW_CRYSTAL, 1)
                .addIngredient(ItemTags.SAPLINGS)
                .addIngredient(ItemTags.SAPLINGS)
                .save(recipeOutput);
        // Glace Crystal
        createGem(ItemInit.GLACE_CRYSTAL, 1)
                .addIngredient(Items.CALCITE)
                .addIngredient(Items.CALCITE)
                .save(recipeOutput);
        // Hollow Crystal
        createGem(ItemInit.HOLLOW_CRYSTAL, 1)
                .addIngredient(Tags.Items.ENDER_PEARLS)
                .addIngredient(Tags.Items.ENDER_PEARLS)
                .save(recipeOutput);
        // Magic Crystal
        createGem(ItemInit.MAGIC_CRYSTAL, 1)
                .addIngredient(Tags.Items.GEMS_AMETHYST)
                .addIngredient(Tags.Items.GEMS_AMETHYST)
                .save(recipeOutput);
        // Nether Star Brilliance
        ZardiusCrucibleRecipeBuilder.create(ItemInit.NETHER_STAR_BRILLIANCE.toStack(4), null)
                .addIngredient(Tags.Items.NETHER_STARS)
                .addIngredient(Tags.Items.GUNPOWDERS)
                .addIngredient(ItemInit.EMBER_CRYSTAL)
                .addIngredient(ItemInit.MAGIC_CRYSTAL)
                .addIngredient(ItemInit.TREMOR_CRYSTAL)
                .setFluidIngredient(Tags.Fluids.LAVA, 2000)
                .save(recipeOutput);
        // Phantom Crystal
        createGem(ItemInit.PHANTOM_CRYSTAL, 1)
                .addIngredient(Items.PHANTOM_MEMBRANE)
                .addIngredient(Items.PHANTOM_MEMBRANE)
                .save(recipeOutput);
        // Radiant Steel Ingot
        ZardiusCrucibleRecipeBuilder.create(ItemInit.RADIANT_STEEL_INGOT.toStack(2), null)
                .addIngredient(ItemInit.NETHER_STAR_BRILLIANCE)
                .addIngredient(Tags.Items.DUSTS_GLOWSTONE)
                .addIngredient(Items.GLOW_INK_SAC)
                .addIngredient(Tags.Items.INGOTS_GOLD)
                .addIngredient(Tags.Items.GEMS_DIAMOND)
                .addIngredient(Tags.Items.GEMS_DIAMOND)
                .addIngredient(ItemInit.GLACE_CRYSTAL)
                .addIngredient(ItemInit.GLACE_CRYSTAL)
                .setFluidIngredient(Tags.Fluids.WATER, 2000)
                .save(recipeOutput);
        // Redstone from crystal
        ZardiusCrucibleRecipeBuilder.create(new ItemStack(Items.REDSTONE, 5), null)
                .addIngredient(ItemInit.REDSTONE_CRYSTAL)
                .addIngredient(ItemInit.REDSTONE_CRYSTAL)
                .addIngredient(Tags.Items.DUSTS_REDSTONE)
                .addIngredient(Items.SUGAR)
                .addIngredient(MagitechTags.Items.GEMS_MANA_CHARGED_FLUORITE)
                .setFluidIngredient(Tags.Fluids.WATER, 100)
                .saveSuffixed(recipeOutput, "_from_redstone_crystal");
        // Surge Crystal
        createGem(ItemInit.SURGE_CRYSTAL, 1)
                .addIngredient(Tags.Items.INGOTS_COPPER)
                .addIngredient(Tags.Items.INGOTS_COPPER)
                .save(recipeOutput);
        // The fire that thinks
        ZardiusCrucibleRecipeBuilder.create(ItemInit.THE_FIRE_THAT_THINKS.toStack(), null)
                .addIngredient(Items.BOOK)
                .addIngredient(Tags.Items.INGOTS_GOLD)
                .addIngredient(MagitechTags.Items.GEMS_FLUORITE)
                .addIngredient(MagitechTags.Items.GEMS_FLUORITE)
                .addIngredient(MagitechBlocks.ALCHECRYSITE)
                .addIngredient(MagitechBlocks.ALCHECRYSITE)
                .addIngredient(MagitechTags.Items.GEMS_TOURMALINE)
                .setFluidIngredient(Tags.Fluids.WATER, 2000)
                .save(recipeOutput);
        // Tremor Crystal
        createGem(ItemInit.TREMOR_CRYSTAL, 1)
                .addIngredient(Items.DEEPSLATE)
                .addIngredient(Items.DEEPSLATE)
                .save(recipeOutput);
    }

    private static ZardiusCrucibleRecipeBuilder createGem(ItemLike result, int count) {
        return ZardiusCrucibleRecipeBuilder.create(new ItemStack(result, count), null)
                .addIngredient(MagitechTags.Items.GEMS_MANA_CHARGED_FLUORITE)
                .addIngredient(MagitechTags.Items.ASPECT_CRYSTAL_BASE)
                .setFluidIngredient(Tags.Fluids.WATER, 250);
    }
}
