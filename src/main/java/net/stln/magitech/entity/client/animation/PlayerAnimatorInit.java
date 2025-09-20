package net.stln.magitech.entity.client.animation;

import java.util.Optional;

import net.minecraft.client.player.AbstractClientPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.stln.magitech.Magitech;
import net.stln.magitech.entity.status.AttributeInit;
import net.stln.magitech.magic.mana.UsedHandData;

import dev.kosmx.playerAnim.api.TransformType;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.api.layered.modifier.AdjustmentModifier;
import dev.kosmx.playerAnim.api.layered.modifier.MirrorModifier;
import dev.kosmx.playerAnim.api.layered.modifier.SpeedModifier;
import dev.kosmx.playerAnim.core.util.Vec3f;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;

/** This is an example implementation of PlayerAnimator resourceLoading and playerMapping */
@EventBusSubscriber(modid = Magitech.MOD_ID, value = Dist.CLIENT)
public class PlayerAnimatorInit {

    @net.neoforged.bus.api.SubscribeEvent
    public static void onClientSetup(net.neoforged.fml.event.lifecycle.FMLClientSetupEvent event) {
        // Set the player construct callback. It can be a lambda function.
        PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
                Magitech.id("animation"),
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
                                                                            AttributeInit
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
