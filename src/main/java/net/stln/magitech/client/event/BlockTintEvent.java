package net.stln.magitech.client.event;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechBlocks;

@EventBusSubscriber(modid = Magitech.MOD_ID, value = Dist.CLIENT)
public class BlockTintEvent {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(
                (state, world, pos, tintIndex) -> {
                    if (tintIndex == 1) {
                        return (world != null && pos != null)
                                ? BiomeColors.getAverageGrassColor(world, pos)
                                : FoliageColor.getDefaultColor();
                    } else {
                        return -1;
                    }
                },
                MagitechBlocks.MISTALIA_PETALS.get());
    }
}
