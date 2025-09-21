package net.stln.magitech.item.component;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.item.tool.upgrade.UpgradeInstance;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.Codec;

public record UpgradeComponent(List<UpgradeInstance> upgrades) {

    public static final Codec<UpgradeComponent> CODEC =
            UpgradeInstance.CODEC.listOf().xmap(UpgradeComponent::new, UpgradeComponent::upgrades);
    public static final StreamCodec<RegistryFriendlyByteBuf, UpgradeComponent> STREAM_CODEC =
            UpgradeInstance.STREAM_CODEC
                    .apply(ByteBufCodecs.list())
                    .map(UpgradeComponent::new, UpgradeComponent::upgrades);
    public static final UpgradeComponent EMPTY = new UpgradeComponent(List.of());

    public @NotNull UpgradeComponent addUpgrade(@NotNull UpgradeInstance upgrade) {
        List<UpgradeInstance> newUpgrades = new ArrayList<>();
        int level = upgrade.level();
        for (UpgradeInstance oldUpgrade : upgrades) {
            if (oldUpgrade.upgrade().equals(upgrade.upgrade())) {
                level += oldUpgrade.level();
            } else {
                newUpgrades.add(oldUpgrade);
            }
        }
        newUpgrades.add(new UpgradeInstance(level, upgrade.upgrade()));

        return new UpgradeComponent(newUpgrades);
    }
}
