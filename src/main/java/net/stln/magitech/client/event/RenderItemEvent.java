package net.stln.magitech.client.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderItemInFrameEvent;
import net.stln.magitech.Magitech;

@EventBusSubscriber(modid = Magitech.MOD_ID)
public class RenderItemEvent {

    @SubscribeEvent
    public static void onPartToolItemRender(RenderItemInFrameEvent event) {}
}
