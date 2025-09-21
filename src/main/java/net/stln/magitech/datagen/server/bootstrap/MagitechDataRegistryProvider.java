package net.stln.magitech.datagen.server.bootstrap;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechDamageTypes;

import org.jetbrains.annotations.NotNull;

public class MagitechDataRegistryProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER =
            new RegistrySetBuilder()
                    .add(Registries.DAMAGE_TYPE, MagitechDataRegistryProvider::damageTypes)
                    .add(Registries.CONFIGURED_FEATURE, MagitechWorldGenInit::configuredFeature)
                    .add(Registries.PLACED_FEATURE, MagitechWorldGenInit::placedFeature)
                    .add(
                            NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                            MagitechWorldGenInit::biomeModifier);

    private static void damageTypes(BootstrapContext<DamageType> context) {
        for (ResourceKey<DamageType> key : MagitechDamageTypes.MAGICAL) {
            registerElementDamage(context, key);
        }

        context.register(
                MagitechDamageTypes.MANA_BERRY_BUSH,
                new DamageType(
                        MagitechDamageTypes.MANA_BERRY_BUSH.location().toDebugFileName(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f,
                        DamageEffects.POKING));
    }

    private static void registerElementDamage(
            @NotNull BootstrapContext<DamageType> context, @NotNull ResourceKey<DamageType> key) {
        context.register(
                key,
                new DamageType(
                        key.location().toDebugFileName(),
                        DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.5f));
    }

    public MagitechDataRegistryProvider(
            PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Magitech.MOD_ID));
    }
}
