package net.stln.magitech.client.render.block;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.stln.magitech.block.entity.ManaVesselBlockEntity;

import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ManaVesselBlockEntityRenderer extends GeoBlockRenderer<ManaVesselBlockEntity> {

    public ManaVesselBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new ManaVesselBlockEntityModel());
    }
}
