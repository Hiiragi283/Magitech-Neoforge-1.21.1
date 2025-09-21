package net.stln.magitech.data.recipe;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import org.jetbrains.annotations.NotNull;

public interface IngredientRecipeBuilder<BUILDER extends MagitechRecipeBuilder>
        extends MagitechRecipeBuilder {
    default @NotNull BUILDER addIngredient(@NotNull TagKey<Item> tagKey) {
        return addIngredient(Ingredient.of(tagKey));
    }

    default @NotNull BUILDER addIngredient(@NotNull ItemLike... items) {
        return addIngredient(Ingredient.of(items));
    }

    @NotNull BUILDER addIngredient(@NotNull Ingredient ingredient);
}
