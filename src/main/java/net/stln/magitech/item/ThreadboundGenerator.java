package net.stln.magitech.item;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.stln.magitech.init.MagitechDataComponents;
import net.stln.magitech.item.component.SpellComponent;
import net.stln.magitech.item.component.ThreadPageComponent;
import net.stln.magitech.magic.spell.SpellLike;

public class ThreadboundGenerator {

    public static ItemStack generateThreadbound(ItemLike item, List<SpellLike> holderSet) {
        ItemStack stack = new ItemStack(item);
        stack.set(MagitechDataComponents.SPELL_COMPONENT, new SpellComponent(holderSet));
        return stack;
    }

    public static ItemStack generateThreadPage(SpellLike holder) {
        ItemStack stack = ItemInit.THREAD_PAGE.toStack();
        stack.set(MagitechDataComponents.THREAD_PAGE_COMPONENT, new ThreadPageComponent(holder));
        return stack;
    }
}
