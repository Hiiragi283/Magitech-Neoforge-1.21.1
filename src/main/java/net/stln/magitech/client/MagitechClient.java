package net.stln.magitech.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.stln.magitech.Magitech;
import net.stln.magitech.entity.EntityInit;
import net.stln.magitech.event.EventInit;
import net.stln.magitech.item.ItemPropertyInit;
import net.stln.magitech.particle.ParticleInit;

@Mod(value = Magitech.MOD_ID, dist = Dist.CLIENT)
public class MagitechClient {
    public MagitechClient(IEventBus eventBus, ModContainer modContainer) {
        eventBus.addListener(this::onClientSetup);
        eventBus.addListener(this::registerParticleFactories);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        EntityInit.registerModEntitiesRenderer();
        EventInit.registerClientEvent();
        ItemPropertyInit.registerItemProperties();
    }

    private void registerParticleFactories(RegisterParticleProvidersEvent event) {
        ParticleInit.registerParticleFactories(event);
    }
}
