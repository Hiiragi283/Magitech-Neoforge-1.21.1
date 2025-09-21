package net.stln.magitech.datagen.server.recipe;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.stln.magitech.data.recipe.SpellConversionRecipeBuilder;
import net.stln.magitech.init.MagitechItems;
import net.stln.magitech.init.MagitechSpells;
import net.stln.magitech.tag.MagitechTags;

import org.jetbrains.annotations.NotNull;

class SpellConversionRecipeProvider {
    static void register(@NotNull RecipeOutput recipeOutput) {
        // Alchaefabric
        SpellConversionRecipeBuilder.create(MagitechItems.ALCHAEFABRIC, 1)
                .addIngredient(Items.PHANTOM_MEMBRANE)
                .setSpell(MagitechSpells.ENERCRUX)
                .save(recipeOutput);
        // Mana-Charged Fluorite
        SpellConversionRecipeBuilder.create(MagitechItems.MANA_CHARGED_FLUORITE, 1)
                .addIngredient(MagitechTags.Items.GEMS_FLUORITE)
                .setSpell(MagitechSpells.ENERCRUX)
                .save(recipeOutput);
    }
}
