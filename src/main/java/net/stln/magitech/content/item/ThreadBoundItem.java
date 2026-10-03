package net.stln.magitech.content.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.stln.magitech.content.gui.ThreadboundMenu;
import net.stln.magitech.content.item.component.SpellComponent;
import net.stln.magitech.content.item.tooltip_item.TooltipTextItem;
import net.stln.magitech.feature.element.Element;
import net.stln.magitech.feature.magic.spell.ISpell;
import net.stln.magitech.helper.ComponentHelper;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadBoundItem extends TooltipTextItem implements ICurioItem, IThreadBoundItem {

    Map<Holder<Attribute>, AttributeModifier> attributeModifiers = new HashMap<>();

    public ThreadBoundItem(Properties settings) {
        super(settings);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> modifierMultimap = ImmutableMultimap.builder();
        for (Map.Entry<Holder<Attribute>, AttributeModifier> modifier : attributeModifiers.entrySet()) {
            modifierMultimap.put(modifier.getKey(), modifier.getValue());
        }
        return modifierMultimap.build();
    }

    public ThreadBoundItem attributeModifier(Map<Holder<Attribute>, AttributeModifier> map) {
        attributeModifiers = map;
        return this;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        player.openMenu(new SimpleMenuProvider(
                (containerId, playerInventory, player2) -> new ThreadboundMenu(containerId, playerInventory),
                Component.literal(player.getItemInHand(usedHand).getHoverName().getString())
        ));
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(usedHand));
    }

    @Override
    public void appendHoverText(ItemStack stack, @NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        int i = 0;
        @NotNull SpellComponent spells = ComponentHelper.getSpells(stack);
        for (Holder<ISpell> holder : spells.spells()) {
            int abs = Math.abs(spells.selected() - i);
            if (abs <= 2 || Screen.hasShiftDown()) {
                Element element = holder.value().getConfig().element();
                if (spells.selected() == i) {
                    tooltipComponents.add(Component.literal("> ").append(ISpell.getDisplayName(holder)).withColor(element.getTextColor().getRGB()));
                } else {
                    tooltipComponents.add(ISpell.getDisplayName(holder).withColor(element.getDark().getRGB()));
                }
            } else if (abs == 3) {
                tooltipComponents.add(Component.literal("...").withColor(0x405060));
            }
            i++;
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
