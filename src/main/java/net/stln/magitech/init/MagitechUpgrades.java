package net.stln.magitech.init;

import net.neoforged.bus.api.IEventBus;
import net.stln.magitech.Magitech;
import net.stln.magitech.element.Element;
import net.stln.magitech.item.tool.ToolStats;
import net.stln.magitech.item.tool.material.MiningLevel;
import net.stln.magitech.item.tool.upgrade.SimpleUpgrade;
import net.stln.magitech.item.tool.upgrade.Upgrade;
import net.stln.magitech.registry.DeferredUpgrade;
import net.stln.magitech.registry.DeferredUpgradeRegister;

import org.jetbrains.annotations.NotNull;

public class MagitechUpgrades {

    public static final DeferredUpgradeRegister REGISTER =
            new DeferredUpgradeRegister(Magitech.MOD_ID);

    public static final DeferredUpgrade<Upgrade> ATTACK_UPGRADE =
            register(
                    "damage_upgrade",
                    new ToolStats(0.1F, 0, 0, 0, 0, 0, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final DeferredUpgrade<Upgrade> ELEMENTAL_ATTACK_UPGRADE =
            register(
                    "elemental_damage_upgrade",
                    new ToolStats(0, 0.1F, 0, 0, 0, 0, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final DeferredUpgrade<Upgrade> SPEED_UPGRADE =
            register(
                    "speed_upgrade",
                    new ToolStats(0, 0, 0.1F, 0, 0, 0, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final DeferredUpgrade<Upgrade> MINING_UPGRADE =
            register(
                    "mining_upgrade",
                    new ToolStats(0, 0, 0, 0.1F, 0, 0, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final DeferredUpgrade<Upgrade> DEFENCE_UPGRADE =
            register(
                    "defence_upgrade",
                    new ToolStats(0, 0, 0, 0, 0.1F, 0, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final DeferredUpgrade<Upgrade> RANGE_UPGRADE =
            register(
                    "range_upgrade",
                    new ToolStats(0, 0, 0, 0, 0, 0.1F, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final DeferredUpgrade<Upgrade> SWEEP_UPGRADE =
            register(
                    "sweep_upgrade",
                    new ToolStats(0, 0, 0, 0, 0, 0, 0.1F, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final DeferredUpgrade<Upgrade> DURABILITY_UPGRADE =
            register(
                    "durability_upgrade",
                    new ToolStats(0, 0, 0, 0, 0, 0, 0, 0.1F, Element.NONE, MiningLevel.NONE, 0));

    public static final DeferredUpgrade<Upgrade> CASTER_POWER_UPGRADE =
            register(
                    "caster_power_upgrade",
                    new ToolStats(0.1F, 0, 0, 0, 0, 0, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final @NotNull DeferredUpgrade<Upgrade> CASTER_ELEMENTAL_POWER_UPGRADE =
            register(
                    "caster_elemental_power_upgrade",
                    new ToolStats(0, 0.1F, 0, 0, 0, 0, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final @NotNull DeferredUpgrade<Upgrade> CASTER_CHARGE_UPGRADE =
            register(
                    "caster_charge_upgrade",
                    new ToolStats(0, 0, 0.1F, 0, 0, 0, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final @NotNull DeferredUpgrade<Upgrade> CASTER_COOLDOWN_UPGRADE =
            register(
                    "caster_cooldown_upgrade",
                    new ToolStats(0, 0, 0, 0.1F, 0, 0, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final @NotNull DeferredUpgrade<Upgrade> CASTER_DEFENCE_UPGRADE =
            register(
                    "caster_defence_upgrade",
                    new ToolStats(0, 0, 0, 0, 0.1F, 0, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final @NotNull DeferredUpgrade<Upgrade> CASTER_PROJECTILE_SPEED_UPGRADE =
            register(
                    "caster_projectile_speed_upgrade",
                    new ToolStats(0, 0, 0, 0, 0, 0.1F, 0, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final @NotNull DeferredUpgrade<Upgrade> CASTER_EFFICIENCY_UPGRADE =
            register(
                    "caster_efficiency_upgrade",
                    new ToolStats(0, 0, 0, 0, 0, 0, 0.1F, 0, Element.NONE, MiningLevel.NONE, 0));
    public static final @NotNull DeferredUpgrade<Upgrade> CASTER_DURABILITY_UPGRADE =
            register(
                    "caster_durability_upgrade",
                    new ToolStats(0, 0, 0, 0, 0, 0, 0, 0.1F, Element.NONE, MiningLevel.NONE, 0));

    private static @NotNull DeferredUpgrade<Upgrade> register(
            @NotNull String name, @NotNull ToolStats stats) {
        return REGISTER.register(name, () -> new SimpleUpgrade(stats));
    }

    public static void registerUpgrades(IEventBus eventBus) {
        Magitech.LOGGER.info("Registering Upgrades for " + Magitech.MOD_ID);
        REGISTER.register(eventBus);
    }
}
