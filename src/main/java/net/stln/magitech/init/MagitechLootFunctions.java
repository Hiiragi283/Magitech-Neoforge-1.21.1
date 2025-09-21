package net.stln.magitech.init;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.stln.magitech.Magitech;
import net.stln.magitech.loot.RandomThreadPageFunction;

public final class MagitechLootFunctions {

    public static final DeferredRegister<LootItemFunctionType<?>> REGISTER =
            DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, Magitech.MOD_ID);

    public static final Supplier<LootItemFunctionType<RandomThreadPageFunction>>
            RANDOM_THREAD_PAGE =
                    REGISTER.register(
                            "random_thread_page",
                            () -> new LootItemFunctionType<>(RandomThreadPageFunction.CODEC));

    public static void registerFunctions(IEventBus eventBus) {
        Magitech.LOGGER.info("Registering Loot Functions for" + Magitech.MOD_ID);
        REGISTER.register(eventBus);
    }
}
