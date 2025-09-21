package net.stln.magitech.datagen.server;

import java.util.Set;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.stln.magitech.init.MagitechBlocks;
import net.stln.magitech.init.MagitechItems;

import org.jetbrains.annotations.NotNull;

public class MagitechBlockLootProvider extends BlockLootSubProvider {

    public MagitechBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(MagitechBlocks.ENGINEERING_WORKBENCH.get());
        dropSelf(MagitechBlocks.ASSEMBLY_WORKBENCH.get());
        dropSelf(MagitechBlocks.REPAIRING_WORKBENCH.get());
        dropSelf(MagitechBlocks.UPGRADE_WORKBENCH.get());
        dropSelf(MagitechBlocks.ZARDIUS_CRUCIBLE.get());
        dropSelf(MagitechBlocks.ALCHEMETRIC_PYLON.get());
        dropSelf(MagitechBlocks.ATHANOR_PILLAR.get());
        dropSelf(MagitechBlocks.MANA_NODE.get());
        dropSelf(MagitechBlocks.MANA_VESSEL.get());
        add(
                MagitechBlocks.FLUORITE_ORE.get(),
                block ->
                        createOreDrop(
                                MagitechBlocks.FLUORITE_ORE.get(), MagitechItems.FLUORITE.get()));
        add(
                MagitechBlocks.DEEPSLATE_FLUORITE_ORE.get(),
                block ->
                        createOreDrop(
                                MagitechBlocks.DEEPSLATE_FLUORITE_ORE.get(),
                                MagitechItems.FLUORITE.get()));
        add(
                MagitechBlocks.TOURMALINE_ORE.get(),
                block ->
                        createOreDrop(
                                MagitechBlocks.TOURMALINE_ORE.get(),
                                MagitechItems.TOURMALINE.get()));
        add(
                MagitechBlocks.DEEPSLATE_TOURMALINE_ORE.get(),
                block ->
                        createOreDrop(
                                MagitechBlocks.DEEPSLATE_TOURMALINE_ORE.get(),
                                MagitechItems.TOURMALINE.get()));
        add(
                MagitechBlocks.FLUORITE_CRYSTAL_CLUSTER.get(),
                block ->
                        createMultipleOreDrops(
                                MagitechBlocks.FLUORITE_CRYSTAL_CLUSTER.get(),
                                MagitechItems.FLUORITE.get(),
                                1,
                                2));
        add(
                MagitechBlocks.REDSTONE_CRYSTAL_CLUSTER.get(),
                block ->
                        createMultipleOreDrops(
                                MagitechBlocks.REDSTONE_CRYSTAL_CLUSTER.get(),
                                MagitechItems.REDSTONE_CRYSTAL.get(),
                                1,
                                2));
        add(
                MagitechBlocks.SULFUR_CRYSTAL_CLUSTER.get(),
                block ->
                        createMultipleOreDrops(
                                MagitechBlocks.SULFUR_CRYSTAL_CLUSTER.get(),
                                MagitechItems.SULFUR.get(),
                                1,
                                2));
        dropSelf(MagitechBlocks.SULFUR_BLOCK.get());
        dropSelf(MagitechBlocks.ALCHECRYSITE.get());
        dropSelf(MagitechBlocks.ALCHECRYSITE_STAIRS.get());
        add(
                MagitechBlocks.ALCHECRYSITE_SLAB.get(),
                block -> createSlabItemTable(MagitechBlocks.ALCHECRYSITE_SLAB.get()));
        dropSelf(MagitechBlocks.ALCHECRYSITE_WALL.get());
        dropSelf(MagitechBlocks.POLISHED_ALCHECRYSITE.get());
        dropSelf(MagitechBlocks.POLISHED_ALCHECRYSITE_STAIRS.get());
        add(
                MagitechBlocks.POLISHED_ALCHECRYSITE_SLAB.get(),
                block -> createSlabItemTable(MagitechBlocks.POLISHED_ALCHECRYSITE_SLAB.get()));
        dropSelf(MagitechBlocks.POLISHED_ALCHECRYSITE_WALL.get());
        dropSelf(MagitechBlocks.ALCHECRYSITE_BRICKS.get());
        dropSelf(MagitechBlocks.ALCHECRYSITE_BRICK_STAIRS.get());
        add(
                MagitechBlocks.ALCHECRYSITE_BRICK_SLAB.get(),
                block -> createSlabItemTable(MagitechBlocks.ALCHECRYSITE_BRICK_SLAB.get()));
        dropSelf(MagitechBlocks.ALCHECRYSITE_BRICK_WALL.get());
        dropSelf(MagitechBlocks.ALCHECRYSITE_TILES.get());
        dropSelf(MagitechBlocks.FLUORITE_BLOCK.get());
        dropSelf(MagitechBlocks.FLUORITE_BRICKS.get());
        dropSelf(MagitechBlocks.FLUORITE_BRICK_STAIRS.get());
        add(
                MagitechBlocks.FLUORITE_BRICK_SLAB.get(),
                block -> createSlabItemTable(MagitechBlocks.FLUORITE_BRICK_SLAB.get()));
        dropSelf(MagitechBlocks.FLUORITE_BRICK_WALL.get());
        dropSelf(MagitechBlocks.CELIFERN_LOG.get());
        dropSelf(MagitechBlocks.CELIFERN_WOOD.get());
        dropSelf(MagitechBlocks.STRIPPED_CELIFERN_LOG.get());
        dropSelf(MagitechBlocks.STRIPPED_CELIFERN_WOOD.get());
        dropSelf(MagitechBlocks.CELIFERN_PLANKS.get());
        dropSelf(MagitechBlocks.CELIFERN_STAIRS.get());
        add(
                MagitechBlocks.CELIFERN_SLAB.get(),
                block -> createSlabItemTable(MagitechBlocks.CELIFERN_SLAB.get()));
        dropSelf(MagitechBlocks.CELIFERN_FENCE.get());
        dropSelf(MagitechBlocks.CELIFERN_FENCE_GATE.get());
        add(
                MagitechBlocks.CELIFERN_DOOR.get(),
                block -> createDoorTable(MagitechBlocks.CELIFERN_DOOR.get()));
        dropSelf(MagitechBlocks.CELIFERN_TRAPDOOR.get());
        dropSelf(MagitechBlocks.CELIFERN_PRESSURE_PLATE.get());
        dropSelf(MagitechBlocks.CELIFERN_BUTTON.get());
        add(
                MagitechBlocks.CELIFERN_LEAVES.get(),
                block ->
                        this.createLeavesDrops(
                                block,
                                MagitechBlocks.CELIFERN_SAPLING.get(),
                                NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(MagitechBlocks.CELIFERN_SAPLING.get());
        dropSelf(MagitechBlocks.CELIFERN_SIGN.get());
        dropSelf(MagitechBlocks.CELIFERN_WALL_SIGN.get());
        dropSelf(MagitechBlocks.CELIFERN_HANGING_SIGN.get());
        dropSelf(MagitechBlocks.CELIFERN_WALL_HANGING_SIGN.get());
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_LOG.get());
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_WOOD.get());
        dropSelf(MagitechBlocks.STRIPPED_CHARCOAL_BIRCH_LOG.get());
        dropSelf(MagitechBlocks.STRIPPED_CHARCOAL_BIRCH_WOOD.get());
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_PLANKS.get());
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_STAIRS.get());
        add(
                MagitechBlocks.CHARCOAL_BIRCH_SLAB.get(),
                block -> createSlabItemTable(MagitechBlocks.CHARCOAL_BIRCH_SLAB.get()));
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_FENCE.get());
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_FENCE_GATE.get());
        add(
                MagitechBlocks.CHARCOAL_BIRCH_DOOR.get(),
                block -> createDoorTable(MagitechBlocks.CHARCOAL_BIRCH_DOOR.get()));
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_TRAPDOOR.get());
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_PRESSURE_PLATE.get());
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_BUTTON.get());
        add(
                MagitechBlocks.CHARCOAL_BIRCH_LEAVES.get(),
                block ->
                        this.createLeavesDrops(
                                block,
                                MagitechBlocks.CHARCOAL_BIRCH_SAPLING.get(),
                                NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_SAPLING.get());
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_SIGN.get());
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_WALL_SIGN.get());
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_HANGING_SIGN.get());
        dropSelf(MagitechBlocks.CHARCOAL_BIRCH_WALL_HANGING_SIGN.get());
        add(
                MagitechBlocks.SCORCHED_GRASS_SOIL.get(),
                block -> createSingleItemTableWithSilkTouch(block, MagitechBlocks.SCORCHED_SOIL));
        dropSelf(MagitechBlocks.SCORCHED_SOIL.get());
        add(
                MagitechBlocks.MISTALIA_PETALS.get(),
                block -> createPetalsDrops(MagitechBlocks.MISTALIA_PETALS.get()));
        HolderLookup.RegistryLookup<Enchantment> registrylookup =
                this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        this.add(
                MagitechBlocks.MANA_BERRY_BUSH.get(),
                block ->
                        this.applyExplosionDecay(
                                block,
                                LootTable.lootTable()
                                        .withPool(
                                                LootPool.lootPool()
                                                        .when(
                                                                LootItemBlockStatePropertyCondition
                                                                        .hasBlockStateProperties(
                                                                                MagitechBlocks
                                                                                        .MANA_BERRY_BUSH
                                                                                        .get())
                                                                        .setProperties(
                                                                                StatePropertiesPredicate
                                                                                        .Builder
                                                                                        .properties()
                                                                                        .hasProperty(
                                                                                                SweetBerryBushBlock
                                                                                                        .AGE,
                                                                                                3)))
                                                        .add(
                                                                LootItem.lootTableItem(
                                                                        MagitechItems.MANA_BERRIES))
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(
                                                                                2.0F, 3.0F)))
                                                        .apply(
                                                                ApplyBonusCount
                                                                        .addUniformBonusCount(
                                                                                registrylookup
                                                                                        .getOrThrow(
                                                                                                Enchantments
                                                                                                        .FORTUNE))))
                                        .withPool(
                                                LootPool.lootPool()
                                                        .when(
                                                                LootItemBlockStatePropertyCondition
                                                                        .hasBlockStateProperties(
                                                                                MagitechBlocks
                                                                                        .MANA_BERRY_BUSH
                                                                                        .get())
                                                                        .setProperties(
                                                                                StatePropertiesPredicate
                                                                                        .Builder
                                                                                        .properties()
                                                                                        .hasProperty(
                                                                                                SweetBerryBushBlock
                                                                                                        .AGE,
                                                                                                2)))
                                                        .add(
                                                                LootItem.lootTableItem(
                                                                        MagitechItems.MANA_BERRIES))
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(
                                                                                1.0F, 2.0F)))
                                                        .apply(
                                                                ApplyBonusCount
                                                                        .addUniformBonusCount(
                                                                                registrylookup
                                                                                        .getOrThrow(
                                                                                                Enchantments
                                                                                                        .FORTUNE))))));
    }

    protected LootTable.Builder createMultipleOreDrops(
            Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup =
                this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock,
                this.applyExplosionDecay(
                        pBlock,
                        LootItem.lootTableItem(item)
                                .apply(
                                        SetItemCountFunction.setCount(
                                                UniformGenerator.between(minDrops, maxDrops)))
                                .apply(
                                        ApplyBonusCount.addOreBonusCount(
                                                registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return MagitechBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
