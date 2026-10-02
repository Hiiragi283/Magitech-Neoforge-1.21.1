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
import net.minecraft.world.item.ItemStack;
import net.stln.magitech.Magitech;
import net.stln.magitech.compat.jei.JeiRecipeTypeInit;
import net.stln.magitech.content.item.ItemInit;
import net.stln.magitech.content.recipe.SpellConversionRecipe;
import net.stln.magitech.helper.ComponentHelper;
import org.jetbrains.annotations.NotNull;

public class SpellConversionRecipeCategory extends AbstractMagitechRecipeCategory<SpellConversionRecipe> {
    public static final ResourceLocation TEXTURE = Magitech.id("textures/gui/jei/spell_conversion_recipe.png");

    public SpellConversionRecipeCategory(IDrawable icon) {
        super(JeiRecipeTypeInit.SPELL_CONVERSION_TYPE, icon);
    }

    public SpellConversionRecipeCategory(IGuiHelper helper) {
        this(helper.createDrawableItemLike(ItemInit.WAND));
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipe.magitech.spell_conversion");
    }

    @Override
    public int getWidth() {
        return 144;
    }

    @Override
    public int getHeight() {
        return 82;
    }

    @Override
    protected void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull SpellConversionRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 16, 16).addIngredients(recipe.ingredient());

        ItemStack threadPage = new ItemStack(ItemInit.THREAD_PAGE.get());
        ComponentHelper.setThreadPage(threadPage, recipe.spell());
        builder.addSlot(RecipeIngredientRole.CATALYST, 32, 48).addItemStack(threadPage);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 112, 16).addItemStack(recipe.result());
    }

    @Override
    public void draw(@NotNull SpellConversionRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        guiGraphics.blit(TEXTURE, 0, 0, 0, 0, 144, 82);
        guiGraphics.blit(recipe.spell().getIconId(), 56, 40, 0, 0, 32, 32, 32, 32);
    }
}
