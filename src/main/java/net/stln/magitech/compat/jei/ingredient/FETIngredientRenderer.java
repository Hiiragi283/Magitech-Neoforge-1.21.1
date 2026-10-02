package net.stln.magitech.compat.jei.ingredient;

import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.TooltipFlag;
import net.stln.magitech.core.api.field_effect.FieldEffectType;
import net.stln.magitech.effect.visual.FieldEffectIconRenderer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public enum FETIngredientRenderer implements IIngredientRenderer<FieldEffectType> {
    INSTANCE;

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, @NotNull FieldEffectType ingredient) {
        ResourceLocation location = FETIngredientHelper.INSTANCE.getResourceLocation(ingredient);
        FieldEffectIconRenderer.render(guiGraphics, location, 0, 0, 16, 1.0F, 1.0F);
    }

    @SuppressWarnings({"removal", "NullableProblems"})
    @Override
    public List<Component> getTooltip(@NotNull FieldEffectType ingredient, @NotNull TooltipFlag tooltipFlag) {
        ResourceLocation location = FETIngredientHelper.INSTANCE.getResourceLocation(ingredient);
        return List.of(
                FieldEffectIconRenderer.getDisplayName(location),
                Component.translatable("gui.magitech.field_effect").withColor(0x808080)
        );
    }
}
