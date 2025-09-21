package net.stln.magitech.init;

import java.util.function.UnaryOperator;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;
import net.stln.magitech.block.*;
import net.stln.magitech.item.TooltipTextBlockItem;
import net.stln.magitech.item.TooltipTextSignItem;
import net.stln.magitech.worldgen.tree.TreeGrowerInit;

public final class MagitechBlocks {
    public static final SoundType CRYSTAL_SOUND =
            new DeferredSoundType(
                    1.0F,
                    1.0F,
                    MagitechSounds.CRYSTAL_BREAK,
                    MagitechSounds.CRYSTAL_STEP,
                    MagitechSounds.CRYSTAL_PLACE,
                    MagitechSounds.CRYSTAL_HIT,
                    MagitechSounds.CRYSTAL_FALL);

    public static final SoundType ALCHECRYSITE_SOUND =
            new DeferredSoundType(
                    0.5F,
                    1.0F,
                    MagitechSounds.ALCHECRYSITE_BREAK,
                    MagitechSounds.ALCHECRYSITE_STEP,
                    MagitechSounds.ALCHECRYSITE_PLACE,
                    MagitechSounds.ALCHECRYSITE_HIT,
                    MagitechSounds.ALCHECRYSITE_FALL);

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Magitech.MOD_ID);

    public static final DeferredBlock<EnginneringWorkbenchBlock> ENGINEERING_WORKBENCH =
            BLOCKS.registerBlock(
                    "engineering_workbench",
                    EnginneringWorkbenchBlock::new,
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2F, 5.0F));

    public static final DeferredItem<BlockItem> ENGINEERING_WORKBENCH_ITEM =
            registerBlockItem(ENGINEERING_WORKBENCH);

    public static final DeferredBlock<AssemblyWorkbenchBlock> ASSEMBLY_WORKBENCH =
            BLOCKS.registerBlock(
                    "assembly_workbench",
                    AssemblyWorkbenchBlock::new,
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2F, 5.0F));

    public static final DeferredItem<BlockItem> ASSEMBLY_WORKBENCH_ITEM =
            registerBlockItem(ASSEMBLY_WORKBENCH);

    public static final DeferredBlock<RepairingWorkbenchBlock> REPAIRING_WORKBENCH =
            BLOCKS.registerBlock(
                    "repairing_workbench",
                    RepairingWorkbenchBlock::new,
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2F, 5.0F));

    public static final DeferredItem<BlockItem> REPAIRING_WORKBENCH_ITEM =
            registerBlockItem(REPAIRING_WORKBENCH);

    public static final DeferredBlock<UpgradeWorkbenchBlock> UPGRADE_WORKBENCH =
            BLOCKS.registerBlock(
                    "upgrade_workbench",
                    UpgradeWorkbenchBlock::new,
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2F, 5.0F));

    public static final DeferredItem<BlockItem> UPGRADE_WORKBENCH_ITEM =
            registerBlockItem(UPGRADE_WORKBENCH);

    public static final DeferredBlock<ZardiusCrucibleBlock> ZARDIUS_CRUCIBLE =
            BLOCKS.registerBlock(
                    "zardius_crucible",
                    ZardiusCrucibleBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                            .sound(SoundType.NETHERITE_BLOCK)
                            .lightLevel((blockState) -> 5)
                            .noOcclusion());

    public static final DeferredItem<BlockItem> ZARDIUS_CRUCIBLE_ITEM =
            registerBlockItem(ZARDIUS_CRUCIBLE);

    public static final DeferredBlock<AlchemetricPylonBlock> ALCHEMETRIC_PYLON =
            BLOCKS.registerBlock(
                    "alchemetric_pylon",
                    AlchemetricPylonBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> ALCHEMETRIC_PYLON_ITEM =
            registerBlockItem(ALCHEMETRIC_PYLON);

    public static final DeferredBlock<AthanorPillarBlock> ATHANOR_PILLAR =
            BLOCKS.registerBlock(
                    "athanor_pillar",
                    AthanorPillarBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> ATHANOR_PILLAR_ITEM =
            registerBlockItem(ATHANOR_PILLAR);

    public static final DeferredBlock<ManaNodeBlock> MANA_NODE =
            BLOCKS.registerBlock(
                    "mana_node",
                    ManaNodeBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                            .sound(CRYSTAL_SOUND)
                            .lightLevel((blockState) -> 10));

    public static final DeferredItem<BlockItem> MANA_NODE_ITEM = registerBlockItem(MANA_NODE);

    public static final DeferredBlock<ManaVesselBlock> MANA_VESSEL =
            BLOCKS.registerBlock(
                    "mana_vessel",
                    ManaVesselBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                            .sound(SoundType.NETHERITE_BLOCK)
                            .lightLevel((blockState) -> 5)
                            .noOcclusion());

    public static final DeferredItem<BlockItem> MANA_VESSEL_ITEM = registerBlockItem(MANA_VESSEL);

    public static final DeferredBlock<DropExperienceBlock> FLUORITE_ORE =
            BLOCKS.registerBlock(
                    "fluorite_ore",
                    (properties) ->
                            new DropExperienceBlock(
                                    UniformInt.of(1, 4),
                                    properties
                                            .mapColor(MapColor.STONE)
                                            .instrument(NoteBlockInstrument.BASEDRUM)
                                            .requiresCorrectToolForDrops()
                                            .strength(3.0F, 3.0F)));

    public static final DeferredItem<BlockItem> FLUORITE_ORE_ITEM = registerBlockItem(FLUORITE_ORE);

    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_FLUORITE_ORE =
            BLOCKS.registerBlock(
                    "deepslate_fluorite_ore",
                    (properties) -> new DropExperienceBlock(UniformInt.of(1, 4), properties),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.DEEPSLATE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .strength(4.5F, 3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE));

    public static final DeferredItem<BlockItem> DEEPSLATE_FLUORITE_ORE_ITEM =
            registerBlockItem(DEEPSLATE_FLUORITE_ORE);

    public static final DeferredBlock<DropExperienceBlock> TOURMALINE_ORE =
            BLOCKS.registerBlock(
                    "tourmaline_ore",
                    (properties) ->
                            new DropExperienceBlock(
                                    UniformInt.of(1, 4),
                                    properties
                                            .mapColor(MapColor.STONE)
                                            .instrument(NoteBlockInstrument.BASEDRUM)
                                            .requiresCorrectToolForDrops()
                                            .strength(3.0F, 3.0F)));

    public static final DeferredItem<BlockItem> TOURMALINE_ORE_ITEM =
            registerBlockItem(TOURMALINE_ORE);

    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_TOURMALINE_ORE =
            BLOCKS.registerBlock(
                    "deepslate_tourmaline_ore",
                    (properties) -> new DropExperienceBlock(UniformInt.of(1, 4), properties),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.DEEPSLATE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .strength(4.5F, 3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE));

    public static final DeferredItem<BlockItem> DEEPSLATE_TOURMALINE_ORE_ITEM =
            registerBlockItem(DEEPSLATE_TOURMALINE_ORE);

    public static final DeferredBlock<FluoriteCrystalClusterBlock> FLUORITE_CRYSTAL_CLUSTER =
            BLOCKS.registerBlock(
                    "fluorite_crystal_cluster",
                    (properties) ->
                            new FluoriteCrystalClusterBlock(
                                    UniformInt.of(0, 2),
                                    properties
                                            .mapColor(MapColor.COLOR_CYAN)
                                            .lightLevel(p_187431_ -> 8)
                                            .sound(CRYSTAL_SOUND)
                                            .forceSolidOn()
                                            .noOcclusion()
                                            .strength(0.5F, 2.0F)
                                            .requiresCorrectToolForDrops()
                                            .pushReaction(PushReaction.DESTROY)));

    public static final DeferredItem<BlockItem> FLUORITE_CRYSTAL_CLUSTER_ITEM =
            registerBlockItem(FLUORITE_CRYSTAL_CLUSTER);

    public static final DeferredBlock<RedstoneCrystalClusterBlock> REDSTONE_CRYSTAL_CLUSTER =
            BLOCKS.registerBlock(
                    "redstone_crystal_cluster",
                    (properties) ->
                            new RedstoneCrystalClusterBlock(
                                    UniformInt.of(1, 3),
                                    properties
                                            .mapColor(MapColor.COLOR_RED)
                                            .lightLevel(p_187431_ -> 5)
                                            .sound(CRYSTAL_SOUND)
                                            .forceSolidOn()
                                            .noOcclusion()
                                            .strength(0.5F, 2.0F)
                                            .requiresCorrectToolForDrops()
                                            .pushReaction(PushReaction.DESTROY)));

    public static final DeferredItem<BlockItem> REDSTONE_CRYSTAL_CLUSTER_ITEM =
            registerBlockItem(REDSTONE_CRYSTAL_CLUSTER);

    public static final DeferredBlock<CrystalClusterBlock> SULFUR_CRYSTAL_CLUSTER =
            BLOCKS.registerBlock(
                    "sulfur_crystal_cluster",
                    (properties) ->
                            new CrystalClusterBlock(
                                    UniformInt.of(0, 1),
                                    properties
                                            .mapColor(MapColor.COLOR_YELLOW)
                                            .sound(SoundType.TUFF)
                                            .forceSolidOn()
                                            .noOcclusion()
                                            .strength(0.5F, 2.0F)
                                            .requiresCorrectToolForDrops()
                                            .pushReaction(PushReaction.DESTROY)));

    public static final DeferredItem<BlockItem> SULFUR_CRYSTAL_CLUSTER_ITEM =
            registerBlockItem(SULFUR_CRYSTAL_CLUSTER);

    public static final DeferredBlock<Block> SULFUR_BLOCK =
            BLOCKS.registerBlock(
                    "sulfur_block",
                    Block::new,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_YELLOW)
                            .sound(SoundType.TUFF)
                            .requiresCorrectToolForDrops()
                            .strength(1.5F, 2.0F));

    public static final DeferredItem<BlockItem> SULFUR_BLOCK_ITEM = registerBlockItem(SULFUR_BLOCK);

    public static final DeferredBlock<Block> ALCHECRYSITE =
            BLOCKS.registerBlock(
                    "alchecrysite",
                    Block::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> ALCHECRYSITE_ITEM = registerBlockItem(ALCHECRYSITE);

    public static final DeferredBlock<Block> ALCHECRYSITE_STAIRS =
            BLOCKS.registerBlock(
                    "alchecrysite_stairs",
                    (properties) ->
                            new StairBlock(ALCHECRYSITE.get().defaultBlockState(), properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_STAIRS)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> ALCHECRYSITE_STAIRS_ITEM =
            registerBlockItem(ALCHECRYSITE_STAIRS);

    public static final DeferredBlock<Block> ALCHECRYSITE_SLAB =
            BLOCKS.registerBlock(
                    "alchecrysite_slab",
                    SlabBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_SLAB)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> ALCHECRYSITE_SLAB_ITEM =
            registerBlockItem(ALCHECRYSITE_SLAB);

    public static final DeferredBlock<Block> ALCHECRYSITE_WALL =
            BLOCKS.registerBlock(
                    "alchecrysite_wall",
                    WallBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_WALL)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> ALCHECRYSITE_WALL_ITEM =
            registerBlockItem(ALCHECRYSITE_WALL);

    public static final DeferredBlock<Block> POLISHED_ALCHECRYSITE =
            BLOCKS.registerBlock(
                    "polished_alchecrysite",
                    Block::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> POLISHED_ALCHECRYSITE_ITEM =
            registerBlockItem(POLISHED_ALCHECRYSITE);

    public static final DeferredBlock<Block> POLISHED_ALCHECRYSITE_STAIRS =
            BLOCKS.registerBlock(
                    "polished_alchecrysite_stairs",
                    (properties) ->
                            new StairBlock(
                                    POLISHED_ALCHECRYSITE.get().defaultBlockState(), properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_STAIRS)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> POLISHED_ALCHECRYSITE_STAIRS_ITEM =
            registerBlockItem(POLISHED_ALCHECRYSITE_STAIRS);

    public static final DeferredBlock<Block> POLISHED_ALCHECRYSITE_SLAB =
            BLOCKS.registerBlock(
                    "polished_alchecrysite_slab",
                    SlabBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_SLAB)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> POLISHED_ALCHECRYSITE_SLAB_ITEM =
            registerBlockItem(POLISHED_ALCHECRYSITE_SLAB);

    public static final DeferredBlock<Block> POLISHED_ALCHECRYSITE_WALL =
            BLOCKS.registerBlock(
                    "polished_alchecrysite_wall",
                    WallBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_WALL)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> POLISHED_ALCHECRYSITE_WALL_ITEM =
            registerBlockItem(POLISHED_ALCHECRYSITE_WALL);

    public static final DeferredBlock<Block> ALCHECRYSITE_BRICKS =
            BLOCKS.registerSimpleBlock(
                    "alchecrysite_bricks",
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> ALCHECRYSITE_BRICKS_ITEM =
            registerBlockItem(ALCHECRYSITE_BRICKS);

    public static final DeferredBlock<Block> ALCHECRYSITE_BRICK_STAIRS =
            BLOCKS.registerBlock(
                    "alchecrysite_brick_stairs",
                    (properties) ->
                            new StairBlock(
                                    ALCHECRYSITE_BRICKS.get().defaultBlockState(), properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_STAIRS)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> ALCHECRYSITE_BRICK_STAIRS_ITEM =
            registerBlockItem(ALCHECRYSITE_BRICK_STAIRS);

    public static final DeferredBlock<Block> ALCHECRYSITE_BRICK_SLAB =
            BLOCKS.registerBlock(
                    "alchecrysite_brick_slab",
                    SlabBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_SLAB)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> ALCHECRYSITE_BRICK_SLAB_ITEM =
            registerBlockItem(ALCHECRYSITE_BRICK_SLAB);

    public static final DeferredBlock<Block> ALCHECRYSITE_BRICK_WALL =
            BLOCKS.registerBlock(
                    "alchecrysite_brick_wall",
                    WallBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_WALL)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> ALCHECRYSITE_BRICK_WALL_ITEM =
            registerBlockItem(ALCHECRYSITE_BRICK_WALL);

    public static final DeferredBlock<Block> ALCHECRYSITE_TILES =
            BLOCKS.registerSimpleBlock(
                    "alchecrysite_tiles",
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_TILES)
                            .sound(ALCHECRYSITE_SOUND));

    public static final DeferredItem<BlockItem> ALCHECRYSITE_TILES_ITEM =
            registerBlockItem(ALCHECRYSITE_TILES);

    private static final BlockBehaviour.Properties FLUORITE_PROPERTIES =
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_CYAN)
                    .sound(CRYSTAL_SOUND)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 2.0F);

    public static final DeferredBlock<Block> FLUORITE_BLOCK =
            BLOCKS.registerSimpleBlock("fluorite_block", FLUORITE_PROPERTIES);

    public static final DeferredItem<BlockItem> FLUORITE_BLOCK_ITEM =
            registerBlockItem(FLUORITE_BLOCK);

    public static final DeferredBlock<Block> FLUORITE_BRICKS =
            BLOCKS.registerSimpleBlock("fluorite_bricks", FLUORITE_PROPERTIES);

    public static final BlockSetType FLUORITE_BRICK_SET_TYPE =
            BlockSetType.register(new BlockSetType("magitech:fluorite_brick"));

    public static final DeferredItem<BlockItem> FLUORITE_BRICKS_ITEM =
            registerBlockItem(FLUORITE_BRICKS);

    public static final DeferredBlock<Block> FLUORITE_BRICK_STAIRS =
            BLOCKS.registerBlock(
                    "fluorite_brick_stairs",
                    properties ->
                            new StairBlock(FLUORITE_BRICKS.get().defaultBlockState(), properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS)
                            .sound(CRYSTAL_SOUND));

    public static final DeferredItem<BlockItem> FLUORITE_BRICK_STAIRS_ITEM =
            registerBlockItem(FLUORITE_BRICK_STAIRS);

    public static final DeferredBlock<Block> FLUORITE_BRICK_SLAB =
            BLOCKS.registerBlock(
                    "fluorite_brick_slab",
                    SlabBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS)
                            .sound(CRYSTAL_SOUND));

    public static final DeferredItem<BlockItem> FLUORITE_BRICK_SLAB_ITEM =
            registerBlockItem(FLUORITE_BRICK_SLAB);

    public static final DeferredBlock<Block> FLUORITE_BRICK_WALL =
            BLOCKS.registerBlock(
                    "fluorite_brick_wall",
                    WallBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)
                            .sound(CRYSTAL_SOUND));

    public static final DeferredItem<BlockItem> FLUORITE_BRICK_WALL_ITEM =
            registerBlockItem(FLUORITE_BRICK_WALL);

    public static final BlockSetType CELIFERN_SET_TYPE =
            BlockSetType.register(new BlockSetType("magitech:celifern"));
    public static final WoodType CELIFERN_WOOD_TYPE =
            WoodType.register(new WoodType("magitech:celifern", CELIFERN_SET_TYPE));

    public static final DeferredBlock<RotatedPillarBlock> CELIFERN_LOG =
            BLOCKS.registerBlock(
                    "celifern_log",
                    RotatedPillarBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                            .mapColor(
                                    p_152624_ ->
                                            p_152624_.getValue(RotatedPillarBlock.AXIS)
                                                            == Direction.Axis.Y
                                                    ? MapColor.COLOR_GREEN
                                                    : MapColor.TERRACOTTA_GREEN));

    public static final DeferredItem<BlockItem> CELIFERN_LOG_ITEM = registerBlockItem(CELIFERN_LOG);

    public static final DeferredBlock<RotatedPillarBlock> CELIFERN_WOOD =
            BLOCKS.registerBlock(
                    "celifern_wood",
                    RotatedPillarBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                            .mapColor(MapColor.TERRACOTTA_GREEN));

    public static final DeferredItem<BlockItem> CELIFERN_WOOD_ITEM =
            registerBlockItem(CELIFERN_WOOD);

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CELIFERN_LOG =
            BLOCKS.registerBlock(
                    "stripped_celifern_log",
                    RotatedPillarBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                            .mapColor(MapColor.COLOR_GREEN));

    public static final DeferredItem<BlockItem> STRIPPED_CELIFERN_LOG_ITEM =
            registerBlockItem(STRIPPED_CELIFERN_LOG);

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CELIFERN_WOOD =
            BLOCKS.registerBlock(
                    "stripped_celifern_wood",
                    RotatedPillarBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                            .mapColor(MapColor.COLOR_GREEN));

    public static final DeferredItem<BlockItem> STRIPPED_CELIFERN_WOOD_ITEM =
            registerBlockItem(STRIPPED_CELIFERN_WOOD);

    public static final DeferredBlock<Block> CELIFERN_PLANKS =
            BLOCKS.registerSimpleBlock(
                    "celifern_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredItem<BlockItem> CELIFERN_PLANKS_ITEM =
            registerBlockItem(CELIFERN_PLANKS);

    public static final DeferredBlock<Block> CELIFERN_SLAB =
            BLOCKS.registerBlock(
                    "celifern_slab",
                    SlabBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));

    public static final DeferredItem<BlockItem> CELIFERN_SLAB_ITEM =
            registerBlockItem(CELIFERN_SLAB);

    public static final DeferredBlock<Block> CELIFERN_STAIRS =
            BLOCKS.registerBlock(
                    "celifern_stairs",
                    properties ->
                            new StairBlock(CELIFERN_PLANKS.get().defaultBlockState(), properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));

    public static final DeferredItem<BlockItem> CELIFERN_STAIRS_ITEM =
            registerBlockItem(CELIFERN_STAIRS);

    public static final DeferredBlock<Block> CELIFERN_DOOR =
            BLOCKS.registerBlock(
                    "celifern_door",
                    properties -> new DoorBlock(CELIFERN_SET_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));

    public static final DeferredItem<BlockItem> CELIFERN_DOOR_ITEM =
            registerBlockItem(CELIFERN_DOOR);

    public static final DeferredBlock<Block> CELIFERN_TRAPDOOR =
            BLOCKS.registerBlock(
                    "celifern_trapdoor",
                    properties -> new TrapDoorBlock(CELIFERN_SET_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    public static final DeferredItem<BlockItem> CELIFERN_TRAPDOOR_ITEM =
            registerBlockItem(CELIFERN_TRAPDOOR);

    public static final DeferredBlock<Block> CELIFERN_FENCE =
            BLOCKS.registerBlock(
                    "celifern_fence",
                    FenceBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));

    public static final DeferredItem<BlockItem> CELIFERN_FENCE_ITEM =
            registerBlockItem(CELIFERN_FENCE);

    public static final DeferredBlock<Block> CELIFERN_FENCE_GATE =
            BLOCKS.registerBlock(
                    "celifern_fence_gate",
                    properties -> new FenceGateBlock(CELIFERN_WOOD_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));

    public static final DeferredItem<BlockItem> CELIFERN_FENCE_GATE_ITEM =
            registerBlockItem(CELIFERN_FENCE_GATE);

    public static final DeferredBlock<Block> CELIFERN_PRESSURE_PLATE =
            BLOCKS.registerBlock(
                    "celifern_pressure_plate",
                    properties -> new PressurePlateBlock(CELIFERN_SET_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));

    public static final DeferredItem<BlockItem> CELIFERN_PRESSURE_PLATE_ITEM =
            registerBlockItem(CELIFERN_PRESSURE_PLATE);

    public static final DeferredBlock<Block> CELIFERN_BUTTON =
            BLOCKS.registerBlock(
                    "celifern_button",
                    properties -> new ButtonBlock(CELIFERN_SET_TYPE, 30, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON));

    public static final DeferredItem<BlockItem> CELIFERN_BUTTON_ITEM =
            registerBlockItem(CELIFERN_BUTTON);

    public static final DeferredBlock<Block> CELIFERN_SIGN =
            BLOCKS.registerBlock(
                    "celifern_sign",
                    properties -> new StandingSignBlock(CELIFERN_WOOD_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN));

    public static final DeferredBlock<Block> CELIFERN_WALL_SIGN =
            BLOCKS.registerBlock(
                    "celifern_wall_sign",
                    properties -> new WallSignBlock(CELIFERN_WOOD_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN));

    public static final DeferredItem<BlockItem> CELIFERN_SIGN_ITEM =
            MagitechItems.ITEMS.registerItem(
                    "celifern_sign",
                    properties ->
                            new TooltipTextSignItem(
                                    properties, CELIFERN_SIGN.get(), CELIFERN_WALL_SIGN.get()));

    public static final DeferredBlock<Block> CELIFERN_HANGING_SIGN =
            BLOCKS.register(
                    "celifern_hanging_sign",
                    key ->
                            new CeilingHangingSignBlock(
                                    CELIFERN_WOOD_TYPE,
                                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));

    public static final DeferredBlock<Block> CELIFERN_WALL_HANGING_SIGN =
            BLOCKS.registerBlock(
                    "celifern_wall_hanging_sign",
                    properties -> new WallHangingSignBlock(CELIFERN_WOOD_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN));

    public static final DeferredItem<BlockItem> CELIFERN_HANGING_SIGN_ITEM =
            MagitechItems.ITEMS.registerItem(
                    "celifern_hanging_sign",
                    properties ->
                            new TooltipTextSignItem(
                                    properties,
                                    CELIFERN_HANGING_SIGN.get(),
                                    CELIFERN_WALL_HANGING_SIGN.get()));

    public static final DeferredBlock<Block> CELIFERN_LEAVES =
            BLOCKS.registerBlock(
                    "celifern_leaves",
                    LeavesBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA_LEAVES));

    public static final DeferredItem<BlockItem> CELIFERN_LEAVES_ITEM =
            registerBlockItem(CELIFERN_LEAVES);

    public static final DeferredBlock<Block> CELIFERN_SAPLING =
            BLOCKS.registerBlock(
                    "celifern_sapling",
                    properties -> new SaplingBlock(TreeGrowerInit.CELIFERN, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    public static final DeferredItem<BlockItem> CELIFERN_SAPLING_ITEM =
            registerBlockItem(CELIFERN_SAPLING);

    public static final BlockSetType CHARCOAL_BIRCH_SET_TYPE =
            BlockSetType.register(new BlockSetType("magitech:charcoal_birch"));
    public static final WoodType CHARCOAL_BIRCH_WOOD_TYPE =
            WoodType.register(new WoodType("magitech:charcoal_birch", CHARCOAL_BIRCH_SET_TYPE));

    public static final DeferredBlock<RotatedPillarBlock> CHARCOAL_BIRCH_LOG =
            BLOCKS.registerBlock(
                    "charcoal_birch_log",
                    RotatedPillarBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                            .mapColor(
                                    p_152624_ ->
                                            p_152624_.getValue(RotatedPillarBlock.AXIS)
                                                            == Direction.Axis.Y
                                                    ? MapColor.COLOR_GREEN
                                                    : MapColor.TERRACOTTA_GREEN));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_LOG_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_LOG);

    public static final DeferredBlock<RotatedPillarBlock> CHARCOAL_BIRCH_WOOD =
            BLOCKS.registerBlock(
                    "charcoal_birch_wood",
                    RotatedPillarBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                            .mapColor(MapColor.TERRACOTTA_GREEN));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_WOOD_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_WOOD);

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHARCOAL_BIRCH_LOG =
            BLOCKS.registerBlock(
                    "stripped_charcoal_birch_log",
                    RotatedPillarBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                            .mapColor(MapColor.COLOR_GREEN));

    public static final DeferredItem<BlockItem> STRIPPED_CHARCOAL_BIRCH_LOG_ITEM =
            registerBlockItem(STRIPPED_CHARCOAL_BIRCH_LOG);

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHARCOAL_BIRCH_WOOD =
            BLOCKS.registerBlock(
                    "stripped_charcoal_birch_wood",
                    RotatedPillarBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                            .mapColor(MapColor.COLOR_GREEN));

    public static final DeferredItem<BlockItem> STRIPPED_CHARCOAL_BIRCH_WOOD_ITEM =
            registerBlockItem(STRIPPED_CHARCOAL_BIRCH_WOOD);

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_PLANKS =
            BLOCKS.registerSimpleBlock(
                    "charcoal_birch_planks",
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_PLANKS_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_PLANKS);

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_SLAB =
            BLOCKS.registerBlock(
                    "charcoal_birch_slab",
                    SlabBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_SLAB_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_SLAB);

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_STAIRS =
            BLOCKS.registerBlock(
                    "charcoal_birch_stairs",
                    properties ->
                            new StairBlock(
                                    CHARCOAL_BIRCH_PLANKS.get().defaultBlockState(), properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_STAIRS_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_STAIRS);

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_DOOR =
            BLOCKS.registerBlock(
                    "charcoal_birch_door",
                    properties -> new DoorBlock(CHARCOAL_BIRCH_SET_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_DOOR_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_DOOR);

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_TRAPDOOR =
            BLOCKS.registerBlock(
                    "charcoal_birch_trapdoor",
                    properties -> new TrapDoorBlock(CHARCOAL_BIRCH_SET_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_TRAPDOOR_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_TRAPDOOR);

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_FENCE =
            BLOCKS.registerBlock(
                    "charcoal_birch_fence",
                    FenceBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_FENCE_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_FENCE);

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_FENCE_GATE =
            BLOCKS.registerBlock(
                    "charcoal_birch_fence_gate",
                    properties -> new FenceGateBlock(CHARCOAL_BIRCH_WOOD_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_FENCE_GATE_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_FENCE_GATE);

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_PRESSURE_PLATE =
            BLOCKS.registerBlock(
                    "charcoal_birch_pressure_plate",
                    properties -> new PressurePlateBlock(CHARCOAL_BIRCH_SET_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_PRESSURE_PLATE_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_PRESSURE_PLATE);

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_BUTTON =
            BLOCKS.registerBlock(
                    "charcoal_birch_button",
                    properties -> new ButtonBlock(CHARCOAL_BIRCH_SET_TYPE, 30, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_BUTTON_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_BUTTON);

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_SIGN =
            BLOCKS.registerBlock(
                    "charcoal_birch_sign",
                    properties -> new StandingSignBlock(CHARCOAL_BIRCH_WOOD_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN));

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_WALL_SIGN =
            BLOCKS.registerBlock(
                    "charcoal_birch_wall_sign",
                    properties -> new WallSignBlock(CHARCOAL_BIRCH_WOOD_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_SIGN_ITEM =
            MagitechItems.ITEMS.registerItem(
                    "charcoal_birch_sign",
                    properties ->
                            new TooltipTextSignItem(
                                    properties,
                                    CHARCOAL_BIRCH_SIGN.get(),
                                    CHARCOAL_BIRCH_WALL_SIGN.get()));

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_HANGING_SIGN =
            BLOCKS.registerBlock(
                    "charcoal_birch_hanging_sign",
                    properties -> new CeilingHangingSignBlock(CHARCOAL_BIRCH_WOOD_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN));

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_WALL_HANGING_SIGN =
            BLOCKS.registerBlock(
                    "charcoal_birch_wall_hanging_sign",
                    properties -> new WallHangingSignBlock(CHARCOAL_BIRCH_WOOD_TYPE, properties),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_HANGING_SIGN_ITEM =
            MagitechItems.ITEMS.registerItem(
                    "charcoal_birch_hanging_sign",
                    properties ->
                            new TooltipTextSignItem(
                                    properties,
                                    CHARCOAL_BIRCH_HANGING_SIGN.get(),
                                    CHARCOAL_BIRCH_WALL_HANGING_SIGN.get()));

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_LEAVES =
            BLOCKS.registerBlock(
                    "charcoal_birch_leaves",
                    LeavesBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA_LEAVES));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_LEAVES_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_LEAVES);

    public static final DeferredBlock<Block> CHARCOAL_BIRCH_SAPLING =
            BLOCKS.registerBlock(
                    "charcoal_birch_sapling",
                    key -> new SaplingBlock(TreeGrowerInit.CHARCOAL_BIRCH, key),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    public static final DeferredItem<BlockItem> CHARCOAL_BIRCH_SAPLING_ITEM =
            registerBlockItem(CHARCOAL_BIRCH_SAPLING);

    public static final DeferredBlock<Block> SCORCHED_GRASS_SOIL =
            BLOCKS.registerBlock(
                    "scorched_grass_soil",
                    ScorchedGrassSoilBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)
                            .sound(SoundType.ROOTED_DIRT)
                            .mapColor(MapColor.COLOR_LIGHT_GRAY));

    public static final DeferredItem<BlockItem> SCORCHED_GRASS_SOIL_ITEM =
            registerBlockItem(SCORCHED_GRASS_SOIL);

    public static final DeferredBlock<Block> SCORCHED_SOIL =
            BLOCKS.registerSimpleBlock(
                    "scorched_soil",
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
                            .sound(SoundType.ROOTED_DIRT)
                            .mapColor(MapColor.COLOR_BROWN));

    public static final DeferredItem<BlockItem> SCORCHED_SOIL_ITEM =
            registerBlockItem(SCORCHED_SOIL);

    public static final DeferredBlock<Block> MANA_BERRY_BUSH =
            BLOCKS.registerBlock(
                    "mana_berry_bush",
                    ManaBerryBushBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)
                            .lightLevel(p_187431_ -> 5));

    public static final DeferredBlock<Block> MISTALIA_PETALS =
            BLOCKS.registerBlock(
                    "mistalia_petals",
                    MistaliaPetalsBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS));

    public static final DeferredItem<BlockItem> MISTALIA_PETALS_ITEM =
            registerBlockItem(MISTALIA_PETALS);

    private static DeferredItem<BlockItem> registerBlockItem(DeferredBlock<?> block) {
        return registerBlockItem(block, UnaryOperator.identity());
    }

    private static DeferredItem<BlockItem> registerBlockItem(
            DeferredBlock<?> block, UnaryOperator<Item.Properties> operator) {
        return MagitechItems.ITEMS.registerItem(
                block.getId().getPath(),
                properties -> new TooltipTextBlockItem(block.get(), operator.apply(properties)));
    }

    public static void registerBlocks(IEventBus eventBus) {
        Magitech.LOGGER.info("Registering Blocks for" + Magitech.MOD_ID);
        BLOCKS.register(eventBus);
    }

    public static void registerStrippableBlocks() {
        StrippableBlockRegistry.register(CELIFERN_LOG.get(), STRIPPED_CELIFERN_LOG.get());
        StrippableBlockRegistry.register(CELIFERN_WOOD.get(), STRIPPED_CELIFERN_WOOD.get());
        StrippableBlockRegistry.register(
                CHARCOAL_BIRCH_LOG.get(), STRIPPED_CHARCOAL_BIRCH_LOG.get());
        StrippableBlockRegistry.register(
                CHARCOAL_BIRCH_WOOD.get(), STRIPPED_CHARCOAL_BIRCH_WOOD.get());
    }
}
