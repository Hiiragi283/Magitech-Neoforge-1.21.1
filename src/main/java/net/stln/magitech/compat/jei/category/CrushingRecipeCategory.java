package net.stln.magitech.compat.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.stln.magitech.Magitech;
import net.stln.magitech.compat.jei.JeiRecipeTypeInit;
import net.stln.magitech.compat.jei.MagitechJeiHelper;
import net.stln.magitech.content.block.BlockInit;
import net.stln.magitech.content.recipe.CrushingRecipe;
import org.jetbrains.annotations.NotNull;

public class CrushingRecipeCategory extends AbstractMagitechRecipeCategory<CrushingRecipe> {
    public static final ResourceLocation TEXTURE = Magitech.id("textures/gui/jei/crushing_recipe.png");

    public CrushingRecipeCategory(IDrawable icon) {
        super(JeiRecipeTypeInit.CRUSHING_TYPE, icon);
    }

    public CrushingRecipeCategory(IGuiHelper helper) {
        this(helper.createDrawableItemLike(BlockInit.CRUSHER));
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipe.magitech.crushing");
    }

    @Override
    public int getWidth() {
        return 112;
    }

    @Override
    public int getHeight() {
        return 50;
    }

    @Override
    protected void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull CrushingRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 16, 16).addItemStacks(MagitechJeiHelper.getDisplayStacks(recipe.getIngredient()));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 16).addItemStack(recipe.output);
    }

    @Override
    public void draw(@NotNull CrushingRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        guiGraphics.blit(TEXTURE, 0, 0, 0, 0, 112, 50);
    }
}
