package net.stln.magitech.datagen.server.tag;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechBiomes;
import net.stln.magitech.tag.MagitechTags;

import org.jetbrains.annotations.NotNull;

public class MagitechBiomeTagsProvider extends TagsProvider<Biome> {
    public MagitechBiomeTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper fileHelper) {
        super(output, Registries.BIOME, lookupProvider, Magitech.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        var mistjade = MagitechBiomes.MISTJADE_FOREST;
        var scorched = MagitechBiomes.SCORCHED_PLAINS;

        tag(BiomeTags.HAS_MINESHAFT).add(mistjade);
        tag(BiomeTags.HAS_RUINED_PORTAL_STANDARD).add(mistjade);
        tag(BiomeTags.HAS_TRIAL_CHAMBERS).add(mistjade);
        tag(BiomeTags.IS_FOREST).add(mistjade);
        tag(BiomeTags.IS_OVERWORLD).add(mistjade);
        tag(BiomeTags.STRONGHOLD_BIASED_TO).add(mistjade);

        tag(MagitechTags.Biomes.HAS_CELIFERN_FOREST).add(mistjade);
        tag(MagitechTags.Biomes.HAS_CHARCOAL_BIRCH_FOREST).add(scorched);
        tag(MagitechTags.Biomes.HAS_MANA_BERRY_BUSH).add(mistjade);
        tag(MagitechTags.Biomes.HAS_MISTALIA_PETALS).add(mistjade);
        tag(MagitechTags.Biomes.IS_MISTJADE).add(mistjade);
        tag(MagitechTags.Biomes.IS_SCORCHED).add(scorched);
    }
}
