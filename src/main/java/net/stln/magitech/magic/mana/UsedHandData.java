package net.stln.magitech.magic.mana;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.world.entity.player.Player;

public class UsedHandData {

    // main = false, off = true
    private static final Map<UUID, boolean[]> handMap = new HashMap<>();

    public static void setUsedHand(Player player, boolean value) {
        UsedHandData.handMap.getOrDefault(player.getUUID(), new boolean[] {false})[0] = value;
    }

    public static boolean[] getUsedHand(Player player) {
        return UsedHandData.handMap.computeIfAbsent(player.getUUID(), k -> new boolean[] {false});
    }
}
