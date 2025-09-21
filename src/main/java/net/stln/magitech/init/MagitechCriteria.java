package net.stln.magitech.init;

import java.util.function.Supplier;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;
import net.stln.magitech.advancement.ToolUpgradeTrigger;

public final class MagitechCriteria {
    public static final DeferredRegister<CriterionTrigger<?>> REGISTER =
            DeferredRegister.create(Registries.TRIGGER_TYPE, Magitech.MOD_ID);

    public static final Supplier<ToolUpgradeTrigger> TOOL_UPGRADE =
            REGISTER.register("tool_upgrade", ToolUpgradeTrigger::new);

    public static void registerCriteria(IEventBus bus) {
        REGISTER.register(bus);
    }
}
