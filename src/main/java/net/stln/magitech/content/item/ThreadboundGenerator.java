package net.stln.magitech.content.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.stln.magitech.content.item.component.ComponentInit;
import net.stln.magitech.content.item.component.SpellComponent;
import net.stln.magitech.content.item.component.ThreadPageComponent;
import net.stln.magitech.feature.magic.spell.SpellLike;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class ThreadboundGenerator {
    public static @NotNull ItemStack generateThreadbound(@NotNull Item item, @NotNull Collection<? extends SpellLike> spells) {
        ItemStack stack = new ItemStack(item);
        stack.set(ComponentInit.SPELL_COMPONENT, new SpellComponent(spells));
        return stack;
    }

    public static @NotNull ItemStack generateThreadPage(@NotNull SpellLike holder) {
        ItemStack stack = ItemInit.THREAD_PAGE.toStack();
        stack.set(ComponentInit.THREAD_PAGE_COMPONENT, new ThreadPageComponent(holder));
        return stack;
    }
}
