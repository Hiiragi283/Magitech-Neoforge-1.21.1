package net.stln.magitech.data.recipe;

import java.util.Objects;

import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.stln.magitech.magic.spell.Spell;
import net.stln.magitech.magic.spell.SpellLike;
import net.stln.magitech.recipe.SpellConversionRecipe;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.base.Preconditions;

public class SpellConversionRecipeBuilder
        implements IngredientRecipeBuilder<SpellConversionRecipeBuilder> {
    private final ItemStack result;

    public static @NotNull SpellConversionRecipeBuilder create(@NotNull ItemLike item, int count) {
        return create(new ItemStack(item, count));
    }

    public static @NotNull SpellConversionRecipeBuilder create(@NotNull ItemStack result) {
        return new SpellConversionRecipeBuilder(result);
    }

    private SpellConversionRecipeBuilder(@NotNull ItemStack result) {
        this.result = result;
    }

    private @Nullable String group;
    private @Nullable Ingredient ingredient;
    private @Nullable Spell spell;

    public @NotNull SpellConversionRecipeBuilder setSpell(@NotNull SpellLike spell) {
        Preconditions.checkArgument(this.spell == null, "Spell already initialized");
        this.spell = spell.asSpell();
        return this;
    }

    @Override
    public @NotNull SpellConversionRecipeBuilder addIngredient(@NotNull Ingredient ingredient) {
        Preconditions.checkArgument(this.ingredient == null, "Ingredient already initialized");
        this.ingredient = ingredient;
        return this;
    }

    @Override
    public @NotNull ResourceLocation getPrimalId() {
        return result.getItemHolder().unwrapKey().map(ResourceKey::location).orElseThrow();
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public void save(@NotNull RecipeOutput recipeOutput, @NotNull ResourceLocation id) {
        recipeOutput.accept(
                id.withPrefix("spelling/"),
                new SpellConversionRecipe(
                        group == null ? "" : group,
                        Objects.requireNonNull(ingredient, "Ingredient is not initialized"),
                        Objects.requireNonNull(spell, "Spell is not initialized"),
                        result),
                null);
    }
}
