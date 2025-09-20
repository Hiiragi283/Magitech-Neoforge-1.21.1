package net.stln.magitech.particle.type;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.particle.option.RuneParticleEffect;

import com.mojang.serialization.MapCodec;

public class RuneParticleType extends ParticleType<RuneParticleEffect> {

    public RuneParticleType(boolean overrideLimitter) {
        super(overrideLimitter);
    }

    @Override
    public MapCodec<RuneParticleEffect> codec() {
        return RuneParticleEffect.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, RuneParticleEffect> streamCodec() {
        return RuneParticleEffect.STREAM_CODEC;
    }
}
