package net.stln.magitech.content.item.component;


import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record ManaContainerComponent(long mana, long maxMana, long maxFlow) {
    public static final Codec<ManaContainerComponent> CODEC = Codec.LONG.listOf(3, 3).xmap(
            list -> new ManaContainerComponent(list.getFirst(), list.get(1), list.get(2)),
            component -> List.of(component.mana, component.maxMana, component.maxFlow)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, ManaContainerComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_LONG,
            ManaContainerComponent::mana,
            ByteBufCodecs.VAR_LONG,
            ManaContainerComponent::maxMana,
            ByteBufCodecs.VAR_LONG,
            ManaContainerComponent::maxFlow,
            ManaContainerComponent::new
    );
}

