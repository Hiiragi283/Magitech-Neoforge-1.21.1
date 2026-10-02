package net.stln.magitech.compat.jei;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.List;

public final class MagitechJeiHelper {
    private MagitechJeiHelper() {
    }

    public static @Unmodifiable @NotNull List<FluidStack> getDisplayStacks(@Nullable SizedFluidIngredient ingredient) {
        if (ingredient == null) {
            return List.of();
        } else {
            return Arrays.stream(ingredient.getFluids()).map(FluidStack::copy).toList();
        }
    }
    
    public static @Unmodifiable @NotNull List<ItemStack> getDisplayStacks(@Nullable SizedIngredient ingredient) {
        if (ingredient == null) {
            return List.of();
        } else {
            return Arrays.stream(ingredient.getItems()).map(ItemStack::copy).toList();
        }
    }
}
