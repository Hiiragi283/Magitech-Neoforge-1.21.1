package net.stln.magitech.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.stln.magitech.Magitech;
import net.stln.magitech.biome.MistjadeForestRegion;
import net.stln.magitech.biome.ScorchedPlainsRegion;
import net.stln.magitech.biome.ScorchedSoilSurfaceRule;

import org.jetbrains.annotations.NotNull;

import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

public final class MagitechBiomes {
    public static final ResourceKey<Biome> MISTJADE_FOREST = create("mistjade_forest");

    public static final ResourceKey<Biome> SCORCHED_PLAINS = create("scorched_plains");

    private static @NotNull ResourceKey<Biome> create(@NotNull String path) {
        return ResourceKey.create(Registries.BIOME, Magitech.id(path));
    }

    public static void registerBiomeRegions(FMLCommonSetupEvent event) {
        event.enqueueWork(
                () -> {
                    Regions.register(new MistjadeForestRegion(Magitech.id("mistjade_forest"), 2));
                    Regions.register(new ScorchedPlainsRegion(Magitech.id("scorched_plains"), 2));

                    SurfaceRuleManager.addSurfaceRules(
                            SurfaceRuleManager.RuleCategory.OVERWORLD,
                            Magitech.MOD_ID,
                            ScorchedSoilSurfaceRule.makeRule());
                });
    }
}
