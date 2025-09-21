package net.stln.magitech.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.stln.magitech.item.tool.toolitem.PartToolItem;
import net.stln.magitech.util.ServerHelper;

public class TraitActionPayLoadHandler {

    public static void handleDataOnMainS2C(
            final TraitActionPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        Entity entity = player.level().getEntity(payload.targetId());
        InteractionHand hand =
                payload.isMainHand() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        Item item = player.getItemInHand(hand).getItem();
        if (item instanceof PartToolItem partToolItem) {
            ItemStack stack = player.getItemInHand(hand);
            PartToolItem.getTraitLevel(PartToolItem.getTraits(stack))
                    .forEach(
                            (trait, integer) -> {
                                Vec3 lookingPos = payload.targetPos();
                                if (lookingPos.x == Double.MAX_VALUE
                                        && lookingPos.y == Double.MAX_VALUE
                                        && lookingPos.z == Double.MAX_VALUE) {
                                    lookingPos = null;
                                }
                                trait.traitAction(
                                        player,
                                        player.level(),
                                        entity,
                                        lookingPos,
                                        stack,
                                        integer,
                                        ((PartToolItem) stack.getItem())
                                                .getSumStats(player, player.level(), stack),
                                        hand,
                                        false);
                            });
        }
    }

    public static void handleDataOnMainC2S(
            final TraitActionPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        Entity entity = player.level().getEntity(payload.targetId());
        InteractionHand hand =
                payload.isMainHand() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        Item item = player.getItemInHand(hand).getItem();
        if (item instanceof PartToolItem partToolItem) {
            ItemStack stack = player.getItemInHand(hand);

            PartToolItem.getTraitLevel(PartToolItem.getTraits(stack))
                    .forEach(
                            (trait, integer) -> {
                                Vec3 lookingPos = payload.targetPos();
                                if (lookingPos.x == Double.MAX_VALUE
                                        && lookingPos.y == Double.MAX_VALUE
                                        && lookingPos.z == Double.MAX_VALUE) {
                                    lookingPos = null;
                                }
                                trait.traitAction(
                                        player,
                                        player.level(),
                                        entity,
                                        lookingPos,
                                        stack,
                                        integer,
                                        ((PartToolItem) stack.getItem())
                                                .getSumStats(player, player.level(), stack),
                                        hand,
                                        false);
                            });
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
