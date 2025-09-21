package net.stln.magitech.magic.mana;

import java.util.UUID;

import net.minecraft.world.entity.player.Player;
import net.stln.magitech.util.TableHelper;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class ManaData {

    private static final Table<UUID, ManaUtil.ManaType, Double> currentManaMap =
            HashBasedTable.create();
    private static final Table<UUID, ManaUtil.ManaType, Double> prevManaMap =
            HashBasedTable.create();

    public static @NotNull Table<UUID, ManaUtil.ManaType, Double> getCurrentManaMap() {
        return currentManaMap;
    }

    public static void cleanUp(@NotNull Player player) {
        TableHelper.removeByRow(currentManaMap, player.getUUID());
        TableHelper.removeByRow(prevManaMap, player.getUUID());
    }

    public static void setCurrentMana(
            @NotNull Player player, @NotNull ManaUtil.ManaType type, double value) {
        currentManaMap.put(player.getUUID(), type, Math.max(value, 0));
    }

    public static void setPrevMana(
            @NotNull Player player, @NotNull ManaUtil.ManaType type, double value) {
        prevManaMap.put(player.getUUID(), type, Math.max(value, 0));
    }

    public static double getCurrentMana(@NotNull Player player, @NotNull ManaUtil.ManaType type) {
        return TableHelper.getOrDefault(currentManaMap, player.getUUID(), type, 0.0);
    }

    public static double getPrevMana(@NotNull Player player, @NotNull ManaUtil.ManaType type) {
        return TableHelper.getOrDefault(prevManaMap, player.getUUID(), type, 0.0);
    }
}
