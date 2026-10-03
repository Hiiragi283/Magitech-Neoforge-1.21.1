package net.stln.magitech.content.recipe.input;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.feature.magic.spell.ISpell;
import org.jetbrains.annotations.NotNull;

public record SpellRecipeInput(@NotNull ItemStack item, @NotNull Holder<ISpell> spell) implements RecipeInput {
    public SpellRecipeInput(@NotNull ItemStack item, @NotNull ISpell spell) {
        this(item, MagitechRegistries.SPELL.wrapAsHolder(spell));
    }
    
    @Override
    public @NotNull ItemStack getItem(int index) {
        return item;
    }

    @Override
    public boolean isEmpty() {
        return item.isEmpty();
    }

    @Override
    public int size() {
        return 0;
    }
}
