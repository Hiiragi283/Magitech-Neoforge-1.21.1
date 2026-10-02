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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.stln.magitech.Magitech;
import net.stln.magitech.compat.jei.JeiRecipeTypeInit;
import net.stln.magitech.content.block.BlockInit;
import net.stln.magitech.content.item.component.ComponentInit;
import net.stln.magitech.content.item.component.MaterialComponent;
import net.stln.magitech.content.recipe.PartCuttingRecipe;
import net.stln.magitech.content.recipe.RecipeInit;
import net.stln.magitech.content.recipe.ToolMaterialRecipe;
import net.stln.magitech.feature.tool.material.MaterialInit;
import net.stln.magitech.helper.ClientHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PartCuttingRecipeCategory extends AbstractMagitechRecipeCategory<PartCuttingRecipe> {
    public static final ResourceLocation TEXTURE = Magitech.id("textures/gui/jei/part_cutting_recipe.png");

    public PartCuttingRecipeCategory(IDrawable icon) {
        super(JeiRecipeTypeInit.PART_CUTTING_TYPE, icon);
    }

    public PartCuttingRecipeCategory(IGuiHelper helper) {
        this(helper.createDrawableItemLike(BlockInit.ENGINEERING_WORKBENCH));
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipe.magitech.part_cutting");
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
    protected void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull PartCuttingRecipe recipe, @NotNull IFocusGroup focuses) {
        List<ToolMaterialRecipe> materialRecipes = ClientHelper.getAllRecipes(RecipeInit.TOOL_MATERIAL_TYPE).stream().map(RecipeHolder::value).toList();
        List<ItemStack> inputs = new ArrayList<>();
        for (ToolMaterialRecipe materialRecipe : materialRecipes) {
            Ingredient ingredient = materialRecipe.getIngredients().getFirst();
            for (ItemStack itemStack : ingredient.getItems()) {
                if (itemStack.isEmpty()) continue;
                inputs.add(itemStack.copyWithCount(recipe.inputCount()));
            }
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 16, 16).addItemStacks(inputs);

        ItemStack resultStack = recipe.result().copy();
        resultStack.set(ComponentInit.MATERIAL_COMPONENT, new MaterialComponent(MaterialInit.SAMPLE));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 16).addItemStack(resultStack);
    }

    @Override
    public void draw(@NotNull PartCuttingRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        guiGraphics.blit(TEXTURE, 0, 0, 0, 0, 112, 50);
    }
}
