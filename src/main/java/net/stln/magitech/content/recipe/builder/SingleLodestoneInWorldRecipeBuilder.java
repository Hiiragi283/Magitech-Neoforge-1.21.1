package net.stln.magitech.content.recipe.builder;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.stln.magitech.content.recipe.SingleLodestoneInWorldRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SingleLodestoneInWorldRecipeBuilder<RECIPE extends SingleLodestoneInWorldRecipe> extends AbstractRecipeBuilder<RECIPE> {
    private final SingleLodestoneInWorldRecipe.Factory<RECIPE> factory;
    private final ItemStack result;

    public SingleLodestoneInWorldRecipeBuilder(String prefix, SingleLodestoneInWorldRecipe.Factory<RECIPE> factory, ItemStack result) {
        super(prefix);
        this.factory = factory;
        this.result = result;
    }

    private String group = "";
    private SizedIngredient ingredient;

    public SingleLodestoneInWorldRecipeBuilder<RECIPE> group(@NotNull String name) {
        this.group = name;
        return this;
    }

    public SingleLodestoneInWorldRecipeBuilder<RECIPE> ingredient(@NotNull SizedIngredient ingredient) {
        this.ingredient = ingredient;
        return this;
    }

    @Override
    protected @Nullable ResourceLocation getRecipeId() {
        return result.getItemHolder().unwrapKey().orElseThrow().location();
    }

    @Override
    protected @NotNull RECIPE createRecipe() {
        return factory.create(group, ingredient, result);
    }
}
