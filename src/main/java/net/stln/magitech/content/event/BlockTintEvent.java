package net.stln.magitech.content.event;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.stln.magitech.Magitech;
import net.stln.magitech.content.block.BlockInit;

@EventBusSubscriber(modid = Magitech.MOD_ID)
public class BlockTintEvent {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {

        event.register((state, world, pos, tintIndex) -> tintIndex == 1 ? (world != null && pos != null)
                ? BiomeColors.getAverageGrassColor(world, pos)
                : FoliageColor.getDefaultColor() : -1, BlockInit.MISTALIA_PETALS.get());
    }

}
