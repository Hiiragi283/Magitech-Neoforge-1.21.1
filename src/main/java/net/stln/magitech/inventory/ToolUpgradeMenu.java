package net.stln.magitech.inventory;

import java.util.List;
import java.util.Random;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.stln.magitech.init.MagitechBlocks;
import net.stln.magitech.init.MagitechCriteria;
import net.stln.magitech.init.MagitechDataComponents;
import net.stln.magitech.init.MagitechMenuTypes;
import net.stln.magitech.item.component.UpgradeComponent;
import net.stln.magitech.item.tool.toolitem.PartToolItem;
import net.stln.magitech.item.tool.upgrade.Upgrade;
import net.stln.magitech.item.tool.upgrade.UpgradeInstance;
import net.stln.magitech.item.tool.upgrade.UpgradeUtil;
import net.stln.magitech.util.ComponentHelper;
import net.stln.magitech.util.ToolMaterialUtil;

import org.jetbrains.annotations.NotNull;

public class ToolUpgradeMenu extends AbstractContainerMenu {

    public static final int INPUT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    private static final int INV_SLOT_START = 2;
    private static final int INV_SLOT_END = 29;
    private static final int USE_ROW_SLOT_START = 29;
    private static final int USE_ROW_SLOT_END = 38;
    private final ContainerLevelAccess access;
    private final Level level;

    /**
     * Stores the game time of the last time the player took items from the the crafting result
     * slot. This is used to prevent the sound from being played multiple times on the same tick.
     */
    public List<Upgrade> upgrades = List.of();

    int upgradeSize = 3;
    long lastSoundTime;
    Runnable slotUpdateListener = () -> {};

    /** The {@linkplain ItemStack} set in the input slot by the player. */
    private ItemStack input = ItemStack.EMPTY;

    public final Container container =
            new SimpleContainer(2) {

                @Override
                public void setChanged() {
                    super.setChanged();
                    ToolUpgradeMenu.this.slotsChanged(this);
                    ToolUpgradeMenu.this.slotUpdateListener.run();
                }
            };

    public ToolUpgradeMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    public ToolUpgradeMenu(
            int containerId, Inventory playerInventory, final ContainerLevelAccess access) {
        super(MagitechMenuTypes.TOOL_UPGRADE.get(), containerId);
        this.access = access;
        this.level = playerInventory.player.level();
        this.addSlot(new Slot(this.container, 0, 20, 69));
        this.addSlot(new Slot(this.container, 1, 20, 30));
        // this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 49) {
        // @Override
        // public boolean mayPlace(ItemStack p_40362_) {
        // return false;
        // }
        //
        // @Override
        // public void onTake(Player p_150672_, ItemStack p_150673_) {
        // p_150673_.onCraftedBy(p_150672_.level(), p_150672_, p_150673_.getCount());
        // ToolUpgradeMenu.this.resultContainer.awardUsedRecipes(p_150672_,
        // this.getRelevantItems());
        // ItemStack itemstack = removeCount();
        // if (!itemstack.isEmpty()) {
        // ToolUpgradeMenu.this.setupResultSlot();
        // }
        //
        // access.execute((p_40364_, p_40365_) -> {
        // long l = p_40364_.getGameTime();
        // if (ToolUpgradeMenu.this.lastSoundTime != l) {
        // ToolUpgradeMenu.this.lastSoundTime = l;
        // }
        // });
        // super.onTake(p_150672_, p_150673_);
        // }
        //
        // private List<ItemStack> getRelevantItems() {
        // return List.of(ToolUpgradeMenu.this.container.getItem());
        // }
        // });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 117 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 175));
        }
    }

    /** Determines whether supplied player can use this container */
    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(this.access, player, MagitechBlocks.UPGRADE_WORKBENCH.get());
    }

    /**
     * Handles the given Button-click on the server, currently only used by enchanting. Name is for
     * legacy.
     */
    @Override
    public boolean clickMenuButton(@NotNull Player player, int id) {
        if (isValidUpgrade(id)) {
            ItemStack stack = container.getItem(0);
            stack.update(
                    MagitechDataComponents.UPGRADE_COMPONENT,
                    UpgradeComponent.EMPTY,
                    upgradeComponent ->
                            upgradeComponent.addUpgrade(new UpgradeInstance(1, upgrades.get(id))));
            stack.set(
                    MagitechDataComponents.UPGRADE_SEED_COMPONENT,
                    player.getRandom().nextInt(Integer.MAX_VALUE));
            ComponentHelper.updateUpgradePoint(stack, value -> value - 1);
            this.container.setItem(0, stack);
            ItemStack material = this.container.getItem(1).copy();
            material.shrink(1);
            this.container.setItem(1, material);
            player.level()
                    .playSound(
                            player,
                            player,
                            SoundEvents.SMITHING_TABLE_USE,
                            SoundSource.BLOCKS,
                            1.0F,
                            1.0F);
            if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
                MagitechCriteria.TOOL_UPGRADE
                        .get()
                        .trigger(
                                serverPlayer,
                                stack,
                                ComponentHelper.getTier(stack)
                                        - ComponentHelper.getUpgradePoint(stack));
                ((PartToolItem) stack.getItem()).reloadComponent(player, level, stack);
            }
        }
        return true;
    }

    private boolean isValidUpgrade(int upgradeIndex) {
        return upgradeIndex >= 0 && upgradeIndex < this.upgradeSize && canUpgrade();
    }

    public boolean canUpgrade() {
        ItemStack itemStack = container.getItem(0);
        return hasUpgradePoint(itemStack) && isCorrectMaterialForUpgrade(itemStack);
    }

    public boolean isCorrectMaterialForUpgrade(ItemStack itemStack) {
        return ToolMaterialUtil.isCorrectMaterialForUpgrade(
                ComponentHelper.getTier(itemStack),
                ComponentHelper.getUpgradePoint(itemStack),
                container.getItem(1));
    }

    public boolean hasUpgradePoint(ItemStack itemStack) {
        return !itemStack.isEmpty()
                && itemStack.getItem() instanceof PartToolItem
                && ComponentHelper.getUpgradePoint(itemStack) > 0;
    }

    /** Callback for when the crafting matrix is changed. */
    @Override
    public void slotsChanged(@NotNull Container inventory) {
        ItemStack itemstack = this.container.getItem(0);
        this.input = itemstack.copy();
        this.setupUpgrade(inventory, itemstack);
    }

    private void setupUpgrade(Container container, ItemStack stack) {
        if (stack.isEmpty() || !(stack.getItem() instanceof PartToolItem)) {
            this.upgrades = List.of();
            return;
        }
        if (!stack.has(MagitechDataComponents.UPGRADE_SEED_COMPONENT)) {
            stack.set(
                    MagitechDataComponents.UPGRADE_SEED_COMPONENT,
                    new Random().nextInt(Integer.MAX_VALUE));
        }
        upgrades =
                UpgradeUtil.getUpgrades(
                        upgradeSize,
                        stack.get(MagitechDataComponents.UPGRADE_SEED_COMPONENT),
                        stack);
    }

    @Override
    public MenuType<?> getType() {
        return MagitechMenuTypes.TOOL_UPGRADE.get();
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code.
     * The stack passed in is null for the initial slot that was double-clicked.
     */
    @Override
    public boolean canTakeItemForPickAll(@NotNull ItemStack stack, @NotNull Slot slot) {
        return super.canTakeItemForPickAll(stack, slot);
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack
     * between the player inventory and the other inventory(s).
     */
    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            Item item = itemstack1.getItem();
            itemstack = itemstack1.copy();
            if (index == 1) {
                item.onCraftedBy(itemstack1, player.level(), player);
                if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index == 0) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemstack1.getItem() instanceof PartToolItem) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                ItemStack itemStack = this.container.getItem(0);
                if (this.hasUpgradePoint(itemStack)
                        && ToolMaterialUtil.isCorrectMaterialForUpgrade(
                                ComponentHelper.getTier(itemStack),
                                ComponentHelper.getUpgradePoint(itemStack),
                                itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 2 && index < 29) {
                    if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 29
                        && index < 38
                        && !this.moveItemStackTo(itemstack1, 2, 29, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            }

            slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
            this.broadcastChanges();
        }

        return itemstack;
    }

    /** Called when the container is closed. */
    @Override
    public void removed(Player player) {
        super.removed(player);
        this.access.execute((p_40313_, p_40314_) -> this.clearContainer(player, this.container));
    }
}
