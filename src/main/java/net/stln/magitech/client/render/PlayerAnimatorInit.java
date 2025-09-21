package net.stln.magitech.client.render;

import java.util.Optional;
import java.util.function.Consumer;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechAttributes;
import net.stln.magitech.magic.mana.UsedHandData;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dev.kosmx.playerAnim.api.TransformType;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.api.layered.modifier.AdjustmentModifier;
import dev.kosmx.playerAnim.api.layered.modifier.MirrorModifier;
import dev.kosmx.playerAnim.api.layered.modifier.SpeedModifier;
import dev.kosmx.playerAnim.core.util.Vec3f;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;

/** This is an example implementation of PlayerAnimator resourceLoading and playerMapping */
@EventBusSubscriber(modid = Magitech.MOD_ID, value = Dist.CLIENT)
public class PlayerAnimatorInit {

    public static final ResourceLocation AMINATION_ID = Magitech.id("animation");

    @SuppressWarnings("unchecked")
    public static @Nullable ModifierLayer<IAnimation> getPlayerAnimation(@NotNull Player player) {
        if (player instanceof AbstractClientPlayer clientPlayer) {
            IAnimation data =
                    PlayerAnimationAccess.getPlayerAssociatedData(clientPlayer).get(AMINATION_ID);
            if (data instanceof ModifierLayer<?> modifierLayer) {
                return (ModifierLayer<IAnimation>) modifierLayer;
            }
        }
        return null;
    }

    public static void usePlayerAnimation(
            @NotNull Player player, @NotNull Consumer<ModifierLayer<IAnimation>> consumer) {
        var animation = getPlayerAnimation(player);
        if (animation != null) {
            consumer.accept(animation);
        }
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // Set the player construct callback. It can be a lambda function.
        PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
                AMINATION_ID,
                42,
                (abstractClientPlayer -> {
                    UsedHandData.setUsedHand(abstractClientPlayer, false);
                    boolean[] mirror = UsedHandData.getUsedHand(abstractClientPlayer);
                    ModifierLayer<IAnimation> testAnimation = new ModifierLayer<>();

                    testAnimation.addModifierBefore(
                            new AbstractFadeModifier(1) {

                                @Override
                                protected float getAlpha(
                                        String s, TransformType transformType, float v) {
                                    return 0;
                                }
                            });
                    testAnimation.addModifierBefore(
                            new MirrorModifier() {

                                @Override
                                public boolean isEnabled() {
                                    return UsedHandData.getUsedHand(abstractClientPlayer)[0];
                                }
                            });
                    testAnimation.addModifierBefore(
                            new AdjustmentModifier(
                                    (part) -> {
                                        if (part.equals("head")) {
                                            return Optional.of(
                                                    new AdjustmentModifier.PartModifier(
                                                            new Vec3f(
                                                                    0,
                                                                    (float)
                                                                                    Math.toRadians(
                                                                                            abstractClientPlayer
                                                                                                            .yBodyRot
                                                                                                    - abstractClientPlayer
                                                                                                            .yHeadRot)
                                                                            * (mirror[0] ? -1 : 1),
                                                                    0),
                                                            new Vec3f(0, 0, 0)));
                                        }
                                        if (part.equals("body")) {
                                            return Optional.of(
                                                    new AdjustmentModifier.PartModifier(
                                                            new Vec3f(
                                                                    0,
                                                                    (float)
                                                                                    Math.toRadians(
                                                                                            abstractClientPlayer
                                                                                                            .yBodyRot
                                                                                                    - abstractClientPlayer
                                                                                                            .yHeadRot)
                                                                            * (mirror[0] ? -1 : 1),
                                                                    0),
                                                            new Vec3f(0, 0, 0)));
                                        }
                                        return Optional.empty();
                                    }));
                    testAnimation.addModifierBefore(
                            new SpeedModifier() {

                                @Override
                                protected void step(float delta) {
                                    super.step(
                                            (float)
                                                    (delta
                                                            * abstractClientPlayer
                                                                    .getAttributeValue(
                                                                            MagitechAttributes
                                                                                    .CASTING_SPEED)));
                                }
                            });
                    return testAnimation;
                }));
    }

    // This method will set your mods animation into the library.
    private static IAnimation registerPlayerAnimation(AbstractClientPlayer player) {
        // This will be invoked for every new player
        return new ModifierLayer<>();
    }
}
