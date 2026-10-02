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
import net.stln.magitech.Magitech;
import net.stln.magitech.compat.jei.JeiRecipeTypeInit;
import net.stln.magitech.content.block.BlockInit;
import net.stln.magitech.content.item.component.ComponentInit;
import net.stln.magitech.content.item.component.MaterialComponent;
import net.stln.magitech.content.item.component.PartMaterialComponent;
import net.stln.magitech.content.recipe.ToolAssemblyRecipe;
import net.stln.magitech.feature.tool.material.MaterialInit;
import net.stln.magitech.feature.tool.material.ToolMaterial;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToolAssemblyRecipeCategory extends AbstractMagitechRecipeCategory<ToolAssemblyRecipe> {
    public static final ResourceLocation TEXTURE = Magitech.id("textures/gui/jei/tool_assembly_recipe.png");
    public static final ResourceLocation WIDGETS = Magitech.id("textures/gui/jei/jei_widgets.png");

    public ToolAssemblyRecipeCategory(IDrawable icon) {
        super(JeiRecipeTypeInit.TOOL_ASSEMBLY_TYPE, icon);
    }

    public ToolAssemblyRecipeCategory(IGuiHelper helper) {
        this(helper.createDrawableItemLike(BlockInit.ASSEMBLY_WORKBENCH));
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipe.magitech.tool_assembly");
    }

    @Override
    public int getWidth() {
        return 128;
    }

    @Override
    public int getHeight() {
        return 122;
    }

    @Override
    protected void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull ToolAssemblyRecipe recipe, @NotNull IFocusGroup focuses) {
        List<Ingredient> ingredients = recipe.getIngredients();

        List<ItemStack> results = new ArrayList<>();
        List<ItemStack> parts = new ArrayList<>();
        List<ToolMaterial> toolMaterials = new ArrayList<>();

        for (Ingredient value : ingredients) {

            ItemStack partStack = value.getItems()[0].copy(); // NOTE: 複数アイテムある場合は適宜対応
            partStack.set(ComponentInit.MATERIAL_COMPONENT, new MaterialComponent(MaterialInit.SAMPLE));
            parts.add(partStack);
            toolMaterials.add(MaterialInit.SAMPLE.get());
        }

        // 完成品 ItemStack を生成
        ItemStack resultStack = recipe.result.copy();
        resultStack.set(ComponentInit.PART_MATERIAL_COMPONENT, new PartMaterialComponent(toolMaterials));
        results.add(resultStack);

        List<ItemStack> partInputStacks = new ArrayList<>();

        for (Ingredient ingredient : recipe.getIngredients()) {

            ItemStack base = ingredient.getItems()[0].copy();
            base.set(ComponentInit.MATERIAL_COMPONENT, new MaterialComponent(MaterialInit.SAMPLE));

            partInputStacks.add(base);
        }

        // 入力スロット
        int partSize = recipe.getIngredients().size();

        for (int i = 0; i < partSize; i++) {
            int x = 55 + i * 17, y = 15;
            x -= (partSize - 1) * 17 / 2; // 中央寄せのためにX座標を調整

            builder.addSlot(RecipeIngredientRole.INPUT, x + 1, y + 1).addItemStack(partInputStacks.get(i));
        }

        // 出力スロット（すべての組み合わせ）
        Collections.shuffle(results);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 56, 88).addItemStacks(results);
    }

    @Override
    public void draw(@NotNull ToolAssemblyRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        int partCount = recipe.getIngredients().size();

        guiGraphics.blit(TEXTURE, 0, 0, 0, 0, 128, 122);

        for (int i = 0; i < partCount; i++) {
            int x = 55 + i * 17, y = 15;
            x -= (partCount - 1) * 17 / 2; // 中央寄せのためにX座標を調整

            guiGraphics.blit(WIDGETS, x, y, 0, 0, 18, 20);
        }
    }
}
