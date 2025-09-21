package net.stln.magitech.compat.curios;

import net.stln.magitech.Magitech;
import net.stln.magitech.tag.MagitechTags;

import top.theillusivec4.curios.api.CuriosApi;

public class CuriosInit {

    public static void registerValidators() {
        CuriosApi.registerCurioPredicate(
                Magitech.id("threadbound"),
                (slotResult -> slotResult.stack().is(MagitechTags.Items.THREAD_BOUND)));
    }
}
