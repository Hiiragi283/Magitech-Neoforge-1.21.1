package net.stln.magitech.particle.type;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.particle.option.VoidGlowParticleEffect;

import com.mojang.serialization.MapCodec;

public class VoidGlowParticleType extends ParticleType<VoidGlowParticleEffect> {

    public VoidGlowParticleType(boolean overrideLimitter) {
        super(overrideLimitter);
    }

    @Override
    public MapCodec<VoidGlowParticleEffect> codec() {
        return VoidGlowParticleEffect.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, VoidGlowParticleEffect> streamCodec() {
        return VoidGlowParticleEffect.STREAM_CODEC;
    }
}
