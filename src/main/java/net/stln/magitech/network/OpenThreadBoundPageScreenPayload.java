package net.stln.magitech.network;

import java.util.UUID;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.stln.magitech.Magitech;

import org.jetbrains.annotations.NotNull;

import io.netty.buffer.ByteBuf;

public record OpenThreadBoundPageScreenPayload(UUID uuid) implements CustomPacketPayload {

    public static final ResourceLocation open_thread_bound_page_screen_C2S_PAYLOAD_ID =
            Magitech.id("open_thread_bound_page_screen");
    public static final Type<OpenThreadBoundPageScreenPayload> TYPE =
            new Type<>(open_thread_bound_page_screen_C2S_PAYLOAD_ID);
    public static final StreamCodec<ByteBuf, OpenThreadBoundPageScreenPayload> STREAM_CODEC =
            StreamCodec.composite(
                    UUIDUtil.STREAM_CODEC,
                    OpenThreadBoundPageScreenPayload::uuid,
                    OpenThreadBoundPageScreenPayload::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
