package net.stln.magitech.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.stln.magitech.particle.particle_option.FlameSmokeParticleEffect;

import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.VertexConsumer;

public class FlameSmokeParticle extends AbstractCustomizableParticle {

    private final SpriteSet spriteProvider;
    private final Vector3f startColor;
    private final Vector3f endColor;

    public FlameSmokeParticle(
            ClientLevel clientWorld,
            double x,
            double y,
            double z,
            double vx,
            double vy,
            double vz,
            FlameSmokeParticleEffect parameters,
            SpriteSet spriteProvider) {
        super(clientWorld, x, y, z, vx, vy, vz);
        this.xd = vx;
        this.yd = vy;
        this.zd = vz;
        this.lifetime = 7 + clientWorld.random.nextInt(0, 5);
        this.alpha = 0.8F;
        this.scale = 1F * parameters.getScale();
        this.gravity = -0.02F;
        this.friction = 0.9F;
        this.spriteProvider = spriteProvider;
        this.setSpriteFromAge(spriteProvider);
        this.startColor = parameters.getFromColor();
        this.endColor = parameters.getToColor();
        this.twinkle = parameters.getTwinkle();
        this.rotSpeed = parameters.getRotSpeed();
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        this.updateColor(tickDelta);

        if (this.age >= this.lifetime * 0.8F) {
            this.alpha = (this.lifetime - this.age) / (this.lifetime * 0.2F) * 0.6F + 0.2F;
            this.alpha *= 0.8F;
        } else if (this.age <= this.lifetime * 0.2F) {
            this.alpha = (this.age - this.lifetime) / (this.lifetime * 0.2F) * 0.6F + 0.2F;
            this.alpha *= 0.8F;
        } else {
            this.alpha = 0.8F;
        }
        this.quadSize =
                Math.min((float) (this.lifetime - this.age) / (this.lifetime) + 0.2F, 1.0F) * 0.1F;
        if (this.twinkle > 1) {
            float multiplier =
                    Math.max(((float) this.age % this.twinkle) / (this.twinkle - 1), 0.1F);
            this.rCol *= multiplier;
            this.gCol *= multiplier;
            this.bCol *= multiplier;
        }

        super.render(vertexConsumer, camera, tickDelta);
    }

    private void updateColor(float tickDelta) {
        float f = ((float) this.age + tickDelta) / ((float) this.lifetime + 1.0F);
        Vector3f vector3f = new Vector3f(this.startColor).lerp(this.endColor, f);
        this.rCol = vector3f.x();
        this.gCol = vector3f.y();
        this.bCol = vector3f.z();
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.move(this.xd, this.yd, this.zd);
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.yd = this.yd - 0.04 * (double) this.gravity;
        }

        this.xd = this.xd * (double) this.friction;
        this.yd = this.yd * (double) this.friction;
        this.zd = this.zd * (double) this.friction;

        rotate();

        this.setSpriteFromAge(this.spriteProvider);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float tint) {
        if (this.age >= this.lifetime * 0.8F) {
            return (int)
                    (((this.lifetime - this.age) / (this.lifetime * 0.2F) * 0.6F + 0.2F) * 240);
        } else {
            return 240;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<FlameSmokeParticleEffect> {

        private final SpriteSet spriteProvider;

        public Provider(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle(
                FlameSmokeParticleEffect parameters,
                ClientLevel world,
                double x,
                double y,
                double z,
                double xd,
                double yd,
                double zd) {
            return new FlameSmokeParticle(
                    world, x, y, z, xd, yd, zd, parameters, this.spriteProvider);
        }
    }
}
