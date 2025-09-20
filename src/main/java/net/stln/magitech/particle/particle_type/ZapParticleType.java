package net.stln.magitech.particle.particle_type;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.particle.particle_option.ZapParticleEffect;

import com.mojang.serialization.MapCodec;

public class ZapParticleType extends ParticleType<ZapParticleEffect> {

    public ZapParticleType(boolean overrideLimitter) {
        super(overrideLimitter);
    }

    @Override
    public MapCodec<ZapParticleEffect> codec() {
        return ZapParticleEffect.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, ZapParticleEffect> streamCodec() {
        return ZapParticleEffect.STREAM_CODEC;
    }
}
