package net.stln.magitech.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.item.tool.upgrade.Upgrade;
import net.stln.magitech.item.tool.upgrade.UpgradeLike;

public class DeferredUpgrade<T extends Upgrade> extends DeferredHolder<Upgrade, T>
        implements UpgradeLike {

    public DeferredUpgrade(ResourceKey<Upgrade> key) {
        super(key);
    }

    public DeferredUpgrade(ResourceLocation id) {
        this(ResourceKey.create(MagitechRegistries.Keys.UPGRADE, id));
    }

    @Override
    public Upgrade asUpgrade() {
        return get();
    }
}
