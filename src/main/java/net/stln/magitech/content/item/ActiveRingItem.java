package net.stln.magitech.content.item;

import net.minecraft.world.item.ItemStack;
import net.stln.magitech.content.item.tooltip_item.TooltipTextItem;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public abstract class ActiveRingItem extends TooltipTextItem implements ICurioItem {
    public ActiveRingItem(Properties settings) {
        super(settings);
    }

    public void tick(SlotContext slotContext, ItemStack stack) {
        // Override in subclasses for ticking behavior
    }

    public void onDamageEntity(SlotContext slotContext, ItemStack stack) {
        // Override in subclasses for damage behavior
    }

    public void onKillEntity(SlotContext slotContext, ItemStack stack) {
        // Override in subclasses for kill behavior
    }

    public void onHurtPlayer(SlotContext slotContext, ItemStack stack) {
        // Override in subclasses for hurt behavior
    }
}
