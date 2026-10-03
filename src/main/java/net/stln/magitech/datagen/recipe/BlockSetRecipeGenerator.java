package net.stln.magitech.datagen.recipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class BlockSetRecipeGenerator {

    // GENERAL

    public static void slab(RecipeOutput output, ItemLike ing, ItemLike result) {
        slab(output, ing, result, RecipeCategory.MISC, "");
    }

    public static void slab(RecipeOutput output, ItemLike ing, ItemLike result, RecipeCategory category, String group) {
        new ShapedRecipeBuilder(category, new ItemStack(result, 6))
                .group(group)
                .pattern("###")
                .define('#', ing)
                .unlockedBy("has_input", has(ing))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    public static void stairs(RecipeOutput output, ItemLike ing, ItemLike result) {
        stairs(output, ing, result, RecipeCategory.MISC, "");
    }

    public static void stairs(RecipeOutput output, ItemLike ing, ItemLike result, RecipeCategory category, String group) {
        new ShapedRecipeBuilder(category, new ItemStack(result, 4))
                .group(group)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ing)
                .unlockedBy("has_input", has(ing))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    public static void door(RecipeOutput output, ItemLike ing, ItemLike result) {
        door(output, ing, result, RecipeCategory.MISC, "");
    }

    public static void door(RecipeOutput output, ItemLike ing, ItemLike result, RecipeCategory category, String group) {
        new ShapedRecipeBuilder(category, new ItemStack(result, 3))
                .group(group)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', ing)
                .unlockedBy("has_input", has(ing))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    public static void trapdoor(RecipeOutput output, ItemLike ing, ItemLike result) {
        trapdoor(output, ing, result, RecipeCategory.MISC, "");
    }

    public static void trapdoor(RecipeOutput output, ItemLike ing, ItemLike result, RecipeCategory category, String group) {
        new ShapedRecipeBuilder(category, new ItemStack(result, 2))
                .group(group)
                .pattern("###")
                .pattern("###")
                .define('#', ing)
                .unlockedBy("has_input", has(ing))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    public static void pressurePlate(RecipeOutput output, ItemLike ing, ItemLike result) {
        pressurePlate(output, ing, result, RecipeCategory.MISC, "");
    }

    public static void pressurePlate(RecipeOutput output, ItemLike ing, ItemLike result, RecipeCategory category, String group) {
        new ShapedRecipeBuilder(category, new ItemStack(result))
                .group(group)
                .pattern("##")
                .define('#', ing)
                .unlockedBy("has_input", has(ing))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    public static void button(RecipeOutput output, ItemLike ing, ItemLike result) {
        new net.minecraft.data.recipes.ShapelessRecipeBuilder(RecipeCategory.REDSTONE, result, 1)
                .group("wooden_button")
                .requires(ing)
                .unlockedBy("has_input", has(ing))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    // STONE

    public static void wall(RecipeOutput output, ItemLike stone, ItemLike result) {
        new ShapedRecipeBuilder(RecipeCategory.MISC, new ItemStack(result, 6))
                .pattern("###")
                .pattern("###")
                .define('#', stone)
                .unlockedBy("has_input", has(stone))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    public static void stonecutting(RecipeOutput output, ItemLike stone, ItemLike result, int count) {
        VanillaSimpleRecipeGenerator.stonecutting(output, Ingredient.of(stone), result.asItem(), count);
    }

    public static void stonecutting(RecipeOutput output, ItemLike stone, ItemLike result, int count, String suffix) {
        VanillaSimpleRecipeGenerator.stonecutting(output, Ingredient.of(stone), result.asItem(), count, suffix);
    }

    // WOOD

    public static void fence(RecipeOutput output, ItemLike planks, ItemLike result) {
        fence(output, planks, result, RecipeCategory.MISC, "");
    }

    public static void fence(RecipeOutput output, ItemLike planks, ItemLike result, RecipeCategory category, String group) {
        new ShapedRecipeBuilder(category, new ItemStack(result, 3))
                .group(group)
                .pattern("#S#")
                .pattern("#S#")
                .define('#', planks)
                .define('S', Items.STICK)
                .unlockedBy("has_input", has(planks))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    public static void fenceGate(RecipeOutput output, ItemLike planks, ItemLike result) {
        fenceGate(output, planks, result, RecipeCategory.MISC, "");
    }

    public static void fenceGate(RecipeOutput output, ItemLike planks, ItemLike result, RecipeCategory category, String group) {
        new ShapedRecipeBuilder(category, new ItemStack(result))
                .group(group)
                .pattern("S#S")
                .pattern("S#S")
                .define('#', planks)
                .define('S', Items.STICK)
                .unlockedBy("has_input", has(planks))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    public static void sign(RecipeOutput output, ItemLike planks, ItemLike result) {
        sign(output, planks, result, RecipeCategory.MISC, "");
    }

    public static void sign(RecipeOutput output, ItemLike planks, ItemLike result, RecipeCategory category, String group) {
        new ShapedRecipeBuilder(category, new ItemStack(result, 3))
                .group(group)
                .pattern("###")
                .pattern("###")
                .pattern(" S ")
                .define('#', planks)
                .define('S', Items.STICK)
                .unlockedBy("has_input", has(planks))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    public static void hangingSign(RecipeOutput output, ItemLike strippedLog, ItemLike result) {
        hangingSign(output, strippedLog, result, RecipeCategory.MISC, "");
    }

    public static void hangingSign(RecipeOutput output, ItemLike strippedLog, ItemLike result, RecipeCategory category, String group) {
        new ShapedRecipeBuilder(category, new ItemStack(result, 6))
                .group(group)
                .pattern("C C")
                .pattern("###")
                .pattern("###")
                .define('#', strippedLog)
                .define('C', Items.CHAIN)
                .unlockedBy("has_input", has(strippedLog))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    public static void boat(RecipeOutput output, ItemLike planks, ItemLike result) {
        new ShapedRecipeBuilder(RecipeCategory.MISC, new ItemStack(result))
                .pattern("# #")
                .pattern("###")
                .define('#', planks)
                .unlockedBy("has_input", has(planks))
                .save(output, VanillaSimpleRecipeGenerator.craftingId(result));
    }

    private static @NotNull Criterion<InventoryChangeTrigger.TriggerInstance> has(@NotNull ItemLike item) {
        return VanillaSimpleRecipeGenerator.has(item.asItem());
    }
}
