package net.stln.magitech.item.armor;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import org.jetbrains.annotations.NotNull;

public class TooltipArmorItem extends ArmorItem {

    public TooltipArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @NotNull TooltipContext context,
            List<Component> tooltipComponents,
            @NotNull TooltipFlag tooltipFlag) {
        stack.getItemHolder()
                .unwrapKey()
                .map(ResourceKey::location)
                .map(id -> id.toLanguageKey("tooltip.item"))
                .map(Component::translatable)
                .map(text -> text.withColor(0x808080))
                .ifPresent(tooltipComponents::add);
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
