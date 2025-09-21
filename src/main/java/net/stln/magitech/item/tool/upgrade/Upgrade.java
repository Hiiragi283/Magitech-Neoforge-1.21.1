package net.stln.magitech.item.tool.upgrade;

import java.util.Objects;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.item.tool.ToolStats;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.Codec;

public interface Upgrade extends UpgradeLike {
    Codec<Upgrade> CODEC = MagitechRegistries.UPGRADE.byNameCodec();
    StreamCodec<RegistryFriendlyByteBuf, Upgrade> STREAM_CODEC =
            ByteBufCodecs.registry(MagitechRegistries.Keys.UPGRADE);

    default ToolStats getUpgradeStats(int level) {
        return ToolStats.DEFAULT;
    }

    @NotNull default ResourceLocation getId() {
        return Objects.requireNonNull(MagitechRegistries.UPGRADE.getKey(this));
    }

    @Override
    default Upgrade asUpgrade() {
        return this;
    }
}
