package net.stln.magitech.magic.mana;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.entity.player.Player;

public class UsedHandData {

    // main = false, off = true
    private static final Map<Player, boolean[]> handMap = new HashMap<>();

    public static void setUsedHand(Player player, boolean value) {
        UsedHandData.handMap.getOrDefault(player, new boolean[] {false})[0] = value;
    }

    public static boolean[] getUsedHand(Player player) {
        UsedHandData.handMap.computeIfAbsent(player, k -> new boolean[] {false});
        return UsedHandData.handMap.get(player);
    }
}
