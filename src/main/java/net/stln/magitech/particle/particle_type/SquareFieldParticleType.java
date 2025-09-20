package net.stln.magitech.particle.particle_type;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.particle.particle_option.SquareFieldParticleEffect;

import com.mojang.serialization.MapCodec;

public class SquareFieldParticleType extends ParticleType<SquareFieldParticleEffect> {

    public SquareFieldParticleType(boolean overrideLimitter) {
        super(overrideLimitter);
    }

    @Override
    public MapCodec<SquareFieldParticleEffect> codec() {
        return SquareFieldParticleEffect.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, SquareFieldParticleEffect> streamCodec() {
        return SquareFieldParticleEffect.STREAM_CODEC;
    }
}
