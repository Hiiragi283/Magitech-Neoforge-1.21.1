package net.stln.magitech.event;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.stln.magitech.Magitech;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.init.MagitechItems;
import net.stln.magitech.init.MagitechSpells;
import net.stln.magitech.item.component.SpellComponent;
import net.stln.magitech.util.ComponentHelper;

@EventBusSubscriber(modid = Magitech.MOD_ID)
public class PlayerFirstSpawnEvent {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();

        CompoundTag data = player.getPersistentData();
        CompoundTag persisted;

        if (!data.contains(Player.PERSISTED_NBT_TAG)) {
            persisted = new CompoundTag();
            data.put(Player.PERSISTED_NBT_TAG, persisted);
        } else {
            persisted = data.getCompound(Player.PERSISTED_NBT_TAG);
        }

        if (!persisted.getBoolean("hasReceivedInitialItems")) {
            ItemStack stack = MagitechItems.GLISTENING_LEXICON.toStack();
            var enercrux = MagitechSpells.ENERCRUX;
            MagitechRegistries.SPELL
                    .holders()
                    .filter(holder -> !holder.is(enercrux))
                    .findAny()
                    .map(Holder::value)
                    .ifPresent(
                            spell -> {
                                ComponentHelper.updateSpells(
                                        stack,
                                        spellComponent ->
                                                new SpellComponent(
                                                        List.of(MagitechSpells.ENERCRUX, spell)));
                                player.getInventory().add(stack);

                                persisted.putBoolean("hasReceivedInitialItems", true);
                            });
        }
    }
}
