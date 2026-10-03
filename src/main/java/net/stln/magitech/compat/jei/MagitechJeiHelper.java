package net.stln.magitech.compat.jei;

import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.stln.magitech.compat.jei.ingredient.JeiSpellIngredient;
import net.stln.magitech.compat.jei.ingredient.SpellIngredientHelper;
import net.stln.magitech.compat.jei.ingredient.SpellIngredientRenderer;
import net.stln.magitech.feature.magic.spell.ISpell;
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

    @SuppressWarnings("NonExtendableApiUsage")
    public static <T extends IRecipeSlotBuilder> @NotNull T setIngredients(@NotNull T acceptor, @NotNull HolderSet<ISpell> spells) {
        acceptor.addIngredients(SpellIngredientHelper.TYPE, spells.stream().map(JeiSpellIngredient::new).toList());
        acceptor.setCustomRenderer(SpellIngredientHelper.TYPE, SpellIngredientRenderer.DEFAULT);
        return acceptor;
    }
}
