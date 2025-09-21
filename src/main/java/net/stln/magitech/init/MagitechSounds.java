package net.stln.magitech.init;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;

public final class MagitechSounds {

    public static final DeferredRegister<SoundEvent> REGISTER =
            DeferredRegister.create(Registries.SOUND_EVENT, Magitech.MOD_ID);

    public static final Supplier<SoundEvent> PHANTOM_BUFF = register("phantom_buff");
    public static final Supplier<SoundEvent> GEOMENDING_BREAK = register("geomending_break");
    public static final Supplier<SoundEvent> PHANTOM_SLAYER_DASH = register("phantom_slayer_dash");
    public static final Supplier<SoundEvent> FROST_BREAK = register("frost_break");
    public static final Supplier<SoundEvent> ZAP = register("zap");
    public static final Supplier<SoundEvent> FLAME = register("flame");
    public static final Supplier<SoundEvent> SONICBOOM = register("sonicboom");
    public static final Supplier<SoundEvent> MYSTICAL = register("mystical");
    public static final Supplier<SoundEvent> GLACE_LAUNCH = register("glace_launch");
    public static final Supplier<SoundEvent> SPARK = register("spark");
    public static final Supplier<SoundEvent> BLOW = register("blow");
    public static final Supplier<SoundEvent> ARCALETH = register("arcaleth");
    public static final Supplier<SoundEvent> FIREBALL = register("fireball");
    public static final Supplier<SoundEvent> TREMIVOX = register("tremivox");
    public static final Supplier<SoundEvent> AELTHERIN = register("aeltherin");
    public static final Supplier<SoundEvent> NULLIXIS = register("nullixis");
    public static final Supplier<SoundEvent> NIVALUNE = register("nivalune");
    public static final Supplier<SoundEvent> FULGENZA = register("fulgenza");
    public static final Supplier<SoundEvent> FULGENZA_CHARGE = register("fulgenza_charge");
    public static final Supplier<SoundEvent> VOIDLANCE = register("voidlance");
    public static final Supplier<SoundEvent> MYSTAVEN = register("mystaven");
    public static final Supplier<SoundEvent> PHANTASTRA = register("phantastra");
    public static final Supplier<SoundEvent> PYROLUX = register("pyrolux");
    public static final Supplier<SoundEvent> FLUVINAE = register("fluvinae");
    public static final Supplier<SoundEvent> VEILMIST = register("veilmist");
    public static final Supplier<SoundEvent> SONISTORM = register("sonistorm");
    public static final Supplier<SoundEvent> GLYMORA = register("glymora");
    public static final Supplier<SoundEvent> TENEBRISOL = register("tenebrisol");
    public static final Supplier<SoundEvent> ECHOLOCATION = register("echolocation");
    public static final Supplier<SoundEvent> ENERCRUX = register("enercrux");
    public static final Supplier<SoundEvent> BLAZEWEND = register("blazewend");
    public static final Supplier<SoundEvent> GLISTELDA = register("glistelda");
    public static final Supplier<SoundEvent> GLISTELDA_BREAK = register("glistelda_break");
    public static final Supplier<SoundEvent> FADANCEA = register("fadancea");
    public static final Supplier<SoundEvent> QUAVERIS = register("quaveris");
    public static final Supplier<SoundEvent> ENVISTRA = register("envistra");
    public static final Supplier<SoundEvent> DISPARUNDRA = register("disparundra");
    public static final Supplier<SoundEvent> SYLLAEZE = register("syllaeze");
    public static final Supplier<SoundEvent> ARCLUME = register("arclume");
    public static final Supplier<SoundEvent> NYMPHORA = register("nymphora");
    public static final Supplier<SoundEvent> TENEBPORT = register("tenebport");
    public static final Supplier<SoundEvent> TENEBPORT_CHARGE = register("tenebport_charge");

    public static final Supplier<SoundEvent> AETHER_LIFTER_JUMP = register("aether_lifter_jump");
    public static final Supplier<SoundEvent> FLAMGLIDE_STRIDER_JUMP =
            register("flamglide_strider_jump");

    public static final Supplier<SoundEvent> ATHANOR_PILLAR_INFUSION =
            register("athanor_pillar_infusion");
    public static final Supplier<SoundEvent> ATHANOR_PILLAR_ZAP = register("athanor_pillar_zap");

    public static final Supplier<SoundEvent> CRYSTAL_BREAK = register("crystal_break");
    public static final Supplier<SoundEvent> CRYSTAL_PLACE = register("crystal_place");
    public static final Supplier<SoundEvent> CRYSTAL_HIT = register("crystal_hit");
    public static final Supplier<SoundEvent> CRYSTAL_STEP = register("crystal_step");
    public static final Supplier<SoundEvent> CRYSTAL_FALL = register("crystal_fall");

    public static final Supplier<SoundEvent> ALCHECRYSITE_BREAK = register("alchecrysite_break");
    public static final Supplier<SoundEvent> ALCHECRYSITE_PLACE = register("alchecrysite_place");
    public static final Supplier<SoundEvent> ALCHECRYSITE_HIT = register("alchecrysite_hit");
    public static final Supplier<SoundEvent> ALCHECRYSITE_STEP = register("alchecrysite_step");
    public static final Supplier<SoundEvent> ALCHECRYSITE_FALL = register("alchecrysite_fall");

    public static final Supplier<SoundEvent> WEAVER_HURT = register("weaver_hurt");
    public static final Supplier<SoundEvent> WEAVER_DEATH = register("weaver_death");

    private static Supplier<SoundEvent> register(String name) {
        return REGISTER.register(name, SoundEvent::createVariableRangeEvent);
    }

    public static void registerSoundEvents(IEventBus eventBus) {
        Magitech.LOGGER.info("Registering Sound for " + Magitech.MOD_ID);
        REGISTER.register(eventBus);
    }
}
