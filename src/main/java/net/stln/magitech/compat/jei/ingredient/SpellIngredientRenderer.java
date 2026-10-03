package net.stln.magitech.compat.jei.ingredient;

import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpellIngredientRenderer implements IIngredientRenderer<JeiSpellIngredient> {
    public static final SpellIngredientRenderer DEFAULT = new SpellIngredientRenderer(32, 32);
    
    private final int width;
    private final int height;
    
    public SpellIngredientRenderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, @NotNull JeiSpellIngredient ingredient) {
        this.render(guiGraphics, ingredient, 0, 0);
    }

    @Override
    public void render(GuiGraphics guiGraphics, JeiSpellIngredient ingredient, int posX, int posY) {
        guiGraphics.blit(ingredient.spell().getIconId(), posX, posY, 0, 0, getWidth(), getHeight(), getWidth(), getHeight());
    }

    @SuppressWarnings("removal")
    @Override
    public @NotNull List<Component> getTooltip(@NotNull JeiSpellIngredient ingredient, @NotNull TooltipFlag tooltipFlag) {
        return List.of(ingredient.spell().getName());
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
