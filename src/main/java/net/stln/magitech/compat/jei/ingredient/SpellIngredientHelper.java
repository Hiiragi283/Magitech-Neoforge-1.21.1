package net.stln.magitech.compat.jei.ingredient;

import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.stln.magitech.content.item.ItemInit;
import net.stln.magitech.helper.ComponentHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public enum SpellIngredientHelper implements IIngredientHelper<JeiSpellIngredient> {
    INSTANCE;

    public static final IIngredientType<JeiSpellIngredient> TYPE = new IIngredientType<>() {
        @Override
        public @NotNull Class<? extends JeiSpellIngredient> getIngredientClass() {
            return JeiSpellIngredient.class;
        }

        @Override
        public @NotNull String getUid() {
            return "spell";
        }
    };

    @Override
    public @NotNull IIngredientType<JeiSpellIngredient> getIngredientType() {
        return TYPE;
    }

    @Override
    public @NotNull String getDisplayName(JeiSpellIngredient ingredient) {
        return ingredient.spell().getName().getString();
    }

    @SuppressWarnings("removal")
    @Override
    public @NotNull String getUniqueId(@NotNull JeiSpellIngredient ingredient, @NotNull UidContext context) {
        return getResourceLocation(ingredient).toString();
    }

    @Override
    public @NotNull ResourceLocation getResourceLocation(JeiSpellIngredient ingredient) {
        return ingredient.holder().unwrapKey().map(ResourceKey::location).orElseThrow(() -> {
            var errorInfo = getErrorInfo(ingredient);
            return new IllegalStateException("Spell has no key in the Spell registry: %s".formatted(errorInfo));
        });
    }

    @Override
    public @NotNull ItemStack getCheatItemStack(@NotNull JeiSpellIngredient ingredient) {
        var stack = ItemInit.THREAD_PAGE.toStack();
        ComponentHelper.setThreadPage(stack, ingredient.holder());
        return stack;
    }

    @Override
    public @NotNull JeiSpellIngredient copyIngredient(@NotNull JeiSpellIngredient ingredient) {
        return new JeiSpellIngredient(ingredient.holder());
    }

    @Override
    public boolean isValidIngredient(@NotNull JeiSpellIngredient ingredient) {
        return ingredient.holder().unwrapKey().isPresent();
    }

    @Override
    public @NotNull Stream<ResourceLocation> getTagStream(@NotNull JeiSpellIngredient ingredient) {
        return ingredient.holder().tags().map(TagKey::location);
    }

    @Override
    public @NotNull String getErrorInfo(@Nullable JeiSpellIngredient ingredient) {
        return ingredient == null ? "null" : ingredient.toString();
    }
}
