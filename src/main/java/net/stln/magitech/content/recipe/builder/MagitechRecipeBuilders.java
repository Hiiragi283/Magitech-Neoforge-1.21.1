package net.stln.magitech.content.recipe.builder;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.stln.magitech.content.recipe.CompressingRecipe;
import net.stln.magitech.content.recipe.CrushingRecipe;

public final class MagitechRecipeBuilders {
    private MagitechRecipeBuilders() {
    }

    // Compressing
    public static SingleLodestoneInWorldRecipeBuilder<CompressingRecipe> compressing(ItemLike result) {
        return compressing(result, 1);
    }

    public static SingleLodestoneInWorldRecipeBuilder<CompressingRecipe> compressing(ItemLike result, int count) {
        return compressing(new ItemStack(result, count));
    }

    public static SingleLodestoneInWorldRecipeBuilder<CompressingRecipe> compressing(ItemStack result) {
        return new SingleLodestoneInWorldRecipeBuilder<>("compressing", CompressingRecipe::new, result);
    }

    // Crushing
    public static SingleLodestoneInWorldRecipeBuilder<CrushingRecipe> crushing(ItemLike result) {
        return crushing(result, 1);
    }

    public static SingleLodestoneInWorldRecipeBuilder<CrushingRecipe> crushing(ItemLike result, int count) {
        return crushing(new ItemStack(result, count));
    }

    public static SingleLodestoneInWorldRecipeBuilder<CrushingRecipe> crushing(ItemStack result) {
        return new SingleLodestoneInWorldRecipeBuilder<>("crushing", CrushingRecipe::new, result);
    }

    // Spell Conversion
    public static SpellConversionRecipeBuilder spell(ItemLike result) {
        return spell(result, 1);
    }

    public static SpellConversionRecipeBuilder spell(ItemLike result, int count) {
        return spell(new ItemStack(result, count));
    }

    public static SpellConversionRecipeBuilder spell(ItemStack result) {
        return new SpellConversionRecipeBuilder(result);
    }

    // Tool Assembly
    public static ToolAssemblyRecipeBuilder toolAssembly(ItemLike result) {
        return toolAssembly(result, 1);
    }

    public static ToolAssemblyRecipeBuilder toolAssembly(ItemLike result, int count) {
        return toolAssembly(new ItemStack(result, count));
    }

    public static ToolAssemblyRecipeBuilder toolAssembly(ItemStack result) {
        return new ToolAssemblyRecipeBuilder(result);
    }

    // Crucible
    public static ZardiusCrucibleRecipeBuilder crucible() {
        return new ZardiusCrucibleRecipeBuilder();
    }
}
