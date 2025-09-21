package net.stln.magitech.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.stln.magitech.util.ComponentHelper;
import net.stln.magitech.util.CuriosHelper;
import net.stln.magitech.util.ServerHelper;

public class ThreadboundSelectPayLoadHandler {

    public static void handleDataOnMainS2C(
            final ThreadBoundSelectPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        CuriosHelper.getThreadBoundStack(player)
                .ifPresent(
                        stack ->
                                ComponentHelper.updateSpells(
                                        stack,
                                        spellComponent ->
                                                spellComponent.setSelected(payload.select())));
    }

    public static void handleDataOnMainC2S(
            final ThreadBoundSelectPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) return;

        ServerHelper.getOptionalServer()
                .ifPresent(
                        server -> {
                            for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers())
                                if (player.getUUID() != serverPlayer.getUUID()) {
                                    PacketDistributor.sendToPlayer(serverPlayer, payload);
                                }
                        });
        CuriosHelper.getThreadBoundStack(player)
                .ifPresent(
                        stack ->
                                ComponentHelper.updateSpells(
                                        stack,
                                        spellComponent ->
                                                spellComponent.setSelected(payload.select())));
    }
}
