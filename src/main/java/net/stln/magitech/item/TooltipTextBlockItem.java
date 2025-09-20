package net.stln.magitech.item;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import org.jetbrains.annotations.NotNull;

public class TooltipTextBlockItem extends BlockItem {

    public TooltipTextBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @NotNull TooltipContext context,
            List<Component> tooltipComponents,
            @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(
                Component.translatable(
                                "tooltip.block." + stack.getItem().toString().replace(":", "."))
                        .withColor(0x808080));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
