package net.stln.magitech.compat.jei.category;

import com.mojang.serialization.Codec;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.ICodecHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractMagitechRecipeCategory<RECIPE extends Recipe<?>> implements IRecipeCategory<RecipeHolder<RECIPE>> {
    private final @NotNull RecipeType<RecipeHolder<RECIPE>> recipeType;
    protected final @Nullable IDrawable icon;

    public AbstractMagitechRecipeCategory(@NotNull RecipeType<RecipeHolder<RECIPE>> recipeType, @Nullable IDrawable icon) {
        this.recipeType = recipeType;
        this.icon = icon;
    }

    @Override
    final public @NotNull RecipeType<RecipeHolder<RECIPE>> getRecipeType() {
        return recipeType;
    }

    @Override
    public abstract int getWidth();

    @Override
    public abstract int getHeight();

    @Override
    final public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    final public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull RecipeHolder<RECIPE> recipe, @NotNull IFocusGroup focuses) {
        setRecipe(builder, recipe.value(), focuses);
    }
    
    protected abstract void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull RECIPE recipe, @NotNull IFocusGroup focuses);

    @Override
    final public void draw(@NotNull RecipeHolder<RECIPE> recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        draw(recipe.value(), recipeSlotsView, guiGraphics, mouseX, mouseY);
    }
    
    public void draw(@NotNull RECIPE recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {}

    @Override
    final public void getTooltip(@NotNull ITooltipBuilder tooltip, @NotNull RecipeHolder<RECIPE> recipe, @NotNull IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        getTooltip(tooltip, recipe.value(), recipeSlotsView, mouseX, mouseY);
    }

    public void getTooltip(@NotNull ITooltipBuilder tooltip, @NotNull RECIPE recipe, @NotNull IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {}

    @Override
    final public @NotNull ResourceLocation getRegistryName(RecipeHolder<RECIPE> recipe) {
        return recipe.id();
    }

    @Override
    final public @NotNull Codec<RecipeHolder<RECIPE>> getCodec(@NotNull ICodecHelper codecHelper, @NotNull IRecipeManager recipeManager) {
        return codecHelper.getRecipeHolderCodec();
    }
}
