package net.stln.magitech.compat.jei.ingredient;

import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.resources.ResourceLocation;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.core.api.field_effect.FieldEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum FETIngredientHelper implements IIngredientHelper<FieldEffectType> {
    INSTANCE;
    
    public static final IIngredientType<FieldEffectType> TYPE = new IIngredientType<>() {
        @Override
        public @NotNull Class<? extends FieldEffectType> getIngredientClass() {
            return FieldEffectType.class;
        }

        @Override
        public @NotNull String getUid() {
            return "field_effect_type";
        }
    };

    @Override
    public @NotNull IIngredientType<FieldEffectType> getIngredientType() {
        return TYPE;
    }

    @Override
    public @NotNull String getDisplayName(FieldEffectType ingredient) {
        return ingredient.getDisplayName().getString();
    }

    @SuppressWarnings({"removal", "NullableProblems"})
    @Override
    public String getUniqueId(@NotNull FieldEffectType ingredient, @NotNull UidContext context) {
        return "field_effect_type:%s".formatted(getResourceLocation(ingredient));
    }

    @Override
    public @NotNull ResourceLocation getResourceLocation(@NotNull FieldEffectType ingredient) {
        ResourceLocation location = MagitechRegistries.FIELD_EFFECT_TYPE.getKey(ingredient);
        if (location == null) {
            var errorInfo = getErrorInfo(ingredient);
            throw new IllegalStateException("null registry name for: %s".formatted(errorInfo));
        }
        return location;
    }

    @Override
    public @NotNull FieldEffectType copyIngredient(@NotNull FieldEffectType ingredient) {
        return ingredient;
    }

    @Override
    public boolean isValidIngredient(@NotNull FieldEffectType ingredient) {
        return MagitechRegistries.FIELD_EFFECT_TYPE.getKey(ingredient) != null;
    }

    @Override
    public @NotNull String getErrorInfo(@Nullable FieldEffectType ingredient) {
        if (ingredient == null) {
            return "null";
        }
        return getDisplayName(ingredient);
    }
}
