package net.stln.magitech.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.stln.magitech.client.event.ClientLeftClickEvent;

public class EventInit {

    public static void registerEvent() {}

    @Environment(EnvType.CLIENT)
    public static void registerClientEvent() {
        ClientLeftClickEvent.register();
    }
}
