package net.stln.magitech.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.stln.magitech.item.tool.toolitem.PartToolItem;
import net.stln.magitech.util.ServerHelper;

public class TraitTickPayLoadHandler {

    public static void handleDataOnMainS2C(
            final TraitTickPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        ItemStack stack = player.getInventory().getItem(payload.slot());
        if (payload.isInventory() && stack.getItem() instanceof PartToolItem partToolItem) {
            PartToolItem.getTraitLevel(PartToolItem.getTraits(stack))
                    .forEach(
                            (trait, integer) ->
                                    trait.inventoryTick(
                                            player,
                                            player.level(),
                                            stack,
                                            integer,
                                            ((PartToolItem) stack.getItem())
                                                    .getSumStats(player, player.level(), stack),
                                            false));
        } else if (stack.getItem() instanceof PartToolItem partToolItem) {
            PartToolItem.getTraitLevel(PartToolItem.getTraits(stack))
                    .forEach(
                            (trait, integer) ->
                                    trait.tick(
                                            player,
                                            player.level(),
                                            stack,
                                            integer,
                                            ((PartToolItem) stack.getItem())
                                                    .getSumStats(player, player.level(), stack),
                                            false));
        }
    }

    public static void handleDataOnMainC2S(
            final TraitTickPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        ItemStack stack = player.getInventory().getItem(payload.slot());
        if (payload.isInventory() && stack.getItem() instanceof PartToolItem partToolItem) {
            PartToolItem.getTraitLevel(PartToolItem.getTraits(stack))
                    .forEach(
                            (trait, integer) ->
                                    trait.inventoryTick(
                                            player,
                                            player.level(),
                                            stack,
                                            integer,
                                            ((PartToolItem) stack.getItem())
                                                    .getSumStats(player, player.level(), stack),
                                            false));
        } else if (stack.getItem() instanceof PartToolItem partToolItem) {
            PartToolItem.getTraitLevel(PartToolItem.getTraits(stack))
                    .forEach(
                            (trait, integer) ->
                                    trait.tick(
                                            player,
                                            player.level(),
                                            stack,
                                            integer,
                                            ((PartToolItem) stack.getItem())
                                                    .getSumStats(player, player.level(), stack),
                                            false));
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
