package net.stln.magitech.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.stln.magitech.item.LeftClickOverrideItem;
import net.stln.magitech.util.ServerHelper;

public class LeftClickPayLoadHandler {

    public static void handleDataOnMainS2C(
            final LeftClickPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        Item item = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
        if (item instanceof LeftClickOverrideItem clickOverrideItem && payload.clickCount() != 0) {
            clickOverrideItem.onLeftClick(player, InteractionHand.MAIN_HAND, player.level());
        }
    }

    public static void handleDataOnMainC2S(
            final LeftClickPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        Item item = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
        if (item instanceof LeftClickOverrideItem clickOverrideItem && payload.clickCount() != 0) {
            clickOverrideItem.onLeftClick(player, InteractionHand.MAIN_HAND, player.level());
        }
        ServerHelper.getOptionalServer()
                .ifPresent(
                        server -> {
                            for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers())
                                if (player.getUUID() != serverPlayer.getUUID()) {
                                    PacketDistributor.sendToPlayer(serverPlayer, payload);
                                }
                        });
    }
}
