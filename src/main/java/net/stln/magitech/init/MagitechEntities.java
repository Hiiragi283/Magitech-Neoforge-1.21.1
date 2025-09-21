package net.stln.magitech.init;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;
import net.stln.magitech.client.render.entity.AeltherinRenderer;
import net.stln.magitech.client.render.entity.ArcalethRenderer;
import net.stln.magitech.client.render.entity.FrigalaRenderer;
import net.stln.magitech.client.render.entity.IgniscaRenderer;
import net.stln.magitech.client.render.entity.MirazienRenderer;
import net.stln.magitech.client.render.entity.NullixisRenderer;
import net.stln.magitech.client.render.entity.TremivoxRenderer;
import net.stln.magitech.client.render.entity.VoltarisRenderer;
import net.stln.magitech.client.render.entity.WeaverRenderer;
import net.stln.magitech.entity.magical.AeltherinEntity;
import net.stln.magitech.entity.magical.ArcalethEntity;
import net.stln.magitech.entity.magical.FrigalaEntity;
import net.stln.magitech.entity.magical.IgniscaEntity;
import net.stln.magitech.entity.magical.MirazienEntity;
import net.stln.magitech.entity.magical.NullixisEntity;
import net.stln.magitech.entity.magical.TremivoxEntity;
import net.stln.magitech.entity.magical.VoltarisEntity;
import net.stln.magitech.entity.mob.WeaverEntity;

@EventBusSubscriber(modid = Magitech.MOD_ID)
public final class MagitechEntities {

    public static final DeferredRegister<EntityType<?>> REGISTER =
            DeferredRegister.create(Registries.ENTITY_TYPE, Magitech.MOD_ID);

    public static final Supplier<EntityType<IgniscaEntity>> IGNISCA_ENTITY =
            registerMobEntity(
                    "ignisca",
                    IgniscaEntity::new,
                    MobCategory.MISC,
                    builder -> builder.sized(0.5F, 0.5F));
    public static final Supplier<EntityType<FrigalaEntity>> FRIGALA_ENTITY =
            registerMobEntity(
                    "frigala",
                    FrigalaEntity::new,
                    MobCategory.MISC,
                    builder -> builder.sized(0.5F, 0.5F));
    public static final Supplier<EntityType<VoltarisEntity>> VOLTARIS_ENTITY =
            registerMobEntity(
                    "voltaris",
                    VoltarisEntity::new,
                    MobCategory.MISC,
                    builder -> builder.sized(1.0F, 1.0F));
    public static final Supplier<EntityType<MirazienEntity>> MIRAZIEN_ENTITY =
            registerMobEntity(
                    "mirazien",
                    MirazienEntity::new,
                    MobCategory.MISC,
                    builder -> builder.sized(0.5F, 0.5F));
    public static final Supplier<EntityType<TremivoxEntity>> TREMIVOX_ENTITY =
            registerMobEntity(
                    "tremivox",
                    TremivoxEntity::new,
                    MobCategory.MISC,
                    builder -> builder.sized(0.75F, 0.75F));
    public static final Supplier<EntityType<ArcalethEntity>> ARCALETH_ENTITY =
            registerMobEntity(
                    "arcaleth",
                    ArcalethEntity::new,
                    MobCategory.MISC,
                    builder -> builder.sized(0.5F, 0.5F));
    public static final Supplier<EntityType<AeltherinEntity>> AELTHERIN_ENTITY =
            registerMobEntity(
                    "aeltherin",
                    AeltherinEntity::new,
                    MobCategory.MISC,
                    builder -> builder.sized(0.5F, 0.5F));
    public static final Supplier<EntityType<NullixisEntity>> NULLIXIS_ENTITY =
            registerMobEntity(
                    "nullixis",
                    NullixisEntity::new,
                    MobCategory.MISC,
                    builder -> builder.sized(0.5F, 0.5F));

    public static final Supplier<EntityType<WeaverEntity>> WEAVER_ENTITY =
            registerMobEntity(
                    "weaver",
                    WeaverEntity::new,
                    MobCategory.MONSTER,
                    (builder) -> builder.sized(0.6F, 2.0F).eyeHeight(1.62F).clientTrackingRange(8));

    public static void registerModEntities(IEventBus eventBus) {
        Magitech.LOGGER.info("Registering Entity for " + Magitech.MOD_ID);
        REGISTER.register(eventBus);
    }

    public static void registerModEntitiesRenderer() {
        Magitech.LOGGER.info("Registering Entity Renderer for " + Magitech.MOD_ID);
        EntityRenderers.register(MagitechEntities.IGNISCA_ENTITY.get(), IgniscaRenderer::new);
        EntityRenderers.register(MagitechEntities.FRIGALA_ENTITY.get(), FrigalaRenderer::new);
        EntityRenderers.register(MagitechEntities.VOLTARIS_ENTITY.get(), VoltarisRenderer::new);
        EntityRenderers.register(MagitechEntities.MIRAZIEN_ENTITY.get(), MirazienRenderer::new);
        EntityRenderers.register(MagitechEntities.TREMIVOX_ENTITY.get(), TremivoxRenderer::new);
        EntityRenderers.register(MagitechEntities.ARCALETH_ENTITY.get(), ArcalethRenderer::new);
        EntityRenderers.register(MagitechEntities.AELTHERIN_ENTITY.get(), AeltherinRenderer::new);
        EntityRenderers.register(MagitechEntities.NULLIXIS_ENTITY.get(), NullixisRenderer::new);

        EntityRenderers.register(MagitechEntities.WEAVER_ENTITY.get(), WeaverRenderer::new);
    }

    private static <T extends Entity> Supplier<EntityType<T>> registerMobEntity(
            String path,
            EntityType.EntityFactory<T> factory,
            MobCategory category,
            UnaryOperator<EntityType.Builder<T>> operator) {
        return REGISTER.register(
                path,
                id -> operator.apply(EntityType.Builder.of(factory, category)).build(id.getPath()));
    }

    @SubscribeEvent
    public static void registerDefaultAttributes(EntityAttributeCreationEvent event) {
        Magitech.LOGGER.info("Registering Entity Attribute for " + Magitech.MOD_ID);
        event.put(WEAVER_ENTITY.get(), WeaverEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
                WEAVER_ENTITY.get(),
                SpawnPlacementTypes.ON_GROUND, // 湧く場所のタイプ
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, // 高さ判定
                ((entityType, serverLevel, spawnType, pos, random) -> true), // 条件 (ここは独自関数でもOK)
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
