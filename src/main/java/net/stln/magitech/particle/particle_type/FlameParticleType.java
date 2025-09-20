package net.stln.magitech.particle.particle_type;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.particle.particle_option.FlameParticleEffect;

import com.mojang.serialization.MapCodec;

public class FlameParticleType extends ParticleType<FlameParticleEffect> {

    public FlameParticleType(boolean overrideLimitter) {
        super(overrideLimitter);
    }

    @Override
    public MapCodec<FlameParticleEffect> codec() {
        return FlameParticleEffect.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, FlameParticleEffect> streamCodec() {
        return FlameParticleEffect.STREAM_CODEC;
    }
}
