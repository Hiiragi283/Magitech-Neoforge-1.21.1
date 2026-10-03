package net.stln.magitech.content.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.registries.holdersets.AnyHolderSet;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.feature.magic.spell.ISpell;
import net.stln.magitech.feature.magic.spell.SpellShape;
import net.stln.magitech.helper.ComponentHelper;
import net.stln.magitech.helper.ConfigHelper;
import net.stln.magitech.helper.StreamHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RandomThreadPageFunction extends LootItemConditionalFunction {

    public static final MapCodec<RandomThreadPageFunction> CODEC = RecordCodecBuilder.mapCodec(
            p_340803_ -> commonFields(p_340803_)
                    .and(RegistryCodecs.homogeneousList(MagitechRegistries.Keys.SPELL).fieldOf("spells").forGetter(RandomThreadPageFunction::getSpells))
                    .apply(p_340803_, RandomThreadPageFunction::new)
    );

    protected final @NotNull HolderSet<ISpell> spells;

    public RandomThreadPageFunction(@NotNull List<LootItemCondition> lootItemConditions) {
        this(lootItemConditions, getAllSpells());
    }

    protected RandomThreadPageFunction(List<LootItemCondition> conditions, HolderSet<ISpell> spells) {
        super(conditions);
        if (spells.size() == 0) {
            this.spells = getAllSpells();
        } else {
            this.spells = spells;
        }
    }

    public static @NotNull HolderSet<ISpell> getAllSpells() {
        HolderSet<ISpell> spellHolders = new AnyHolderSet<>(MagitechRegistries.SPELL.asLookup());
        if (ConfigHelper.isDashSpellsDisabled()) {
            spellHolders = HolderSet.direct(spellHolders.stream().filter(holder -> holder.value().asSpell().getConfig().shape() != SpellShape.DASH).toList());
        }
        return spellHolders;
    }

    public static LootItemFunction.Builder builder() {
        return simpleBuilder(RandomThreadPageFunction::new);
    }

    public @NotNull HolderSet<ISpell> getSpells() {
        return spells;
    }

    @Override
    protected @NotNull ItemStack run(@NotNull ItemStack stack, @NotNull LootContext context) {
        StreamHelper.findRandom(spells, context.getRandom()).ifPresent(holder -> ComponentHelper.setThreadPage(stack, holder.value()));
        return stack;
    }

    @Override
    public @NotNull LootItemFunctionType<? extends LootItemConditionalFunction> getType() {
        return LootFunctionInit.RANDOM_THREAD_PAGE.get();  // 登録済みである必要あり
    }
}
