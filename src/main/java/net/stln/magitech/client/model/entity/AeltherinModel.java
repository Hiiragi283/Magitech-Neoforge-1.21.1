package net.stln.magitech.client.model.entity;

import net.minecraft.resources.ResourceLocation;
import net.stln.magitech.Magitech;
import net.stln.magitech.entity.magical.AeltherinEntity;

import software.bernie.geckolib.model.GeoModel;

public class AeltherinModel extends GeoModel<AeltherinEntity> {

    // This layer location should be baked with EntityRendererProvider.Context in
    // the entity renderer
    // and passed into this model's constructor
    public static final ResourceLocation TEXTURE = Magitech.id("textures/entity/aeltherin.png");
    public static final ResourceLocation GEO = Magitech.id("geo/entity/aeltherin.geo.json");
    public static final ResourceLocation ANIM =
            Magitech.id("animations/entity/aeltherin.animation.json");

    @Override
    public ResourceLocation getModelResource(AeltherinEntity animatable) {
        return GEO;
    }

    @Override
    public ResourceLocation getTextureResource(AeltherinEntity animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(AeltherinEntity animatable) {
        return ANIM;
    }
}
