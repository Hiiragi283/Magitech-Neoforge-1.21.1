package net.stln.magitech.datagen.server.tag;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechItems;
import net.stln.magitech.tag.MagitechTags;

import org.jetbrains.annotations.NotNull;

import top.theillusivec4.curios.api.CuriosTags;

public class MagitechItemTagsProvider extends ItemTagsProvider {
    public MagitechItemTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagsProvider.TagLookup<Block>> blockTags,
            ExistingFileHelper fileHelper) {
        super(output, lookupProvider, blockTags, Magitech.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        copy();
        addMaterials();

        tag(ItemTags.FOOT_ARMOR)
                .add(MagitechItems.AETHER_LIFTER.get())
                .add(MagitechItems.FLAMGLIDE_STRIDER.get());

        tag(Tags.Items.FOODS_BERRY).add(MagitechItems.MANA_BERRIES.get());

        // Curios
        tag(CuriosTags.RING)
                .add(MagitechItems.CHARGEBIND_RING.get())
                .add(MagitechItems.DAWN_RING.get())
                .add(MagitechItems.FLUXBOUND_RING.get())
                .add(MagitechItems.GALEVENT_RING.get())
                .add(MagitechItems.MANA_RING.get())
                .add(MagitechItems.TORSION_RING.get())
                .add(MagitechItems.UMBRAL_RING.get());
    }

    private void copy() {
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(MagitechTags.Blocks.ORES_FLUORITE, MagitechTags.Items.ORES_FLUORITE);
        copy(MagitechTags.Blocks.ORES_TOURMALINE, MagitechTags.Items.ORES_TOURMALINE);

        copy(Tags.Blocks.STRIPPED_LOGS, Tags.Items.STRIPPED_LOGS);
        copy(Tags.Blocks.STRIPPED_WOODS, Tags.Items.STRIPPED_WOODS);
    }

    private void addMaterials() {
        // Ingots

        // Gems
        tag(Tags.Items.GEMS)
                .addTag(MagitechTags.Items.GEMS_CITRINE)
                .addTag(MagitechTags.Items.GEMS_FLUORITE)
                .addTag(MagitechTags.Items.GEMS_MANA_CHARGED_FLUORITE)
                .addTag(MagitechTags.Items.GEMS_REDSTONE_CRYSTAL)
                .addTag(MagitechTags.Items.GEMS_SULFUR)
                .addTag(MagitechTags.Items.GEMS_TOURMALINE);

        tag(MagitechTags.Items.GEMS_CITRINE).add(MagitechItems.CITRINE.get());
        tag(MagitechTags.Items.GEMS_FLUORITE).add(MagitechItems.FLUORITE.get());
        tag(MagitechTags.Items.GEMS_MANA_CHARGED_FLUORITE)
                .add(MagitechItems.MANA_CHARGED_FLUORITE.get());
        tag(MagitechTags.Items.GEMS_REDSTONE_CRYSTAL).add(MagitechItems.REDSTONE_CRYSTAL.get());
        tag(MagitechTags.Items.GEMS_SULFUR).add(MagitechItems.SULFUR.get());
        tag(MagitechTags.Items.GEMS_TOURMALINE).add(MagitechItems.TOURMALINE.get());
    }
}
