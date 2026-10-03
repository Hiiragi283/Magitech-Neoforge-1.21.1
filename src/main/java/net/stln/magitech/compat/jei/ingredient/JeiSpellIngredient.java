package net.stln.magitech.compat.jei.ingredient;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.stln.magitech.feature.magic.spell.ISpell;
import org.jetbrains.annotations.NotNull;

public record JeiSpellIngredient(@NotNull Holder<ISpell> holder) {
    public static final Codec<JeiSpellIngredient> CODEC = ISpell.HOLDER_CODEC.xmap(JeiSpellIngredient::new, JeiSpellIngredient::holder);

    public @NotNull ISpell spell() {
        return holder().value();
    }

    public @NotNull Component getName() {
        return ISpell.getDisplayName(holder());
    }
}
