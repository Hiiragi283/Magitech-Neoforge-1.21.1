package net.stln.magitech.data;

import net.stln.magitech.element.Element;

import com.mojang.serialization.Codec;

public record EntityElementData(Element element) {

    public static final Codec<EntityElementData> CODEC =
            Element.CODEC.xmap(EntityElementData::new, EntityElementData::element);
}
