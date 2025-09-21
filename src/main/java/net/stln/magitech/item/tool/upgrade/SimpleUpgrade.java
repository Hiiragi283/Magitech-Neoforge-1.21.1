package net.stln.magitech.item.tool.upgrade;

import net.stln.magitech.item.tool.ToolStats;

public record SimpleUpgrade(ToolStats upgradeStats) implements Upgrade {

    public ToolStats getUpgradeStats(int level) {
        return ToolStats.mulWithoutElementCode(this.upgradeStats, level);
    }
}
