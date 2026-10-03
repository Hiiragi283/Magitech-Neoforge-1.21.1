package net.stln.magitech.content.event;

import net.minecraft.core.HolderSet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.stln.magitech.Magitech;
import net.stln.magitech.content.item.ItemInit;
import net.stln.magitech.content.item.component.SpellComponent;
import net.stln.magitech.content.loot.RandomThreadPageFunction;
import net.stln.magitech.feature.magic.spell.ISpell;
import net.stln.magitech.feature.magic.spell.SpellInit;
import net.stln.magitech.helper.ComponentHelper;
import net.stln.magitech.helper.StreamHelper;

@EventBusSubscriber(modid = Magitech.MOD_ID)
public class PlayerFirstSpawnEvent {

    @SuppressWarnings("deprecation")
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
            ItemStack stack = new ItemStack(ItemInit.GLISTENING_LEXICON.get());
            var enercrux = SpellInit.ENERCRUX;

            HolderSet<ISpell> allSpells = RandomThreadPageFunction.getAllSpells();
            StreamHelper.findRandom(allSpells.stream().filter(holder -> !holder.is(enercrux)), player.getRandom(), allSpells.size())
                    .ifPresent(spell -> {
                        ComponentHelper.updateSpells(stack, spellComponent -> new SpellComponent(HolderSet.direct(SpellInit.ENERCRUX, spell)));
                        player.getInventory().add(stack);

                        persisted.putBoolean("hasReceivedInitialItems", true);
                    });
        }
    }
}
