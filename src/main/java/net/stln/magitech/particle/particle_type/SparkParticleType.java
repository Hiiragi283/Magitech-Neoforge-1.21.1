package net.stln.magitech.particle.particle_type;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.particle.particle_option.SparkParticleEffect;

import com.mojang.serialization.MapCodec;

public class SparkParticleType extends ParticleType<SparkParticleEffect> {

    public SparkParticleType(boolean overrideLimitter) {
        super(overrideLimitter);
    }

    @Override
    public MapCodec<SparkParticleEffect> codec() {
        return SparkParticleEffect.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, SparkParticleEffect> streamCodec() {
        return SparkParticleEffect.STREAM_CODEC;
    }
}
