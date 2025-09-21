package net.stln.magitech.magic.charge;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.world.entity.player.Player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChargeData {

    private static final Map<UUID, Charge> chargeMapClient = new HashMap<>();
    private static final Map<UUID, Charge> chargeMapServer = new HashMap<>();

    private static @NotNull Map<UUID, Charge> getChargeMap(boolean isClient) {
        return isClient ? chargeMapClient : chargeMapServer;
    }

    public static void cleanUp(@NotNull Player player) {
        chargeMapClient.remove(player.getUUID());
        chargeMapServer.remove(player.getUUID());
    }

    public static void setCurrentCharge(@NotNull Player player, @NotNull Charge charge) {
        getChargeMap(player.level().isClientSide).put(player.getUUID(), charge);
    }

    public static void removeCharge(@NotNull Player player) {
        getChargeMap(player.level().isClientSide).remove(player.getUUID());
    }

    public static @Nullable Charge getCurrentCharge(@NotNull Player player) {
        return getChargeMap(player.level().isClientSide).getOrDefault(player.getUUID(), null);
    }
}
