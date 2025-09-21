package net.stln.magitech.util;

import net.minecraft.world.entity.Entity;
import net.stln.magitech.element.Element;

import org.jetbrains.annotations.NotNull;

public class ElementHelper {
    public static float getElementalDamageValue(
            float base, @NotNull Entity entity, @NotNull Element element) {
        return base * DataMapHelper.getElementMultiplier(entity, element);
    }
}
