package net.stln.magitech.item.tool.upgrade;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record UpgradeInstance(int level, Upgrade upgrade) {

    public static final Codec<UpgradeInstance> CODEC =
            RecordCodecBuilder.create(
                    instance ->
                            instance.group(
                                            Codec.INT
                                                    .fieldOf("level")
                                                    .forGetter(UpgradeInstance::level),
                                            Upgrade.CODEC
                                                    .fieldOf("upgrade")
                                                    .forGetter(UpgradeInstance::upgrade))
                                    .apply(instance, UpgradeInstance::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, UpgradeInstance> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT,
                    UpgradeInstance::level,
                    Upgrade.STREAM_CODEC,
                    UpgradeInstance::upgrade,
                    UpgradeInstance::new);

    public UpgradeInstance(int level, UpgradeLike upgradeLike) {
        this(level, upgradeLike.asUpgrade());
    }
}
