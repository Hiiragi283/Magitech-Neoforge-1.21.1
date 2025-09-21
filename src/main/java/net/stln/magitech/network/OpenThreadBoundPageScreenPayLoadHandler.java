package net.stln.magitech.network;

import java.util.Objects;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.stln.magitech.inventory.ThreadboundMenu;
import net.stln.magitech.item.ThreadBoundItem;
import net.stln.magitech.util.CuriosHelper;

public class OpenThreadBoundPageScreenPayLoadHandler {

    public static void handleDataOnMainC2S(
            final OpenThreadBoundPageScreenPayload payload, final IPayloadContext context) {
        Player player;
        Level level = context.player().level();
        player =
                level.players().stream()
                        .filter(search -> Objects.equals(search.getUUID(), payload.uuid()))
                        .findFirst()
                        .orElse(null);
        if (player == null) {
            return;
        }
        if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ThreadBoundItem) {
            player.openMenu(
                    new SimpleMenuProvider(
                            (containerId, playerInventory, player2) ->
                                    new ThreadboundMenu(containerId, playerInventory),
                            player.getItemInHand(InteractionHand.MAIN_HAND).getDisplayName()));
        } else {
            CuriosHelper.getThreadBoundStack(player)
                    .ifPresent(
                            stack ->
                                    player.openMenu(
                                            new SimpleMenuProvider(
                                                    (containerId, playerInventory, player2) ->
                                                            new ThreadboundMenu(
                                                                    containerId, playerInventory),
                                                    stack.getDisplayName())));
        }
    }
}
