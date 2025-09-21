package net.stln.magitech.data.recipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import org.jetbrains.annotations.NotNull;

public interface MagitechRecipeBuilder extends RecipeBuilder {
    @Deprecated
    @Override
    default @NotNull RecipeBuilder unlockedBy(
            @NotNull String name, @NotNull Criterion<?> criterion) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    default @NotNull Item getResult() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    default void save(@NotNull RecipeOutput recipeOutput, @NotNull String id) {
        throw new UnsupportedOperationException();
    }

    @NotNull ResourceLocation getPrimalId();

    default void savePrefixed(@NotNull RecipeOutput recipeOutput, @NotNull String prefix) {
        save(recipeOutput, getPrimalId().withPrefix(prefix));
    }

    default void saveSuffixed(@NotNull RecipeOutput recipeOutput, @NotNull String prefix) {
        save(recipeOutput, getPrimalId().withSuffix(prefix));
    }

    @Override
    default void save(@NotNull RecipeOutput recipeOutput) {
        save(recipeOutput, getPrimalId());
    }
}
