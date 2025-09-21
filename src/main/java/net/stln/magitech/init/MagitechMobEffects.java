package net.stln.magitech.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;
import net.stln.magitech.entity.effect.CustomMobEffect;
import net.stln.magitech.entity.effect.EcholocationMobEffect;
import net.stln.magitech.entity.effect.PhaseLockMobEffect;
import net.stln.magitech.entity.effect.VoidrotMobEffect;

public final class MagitechMobEffects {

    private static final DeferredRegister<MobEffect> REGISTER =
            DeferredRegister.create(Registries.MOB_EFFECT, Magitech.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> MANA_ADDICTION =
            REGISTER.register(
                    "mana_addiction",
                    id ->
                            new CustomMobEffect(MobEffectCategory.NEUTRAL, 0x40FFF0)
                                    .addAttributeModifier(
                                            MagitechAttributes.MANA_REGEN,
                                            id,
                                            2,
                                            AttributeModifier.Operation.ADD_VALUE)
                                    .addAttributeModifier(
                                            MagitechAttributes.MAX_MANA,
                                            id,
                                            -50,
                                            AttributeModifier.Operation.ADD_VALUE));

    public static final DeferredHolder<MobEffect, MobEffect> LEAP_STEP =
            REGISTER.register(
                    "leap_step",
                    id ->
                            new CustomMobEffect(MobEffectCategory.BENEFICIAL, 0xF0F0FF)
                                    .addAttributeModifier(
                                            Attributes.MOVEMENT_SPEED,
                                            id,
                                            0.5,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                                    .addAttributeModifier(
                                            Attributes.STEP_HEIGHT,
                                            id,
                                            0.5,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static final DeferredHolder<MobEffect, MobEffect> SEIZE =
            REGISTER.register(
                    "seize",
                    id ->
                            new CustomMobEffect(MobEffectCategory.HARMFUL, 0xFFFFC0)
                                    .addAttributeModifier(
                                            Attributes.MOVEMENT_SPEED,
                                            id,
                                            -1,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final DeferredHolder<MobEffect, MobEffect> ECHOLOCATION =
            REGISTER.register(
                    "echolocation",
                    id -> new EcholocationMobEffect(MobEffectCategory.BENEFICIAL, 0x004040));

    public static final DeferredHolder<MobEffect, MobEffect> VOIDROT =
            REGISTER.register(
                    "voidrot", id -> new VoidrotMobEffect(MobEffectCategory.HARMFUL, 0x400080));

    public static final DeferredHolder<MobEffect, MobEffect> PHASELOCK =
            REGISTER.register(
                    "phase_lock",
                    id -> new PhaseLockMobEffect(MobEffectCategory.HARMFUL, 0x200040));

    public static void registerMobEffects(IEventBus eventBus) {
        Magitech.LOGGER.info("Registering Mob Effect for " + Magitech.MOD_ID);
        REGISTER.register(eventBus);
    }
}
