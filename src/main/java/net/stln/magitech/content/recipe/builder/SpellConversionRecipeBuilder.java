package net.stln.magitech.content.recipe.builder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.stln.magitech.content.recipe.SpellConversionRecipe;
import net.stln.magitech.feature.magic.spell.ISpell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpellConversionRecipeBuilder extends AbstractRecipeBuilder<SpellConversionRecipe> {
    private final ItemStack result;

    public SpellConversionRecipeBuilder(ItemStack result) {
        super("spell_conversion");
        this.result = result;
    }

    private String group = "";
    private Ingredient ingredient;
    private HolderSet<ISpell> spells;

    public SpellConversionRecipeBuilder group(@NotNull String name) {
        this.group = name;
        return this;
    }

    public SpellConversionRecipeBuilder ingredient(@NotNull Ingredient ingredient) {
        this.ingredient = ingredient;
        return this;
    }

    @SafeVarargs
    public final SpellConversionRecipeBuilder spells(@NotNull Holder<ISpell>... spells) {
        return spells(HolderSet.direct(spells));
    }
    
    public final SpellConversionRecipeBuilder spells(@NotNull TagKey<ISpell> tagKey, @NotNull HolderLookup.Provider provider) {
        return spells(provider.lookupOrThrow(tagKey.registry()).getOrThrow(tagKey));
    }

    public SpellConversionRecipeBuilder spells(@NotNull HolderSet<ISpell> spells) {
        this.spells = spells;
        return this;
    }

    @Override
    protected @Nullable ResourceLocation getRecipeId() {
        return result.getItemHolder().unwrapKey().orElseThrow().location();
    }

    @Override
    protected @NotNull SpellConversionRecipe createRecipe() {
        return new SpellConversionRecipe(group, ingredient, spells, result);
    }
}
