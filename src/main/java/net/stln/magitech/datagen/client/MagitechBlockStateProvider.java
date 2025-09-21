package net.stln.magitech.datagen.client;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechBlocks;

public class MagitechBlockStateProvider extends BlockStateProvider {

    public MagitechBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Magitech.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        sideBottomTopBlockWithItem(MagitechBlocks.ENGINEERING_WORKBENCH.get());
        sideBottomTopBlockWithItem(MagitechBlocks.ASSEMBLY_WORKBENCH.get());
        sideBottomTopBlockWithItem(MagitechBlocks.REPAIRING_WORKBENCH.get());
        sideBottomTopBlockWithItem(MagitechBlocks.UPGRADE_WORKBENCH.get());
        handModeledBlockWithItem(MagitechBlocks.ALCHEMETRIC_PYLON.get());
        handModeledBlockWithItem(MagitechBlocks.ATHANOR_PILLAR.get());
        handModeledBlockWithItem(MagitechBlocks.ZARDIUS_CRUCIBLE.get());
        directionalHandModeledBlockWithItem(MagitechBlocks.MANA_NODE.get());
        handModeledBlockWithInventoryModelItem(MagitechBlocks.MANA_VESSEL.get());
        blockWithItem(MagitechBlocks.FLUORITE_ORE.get());
        blockWithItem(MagitechBlocks.DEEPSLATE_FLUORITE_ORE.get());
        blockWithItem(MagitechBlocks.TOURMALINE_ORE.get());
        blockWithItem(MagitechBlocks.DEEPSLATE_TOURMALINE_ORE.get());
        blockWithItem(MagitechBlocks.SULFUR_BLOCK.get());
        blockWithItem(MagitechBlocks.ALCHECRYSITE.get());
        stairsBlockWithItem(
                MagitechBlocks.ALCHECRYSITE_STAIRS.get(), MagitechBlocks.ALCHECRYSITE.get());
        slabBlockWithItem(
                MagitechBlocks.ALCHECRYSITE_SLAB.get(), MagitechBlocks.ALCHECRYSITE.get());
        wallBlockWithItem(
                MagitechBlocks.ALCHECRYSITE_WALL.get(), MagitechBlocks.ALCHECRYSITE.get());
        blockWithItem(MagitechBlocks.POLISHED_ALCHECRYSITE.get());
        stairsBlockWithItem(
                MagitechBlocks.POLISHED_ALCHECRYSITE_STAIRS.get(),
                MagitechBlocks.POLISHED_ALCHECRYSITE.get());
        slabBlockWithItem(
                MagitechBlocks.POLISHED_ALCHECRYSITE_SLAB.get(),
                MagitechBlocks.POLISHED_ALCHECRYSITE.get());
        wallBlockWithItem(
                MagitechBlocks.POLISHED_ALCHECRYSITE_WALL.get(),
                MagitechBlocks.POLISHED_ALCHECRYSITE.get());
        blockWithItem(MagitechBlocks.ALCHECRYSITE_BRICKS.get());
        stairsBlockWithItem(
                MagitechBlocks.ALCHECRYSITE_BRICK_STAIRS.get(),
                MagitechBlocks.ALCHECRYSITE_BRICKS.get());
        slabBlockWithItem(
                MagitechBlocks.ALCHECRYSITE_BRICK_SLAB.get(),
                MagitechBlocks.ALCHECRYSITE_BRICKS.get());
        wallBlockWithItem(
                MagitechBlocks.ALCHECRYSITE_BRICK_WALL.get(),
                MagitechBlocks.ALCHECRYSITE_BRICKS.get());
        blockWithItem(MagitechBlocks.ALCHECRYSITE_TILES.get());
        blockWithItem(MagitechBlocks.FLUORITE_BLOCK.get());
        blockWithItem(MagitechBlocks.FLUORITE_BRICKS.get());
        stairsBlockWithItem(
                MagitechBlocks.FLUORITE_BRICK_STAIRS.get(), MagitechBlocks.FLUORITE_BRICKS.get());
        slabBlockWithItem(
                MagitechBlocks.FLUORITE_BRICK_SLAB.get(), MagitechBlocks.FLUORITE_BRICKS.get());
        wallBlockWithItem(
                MagitechBlocks.FLUORITE_BRICK_WALL.get(), MagitechBlocks.FLUORITE_BRICKS.get());
        logBlockWithItem(MagitechBlocks.CELIFERN_LOG.get());
        woodBlockWithItem(MagitechBlocks.CELIFERN_WOOD.get());
        logBlockWithItem(MagitechBlocks.STRIPPED_CELIFERN_LOG.get());
        woodBlockWithItem(MagitechBlocks.STRIPPED_CELIFERN_WOOD.get());
        blockWithItem(MagitechBlocks.CELIFERN_PLANKS.get());
        stairsBlockWithItem(
                MagitechBlocks.CELIFERN_STAIRS.get(), MagitechBlocks.CELIFERN_PLANKS.get());
        slabBlockWithItem(MagitechBlocks.CELIFERN_SLAB.get(), MagitechBlocks.CELIFERN_PLANKS.get());
        fenceBlockWithItem(
                MagitechBlocks.CELIFERN_FENCE.get(), MagitechBlocks.CELIFERN_PLANKS.get());
        fenceGateBlockWithItem(
                MagitechBlocks.CELIFERN_FENCE_GATE.get(), MagitechBlocks.CELIFERN_PLANKS.get());
        doorBlock(MagitechBlocks.CELIFERN_DOOR.get());
        trapdoorBlockWithItem(MagitechBlocks.CELIFERN_TRAPDOOR.get(), true);
        pressurePlateBlockWithItem(
                MagitechBlocks.CELIFERN_PRESSURE_PLATE.get(), MagitechBlocks.CELIFERN_PLANKS.get());
        buttonBlockWithItem(
                MagitechBlocks.CELIFERN_BUTTON.get(), MagitechBlocks.CELIFERN_PLANKS.get());
        leavesBlockWithItem(MagitechBlocks.CELIFERN_LEAVES.get());
        saplingBlock(MagitechBlocks.CELIFERN_SAPLING.get());
        signBlock(
                MagitechBlocks.CELIFERN_SIGN.get(),
                MagitechBlocks.CELIFERN_WALL_SIGN.get(),
                MagitechBlocks.CELIFERN_PLANKS.get());
        hangingSignBlock(
                MagitechBlocks.CELIFERN_HANGING_SIGN.get(),
                MagitechBlocks.CELIFERN_WALL_HANGING_SIGN.get(),
                MagitechBlocks.STRIPPED_CELIFERN_LOG.get());
        logBlockWithItem(MagitechBlocks.CHARCOAL_BIRCH_LOG.get());
        woodBlockWithItem(MagitechBlocks.CHARCOAL_BIRCH_WOOD.get());
        logBlockWithItem(MagitechBlocks.STRIPPED_CHARCOAL_BIRCH_LOG.get());
        woodBlockWithItem(MagitechBlocks.STRIPPED_CHARCOAL_BIRCH_WOOD.get());
        blockWithItem(MagitechBlocks.CHARCOAL_BIRCH_PLANKS.get());
        stairsBlockWithItem(
                MagitechBlocks.CHARCOAL_BIRCH_STAIRS.get(),
                MagitechBlocks.CHARCOAL_BIRCH_PLANKS.get());
        slabBlockWithItem(
                MagitechBlocks.CHARCOAL_BIRCH_SLAB.get(),
                MagitechBlocks.CHARCOAL_BIRCH_PLANKS.get());
        fenceBlockWithItem(
                MagitechBlocks.CHARCOAL_BIRCH_FENCE.get(),
                MagitechBlocks.CHARCOAL_BIRCH_PLANKS.get());
        fenceGateBlockWithItem(
                MagitechBlocks.CHARCOAL_BIRCH_FENCE_GATE.get(),
                MagitechBlocks.CHARCOAL_BIRCH_PLANKS.get());
        doorBlock(MagitechBlocks.CHARCOAL_BIRCH_DOOR.get());
        trapdoorBlockWithItem(MagitechBlocks.CHARCOAL_BIRCH_TRAPDOOR.get(), true);
        pressurePlateBlockWithItem(
                MagitechBlocks.CHARCOAL_BIRCH_PRESSURE_PLATE.get(),
                MagitechBlocks.CHARCOAL_BIRCH_PLANKS.get());
        buttonBlockWithItem(
                MagitechBlocks.CHARCOAL_BIRCH_BUTTON.get(),
                MagitechBlocks.CHARCOAL_BIRCH_PLANKS.get());
        leavesBlockWithItem(MagitechBlocks.CHARCOAL_BIRCH_LEAVES.get());
        saplingBlock(MagitechBlocks.CHARCOAL_BIRCH_SAPLING.get());
        signBlock(
                MagitechBlocks.CHARCOAL_BIRCH_SIGN.get(),
                MagitechBlocks.CHARCOAL_BIRCH_WALL_SIGN.get(),
                MagitechBlocks.CHARCOAL_BIRCH_PLANKS.get());
        hangingSignBlock(
                MagitechBlocks.CHARCOAL_BIRCH_HANGING_SIGN.get(),
                MagitechBlocks.CHARCOAL_BIRCH_WALL_HANGING_SIGN.get(),
                MagitechBlocks.STRIPPED_CHARCOAL_BIRCH_LOG.get());
        grassBlockWithItem(
                MagitechBlocks.SCORCHED_GRASS_SOIL.get(), MagitechBlocks.SCORCHED_SOIL.get());
        blockWithItem(MagitechBlocks.SCORCHED_SOIL.get());
    }

    private String getName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    private void blockItem(Block block) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(blockTexture(block)));
    }

    private void handModeledBlockWithItem(Block block) {
        simpleBlock(
                block,
                new ModelFile.ExistingModelFile(
                        blockTexture(block), this.models().existingFileHelper));
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(blockTexture(block)));
    }

    private void handModeledBlockWithInventoryModelItem(Block block) {
        simpleBlock(
                block,
                new ModelFile.ExistingModelFile(
                        blockTexture(block), this.models().existingFileHelper));
        simpleBlockItem(
                block,
                new ModelFile.UncheckedModelFile(blockTexture(block).withSuffix("_inventory")));
    }

    private void sideBottomTopBlockWithItem(Block block) {
        simpleBlockWithItem(
                block,
                models().cubeBottomTop(
                                getName(block),
                                blockTexture(block).withSuffix("_side"),
                                blockTexture(block).withSuffix("_bottom"),
                                blockTexture(block).withSuffix("_top")));
        blockItem(block);
    }

    private void grassBlockWithItem(Block block, Block soil) {
        simpleBlockWithItem(
                block,
                models().cubeBottomTop(
                                getName(block),
                                blockTexture(block).withSuffix("_side"),
                                blockTexture(soil),
                                blockTexture(block).withSuffix("_top")));
        blockItem(block);
    }

    private void directionalHandModeledBlockWithItem(Block block) {
        directionalBlock(
                block,
                new ModelFile.ExistingModelFile(
                        blockTexture(block), this.models().existingFileHelper));
        blockItem(block);
    }

    private void logBlockWithItem(Block block) {
        logBlock((RotatedPillarBlock) block);
        blockItem(block);
    }

    private void woodBlockWithItem(Block block) {
        ResourceLocation resourceLocation =
                ResourceLocation.parse(blockTexture(block).toString().replace("_wood", "_log"));
        axisBlock((RotatedPillarBlock) block, resourceLocation, resourceLocation);
        blockItem(block);
    }

    private void stairsBlockWithItem(Block block, Block fullTextureBlock) {
        stairsBlock((StairBlock) block, blockTexture(fullTextureBlock));
        blockItem(block);
    }

    private void slabBlockWithItem(Block block, Block fullTextureBlock) {
        slabBlock(
                (SlabBlock) block, blockTexture(fullTextureBlock), blockTexture(fullTextureBlock));
        blockItem(block);
    }

    private void fenceBlockWithItem(Block block, Block fullTextureBlock) {
        fenceBlock((FenceBlock) block, blockTexture(fullTextureBlock));
        simpleBlockItem(
                block,
                models().fenceInventory(
                                getName(block) + "_inventory", blockTexture(fullTextureBlock)));
    }

    private void wallBlockWithItem(Block block, Block fullTextureBlock) {
        wallBlock((WallBlock) block, blockTexture(fullTextureBlock));
        simpleBlockItem(
                block,
                models().wallInventory(
                                getName(block) + "_inventory", blockTexture(fullTextureBlock)));
    }

    private void fenceGateBlockWithItem(Block block, Block fullTextureBlock) {
        fenceGateBlock((FenceGateBlock) block, blockTexture(fullTextureBlock));
        blockItem(block);
    }

    private void doorBlock(Block block) {
        doorBlockWithRenderType(
                (DoorBlock) block,
                blockTexture(block).withSuffix("_bottom"),
                blockTexture(block).withSuffix("_top"),
                "cutout");
    }

    private void trapdoorBlockWithItem(Block block, boolean orientable) {
        trapdoorBlockWithRenderType(
                (TrapDoorBlock) block, blockTexture(block), orientable, "cutout");
        simpleBlockItem(
                block, new ModelFile.UncheckedModelFile(blockTexture(block).withSuffix("_bottom")));
    }

    private void pressurePlateBlockWithItem(Block block, Block fullTextureBlock) {
        pressurePlateBlock((PressurePlateBlock) block, blockTexture(fullTextureBlock));
        blockItem(block);
    }

    private void buttonBlockWithItem(Block block, Block fullTextureBlock) {
        buttonBlock((ButtonBlock) block, blockTexture(fullTextureBlock));
        simpleBlockItem(
                block,
                models().buttonInventory(
                                getName(block) + "_inventory", blockTexture(fullTextureBlock)));
    }

    private void leavesBlockWithItem(Block block) {
        simpleBlockWithItem(
                block,
                models().singleTexture(
                                getName(block),
                                ResourceLocation.parse("minecraft:block/leaves"),
                                "all",
                                blockTexture(block))
                        .renderType("cutout"));
        blockItem(block);
    }

    private void saplingBlock(Block block) {
        simpleBlock(
                block, models().cross(getName(block), blockTexture(block)).renderType("cutout"));
    }

    private void signBlock(Block sign, Block wallSign, Block fullTextureBlock) {
        signBlock(
                (StandingSignBlock) sign, (WallSignBlock) wallSign, blockTexture(fullTextureBlock));
    }

    private void hangingSignBlock(Block sign, Block wallSign, Block fullTextureBlock) {
        hangingSignBlock(
                (CeilingHangingSignBlock) sign,
                (WallHangingSignBlock) wallSign,
                blockTexture(fullTextureBlock));
    }

    private void blockWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }
}
