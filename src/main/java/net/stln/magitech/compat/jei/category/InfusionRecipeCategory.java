package net.stln.magitech.compat.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.stln.magitech.Magitech;
import net.stln.magitech.compat.jei.JeiRecipeTypeInit;
import net.stln.magitech.compat.jei.MagitechJeiHelper;
import net.stln.magitech.content.block.BlockInit;
import net.stln.magitech.content.recipe.InfusionRecipe;
import net.stln.magitech.helper.EnergyFormatter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InfusionRecipeCategory extends AbstractMagitechRecipeCategory<InfusionRecipe> {
    public static final ResourceLocation TEXTURE = Magitech.id("textures/gui/jei/infusion_recipe.png");
    public static final ResourceLocation WIDGETS = Magitech.id("textures/gui/jei/jei_widgets.png");
    protected static long GAUGE_MAX_MANA = 100000; // 表示用の最大マナ量

    public InfusionRecipeCategory(IDrawable icon) {
        super(JeiRecipeTypeInit.INFUSION_TYPE, icon);
    }

    public InfusionRecipeCategory(IGuiHelper helper) {
        this(helper.createDrawableItemLike(BlockInit.INFUSION_ALTAR));
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipe.magitech.infusion");
    }

    @Override
    public int getWidth() {
        return 128;
    }

    @Override
    public int getHeight() {
        return 154;
    }

    @Override
    protected void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull InfusionRecipe recipe, @NotNull IFocusGroup focuses) {
        List<SizedIngredient> ingredients = recipe.getSizedIngredients();
        int size = ingredients.size();

        builder.addSlot(RecipeIngredientRole.INPUT, 56, 32).addItemStacks(MagitechJeiHelper.getDisplayStacks(recipe.getBase()));

        for (int i = 0; i < size; i++) {
            int x = 16, y = 68 + i * 17;
            y -= (size - 1) * 17 / 2; // 中央寄せのためにX座標を調整

            builder.addSlot(RecipeIngredientRole.INPUT, x, y).addItemStacks(MagitechJeiHelper.getDisplayStacks(ingredients.get(i)));
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 56, 120).addItemStack(recipe.output);
    }

    @Override
    public void draw(@NotNull InfusionRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        int size = recipe.getSizedIngredients().size();
        long mana = recipe.getMana();

        guiGraphics.blit(TEXTURE, 0, 0, 0, 0, 128, 154);

        for (int i = 0; i < size; i++) {
            int x = 15, y = 67 + i * 17;
            y -= (size - 1) * 17 / 2; // 中央寄せのためにX座標を調整

            guiGraphics.blit(WIDGETS, x, y, 0, 0, 18, 20);
        }

        int height = (int) (Math.min((double) mana / GAUGE_MAX_MANA * 72, 72));
        guiGraphics.blit(TEXTURE, 96, 40 + 72 - height, 128, 0, 16, height);
    }

    @Override
    public void getTooltip(@NotNull ITooltipBuilder tooltip, @NotNull InfusionRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        super.getTooltip(tooltip, recipe, recipeSlotsView, mouseX, mouseY);
        if (mouseX >= 96 && mouseX <= 112 && mouseY >= 40 && mouseY <= 112) {
            tooltip.add(Component.translatable("recipe.magitech.required_mana").append(Component.literal(": " + EnergyFormatter.formatValue(recipe.getMana()))).withColor(0xcdffde));
        }
    }
}
