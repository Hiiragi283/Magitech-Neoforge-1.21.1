package net.stln.magitech.particle.type;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.particle.option.UnstableSquareParticleEffect;

import com.mojang.serialization.MapCodec;

public class UnstableSquareParticleType extends ParticleType<UnstableSquareParticleEffect> {

    public UnstableSquareParticleType(boolean overrideLimitter) {
        super(overrideLimitter);
    }

    @Override
    public MapCodec<UnstableSquareParticleEffect> codec() {
        return UnstableSquareParticleEffect.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, UnstableSquareParticleEffect>
            streamCodec() {
        return UnstableSquareParticleEffect.STREAM_CODEC;
    }
}
