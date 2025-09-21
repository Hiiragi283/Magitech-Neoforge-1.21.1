package net.stln.magitech.compat.jei;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechBlocks;
import net.stln.magitech.init.MagitechDataComponents;
import net.stln.magitech.init.MagitechRecipes;
import net.stln.magitech.item.component.MaterialComponent;
import net.stln.magitech.recipe.PartCuttingRecipe;
import net.stln.magitech.recipe.ToolMaterialRecipe;
import net.stln.magitech.util.ClientHelper;

import org.jetbrains.annotations.NotNull;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;

public class PartCuttingRecipeCategory extends AbstractMagitechRecipeCategory<PartCuttingRecipe> {

    public static final ResourceLocation UID = Magitech.id("part_cutting");
    public static final ResourceLocation TEXTURE = Magitech.id("textures/gui/jei_widgets.png");

    public static final RecipeType<PartCuttingRecipe> PART_CUTTING_RECIPE_TYPE =
            new RecipeType<>(UID, PartCuttingRecipe.class);

    public PartCuttingRecipeCategory(IGuiHelper helper) {
        super(helper, MagitechBlocks.ENGINEERING_WORKBENCH);
    }

    @Override
    public @NotNull RecipeType<PartCuttingRecipe> getRecipeType() {
        return PART_CUTTING_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipe.magitech.part_cutting");
    }

    @Override
    public void draw(
            @NotNull PartCuttingRecipe recipe,
            @NotNull IRecipeSlotsView recipeSlotsView,
            @NotNull GuiGraphics guiGraphics,
            double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        guiGraphics.blit(TEXTURE, 18, 4, 0, 0, 18, 18);
        guiGraphics.blit(TEXTURE, 40, 8, 0, 18, 21, 10);
        guiGraphics.blit(TEXTURE, 65, 4, 36, 0, 18, 18);
    }

    @Override
    public int getWidth() {
        return 101;
    }

    @Override
    public int getHeight() {
        return 26;
    }

    @Override
    protected void setRecipe(
            @NotNull IRecipeLayoutBuilder builder,
            @NotNull PartCuttingRecipe recipe,
            @NotNull IFocusGroup focuses,
            @NotNull RecipeManager recipeManager,
            @NotNull RegistryAccess access) {
        List<ToolMaterialRecipe> materialRecipes =
                ClientHelper.getAllRecipes(MagitechRecipes.TOOL_MATERIAL_TYPE);
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 5)
                .addItemStacks(
                        materialRecipes.stream()
                                .map(ToolMaterialRecipe::getIngredients)
                                .flatMap(NonNullList::stream)
                                .map(Ingredient::getItems)
                                .flatMap(Arrays::stream)
                                .toList());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 66, 5)
                .addItemStacks(
                        materialRecipes.stream()
                                .map(
                                        recipe1 -> {
                                            var stack = recipe1.getResultItem(access).copy();
                                            stack.set(
                                                    MagitechDataComponents.MATERIAL_COMPONENT,
                                                    new MaterialComponent(
                                                            recipe1.getToolMaterial()));

                                            return stack;
                                        })
                                .filter(Predicate.not(ItemStack::isEmpty))
                                .toList());
    }
}
