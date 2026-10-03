package net.stln.magitech.content.recipe.builder;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.stln.magitech.content.fluid.FluidContent;
import org.jetbrains.annotations.NotNull;

public enum IngredientCreator {
    INSTANCE;

    // Item
    public @NotNull Ingredient item(@NotNull ItemLike item) {
        return Ingredient.of(item);
    }

    public @NotNull Ingredient item(@NotNull ItemLike... item) {
        return Ingredient.of(item);
    }

    public @NotNull Ingredient item(@NotNull TagKey<Item> tagKey) {
        return Ingredient.of(tagKey);
    }

    // Sized Item
    public @NotNull SizedIngredient sizedItem(@NotNull ItemLike item) {
        return sizedItem(item, 1);
    }

    public @NotNull SizedIngredient sizedItem(@NotNull ItemLike item, int count) {
        return SizedIngredient.of(item, count);
    }

    public @NotNull SizedIngredient sizedItem(@NotNull TagKey<Item> tagKey) {
        return sizedItem(tagKey, 1);
    }

    public @NotNull SizedIngredient sizedItem(@NotNull TagKey<Item> tagKey, int count) {
        return SizedIngredient.of(tagKey, count);
    }

    public @NotNull SizedIngredient sizedItem(@NotNull Ingredient ingredient) {
        return sizedItem(ingredient, 1);
    }

    public @NotNull SizedIngredient sizedItem(@NotNull Ingredient ingredient, int count) {
        return new SizedIngredient(ingredient, count);
    }

    // Sized Fluid
    public @NotNull SizedFluidIngredient sizedFluid(@NotNull Fluid fluid, int amount) {
        return SizedFluidIngredient.of(fluid, amount);
    }

    public @NotNull SizedFluidIngredient sizedFluid(@NotNull TagKey<Fluid> tagKey, int amount) {
        return SizedFluidIngredient.of(tagKey, amount);
    }

    public @NotNull SizedFluidIngredient sizedFluid(@NotNull FluidContent content, int amount) {
        return sizedFluid(content.fluidTag(), amount);
    }

    public @NotNull SizedFluidIngredient sizedFluid(@NotNull FluidIngredient ingredient, int amount) {
        return new SizedFluidIngredient(ingredient, amount);
    }
}
