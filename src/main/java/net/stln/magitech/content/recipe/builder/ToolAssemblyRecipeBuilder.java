package net.stln.magitech.content.recipe.builder;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.stln.magitech.content.recipe.ToolAssemblyRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ToolAssemblyRecipeBuilder extends AbstractRecipeBuilder<ToolAssemblyRecipe> {
    private final ItemStack result;

    public ToolAssemblyRecipeBuilder(ItemStack result) {
        super("tool_assemble");
        this.result = result;
    }

    private String group = "";
    private final List<Ingredient> ingredients = new ArrayList<>();

    public ToolAssemblyRecipeBuilder group(@NotNull String name) {
        this.group = name;
        return this;
    }

    public ToolAssemblyRecipeBuilder ingredient(@NotNull Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    @Override
    protected @Nullable ResourceLocation getRecipeId() {
        return result.getItemHolder().unwrapKey().orElseThrow().location();
    }

    @Override
    protected @NotNull ToolAssemblyRecipe createRecipe() {
        return new ToolAssemblyRecipe(group, ingredients, result);
    }
}
