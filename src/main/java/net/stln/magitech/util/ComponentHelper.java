package net.stln.magitech.util;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

import net.minecraft.core.component.DataComponentHolder;
import net.neoforged.neoforge.common.MutableDataComponentHolder;
import net.stln.magitech.init.MagitechDataComponents;
import net.stln.magitech.item.component.*;
import net.stln.magitech.item.tool.material.ToolMaterial;
import net.stln.magitech.item.tool.upgrade.UpgradeInstance;
import net.stln.magitech.magic.spell.Spell;
import net.stln.magitech.magic.spell.SpellLike;

import org.jetbrains.annotations.NotNull;

public class ComponentHelper {

    // Getter
    public static @NotNull List<ToolMaterial> getPartMaterials(
            @NotNull DataComponentHolder holder) {
        return holder.getOrDefault(
                        MagitechDataComponents.PART_MATERIAL_COMPONENT, PartMaterialComponent.EMPTY)
                .materials();
    }

    public static @NotNull Optional<ToolMaterial> getMaterial(@NotNull DataComponentHolder holder) {
        return Optional.ofNullable(holder.get(MagitechDataComponents.MATERIAL_COMPONENT))
                .map(MaterialComponent::material);
    }

    public static @NotNull SpellComponent getSpells(@NotNull DataComponentHolder holder) {
        return holder.getOrDefault(MagitechDataComponents.SPELL_COMPONENT, SpellComponent.EMPTY);
    }

    public static @NotNull Optional<Spell> getThreadPageSpell(@NotNull DataComponentHolder holder) {
        return Optional.ofNullable(holder.get(MagitechDataComponents.THREAD_PAGE_COMPONENT))
                .map(ThreadPageComponent::spell);
    }

    public static int getTier(@NotNull DataComponentHolder holder) {
        return holder.getOrDefault(MagitechDataComponents.TIER_COMPONENT, 0);
    }

    public static @NotNull List<UpgradeInstance> getUpgrades(@NotNull DataComponentHolder holder) {
        return holder.getOrDefault(MagitechDataComponents.UPGRADE_COMPONENT, UpgradeComponent.EMPTY)
                .upgrades();
    }

    public static int getUpgradePoint(@NotNull DataComponentHolder holder) {
        return holder.getOrDefault(MagitechDataComponents.UPGRADE_POINT_COMPONENT, 0);
    }

    public static boolean isBroken(@NotNull DataComponentHolder holder) {
        return holder.getOrDefault(MagitechDataComponents.BROKEN_COMPONENT, false);
    }

    // Setter
    public static void setThreadPage(
            @NotNull MutableDataComponentHolder holder, @NotNull SpellLike spell) {
        holder.set(MagitechDataComponents.THREAD_PAGE_COMPONENT, new ThreadPageComponent(spell));
    }

    public static void updateSpells(
            @NotNull MutableDataComponentHolder holder,
            @NotNull UnaryOperator<SpellComponent> operator) {
        holder.update(MagitechDataComponents.SPELL_COMPONENT, SpellComponent.EMPTY, operator);
    }

    public static void updateUpgradePoint(
            @NotNull MutableDataComponentHolder holder, UnaryOperator<Integer> operator) {
        holder.update(MagitechDataComponents.UPGRADE_POINT_COMPONENT, 0, operator);
    }
}
