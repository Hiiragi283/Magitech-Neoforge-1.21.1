package net.stln.magitech.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.stln.magitech.Magitech;
import net.stln.magitech.client.render.block.AlchemetricPylonBlockEntityRenderer;
import net.stln.magitech.client.render.block.AthanorPillarBlockEntityRenderer;
import net.stln.magitech.client.render.block.ManaVesselBlockEntityRenderer;
import net.stln.magitech.client.render.block.ZardiusCrucibleBlockEntityRenderer;
import net.stln.magitech.event.EventInit;
import net.stln.magitech.init.MagitechBlockEntityTypes;
import net.stln.magitech.init.MagitechEntities;
import net.stln.magitech.particle.ParticleInit;

@Mod(value = Magitech.MOD_ID, dist = Dist.CLIENT)
public class MagitechClient {
    public MagitechClient(IEventBus eventBus, ModContainer modContainer) {
        eventBus.addListener(this::onClientSetup);
        eventBus.addListener(this::registerParticleFactories);
        eventBus.addListener(this::registerBlockEntityRenderer);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        MagitechEntities.registerModEntitiesRenderer();
        EventInit.registerClientEvent();
        ItemPropertyInit.registerItemProperties();
    }

    private void registerParticleFactories(RegisterParticleProvidersEvent event) {
        ParticleInit.registerParticleFactories(event);
    }

    private void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                MagitechBlockEntityTypes.ALCHEMETRIC_PYLON.get(),
                AlchemetricPylonBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(
                MagitechBlockEntityTypes.ATHANOR_PILLAR.get(),
                AthanorPillarBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(
                MagitechBlockEntityTypes.ZARDIUS_CRUCIBLE.get(),
                ZardiusCrucibleBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(
                MagitechBlockEntityTypes.MANA_VESSEL.get(), ManaVesselBlockEntityRenderer::new);
    }
}
