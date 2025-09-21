package net.stln.magitech.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.stln.magitech.init.MagitechItems;
import net.stln.magitech.item.armor.AetherLifterItem;
import net.stln.magitech.util.ServerHelper;

public class DoubleJumpPayLoadHandler {

    public static void handleDataOnMainS2C(
            final DoubleJumpPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        if (boots.is(MagitechItems.AETHER_LIFTER)) {
            AetherLifterItem.doubleJump(player, payload.jumpCount(), boots);
        }
    }

    public static void handleDataOnMainC2S(
            final DoubleJumpPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        if (boots.is(MagitechItems.AETHER_LIFTER)) {
            AetherLifterItem.doubleJump(player, payload.jumpCount(), boots);
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
