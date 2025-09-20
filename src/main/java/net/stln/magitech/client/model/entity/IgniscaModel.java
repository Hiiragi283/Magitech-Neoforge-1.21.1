package net.stln.magitech.client.model.entity;

import net.minecraft.resources.ResourceLocation;
import net.stln.magitech.Magitech;
import net.stln.magitech.entity.magical.IgniscaEntity;

import software.bernie.geckolib.model.GeoModel;

public class IgniscaModel extends GeoModel<IgniscaEntity> {

    // This layer location should be baked with EntityRendererProvider.Context in
    // the entity renderer
    // and passed into this model's constructor
    public static final ResourceLocation TEXTURE = Magitech.id("textures/entity/ignisca.png");
    public static final ResourceLocation GEO = Magitech.id("geo/entity/ignisca.geo.json");

    @Override
    public ResourceLocation getModelResource(IgniscaEntity animatable) {
        return GEO;
    }

    @Override
    public ResourceLocation getTextureResource(IgniscaEntity animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(IgniscaEntity animatable) {
        return null;
    }
}
