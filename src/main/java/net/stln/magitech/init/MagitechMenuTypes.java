package net.stln.magitech.init;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;
import net.stln.magitech.gui.*;
import net.stln.magitech.gui.overlay.ManaContainerInfoOverlay;
import net.stln.magitech.gui.overlay.ManaGaugeOverlay;
import net.stln.magitech.gui.overlay.SpellGaugeOverlay;
import net.stln.magitech.inventory.*;

@EventBusSubscriber(modid = Magitech.MOD_ID, value = Dist.CLIENT)
public final class MagitechMenuTypes {

    public static final DeferredRegister<MenuType<?>> REGISTER =
            DeferredRegister.create(Registries.MENU, Magitech.MOD_ID);
    public static final Supplier<MenuType<PartCuttingMenu>> PART_CUTTING =
            register("part_cutting_menu", PartCuttingMenu::new);
    public static final Supplier<MenuType<ToolAssemblyMenu>> TOOL_ASSEMBLY =
            register("tool_assembly_menu", ToolAssemblyMenu::new);
    public static final Supplier<MenuType<ToolRepairingMenu>> TOOL_REPAIRING =
            register("tool_repairing_menu", ToolRepairingMenu::new);
    public static final Supplier<MenuType<ToolUpgradeMenu>> TOOL_UPGRADE =
            register("tool_upgrade_menu", ToolUpgradeMenu::new);
    public static final Supplier<MenuType<ThreadboundMenu>> THREADBOUND =
            register("threadbound_menu", ThreadboundMenu::new);

    private static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(
            String name, MenuType.MenuSupplier<T> supplier) {
        return REGISTER.register(name, () -> new MenuType<>(supplier, FeatureFlags.VANILLA_SET));
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRegisterOverlays(RegisterGuiLayersEvent event) {
        event.registerAboveAll(Magitech.id("mana_gauge"), new ManaGaugeOverlay());
        event.registerAboveAll(Magitech.id("spell_gauge"), new SpellGaugeOverlay());
        event.registerAboveAll(Magitech.id("mana_container_info"), new ManaContainerInfoOverlay());
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(PART_CUTTING.get(), PartCuttingScreen::new);
        event.register(TOOL_ASSEMBLY.get(), ToolAssemblyScreen::new);
        event.register(TOOL_REPAIRING.get(), ToolRepairingScreen::new);
        event.register(TOOL_UPGRADE.get(), ToolUpgradeScreen::new);
        event.register(THREADBOUND.get(), ThreadboundScreen::new);
    }

    public static void registerMenus(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
