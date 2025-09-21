package net.stln.magitech.init;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;
import net.stln.magitech.block.entity.AlchemetricPylonBlockEntity;
import net.stln.magitech.block.entity.AthanorPillarBlockEntity;
import net.stln.magitech.block.entity.ManaVesselBlockEntity;
import net.stln.magitech.block.entity.ZardiusCrucibleBlockEntity;

import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Magitech.MOD_ID)
public final class MagitechBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> REGISTER =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Magitech.MOD_ID);

    public static void register(IEventBus eventBus) {
        Magitech.LOGGER.info("Registering Block Entity type for" + Magitech.MOD_ID);
        REGISTER.register(eventBus);
    }

    public static final Supplier<BlockEntityType<ZardiusCrucibleBlockEntity>> ZARDIUS_CRUCIBLE =
            register("zardius_crucible", ZardiusCrucibleBlockEntity::new);

    public static final Supplier<BlockEntityType<AlchemetricPylonBlockEntity>> ALCHEMETRIC_PYLON =
            register("alchemetric_pylon", AlchemetricPylonBlockEntity::new);

    public static final Supplier<BlockEntityType<AthanorPillarBlockEntity>> ATHANOR_PILLAR =
            register("athanor_pillar", AthanorPillarBlockEntity::new);

    public static final Supplier<BlockEntityType<ManaVesselBlockEntity>> MANA_VESSEL =
            register("mana_vessel", ManaVesselBlockEntity::new);

    @SuppressWarnings("DataFlowIssue")
    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(
            @NotNull String name, @NotNull BlockEntityType.BlockEntitySupplier<T> supplier) {
        return REGISTER.register(name, () -> BlockEntityType.Builder.of(supplier).build(null));
    }

    @SubscribeEvent
    public static void addBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(ZARDIUS_CRUCIBLE.get(), MagitechBlocks.ZARDIUS_CRUCIBLE.get());
        event.modify(ALCHEMETRIC_PYLON.get(), MagitechBlocks.ALCHEMETRIC_PYLON.get());
        event.modify(ATHANOR_PILLAR.get(), MagitechBlocks.ATHANOR_PILLAR.get());
        event.modify(MANA_VESSEL.get(), MagitechBlocks.MANA_VESSEL.get());

        event.modify(
                BlockEntityType.SIGN,
                MagitechBlocks.CELIFERN_SIGN.get(),
                MagitechBlocks.CELIFERN_WALL_SIGN.get());
        event.modify(
                BlockEntityType.HANGING_SIGN,
                MagitechBlocks.CELIFERN_HANGING_SIGN.get(),
                MagitechBlocks.CELIFERN_WALL_HANGING_SIGN.get());
        event.modify(
                BlockEntityType.SIGN,
                MagitechBlocks.CHARCOAL_BIRCH_SIGN.get(),
                MagitechBlocks.CHARCOAL_BIRCH_WALL_SIGN.get());
        event.modify(
                BlockEntityType.HANGING_SIGN,
                MagitechBlocks.CHARCOAL_BIRCH_HANGING_SIGN.get(),
                MagitechBlocks.CHARCOAL_BIRCH_WALL_HANGING_SIGN.get());
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ALCHEMETRIC_PYLON.get(),
                (blockEntity, direction) -> blockEntity.inventory);
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ATHANOR_PILLAR.get(),
                (blockEntity, direction) -> blockEntity.inventory);
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ZARDIUS_CRUCIBLE.get(),
                (blockEntity, direction) -> blockEntity.inventory);
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                ZARDIUS_CRUCIBLE.get(),
                (blockEntity, direction) -> blockEntity.fluidTank);
    }
}
