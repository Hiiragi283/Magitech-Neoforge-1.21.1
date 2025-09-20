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

public record SyncManaPayload(double value, int manaType, UUID uuid)
        implements CustomPacketPayload {

    public static final ResourceLocation SYNC_MANA_PAYLOAD_ID = Magitech.id("sync_mana");
    public static final Type<SyncManaPayload> TYPE = new Type<>(SYNC_MANA_PAYLOAD_ID);
    public static final StreamCodec<ByteBuf, SyncManaPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.DOUBLE,
                    SyncManaPayload::value,
                    ByteBufCodecs.INT,
                    SyncManaPayload::manaType,
                    UUIDUtil.STREAM_CODEC,
                    SyncManaPayload::uuid,
                    SyncManaPayload::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
