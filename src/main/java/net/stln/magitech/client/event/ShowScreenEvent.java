package net.stln.magitech.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.stln.magitech.Magitech;
import net.stln.magitech.gui.overlay.RadialSpellMenuOverlay;
import net.stln.magitech.util.ClientHelper;
import net.stln.magitech.util.CuriosHelper;

@EventBusSubscriber(modid = Magitech.MOD_ID, value = Dist.CLIENT)
public class ShowScreenEvent {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        CuriosHelper.getThreadBoundStack(ClientHelper.getPlayer())
                .ifPresent(
                        stack -> {
                            while (KeyMappingEvent.RADIAL_SPELL_MENU.get().consumeClick()
                                    && Minecraft.getInstance().screen == null
                                    && !stack.isEmpty()) {
                                Minecraft.getInstance()
                                        .setScreen(new RadialSpellMenuOverlay(Component.empty()));
                            }
                        });
    }
}
