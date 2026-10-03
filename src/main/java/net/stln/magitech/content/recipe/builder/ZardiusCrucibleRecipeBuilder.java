package net.stln.magitech.content.recipe.builder;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.stln.magitech.content.fluid.FluidContent;
import net.stln.magitech.content.recipe.ZardiusCrucibleRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ZardiusCrucibleRecipeBuilder extends AbstractRecipeBuilder<ZardiusCrucibleRecipe> {
    public ZardiusCrucibleRecipeBuilder() {
        super("zardius_crucible");
    }

    private String group = "";
    private final List<SizedIngredient> ingredients = new ArrayList<>();
    private SizedFluidIngredient fluidIngredient;
    private long mana = 0L;
    private @Nullable ItemStack result;
    private @Nullable FluidStack fluidResult;

    public ZardiusCrucibleRecipeBuilder group(@NotNull String name) {
        this.group = name;
        return this;
    }

    public ZardiusCrucibleRecipeBuilder ingredient(@NotNull SizedIngredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public ZardiusCrucibleRecipeBuilder fluidIngredient(@NotNull SizedFluidIngredient fluidIngredient) {
        this.fluidIngredient = fluidIngredient;
        return this;
    }

    public ZardiusCrucibleRecipeBuilder mana(long value) {
        this.mana = value;
        return this;
    }

    public ZardiusCrucibleRecipeBuilder result(@NotNull ItemLike result) {
        return result(result, 1);
    }

    public ZardiusCrucibleRecipeBuilder result(@NotNull ItemLike result, int count) {
        return result(new ItemStack(result, count));
    }

    public ZardiusCrucibleRecipeBuilder result(@NotNull ItemStack result) {
        this.result = result;
        return this;
    }

    public ZardiusCrucibleRecipeBuilder result(@NotNull Fluid result, int amount) {
        return result(new FluidStack(result, amount));
    }

    public ZardiusCrucibleRecipeBuilder result(@NotNull FluidContent result, int amount) {
        return result(result.toStack(amount));
    }

    public ZardiusCrucibleRecipeBuilder result(@NotNull FluidStack fluidResult) {
        this.fluidResult = fluidResult;
        return this;
    }

    @Override
    protected @Nullable ResourceLocation getRecipeId() {
        return getFluidResult()
                .map(FluidStack::getFluidHolder)
                .flatMap(Holder::unwrapKey)
                .map(ResourceKey::location)
                .orElseGet(() -> getItemResult().map(ItemStack::getItemHolder).flatMap(Holder::unwrapKey).map(ResourceKey::location).orElse(null));
    }

    private Optional<ItemStack> getItemResult() {
        return Optional.ofNullable(result).filter(stack -> !stack.isEmpty());
    }

    private Optional<FluidStack> getFluidResult() {
        return Optional.ofNullable(fluidResult).filter(stack -> !stack.isEmpty());
    }

    @Override
    protected @NotNull ZardiusCrucibleRecipe createRecipe() {
        return new ZardiusCrucibleRecipe(group, ingredients, fluidIngredient, mana, getItemResult(), getFluidResult());
    }
}
