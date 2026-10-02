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
import net.stln.magitech.content.block.BlockInit;
import net.stln.magitech.content.recipe.CompressingRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CompressingRecipeCategory extends AbstractMagitechRecipeCategory<CompressingRecipe> {
    public static final ResourceLocation TEXTURE = Magitech.id("textures/gui/jei/compressing_recipe.png");

    public CompressingRecipeCategory(IDrawable icon) {
        super(JeiRecipeTypeInit.COMPRESSING_TYPE, icon);
    }

    public CompressingRecipeCategory(IGuiHelper helper) {
        this(helper.createDrawableItemLike(BlockInit.COMPRESSOR));
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipe.magitech.compressing");
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
    protected void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull CompressingRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 16, 16).addItemStacks(List.of(recipe.getSizedIngredient().getItems()));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 16).addItemStack(recipe.output);
    }

    @Override
    public void draw(@NotNull CompressingRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        guiGraphics.blit(TEXTURE, 0, 0, 0, 0, 112, 50);
    }
}
