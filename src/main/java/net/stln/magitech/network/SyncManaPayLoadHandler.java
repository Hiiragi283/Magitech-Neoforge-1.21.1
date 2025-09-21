package net.stln.magitech.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.stln.magitech.magic.mana.ManaData;
import net.stln.magitech.magic.mana.ManaUtil;
import net.stln.magitech.util.ServerHelper;

public class SyncManaPayLoadHandler {

    public static void handleDataOnMainS2C(
            final SyncManaPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        ManaData.setPrevMana(
                player,
                ManaUtil.getManaType(payload.manaType()),
                ManaData.getCurrentMana(player, ManaUtil.getManaType(payload.manaType())));
        ManaData.setCurrentMana(player, ManaUtil.getManaType(payload.manaType()), payload.value());
    }

    public static void handleDataOnMainC2S(
            final SyncManaPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        ManaData.setPrevMana(
                player,
                ManaUtil.getManaType(payload.manaType()),
                ManaData.getCurrentMana(player, ManaUtil.getManaType(payload.manaType())));
        ManaData.setCurrentMana(player, ManaUtil.getManaType(payload.manaType()), payload.value());
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
