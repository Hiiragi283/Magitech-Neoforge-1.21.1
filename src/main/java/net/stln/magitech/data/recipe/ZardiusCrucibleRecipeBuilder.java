package net.stln.magitech.data.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.stln.magitech.recipe.ZardiusCrucibleRecipe;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ZardiusCrucibleRecipeBuilder
        implements IngredientRecipeBuilder<ZardiusCrucibleRecipeBuilder> {
    private final @Nullable ItemStack result;
    private final @Nullable FluidStack resultFluid;

    public static ZardiusCrucibleRecipeBuilder create(
            @Nullable ItemStack result, @Nullable FluidStack resultFluid) {
        if (result == null && resultFluid == null) {
            throw new IllegalArgumentException("Either item or fluid result must not be null");
        }
        return new ZardiusCrucibleRecipeBuilder(result, resultFluid);
    }

    private ZardiusCrucibleRecipeBuilder(
            @Nullable ItemStack result, @Nullable FluidStack resultFluid) {
        this.result = result;
        this.resultFluid = resultFluid;
    }

    private @Nullable String group;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private @Nullable SizedFluidIngredient fluidIngredient;

    public @NotNull ZardiusCrucibleRecipeBuilder setFluidIngredient(
            @NotNull Fluid fluid, int amount) {
        return setFluidIngredient(SizedFluidIngredient.of(fluid, amount));
    }

    public @NotNull ZardiusCrucibleRecipeBuilder setFluidIngredient(
            @NotNull TagKey<Fluid> tagKey, int amount) {
        return setFluidIngredient(SizedFluidIngredient.of(tagKey, amount));
    }

    public @NotNull ZardiusCrucibleRecipeBuilder setFluidIngredient(
            @NotNull SizedFluidIngredient fluidIngredient) {
        this.fluidIngredient = fluidIngredient;
        return this;
    }

    @Override
    public @NotNull ZardiusCrucibleRecipeBuilder addIngredient(@NotNull Ingredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    @Override
    public @NotNull ResourceLocation getPrimalId() {
        if (result != null) {
            return result.getItemHolder().unwrapKey().map(ResourceKey::location).orElseThrow();
        } else if (resultFluid != null) {
            return resultFluid
                    .getFluidHolder()
                    .unwrapKey()
                    .map(ResourceKey::location)
                    .orElseThrow();
        } else throw new IllegalStateException("Both item or fluid result is empty");
    }

    @Override
    public @NotNull ZardiusCrucibleRecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public void save(@NotNull RecipeOutput recipeOutput, @NotNull ResourceLocation id) {
        recipeOutput.accept(
                id.withPrefix("crucible/"),
                new ZardiusCrucibleRecipe(
                        group == null ? "" : group,
                        ingredients,
                        Objects.requireNonNull(
                                fluidIngredient, "Fluid Ingredient is not initialized"),
                        Optional.ofNullable(result),
                        Optional.ofNullable(resultFluid)),
                null);
    }
}
