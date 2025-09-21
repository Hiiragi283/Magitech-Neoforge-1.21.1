package net.stln.magitech.datagen.server.recipe;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import org.jetbrains.annotations.NotNull;

public class MagitechRecipeProvider extends RecipeProvider {
    public MagitechRecipeProvider(
            PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(
            @NotNull RecipeOutput recipeOutput, HolderLookup.@NotNull Provider holderLookup) {
        CrucibleRecipeProvider.register(recipeOutput);
        SpellConversionRecipeProvider.register(recipeOutput);
    }
}
