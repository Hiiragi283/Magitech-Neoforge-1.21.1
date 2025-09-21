package net.stln.magitech.datagen.server.tag;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechBlocks;
import net.stln.magitech.tag.MagitechTags;

import org.jetbrains.annotations.NotNull;

public class MagitechBlockTagsProvider extends IntrinsicHolderTagsProvider<Block> {
    @SuppressWarnings("deprecation")
    public MagitechBlockTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper fileHelper) {
        super(
                output,
                Registries.BLOCK,
                lookupProvider,
                block -> block.builtInRegistryHolder().getKey(),
                Magitech.MOD_ID,
                fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // Ores
        tag(Tags.Blocks.ORES)
                .addTag(MagitechTags.Blocks.ORES_FLUORITE)
                .addTag(MagitechTags.Blocks.ORES_TOURMALINE);

        tag(MagitechTags.Blocks.ORES_FLUORITE)
                .add(MagitechBlocks.FLUORITE_ORE.get())
                .add(MagitechBlocks.DEEPSLATE_FLUORITE_ORE.get());

        tag(MagitechTags.Blocks.ORES_TOURMALINE)
                .add(MagitechBlocks.TOURMALINE_ORE.get())
                .add(MagitechBlocks.DEEPSLATE_TOURMALINE_ORE.get());
        // Stripped Logs
        tag(Tags.Blocks.STRIPPED_LOGS)
                .add(MagitechBlocks.STRIPPED_CELIFERN_LOG.get())
                .add(MagitechBlocks.STRIPPED_CHARCOAL_BIRCH_LOG.get());
        // Stripped Woods
        tag(Tags.Blocks.STRIPPED_WOODS)
                .add(MagitechBlocks.STRIPPED_CELIFERN_WOOD.get())
                .add(MagitechBlocks.STRIPPED_CHARCOAL_BIRCH_WOOD.get());
    }
}
