package net.stln.magitech.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.stln.magitech.item.tool.material.ToolMaterial;
import net.stln.magitech.tag.MagitechTags;

import org.jetbrains.annotations.NotNull;

public class ToolMaterialUtil {

    public static @NotNull List<ToolMaterial> getMaterialCombinationAt(
            @NotNull List<ToolMaterial> materials, int partCount, long index) {
        List<ToolMaterial> result = new ArrayList<>();
        int base = materials.size();

        for (int i = 0; i < partCount; i++) {
            int materialIndex = (int) (index % base);
            result.add(materials.get(materialIndex));
            index /= base;
        }

        return result;
    }

    public static boolean isCorrectMaterialForUpgrade(int tier, int point, @NotNull Item item) {
        return item.getDefaultInstance().is(getUpgradeMaterialTag(tier, point));
    }

    public static TagKey<Item> getUpgradeMaterialTag(int tier, int point) {
        return switch (tier - point) {
            case 0, 1, 2, 3, 4 -> MagitechTags.Items.UPGRADE_MATERIAL_0;
            case 5, 6, 7, 8, 9 -> MagitechTags.Items.UPGRADE_MATERIAL_5;
            case 10, 11, 12, 13, 14 -> MagitechTags.Items.UPGRADE_MATERIAL_10;
            case 15, 16, 17, 18, 19 -> MagitechTags.Items.UPGRADE_MATERIAL_15;
            default -> MagitechTags.Items.UPGRADE_MATERIAL_20;
        };
    }
}
