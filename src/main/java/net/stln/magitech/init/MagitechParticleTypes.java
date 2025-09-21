package net.stln.magitech.init;

import java.util.function.Supplier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;
import net.stln.magitech.client.particle.*;
import net.stln.magitech.particle.*;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.MapCodec;

public class MagitechParticleTypes {

    public static final DeferredRegister<ParticleType<?>> REGISTER =
            DeferredRegister.create(Registries.PARTICLE_TYPE, Magitech.MOD_ID);

    public static final Supplier<ParticleType<SquareFieldParticleEffect>> SQUARE_FIELD =
            register(
                    "square_field",
                    SquareFieldParticleEffect.CODEC,
                    SquareFieldParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<UnstableSquareParticleEffect>> UNSTABLE_SQUARE =
            register(
                    "unstable_square",
                    UnstableSquareParticleEffect.CODEC,
                    UnstableSquareParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<SquareParticleEffect>> SQUARE =
            register("square", SquareParticleEffect.CODEC, SquareParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<SquareNoCullParticleEffect>> SQUARE_NO_CULL =
            register(
                    "square_no_cull",
                    SquareNoCullParticleEffect.CODEC,
                    SquareNoCullParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<FlameParticleEffect>> FLAME =
            register("flame", FlameParticleEffect.CODEC, FlameParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<FlameSmokeParticleEffect>> FLAME_SMOKE =
            register(
                    "flame_smoke",
                    FlameSmokeParticleEffect.CODEC,
                    FlameSmokeParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<FrostParticleEffect>> FROST =
            register("frost", FrostParticleEffect.CODEC, FrostParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<FrostShortParticleEffect>> FROST_SHORT =
            register(
                    "frost_short",
                    FrostShortParticleEffect.CODEC,
                    FrostShortParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<SparkParticleEffect>> SPARK =
            register("spark", SparkParticleEffect.CODEC, SparkParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<MembraneParticleEffect>> MEMBRANE =
            register("membrane", MembraneParticleEffect.CODEC, MembraneParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<WaveParticleEffect>> WAVE =
            register("wave", WaveParticleEffect.CODEC, WaveParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<WaveNoCullParticleEffect>> WAVE_NO_CULL =
            register(
                    "wave_no_cull",
                    WaveNoCullParticleEffect.CODEC,
                    WaveNoCullParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<RuneParticleEffect>> RUNE =
            register("rune", RuneParticleEffect.CODEC, RuneParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<BlowParticleEffect>> BLOW =
            register("blow", BlowParticleEffect.CODEC, BlowParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<VoidGlowParticleEffect>> VOID_GLOW =
            register(
                    "void_glow", VoidGlowParticleEffect.CODEC, VoidGlowParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<PowerupParticleEffect>> POWERUP =
            register("powerup", PowerupParticleEffect.CODEC, PowerupParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<PowerupNoCullParticleEffect>> POWERUP_NO_CULL =
            register(
                    "powerup_no_cull",
                    PowerupNoCullParticleEffect.CODEC,
                    PowerupNoCullParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<ZapParticleEffect>> ZAP =
            register("zap", ZapParticleEffect.CODEC, ZapParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<ManaZapParticleEffect>> MANA_ZAP =
            register("mana_zap", ManaZapParticleEffect.CODEC, ManaZapParticleEffect.STREAM_CODEC);
    public static final Supplier<ParticleType<BeamParticleEffect>> BEAM =
            register("beam", BeamParticleEffect.CODEC, BeamParticleEffect.STREAM_CODEC);

    private static <T extends ParticleOptions> Supplier<ParticleType<T>> register(
            String name,
            @NotNull MapCodec<T> codec,
            @NotNull StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        return register(name, codec, streamCodec, true);
    }

    private static <T extends ParticleOptions> Supplier<ParticleType<T>> register(
            String name,
            @NotNull MapCodec<T> codec,
            @NotNull StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec,
            boolean overrideLimitter) {
        return REGISTER.register(
                name, () -> new MagitechParticleType<>(codec, streamCodec, overrideLimitter));
    }

    private static class MagitechParticleType<T extends ParticleOptions> extends ParticleType<T> {
        private final MapCodec<T> codec;
        private final StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec;

        public MagitechParticleType(
                @NotNull MapCodec<T> codec,
                @NotNull StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec,
                boolean overrideLimitter) {
            super(overrideLimitter);
            this.codec = codec;
            this.streamCodec = streamCodec;
        }

        @Override
        public @NotNull MapCodec<T> codec() {
            return codec;
        }

        @Override
        public @NotNull StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
            return streamCodec;
        }
    }

    @Environment(EnvType.CLIENT)
    public static void registerParticleClient(IEventBus eventBus) {
        Magitech.LOGGER.info("Registering Client Particle for " + Magitech.MOD_ID);
        REGISTER.register(eventBus);
    }

    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SQUARE_FIELD.get(), SquareFieldParticle.Provider::new);
        event.registerSpriteSet(UNSTABLE_SQUARE.get(), UnstableSquareParticle.Provider::new);
        event.registerSpriteSet(SQUARE.get(), SquareParticle.Provider::new);
        event.registerSpriteSet(SQUARE_NO_CULL.get(), SquareNoCullParticle.Provider::new);
        event.registerSpriteSet(FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(FLAME_SMOKE.get(), FlameSmokeParticle.Provider::new);
        event.registerSpriteSet(FROST.get(), FrostParticle.Provider::new);
        event.registerSpriteSet(FROST_SHORT.get(), FrostShortParticle.Provider::new);
        event.registerSpriteSet(SPARK.get(), SparkParticle.Provider::new);
        event.registerSpriteSet(MEMBRANE.get(), MembraneParticle.Provider::new);
        event.registerSpriteSet(WAVE.get(), WaveParticle.Provider::new);
        event.registerSpriteSet(WAVE_NO_CULL.get(), WaveNoCullParticle.Provider::new);
        event.registerSpriteSet(RUNE.get(), RuneParticle.Provider::new);
        event.registerSpriteSet(BLOW.get(), BlowParticle.Provider::new);
        event.registerSpriteSet(VOID_GLOW.get(), VoidGlowParticle.Provider::new);
        event.registerSpriteSet(POWERUP.get(), PowerupParticle.Provider::new);
        event.registerSpriteSet(POWERUP_NO_CULL.get(), PowerupNoCullParticle.Provider::new);
        event.registerSpriteSet(ZAP.get(), ZapParticle.Provider::new);
        event.registerSpriteSet(MANA_ZAP.get(), ManaZapParticle.Provider::new);
        event.registerSpriteSet(BEAM.get(), BeamParticle.Provider::new);
    }
}
