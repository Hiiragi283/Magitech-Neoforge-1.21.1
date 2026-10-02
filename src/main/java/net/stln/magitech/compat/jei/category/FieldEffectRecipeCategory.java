package net.stln.magitech.compat.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.stln.magitech.Magitech;
import net.stln.magitech.compat.jei.JeiRecipeTypeInit;
import net.stln.magitech.compat.jei.ingredient.FETIngredientHelper;
import net.stln.magitech.content.block.BlockInit;
import net.stln.magitech.content.recipe.FieldEffectRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class FieldEffectRecipeCategory extends AbstractMagitechRecipeCategory<FieldEffectRecipe> {
    public static final ResourceLocation TEXTURE = Magitech.id("textures/gui/jei/field_effect_recipe.png");

    public FieldEffectRecipeCategory(IDrawable icon) {
        super(JeiRecipeTypeInit.FIELD_EFFECT_TYPE, icon);
    }

    public FieldEffectRecipeCategory(IGuiHelper helper) {
        this(helper.createDrawableItemLike(BlockInit.ENVIROMETER));
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipe.magitech.field_effect");
    }

    @Override
    public int getWidth() {
        return 112;
    }

    @Override
    public int getHeight() {
        return 74;
    }

    @Override
    protected void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull FieldEffectRecipe recipe, @NotNull IFocusGroup focuses) {
        recipe.getIngredient().ifPresent(ingredient -> addItemInput(builder, ingredient));
        recipe.getFluidIngredient().ifPresent(ingredient -> builder.addSlot(RecipeIngredientRole.INPUT, 16, 16)
                .addIngredients(NeoForgeTypes.FLUID_STACK, Arrays.stream(ingredient.getFluids()).toList()));

        builder.addSlot(RecipeIngredientRole.INPUT, 48, 40).addIngredient(FETIngredientHelper.TYPE, recipe.getFieldEffect());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 16).addItemStacks(recipe.getResults());
        recipe.getFluidResult().ifPresent(fluid -> builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 16)
                .addIngredient(NeoForgeTypes.FLUID_STACK, fluid));
    }

    private static void addItemInput(IRecipeLayoutBuilder builder, SizedIngredient ingredient) {
        builder.addSlot(RecipeIngredientRole.INPUT, 16, 16).addItemStacks(List.of(ingredient.getItems()));
    }

    @Override
    public void draw(@NotNull FieldEffectRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        guiGraphics.blit(TEXTURE, 0, 0, 0, 0, 112, 74);
    }
}
