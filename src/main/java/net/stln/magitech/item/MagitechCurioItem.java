package net.stln.magitech.item;

import java.util.Map;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class MagitechCurioItem extends TooltipTextItem implements ICurioItem {
    private final Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMap;

    public MagitechCurioItem(
            Map<Holder<Attribute>, AttributeModifier> attributeModifierMap, Properties properties) {
        this(Multimaps.forMap(attributeModifierMap), properties);
    }

    public MagitechCurioItem(
            Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMap,
            Properties properties) {
        super(properties);
        this.attributeModifierMap = attributeModifierMap;
    }

    @Override
    public final Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(
            SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        return ImmutableMultimap.copyOf(attributeModifierMap);
    }
}
