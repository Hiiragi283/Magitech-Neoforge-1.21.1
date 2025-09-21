package net.stln.magitech.compat.jei;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechBlocks;
import net.stln.magitech.recipe.ZardiusCrucibleRecipe;
import net.stln.magitech.util.ClientHelper;

import org.jetbrains.annotations.NotNull;

import com.mojang.datafixers.util.Pair;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;

public class ZardiusCrucibleRecipeCategory
        extends AbstractMagitechRecipeCategory<ZardiusCrucibleRecipe> {

    public static final ResourceLocation UID = Magitech.id("recipe.magitech.zardius_crucible");
    public static final ResourceLocation TEXTURE = Magitech.id("textures/gui/jei_widgets.png");

    public static final RecipeType<ZardiusCrucibleRecipe> ZARDIUS_CRUCIBLE_RECIPE_TYPE =
            new RecipeType<>(UID, ZardiusCrucibleRecipe.class);

    public ZardiusCrucibleRecipeCategory(IGuiHelper helper) {
        super(helper, MagitechBlocks.ZARDIUS_CRUCIBLE);
    }

    @Override
    public @NotNull RecipeType<ZardiusCrucibleRecipe> getRecipeType() {
        return ZARDIUS_CRUCIBLE_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipe.magitech.zardius_crucible");
    }

    @Override
    public void draw(
            @NotNull ZardiusCrucibleRecipe recipe,
            @NotNull IRecipeSlotsView recipeSlotsView,
            @NotNull GuiGraphics guiGraphics,
            double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        int size = recipe.getIngredients().size();
        for (int i = 0; i < size; i++) {
            Pair<Integer, Integer> pair = getSlotPosition(i, size);
            guiGraphics.blit(TEXTURE, pair.getFirst(), pair.getSecond(), 0, 0, 18, 18);
        }
        guiGraphics.blit(TEXTURE, 73, 13, 18, 0, 18, 18);
        guiGraphics.blit(TEXTURE, 95, 17, 0, 18, 21, 10);
        var access = ClientHelper.getRegistryAccess();
        if (access == null) return;
        if (!recipe.getResultItem(access).isEmpty()) {
            guiGraphics.blit(TEXTURE, 120, 13, 36, 0, 18, 18);
        }
        if (recipe.resultFluid().isPresent()) {
            guiGraphics.blit(TEXTURE, 138, 13, 54, 0, 18, 18);
        }
    }

    @Override
    public int getWidth() {
        return 155;
    }

    @Override
    public int getHeight() {
        return 44;
    }

    @Override
    protected void setRecipe(
            @NotNull IRecipeLayoutBuilder builder,
            @NotNull ZardiusCrucibleRecipe recipe,
            @NotNull IFocusGroup focuses,
            @NotNull RecipeManager recipeManager,
            @NotNull RegistryAccess access) {
        List<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            Pair<Integer, Integer> pair = getSlotPosition(i, ingredients.size());
            builder.addSlot(RecipeIngredientRole.INPUT, pair.getFirst() + 1, pair.getSecond() + 1)
                    .addIngredients(ingredients.get(i));
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 74, 14)
                .addIngredients(
                        NeoForgeTypes.FLUID_STACK,
                        Arrays.stream(recipe.fluidIngredient().getFluids()).toList())
                .addRichTooltipCallback(FLUID_TOOLTIP);
        if (!recipe.getResultItem(access).isEmpty()) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 121, 14)
                    .addItemStack(recipe.getResultItem(access));
        }
        recipe.resultFluid()
                .ifPresent(
                        fluidStack ->
                                builder.addSlot(RecipeIngredientRole.INPUT, 139, 14)
                                        .addIngredient(NeoForgeTypes.FLUID_STACK, fluidStack)
                                        .addRichTooltipCallback(FLUID_TOOLTIP));
    }

    private static final IRecipeSlotRichTooltipCallback FLUID_TOOLTIP =
            (recipeSlotView, tooltip) ->
                    recipeSlotView
                            .getDisplayedIngredient(NeoForgeTypes.FLUID_STACK)
                            .ifPresent(
                                    fluid -> {
                                        int amount = fluid.getAmount();
                                        // mB単位で表示
                                        tooltip.add(
                                                Component.literal(amount + " mB")
                                                        .withColor(0x808080));
                                    });

    private static @NotNull Pair<Integer, Integer> getSlotPosition(int index, int size) {
        int x, y;

        if (size <= 3) {
            // 横1列（中央寄せ、Y + 9）
            int totalWidth = size * 18;
            int startX = 19 + (36 - totalWidth) / 2; // 中央寄せ（基準幅36）
            x = startX + index * 18;
            y = 4 + 9;
        } else if (size == 4) {
            // 2x2 グリッド（中央寄せ）
            int row = index / 2;
            int col = index % 2;
            x = 19 + col * 18; // 横方向中央に調整
            y = 4 + row * 18;
        } else if (size < 7) {
            // 2列3行（左→右→下に）
            int row = index / 3;
            int col = index % 3;
            int totalWidth = 3 * 18;
            x = 19 + col * 18 + (36 - totalWidth) / 2;
            y = 4 + row * 18;
        } else {
            // 2列で縦並び（なるべく均等）
            int row = index / 4;
            int col = index % 4;
            int totalWidth = 4 * 18;
            x = 19 + col * 18 + (36 - totalWidth) / 2;
            y = 4 + row * 18;
        }
        return Pair.of(x, y);
    }
}
