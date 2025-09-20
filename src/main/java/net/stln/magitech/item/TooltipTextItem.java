package net.stln.magitech.item;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import org.jetbrains.annotations.NotNull;

public class TooltipTextItem extends Item {

    public TooltipTextItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @NotNull TooltipContext context,
            List<Component> tooltipComponents,
            @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(
                Component.translatable(
                                "tooltip.item." + stack.getItem().toString().replace(":", "."))
                        .withColor(0x808080));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
