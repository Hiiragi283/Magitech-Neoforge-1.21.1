package net.stln.magitech.particle.type;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.particle.option.FlameSmokeParticleEffect;

import com.mojang.serialization.MapCodec;

public class FlameSmokeParticleType extends ParticleType<FlameSmokeParticleEffect> {

    public FlameSmokeParticleType(boolean overrideLimitter) {
        super(overrideLimitter);
    }

    @Override
    public MapCodec<FlameSmokeParticleEffect> codec() {
        return FlameSmokeParticleEffect.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, FlameSmokeParticleEffect> streamCodec() {
        return FlameSmokeParticleEffect.STREAM_CODEC;
    }
}
