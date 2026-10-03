package net.stln.magitech.content.item.component;


import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.feature.magic.spell.ISpell;
import org.jetbrains.annotations.NotNull;

public record ThreadPageComponent(@NotNull Holder<ISpell> spell) {
    public static final Codec<ThreadPageComponent> CODEC = ISpell.HOLDER_CODEC.xmap(ThreadPageComponent::new, ThreadPageComponent::spell);
    public static final StreamCodec<RegistryFriendlyByteBuf, ThreadPageComponent> STREAM_CODEC = ISpell.HOLDER_STREAM_CODEC.map(ThreadPageComponent::new, ThreadPageComponent::spell);
}

