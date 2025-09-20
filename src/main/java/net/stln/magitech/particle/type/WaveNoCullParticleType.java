package net.stln.magitech.particle.type;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.particle.option.WaveNoCullParticleEffect;

import com.mojang.serialization.MapCodec;

public class WaveNoCullParticleType extends ParticleType<WaveNoCullParticleEffect> {

    public WaveNoCullParticleType(boolean overrideLimitter) {
        super(overrideLimitter);
    }

    @Override
    public MapCodec<WaveNoCullParticleEffect> codec() {
        return WaveNoCullParticleEffect.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, WaveNoCullParticleEffect> streamCodec() {
        return WaveNoCullParticleEffect.STREAM_CODEC;
    }
}
