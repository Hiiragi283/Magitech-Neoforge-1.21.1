package net.stln.magitech.feature.magic.spell;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.feature.magic.spell.spell.ember.*;
import net.stln.magitech.feature.magic.spell.spell.flow.*;
import net.stln.magitech.feature.magic.spell.spell.glace.*;
import net.stln.magitech.feature.magic.spell.spell.hollow.*;
import net.stln.magitech.feature.magic.spell.spell.magic.*;
import net.stln.magitech.feature.magic.spell.spell.mana.*;
import net.stln.magitech.feature.magic.spell.spell.phantom.*;
import net.stln.magitech.feature.magic.spell.spell.surge.*;
import net.stln.magitech.feature.magic.spell.spell.tremor.*;
import org.jetbrains.annotations.NotNull;

public class SpellInit {
    public static final DeferredRegister<ISpell> REGISTER = DeferredRegister.create(MagitechRegistries.Keys.SPELL, Magitech.MOD_ID);

    public static final DeferredHolder<ISpell, ISpell> IGNISCA = register("ignisca", new Ignisca());
    public static final DeferredHolder<ISpell, ISpell> PYROLUX = register("pyrolux", new Pyrolux());
    public static final DeferredHolder<ISpell, ISpell> FLUVALEN = register("fluvalen", new Fluvalen());
    public static final DeferredHolder<ISpell, ISpell> BLAZEWEND = register("blazewend", new Blazewend());
    public static final DeferredHolder<ISpell, ISpell> VOLKARIN = register("volkarin", new Volkarin());
    public static final DeferredHolder<ISpell, ISpell> ARDOVITAE = register("ardovitae", new Ardovitae());

    public static final DeferredHolder<ISpell, ISpell> FRIGALA = register("frigala", new Frigala());
    public static final DeferredHolder<ISpell, ISpell> CRYOLUXA = register("cryoluxa", new Cryoluxa());
    public static final DeferredHolder<ISpell, ISpell> NIVALUNE = register("nivalune", new Nivalune());
    public static final DeferredHolder<ISpell, ISpell> GLISTELDA = register("glistelda", new Glistelda());
    public static final DeferredHolder<ISpell, ISpell> FROSBLAST = register("frosblast", new Frosblast());

    public static final DeferredHolder<ISpell, ISpell> VOLTARIS = register("voltaris", new Voltaris());
    public static final DeferredHolder<ISpell, ISpell> FULGENZA = register("fulgenza", new Fulgenza());
    public static final DeferredHolder<ISpell, ISpell> SPARKION = register("sparkion", new Sparkion());
    public static final DeferredHolder<ISpell, ISpell> ARCLUME = register("arclume", new Arclume());
    public static final DeferredHolder<ISpell, ISpell> ELECTROIDE = register("electroide", new Electroide());

    public static final DeferredHolder<ISpell, ISpell> MIRAZIEN = register("mirazien", new Mirazien());
    public static final DeferredHolder<ISpell, ISpell> PHANTASTRA = register("phantastra", new Phantastra());
    public static final DeferredHolder<ISpell, ISpell> VEILMIST = register("veilmist", new Veilmist());
    public static final DeferredHolder<ISpell, ISpell> FADANCEA = register("fadancea", new Fadancea());
    public static final DeferredHolder<ISpell, ISpell> ILLUSFLARE = register("illusflare", new Illusflare());
    public static final DeferredHolder<ISpell, ISpell> LUXGRAIL = register("luxgrail", new Luxgrail());

    public static final DeferredHolder<ISpell, ISpell> TREMIVOX = register("tremivox", new Tremivox());
    public static final DeferredHolder<ISpell, ISpell> OSCILBEAM = register("oscilbeam", new Oscilbeam());
    public static final DeferredHolder<ISpell, ISpell> SONISTORM = register("sonistorm", new Sonistorm());
    public static final DeferredHolder<ISpell, ISpell> QUAVERIS = register("quaveris", new Quaveris());
    public static final DeferredHolder<ISpell, ISpell> SHOCKVANE = register("shockvane", new Shockvane());

    public static final DeferredHolder<ISpell, ISpell> ARCALETH = register("arcaleth", new Arcaleth());
    public static final DeferredHolder<ISpell, ISpell> MYSTAVEN = register("mystaven", new Mystaven());
    public static final DeferredHolder<ISpell, ISpell> GLYMORA = register("glymora", new Glymora());
    public static final DeferredHolder<ISpell, ISpell> ENVISTRA = register("envistra", new Envistra());
    public static final DeferredHolder<ISpell, ISpell> HEXFLARE = register("hexflare", new Hexflare());
    public static final DeferredHolder<ISpell, ISpell> MYSTPHEL = register("mystphel", new Mystphel());

    public static final DeferredHolder<ISpell, ISpell> AELTHERIN = register("aeltherin", new Aeltherin());
    public static final DeferredHolder<ISpell, ISpell> FLUVINAE = register("fluvinae", new Fluvinae());
    public static final DeferredHolder<ISpell, ISpell> MISTRELUNE = register("mistrelune", new Mistrelune());
    public static final DeferredHolder<ISpell, ISpell> SYLLAEZE = register("syllaeze", new Syllaeze());
    public static final DeferredHolder<ISpell, ISpell> HYDRELUX = register("hydrelux", new Hydrelux());
    public static final DeferredHolder<ISpell, ISpell> NYMPHORA = register("nymphora", new Nymphora());
    public static final DeferredHolder<ISpell, ISpell> HYDRAERUN = register("hydraerun", new Hydraerun());

    public static final DeferredHolder<ISpell, ISpell> NULLIXIS = register("nullixis", new Nullixis());
    public static final DeferredHolder<ISpell, ISpell> VOIDLANCE = register("voidlance", new Voidlance());
    public static final DeferredHolder<ISpell, ISpell> TENEBRISOL = register("tenebrisol", new Tenebrisol());
    public static final DeferredHolder<ISpell, ISpell> DISPARUNDRA = register("disparundra", new Disparundra());
    public static final DeferredHolder<ISpell, ISpell> NIHILFLARE = register("nihilflare", new Nihilflare());
    public static final DeferredHolder<ISpell, ISpell> TENEBPORT = register("tenebport", new Tenebport());

    public static final DeferredHolder<ISpell, ISpell> AETHERIX = register("aetherix", new Aetherix());
    public static final DeferredHolder<ISpell, ISpell> THAUMIRIA = register("thaumiris", new Thaumiris());
    public static final DeferredHolder<ISpell, ISpell> ESFOUNTIA = register("esfountia", new Esfountia());
    public static final DeferredHolder<ISpell, ISpell> QUINTEX = register("quintex", new Quintex());
    public static final DeferredHolder<ISpell, ISpell> ENERCRUX = register("enercrux", new Enercrux());

    private static @NotNull DeferredHolder<ISpell, ISpell> register(@NotNull String path, @NotNull ISpell spell) {
        return REGISTER.register(path, () -> spell);
    }

    public static void registerSpells(IEventBus bus) {
        Magitech.LOGGER.info("Registering Spells for" + Magitech.MOD_ID);
        REGISTER.register(bus);
    }
}
