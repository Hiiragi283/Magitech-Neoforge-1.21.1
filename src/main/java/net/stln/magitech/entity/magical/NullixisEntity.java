package net.stln.magitech.entity.magical;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.stln.magitech.element.Element;
import net.stln.magitech.init.MagitechEntities;
import net.stln.magitech.init.MagitechSounds;
import net.stln.magitech.particle.VoidGlowParticleEffect;

import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NullixisEntity extends SpellProjectileEntity {

    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public NullixisEntity(EntityType<? extends SpellProjectileEntity> entityType, Level world) {
        super(entityType, world);
    }

    public NullixisEntity(Level world, Player player, float damage) {
        super(MagitechEntities.NULLIXIS_ENTITY.get(), player, world, ItemStack.EMPTY, damage);
    }

    public NullixisEntity(Level world, Player player, ItemStack weapon, float damage) {
        super(MagitechEntities.NULLIXIS_ENTITY.get(), player, world, weapon, damage);
    }

    public NullixisEntity(
            EntityType<? extends SpellProjectileEntity> type,
            double x,
            double y,
            double z,
            Level world,
            ItemStack stack,
            @NotNull ItemStack weapon,
            float damage) {
        super(type, x, y, z, world, weapon, damage);
    }

    public NullixisEntity(
            EntityType<? extends SpellProjectileEntity> type,
            LivingEntity owner,
            Level world,
            ItemStack stack,
            @NotNull ItemStack shotFrom,
            float damage) {
        super(type, owner, world, shotFrom, damage);
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 vec32 = this.position();
        Vec3 vec33 = vec32.add(this.getDeltaMovement().scale(3));
        HitResult hitresult =
                this.level()
                        .clip(
                                new ClipContext(
                                        vec32,
                                        vec33,
                                        ClipContext.Block.COLLIDER,
                                        ClipContext.Fluid.NONE,
                                        this));
        if (hitresult.getType() != HitResult.Type.MISS) {
            vec33 = hitresult.getLocation();
        }

        while (!this.isRemoved()) {
            EntityHitResult entityhitresult = this.findHitEntity(vec32, vec33);
            if (entityhitresult != null) {
                hitresult = entityhitresult;
            }

            if (hitresult != null && hitresult.getType() == HitResult.Type.ENTITY) {
                Entity entity = ((EntityHitResult) hitresult).getEntity();
                Entity entity1 = this.getOwner();
                if (entity instanceof Player
                        && entity1 instanceof Player
                        && !((Player) entity1).canHarmPlayer((Player) entity)) {
                    hitresult = null;
                    entityhitresult = null;
                }
            }

            if (hitresult != null && hitresult.getType() != HitResult.Type.MISS) {
                if (net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult))
                    break;
                ProjectileDeflection projectiledeflection = this.hitTargetOrDeflectSelf(hitresult);
                this.hasImpulse = true;
                if (projectiledeflection != ProjectileDeflection.NONE) {
                    break;
                }
            }

            if (hitresult != null) {
                hitresult.getType();
            }

            break;
        }
        Level world = this.level();
        Vec3 deltaMovement = this.getDeltaMovement();
        if (world.isClientSide) {
            Vector3f fromColor = new Vector3f(1.0F, 1.0F, 1.0F);
            Vector3f toColor = new Vector3f(1.0F, 1.0F, 1.0F);
            float scale = 1.0F;
            int twinkle = 1;
            float rotSpeed = 0.0F;
            int particleAmount = 5;
            for (int i = 0; i < particleAmount; i++) {
                double x = this.getX() - deltaMovement.x + (random.nextFloat() - 0.5) / 10;
                double y = this.getY(0.5F) - deltaMovement.y + (random.nextFloat() - 0.5) / 10;
                double z = this.getZ() - deltaMovement.z + (random.nextFloat() - 0.5) / 10;
                double vx = deltaMovement.x / 4;
                double vy = deltaMovement.y / 4;
                double vz = deltaMovement.z / 4;
                world.addParticle(
                        new VoidGlowParticleEffect(fromColor, toColor, scale, twinkle, rotSpeed),
                        x,
                        y,
                        z,
                        vx,
                        vy,
                        vz);
            }
        }
        int axisX = this.tickCount % 3 != 0 ? -1 : 2;
        int axisY = this.tickCount % 3 != 1 ? -1 : 2;
        int axisZ = this.tickCount % 3 != 2 ? -1 : 2;
        this.setPos(
                deltaMovement.x * axisX + this.position().x,
                deltaMovement.y * axisY + this.position().y,
                deltaMovement.z * axisZ + this.position().z);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        applyDamage(entity, getElementalDamageSource(), getElementalDamageValue(entity));
        hitParticle();

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, EntityEvent.DEATH);
        }
    }

    @Override
    @NotNull public Element getElement() {
        return Element.HOLLOW;
    }

    @Override
    public void handleEntityEvent(byte status) {
        if (status == EntityEvent.DEATH) {
            if (this.level().isClientSide) {
                hitParticle();
            } else {
                this.discard();
            }
        }
        super.handleEntityEvent(status);
    }

    protected void hitParticle() {
        Level world = this.level();
        if (world.isClientSide) {
            Vector3f fromColor = new Vector3f(1.0F, 1.0F, 1.0F);
            Vector3f toColor = new Vector3f(1.0F, 1.0F, 1.0F);
            float scale = 1.0F;
            float rotSpeed = 0.0F;
            int particleAmount = 10;
            for (int i = 0; i < particleAmount; i++) {
                int twinkle = 1;

                double x =
                        this.getX() - this.getDeltaMovement().x + (random.nextFloat() - 0.5) / 10;
                double y =
                        this.getY(0.5F)
                                - this.getDeltaMovement().y
                                + (random.nextFloat() - 0.5) / 10;
                double z =
                        this.getZ() - this.getDeltaMovement().z + (random.nextFloat() - 0.5) / 10;
                double vx = (random.nextFloat() - 0.5) / 6;
                double vy = (random.nextFloat() - 0.5) / 6;
                double vz = (random.nextFloat() - 0.5) / 6;
                Vector3f endPos =
                        this.position()
                                .add(
                                        new Vec3(
                                                this.random.nextFloat() * 4 - 2,
                                                this.random.nextFloat() * 4 - 2,
                                                this.random.nextFloat() * 4 - 2))
                                .toVector3f();

                world.addParticle(
                        new VoidGlowParticleEffect(fromColor, toColor, scale, twinkle, rotSpeed),
                        x,
                        y,
                        z,
                        vx,
                        vy,
                        vz);
            }
        }
    }

    @Override
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return MagitechSounds.NULLIXIS.get();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(this, "idle", (event) -> event.setAndContinue(IDLE)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
}
