package net.stln.magitech.content.item;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.stln.magitech.content.item.component.ComponentInit;
import net.stln.magitech.content.item.component.SpellComponent;
import net.stln.magitech.content.item.component.ThreadPageComponent;
import net.stln.magitech.feature.magic.spell.ISpell;
import org.jetbrains.annotations.NotNull;

public class ThreadboundGenerator {
    public static @NotNull ItemStack generateThreadbound(@NotNull ItemLike item, @NotNull HolderSet<ISpell> spells) {
        ItemStack stack = new ItemStack(item);
        stack.set(ComponentInit.SPELL_COMPONENT, new SpellComponent(spells));
        return stack;
    }

    public static @NotNull ItemStack generateThreadPage(@NotNull Holder<ISpell> holder) {
        ItemStack stack = ItemInit.THREAD_PAGE.toStack();
        stack.set(ComponentInit.THREAD_PAGE_COMPONENT, new ThreadPageComponent(holder));
        return stack;
    }
}
