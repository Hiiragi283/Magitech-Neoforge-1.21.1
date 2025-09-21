package net.stln.magitech.init;

import java.util.function.Function;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.PercentageAttribute;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;

import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Magitech.MOD_ID)
public class MagitechAttributes {

    private static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(Registries.ATTRIBUTE, Magitech.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> MAX_MANA =
            registerRanged("max_mana", 100);
    public static final DeferredHolder<Attribute, Attribute> MANA_REGEN =
            registerRanged("mana_regen", 2);
    public static final DeferredHolder<Attribute, Attribute> MAX_NOCTIS =
            registerRanged("max_noctis", 50);
    public static final DeferredHolder<Attribute, Attribute> NOCTIS_REGEN =
            registerRanged("noctis_regen", 0.5);
    public static final DeferredHolder<Attribute, Attribute> MAX_LUMINIS =
            registerRanged("max_luminis", 50);
    public static final DeferredHolder<Attribute, Attribute> LUMINIS_REGEN =
            registerRanged("luminis_regen", 0.5);
    public static final DeferredHolder<Attribute, Attribute> MAX_FLUXIA =
            registerRanged("max_fluxia", 50);
    public static final DeferredHolder<Attribute, Attribute> FLUXIA_REGEN =
            registerRanged("fluxia_regen", 0.5);

    public static final DeferredHolder<Attribute, Attribute> SPELL_POWER =
            registerPercent("spell_power", 1);
    public static final DeferredHolder<Attribute, Attribute> CASTING_SPEED =
            registerPercent("casting_speed", 1);
    public static final DeferredHolder<Attribute, Attribute> COOLDOWN_SPEED =
            registerPercent("cooldown_speed", 1);
    public static final DeferredHolder<Attribute, Attribute> PROJECTILE_SPEED =
            registerPercent("projectile_speed", 1);
    public static final DeferredHolder<Attribute, Attribute> MANA_EFFICIENCY =
            registerPercent("mana_efficiency", 1);

    public static final DeferredHolder<Attribute, Attribute> EMBER_SPELL_POWER =
            registerPercent("ember_spell_power", 1);
    public static final DeferredHolder<Attribute, Attribute> GLACE_SPELL_POWER =
            registerPercent("glace_spell_power", 1);
    public static final DeferredHolder<Attribute, Attribute> SURGE_SPELL_POWER =
            registerPercent("surge_spell_power", 1);
    public static final DeferredHolder<Attribute, Attribute> PHANTOM_SPELL_POWER =
            registerPercent("phantom_spell_power", 1);
    public static final DeferredHolder<Attribute, Attribute> TREMOR_SPELL_POWER =
            registerPercent("tremor_spell_power", 1);
    public static final DeferredHolder<Attribute, Attribute> MAGIC_SPELL_POWER =
            registerPercent("magic_spell_power", 1);
    public static final DeferredHolder<Attribute, Attribute> FLOW_SPELL_POWER =
            registerPercent("flow_spell_power", 1);
    public static final DeferredHolder<Attribute, Attribute> HOLLOW_SPELL_POWER =
            registerPercent("hollow_spell_power", 1);

    private static @NotNull DeferredHolder<Attribute, Attribute> register(
            @NotNull String name, @NotNull Function<String, Attribute> function) {
        return ATTRIBUTES.register(name, id -> function.apply(id.toLanguageKey("attribute")));
    }

    private static @NotNull DeferredHolder<Attribute, Attribute> registerRanged(
            @NotNull String name, double defaultValue) {
        return register(
                name,
                name1 ->
                        new RangedAttribute(name1, defaultValue, 0, Double.MAX_VALUE)
                                .setSyncable(true));
    }

    private static @NotNull DeferredHolder<Attribute, Attribute> registerPercent(
            @NotNull String name, double defaultValue) {
        return register(
                name,
                name1 ->
                        new PercentageAttribute(name1, defaultValue, 0, Double.MAX_VALUE)
                                .setSyncable(true));
    }

    public static void registerEntityAttributes(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

    @SubscribeEvent
    public static void modifyAttributes(EntityAttributeModificationEvent event) {
        event.getTypes()
                .forEach(
                        entity ->
                                ATTRIBUTES
                                        .getEntries()
                                        .forEach(attribute -> event.add(entity, attribute)));
    }
}
