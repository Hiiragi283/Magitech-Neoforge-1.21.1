package net.stln.magitech.item.tool.upgrade;

import java.util.*;
import java.util.stream.Stream;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.item.tool.toolitem.SpellCasterItem;
import net.stln.magitech.tag.MagitechTags;

public class UpgradeUtil {

    public static List<Upgrade> getUpgrades(int size, int seed, ItemStack stack) {
        Stream<Holder.Reference<Upgrade>> upgrades = MagitechRegistries.UPGRADE.holders();
        if (stack.getItem() instanceof SpellCasterItem) {
            upgrades = upgrades.filter(holder -> holder.is(MagitechTags.Upgrades.IS_SPELL));
        }
        return pickRandomValues(upgrades.map(Holder.Reference::value).toList(), seed, size);
    }

    public static <V> List<V> pickRandomValues(Collection<V> values, long seed, int count) {
        // RandomSourceでseed固定
        RandomSource random = RandomSource.create(seed);

        // valuesをリスト化
        List<V> list = new ArrayList<>(values);

        // ランダムにシャッフル
        Collections.shuffle(list, new Random(random.nextLong()));

        // 指定数だけ取り出す（要素数が足りなければ全部返す）
        return list.subList(0, Math.min(count, list.size()));
    }
}
