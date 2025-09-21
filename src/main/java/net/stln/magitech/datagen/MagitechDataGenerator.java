package net.stln.magitech.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.stln.magitech.Magitech;
import net.stln.magitech.datagen.client.MagitechBlockStateProvider;
import net.stln.magitech.datagen.client.MagitechItemModelProvider;
import net.stln.magitech.datagen.server.MagitechBlockLootProvider;
import net.stln.magitech.datagen.server.MagitechDataMapProvider;
import net.stln.magitech.datagen.server.MagitechEntityLootTableProvider;
import net.stln.magitech.datagen.server.bootstrap.MagitechDataRegistryProvider;
import net.stln.magitech.datagen.server.recipe.MagitechRecipeProvider;
import net.stln.magitech.datagen.server.tag.MagitechBiomeTagsProvider;
import net.stln.magitech.datagen.server.tag.MagitechBlockTagsProvider;
import net.stln.magitech.datagen.server.tag.MagitechDamageTypeTagsProvider;
import net.stln.magitech.datagen.server.tag.MagitechItemTagsProvider;

@EventBusSubscriber(modid = Magitech.MOD_ID)
public class MagitechDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider =
                generator
                        .addProvider(
                                true,
                                new MagitechDataRegistryProvider(
                                        packOutput, event.getLookupProvider()))
                        .getRegistryProvider();

        // Server
        generator.addProvider(
                event.includeServer(), new MagitechDataMapProvider(packOutput, lookupProvider));
        generator.addProvider(
                event.includeServer(), new MagitechRecipeProvider(packOutput, lookupProvider));
        generator.addProvider(
                event.includeServer(),
                new LootTableProvider(
                        packOutput,
                        Collections.emptySet(),
                        List.of(
                                new LootTableProvider.SubProviderEntry(
                                        MagitechBlockLootProvider::new, LootContextParamSets.BLOCK),
                                new LootTableProvider.SubProviderEntry(
                                        MagitechEntityLootTableProvider::new,
                                        LootContextParamSets.ENTITY)),
                        lookupProvider));

        var blockTags =
                generator
                        .addProvider(
                                event.includeServer(),
                                new MagitechBlockTagsProvider(
                                        packOutput, lookupProvider, fileHelper))
                        .contentsGetter();
        generator.addProvider(
                event.includeServer(),
                new MagitechItemTagsProvider(packOutput, lookupProvider, blockTags, fileHelper));

        generator.addProvider(
                event.includeServer(),
                new MagitechBiomeTagsProvider(packOutput, lookupProvider, fileHelper));
        generator.addProvider(
                event.includeServer(),
                new MagitechDamageTypeTagsProvider(packOutput, lookupProvider, fileHelper));

        // Client
        generator.addProvider(
                event.includeClient(), new MagitechItemModelProvider(packOutput, fileHelper));
        generator.addProvider(
                event.includeClient(), new MagitechBlockStateProvider(packOutput, fileHelper));
    }
}
