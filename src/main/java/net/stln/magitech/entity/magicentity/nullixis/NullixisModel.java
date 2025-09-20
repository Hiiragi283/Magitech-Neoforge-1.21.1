package net.stln.magitech.entity.magicentity.nullixis;

import net.minecraft.resources.ResourceLocation;
import net.stln.magitech.Magitech;

import software.bernie.geckolib.model.GeoModel;

public class NullixisModel extends GeoModel<NullixisEntity> {

    // This layer location should be baked with EntityRendererProvider.Context in
    // the entity renderer
    // and passed into this model's constructor
    public static final ResourceLocation TEXTURE = Magitech.id("textures/entity/nullixis.png");
    public static final ResourceLocation GEO = Magitech.id("geo/entity/nullixis.geo.json");
    public static final ResourceLocation ANIM =
            Magitech.id("animations/entity/nullixis.animation.json");

    @Override
    public ResourceLocation getModelResource(NullixisEntity animatable) {
        return GEO;
    }

    @Override
    public ResourceLocation getTextureResource(NullixisEntity animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(NullixisEntity animatable) {
        return ANIM;
    }
}
