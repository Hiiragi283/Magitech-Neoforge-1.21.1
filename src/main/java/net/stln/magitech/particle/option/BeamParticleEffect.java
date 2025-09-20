package net.stln.magitech.particle.option;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.stln.magitech.particle.ParticleInit;

import org.joml.Vector3f;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.netty.buffer.ByteBuf;

public class BeamParticleEffect extends AbstractCustomizableParticleEffect {

    public static final MapCodec<BeamParticleEffect> CODEC =
            RecordCodecBuilder.mapCodec(
                    instance ->
                            instance.group(
                                            ExtraCodecs.VECTOR3F
                                                    .fieldOf("from_color")
                                                    .forGetter(effect -> effect.fromColor),
                                            ExtraCodecs.VECTOR3F
                                                    .fieldOf("to_color")
                                                    .forGetter(effect -> effect.toColor),
                                            ExtraCodecs.VECTOR3F
                                                    .fieldOf("to_pos")
                                                    .forGetter(effect -> effect.toPos),
                                            SCALE_CODEC
                                                    .fieldOf("scale")
                                                    .forGetter(
                                                            AbstractCustomizableParticleEffect
                                                                    ::getScale),
                                            TWINKLE_CODEC
                                                    .fieldOf("twinkle")
                                                    .forGetter(
                                                            AbstractCustomizableParticleEffect
                                                                    ::getTwinkle),
                                            ROTATE_SPEED_CODEC
                                                    .fieldOf("rotate_speed")
                                                    .forGetter(
                                                            AbstractCustomizableParticleEffect
                                                                    ::getRotSpeed))
                                    .apply(instance, BeamParticleEffect::new));
    public static final StreamCodec<ByteBuf, BeamParticleEffect> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VECTOR3F,
                    effect -> effect.fromColor,
                    ByteBufCodecs.VECTOR3F,
                    effect -> effect.toColor,
                    ByteBufCodecs.VECTOR3F,
                    effect -> effect.toPos,
                    ByteBufCodecs.FLOAT,
                    AbstractCustomizableParticleEffect::getScale,
                    ByteBufCodecs.INT,
                    AbstractCustomizableParticleEffect::getTwinkle,
                    ByteBufCodecs.FLOAT,
                    AbstractCustomizableParticleEffect::getRotSpeed,
                    BeamParticleEffect::new);

    private final Vector3f fromColor;
    private final Vector3f toColor;
    private final Vector3f toPos;

    public BeamParticleEffect(
            Vector3f fromColor,
            Vector3f toColor,
            Vector3f toPos,
            float scale,
            int twinkle,
            float rotSpeed) {
        super(scale, twinkle, rotSpeed);
        this.fromColor = fromColor;
        this.toColor = toColor;
        this.toPos = toPos;
    }

    public Vector3f getFromColor() {
        return this.fromColor;
    }

    public Vector3f getToColor() {
        return this.toColor;
    }

    public Vector3f getToPos() {
        return this.toPos;
    }

    @Override
    public ParticleType<BeamParticleEffect> getType() {
        return ParticleInit.BEAM.get();
    }
}
