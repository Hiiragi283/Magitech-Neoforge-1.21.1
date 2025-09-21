package net.stln.magitech.item.tool.trait;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.stln.magitech.item.tool.ToolStats;
import net.stln.magitech.particle.SquareFieldParticleEffect;
import net.stln.magitech.particle.UnstableSquareParticleEffect;
import net.stln.magitech.util.EffectUtil;

import org.joml.Vector3f;

public class EnderDrawTrait extends Trait {

    @Override
    public void tick(
            Player player,
            Level level,
            ItemStack stack,
            int traitLevel,
            ToolStats stats,
            boolean isHost) {
        if (player.isCrouching()) {
            float range = traitLevel * 4;
            Vec3 rangeVec = new Vec3(range, range, range);
            List<Entity> list =
                    level.getEntities(
                            player,
                            new AABB(
                                    player.getPosition(0F).subtract(rangeVec),
                                    player.getPosition(0F).add(rangeVec)),
                            entity -> entity instanceof ItemEntity);
            if (!list.isEmpty()) {
                Entity item = null;
                for (Entity entity : list) {
                    if (entity.getPosition(0F).distanceTo(player.getPosition(0F)) > 0.5) {
                        item = entity;
                    }
                }
                if (item != null) {
                    Vec3 playerPos = new Vec3(player.getX(), player.getY(0.5F), player.getZ());
                    Vec3 itemPos = new Vec3(item.getX(), item.getY(0.5F), item.getZ());
                    level.addParticle(
                            new SquareFieldParticleEffect(
                                    new Vector3f(0.0F, 1.0F, 0.8F),
                                    new Vector3f(0.0F, 1.0F, 0.8F),
                                    1.0F,
                                    1,
                                    0),
                            item.getX(),
                            item.getY() + 0.01,
                            item.getZ(),
                            0,
                            0,
                            0);
                    level.addParticle(
                            new SquareFieldParticleEffect(
                                    new Vector3f(0.0F, 1.0F, 0.8F),
                                    new Vector3f(1.0F, 1.0F, 1.0F),
                                    1.0F,
                                    1,
                                    0),
                            player.getX(),
                            player.getY() + 0.01,
                            player.getZ(),
                            0,
                            0,
                            0);
                    EffectUtil.lineEffect(
                            level,
                            new UnstableSquareParticleEffect(
                                    new Vector3f(0.0F, 1.0F, 0.8F),
                                    new Vector3f(0.0F, 1.0F, 0.8F),
                                    1.0F,
                                    4,
                                    0),
                            playerPos,
                            itemPos,
                            4,
                            false);
                    level.playSound(
                            player,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.PLAYER_TELEPORT,
                            SoundSource.PLAYERS);
                    item.setPos(player.getPosition(0F));
                    item.setDeltaMovement(0, 0, 0);
                }
            }
        }
    }

    @Override
    public int getColor() {
        return 0x006050;
    }

    @Override
    public Component getName() {
        return Component.translatable("trait.magitech.ender_draw");
    }
}
