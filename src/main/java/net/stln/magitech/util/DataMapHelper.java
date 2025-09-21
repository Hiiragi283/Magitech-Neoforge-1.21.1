package net.stln.magitech.util;

import java.util.Optional;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.stln.magitech.data.DataMapTypeInit;
import net.stln.magitech.data.EntityElementData;
import net.stln.magitech.element.Element;
import net.stln.magitech.element.ElementAffinity;
import net.stln.magitech.element.ElementAffinityRegister;

import org.jetbrains.annotations.NotNull;

public class DataMapHelper {

    public static @NotNull Optional<ElementAffinity> getElementAffinity(
            @NotNull Entity target, @NotNull Element element) {
        EntityElementData elementData =
                BuiltInRegistries.ENTITY_TYPE
                        .wrapAsHolder(target.getType())
                        .getData(DataMapTypeInit.ENTITY_ELEMENT);
        if (elementData != null) {
            var targetElement = elementData.element();
            return Optional.of(ElementAffinityRegister.getElementAffinity(element, targetElement));
        }
        return Optional.empty();
    }

    public static float getElementMultiplier(@NotNull Entity target, @NotNull Element element) {
        return getElementAffinity(target, element).map(ElementAffinity::getMultiplier).orElse(1f);
    }
}
