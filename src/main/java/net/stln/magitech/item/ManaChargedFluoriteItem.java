package net.stln.magitech.item;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.stln.magitech.block.entity.ManaContainerBlockEntity;
import net.stln.magitech.init.MagitechSounds;
import net.stln.magitech.magic.mana.ManaData;
import net.stln.magitech.magic.mana.ManaUtil;
import net.stln.magitech.particle.PowerupParticleEffect;
import net.stln.magitech.util.EffectUtil;

import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class ManaChargedFluoriteItem extends TooltipTextItem {

    public ManaChargedFluoriteItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        double currentMana = ManaData.getCurrentMana(player, ManaUtil.ManaType.MANA);
        double maxMana = ManaUtil.getMaxMana(player, ManaUtil.ManaType.MANA);
        if (currentMana < maxMana) {
            stack.consume(1, player);
            level.playSound(
                    player,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    MagitechSounds.CRYSTAL_BREAK.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F);
            EffectUtil.entityEffect(
                    level,
                    new PowerupParticleEffect(
                            new Vector3f(0.9F, 1.0F, 0.7F),
                            new Vector3f(0.3F, 1.0F, 0.9F),
                            1F,
                            1,
                            0),
                    player,
                    20);
            ManaUtil.setMana(player, ManaUtil.ManaType.MANA, Math.min(currentMana + 45, maxMana));
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof ManaContainerBlockEntity containerBlockEntity
                && !containerBlockEntity.isFull()) {
            stack.consume(1, player);
            containerBlockEntity.addMana(45);
            level.playSound(
                    player,
                    pos,
                    MagitechSounds.CRYSTAL_BREAK.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F);
            for (int i = 0; i < 40; i++) {
                double x = pos.getCenter().x + Mth.nextDouble(player.getRandom(), -0.75, 0.75);
                double y = pos.getCenter().y + Mth.nextDouble(player.getRandom(), -0.75, 0.75);
                double z = pos.getCenter().z + Mth.nextDouble(player.getRandom(), -0.75, 0.75);
                level.addParticle(
                        new PowerupParticleEffect(
                                new Vector3f(0.9F, 1.0F, 0.7F),
                                new Vector3f(0.3F, 1.0F, 0.9F),
                                1F,
                                1,
                                0),
                        x,
                        y,
                        z,
                        0,
                        0,
                        0);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @NotNull TooltipContext context,
            List<Component> tooltipComponents,
            @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(
                Component.translatable("tooltip.hint.item.magitech.mana_charged_fluorite")
                        .withColor(0x60A090));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
