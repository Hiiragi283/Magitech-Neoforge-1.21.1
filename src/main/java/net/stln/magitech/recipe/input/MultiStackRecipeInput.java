package net.stln.magitech.recipe.input;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import org.jetbrains.annotations.NotNull;

public record MultiStackRecipeInput(List<ItemStack> stacks) implements RecipeInput {

    @Override
    public @NotNull ItemStack getItem(int index) {
        return stacks.get(index);
    }

    @Override
    public int size() {
        return this.stacks.size();
    }

    @Override
    public boolean isEmpty() {
        return this.stacks.isEmpty();
    }

    public int ingredientCount() {
        return (int) stacks.stream().filter(Predicate.not(ItemStack::isEmpty)).count();
    }

    public StackedContents stackedContents() {
        final StackedContents stackedContents = new StackedContents();

        for (ItemStack itemstack : stacks) {
            if (!itemstack.isEmpty()) {
                stackedContents.accountStack(itemstack, 1);
            }
        }
        return stackedContents;
    }
}
