package net.stln.magitech.datagen.server.tag;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.stln.magitech.Magitech;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.init.MagitechUpgrades;
import net.stln.magitech.item.tool.upgrade.Upgrade;
import net.stln.magitech.tag.MagitechTags;

import org.jetbrains.annotations.NotNull;

public class MagitechUpgradeTypeTagsProvider extends TagsProvider<Upgrade> {
    public MagitechUpgradeTypeTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper fileHelper) {
        super(output, MagitechRegistries.Keys.UPGRADE, lookupProvider, Magitech.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(MagitechTags.Upgrades.IS_SPELL)
                .add(MagitechUpgrades.CASTER_POWER_UPGRADE.getKey())
                .add(MagitechUpgrades.CASTER_ELEMENTAL_POWER_UPGRADE.getKey())
                .add(MagitechUpgrades.CASTER_CHARGE_UPGRADE.getKey())
                .add(MagitechUpgrades.CASTER_COOLDOWN_UPGRADE.getKey())
                .add(MagitechUpgrades.CASTER_DEFENCE_UPGRADE.getKey())
                .add(MagitechUpgrades.CASTER_PROJECTILE_SPEED_UPGRADE.getKey())
                .add(MagitechUpgrades.CASTER_EFFICIENCY_UPGRADE.getKey())
                .add(MagitechUpgrades.CASTER_DURABILITY_UPGRADE.getKey());
    }
}
