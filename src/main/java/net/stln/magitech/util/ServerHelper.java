package net.stln.magitech.util;

import java.util.Optional;

import net.minecraft.server.MinecraftServer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ServerHelper {
    public static @Nullable MinecraftServer getCurrentServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

    public static @NotNull Optional<MinecraftServer> getOptionalServer() {
        return Optional.ofNullable(getCurrentServer());
    }
}
