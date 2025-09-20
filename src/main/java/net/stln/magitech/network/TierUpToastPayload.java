package net.stln.magitech.network;

import java.util.UUID;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.stln.magitech.Magitech;

import org.jetbrains.annotations.NotNull;

import io.netty.buffer.ByteBuf;

public record TierUpToastPayload(int slot, int newTier, UUID uuid) implements CustomPacketPayload {

    public static final ResourceLocation TIER_UP_PAYLOAD_ID = Magitech.id("tier_up_toast");
    public static final Type<TierUpToastPayload> TYPE = new Type<>(TIER_UP_PAYLOAD_ID);
    public static final StreamCodec<ByteBuf, TierUpToastPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT,
                    TierUpToastPayload::slot,
                    ByteBufCodecs.INT,
                    TierUpToastPayload::newTier,
                    UUIDUtil.STREAM_CODEC,
                    TierUpToastPayload::uuid,
                    TierUpToastPayload::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
