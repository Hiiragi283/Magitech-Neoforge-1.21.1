package net.stln.magitech.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.stln.magitech.init.MagitechParticleTypes;

import org.joml.Vector3f;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.netty.buffer.ByteBuf;

public class SparkParticleEffect extends AbstractCustomizableParticleEffect {

    public static final MapCodec<SparkParticleEffect> CODEC =
            RecordCodecBuilder.mapCodec(
                    instance ->
                            instance.group(
                                            ExtraCodecs.VECTOR3F
                                                    .fieldOf("from_color")
                                                    .forGetter(effect -> effect.fromColor),
                                            ExtraCodecs.VECTOR3F
                                                    .fieldOf("to_color")
                                                    .forGetter(effect -> effect.toColor),
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
                                    .apply(instance, SparkParticleEffect::new));
    public static final StreamCodec<ByteBuf, SparkParticleEffect> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VECTOR3F,
                    effect -> effect.fromColor,
                    ByteBufCodecs.VECTOR3F,
                    effect -> effect.toColor,
                    ByteBufCodecs.FLOAT,
                    AbstractCustomizableParticleEffect::getScale,
                    ByteBufCodecs.INT,
                    AbstractCustomizableParticleEffect::getTwinkle,
                    ByteBufCodecs.FLOAT,
                    AbstractCustomizableParticleEffect::getRotSpeed,
                    SparkParticleEffect::new);

    private final Vector3f fromColor;
    private final Vector3f toColor;

    public SparkParticleEffect(
            Vector3f fromColor, Vector3f toColor, float scale, int twinkle, float rotSpeed) {
        super(scale, twinkle, rotSpeed);
        this.fromColor = fromColor;
        this.toColor = toColor;
    }

    public Vector3f getFromColor() {
        return this.fromColor;
    }

    public Vector3f getToColor() {
        return this.toColor;
    }

    @Override
    public ParticleType<SparkParticleEffect> getType() {
        return MagitechParticleTypes.SPARK.get();
    }
}
