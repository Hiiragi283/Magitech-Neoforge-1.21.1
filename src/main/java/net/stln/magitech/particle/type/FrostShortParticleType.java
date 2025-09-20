package net.stln.magitech.particle.type;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.particle.option.FrostShortParticleEffect;

import com.mojang.serialization.MapCodec;

public class FrostShortParticleType extends ParticleType<FrostShortParticleEffect> {

    public FrostShortParticleType(boolean overrideLimitter) {
        super(overrideLimitter);
    }

    @Override
    public MapCodec<FrostShortParticleEffect> codec() {
        return FrostShortParticleEffect.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, FrostShortParticleEffect> streamCodec() {
        return FrostShortParticleEffect.STREAM_CODEC;
    }
}
