package net.stln.magitech.network;

import java.util.Optional;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.stln.magitech.item.component.SpellComponent;
import net.stln.magitech.magic.spell.Spell;
import net.stln.magitech.util.ComponentHelper;
import net.stln.magitech.util.CuriosHelper;
import net.stln.magitech.util.ServerHelper;

import org.jetbrains.annotations.NotNull;

public class ReleaseUsingSpellPayLoadHandler {

    public static void handleDataOnMainS2C(
            final ReleaseUsingSpellPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) {
            return;
        }
        getSpell(player)
                .ifPresent(
                        spell ->
                                spell.finishUsing(
                                        payload.stack(),
                                        player.level(),
                                        player,
                                        payload.chargeTime(),
                                        false));
    }

    public static void handleDataOnMainC2S(
            final ReleaseUsingSpellPayload payload, final IPayloadContext context) {
        Player player = context.player().level().getPlayerByUUID(payload.uuid());
        if (player == null) return;
        getSpell(player)
                .ifPresent(
                        spell ->
                                spell.finishUsing(
                                        payload.stack(),
                                        player.level(),
                                        player,
                                        payload.chargeTime(),
                                        false));
        ServerHelper.getOptionalServer()
                .ifPresent(
                        server -> {
                            for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers())
                                if (player.getUUID() != serverPlayer.getUUID()) {
                                    PacketDistributor.sendToPlayer(serverPlayer, payload);
                                }
                        });
    }

    private static Optional<Spell> getSpell(@NotNull Player player) {
        return CuriosHelper.getThreadBoundStack(player)
                .map(
                        stack -> {
                            SpellComponent spellComponent = ComponentHelper.getSpells(stack);
                            return spellComponent.spells().get(spellComponent.selected());
                        });
    }
}
