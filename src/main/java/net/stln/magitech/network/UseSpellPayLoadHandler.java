package net.stln.magitech.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.stln.magitech.item.component.SpellComponent;
import net.stln.magitech.util.ComponentHelper;
import net.stln.magitech.util.CuriosHelper;
import net.stln.magitech.util.ServerHelper;

public class UseSpellPayLoadHandler {

    public static void handleDataOnMainS2C(
            final UseSpellPayload payload, final IPayloadContext context) {
        Level level = context.player().level();
        Player player = level.getPlayerByUUID(payload.uuid());
        InteractionHand hand =
                payload.isMainHand() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        CuriosHelper.getThreadBoundStack(player)
                .ifPresent(
                        stack -> {
                            SpellComponent spellComponent = ComponentHelper.getSpells(stack);
                            spellComponent.getSelectedSpell().use(level, player, hand, false);
                        });
    }

    public static void handleDataOnMainC2S(
            final UseSpellPayload payload, final IPayloadContext context) {
        Level level = context.player().level();
        Player player = level.getPlayerByUUID(payload.uuid());
        if (player == null) return;
        InteractionHand hand =
                payload.isMainHand() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        CuriosHelper.getThreadBoundStack(player)
                .ifPresent(
                        stack -> {
                            SpellComponent spellComponent = ComponentHelper.getSpells(stack);
                            spellComponent.getSelectedSpell().use(level, player, hand, false);
                            ServerHelper.getOptionalServer()
                                    .ifPresent(
                                            server -> {
                                                for (ServerPlayer serverPlayer :
                                                        server.getPlayerList().getPlayers())
                                                    if (player.getUUID()
                                                            != serverPlayer.getUUID()) {
                                                        PacketDistributor.sendToPlayer(
                                                                serverPlayer, payload);
                                                    }
                                            });
                        });
    }
}
