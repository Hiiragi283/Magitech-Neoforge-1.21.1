package net.stln.magitech.event;

import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import net.stln.magitech.Magitech;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

@EventBusSubscriber(modid = Magitech.MOD_ID, value = Dist.CLIENT)
public class KeyMappingEvent {

    public static final Lazy<KeyMapping> RADIAL_SPELL_MENU =
            Lazy.of(
                    () ->
                            new KeyMapping(
                                    "key.magitech.radial_spell_menu",
                                    KeyConflictContext.UNIVERSAL,
                                    InputConstants.Type.KEYSYM,
                                    GLFW.GLFW_KEY_R,
                                    "key.categories.magitech.category"));
    public static final Lazy<KeyMapping> TRAIT_ACTION =
            Lazy.of(
                    () ->
                            new KeyMapping(
                                    "key.magitech.trait_action",
                                    KeyConflictContext.IN_GAME,
                                    InputConstants.Type.KEYSYM,
                                    GLFW.GLFW_KEY_V,
                                    "key.categories.magitech.category"));
    public static final Lazy<KeyMapping> DOUBLE_JUMP =
            Lazy.of(
                    () ->
                            new KeyMapping(
                                    "key.magitech.double_jump",
                                    KeyConflictContext.IN_GAME,
                                    InputConstants.Type.KEYSYM,
                                    GLFW.GLFW_KEY_SPACE,
                                    "key.categories.magitech.category"));
    public static final Lazy<KeyMapping> LONG_JUMP =
            Lazy.of(
                    () ->
                            new KeyMapping(
                                    "key.magitech.long_jump",
                                    KeyConflictContext.IN_GAME,
                                    InputConstants.Type.KEYSYM,
                                    GLFW.GLFW_KEY_SPACE,
                                    "key.categories.magitech.category"));
    public static final Lazy<KeyMapping> SPELL_SHIFT_RIGHT =
            Lazy.of(
                    () ->
                            new KeyMapping(
                                    "key.magitech.spell_shift_right",
                                    KeyConflictContext.IN_GAME,
                                    InputConstants.Type.KEYSYM,
                                    GLFW.GLFW_KEY_C,
                                    "key.categories.magitech.category"));
    public static final Lazy<KeyMapping> SPELL_SHIFT_LEFT =
            Lazy.of(
                    () ->
                            new KeyMapping(
                                    "key.magitech.spell_shift_left",
                                    KeyConflictContext.IN_GAME,
                                    InputConstants.Type.KEYSYM,
                                    GLFW.GLFW_KEY_X,
                                    "key.categories.magitech.category"));
    public static final Lazy<KeyMapping> open_thread_bound_page_screen =
            Lazy.of(
                    () ->
                            new KeyMapping(
                                    "key.magitech.open_thread_bound_page_screen",
                                    KeyConflictContext.IN_GAME,
                                    InputConstants.Type.KEYSYM,
                                    GLFW.GLFW_KEY_N,
                                    "key.categories.magitech.category"));
    public static final Lazy<KeyMapping> OPEN_SPELLBOUND_AS_GUIDEBOOK =
            Lazy.of(
                    () ->
                            new KeyMapping(
                                    "key.magitech.open_spellbound_as_guidebook",
                                    KeyConflictContext.IN_GAME,
                                    InputConstants.Type.KEYSYM,
                                    GLFW.GLFW_KEY_COMMA,
                                    "key.categories.magitech.category"));

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(RADIAL_SPELL_MENU.get());
        event.register(TRAIT_ACTION.get());
        event.register(DOUBLE_JUMP.get());
        event.register(LONG_JUMP.get());
        event.register(SPELL_SHIFT_RIGHT.get());
        event.register(SPELL_SHIFT_LEFT.get());
        event.register(open_thread_bound_page_screen.get());
        event.register(OPEN_SPELLBOUND_AS_GUIDEBOOK.get());
    }
}
