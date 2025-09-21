package net.stln.magitech.loot;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.init.MagitechLootFunctions;
import net.stln.magitech.magic.spell.Spell;
import net.stln.magitech.util.ComponentHelper;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class RandomThreadPageFunction extends LootItemConditionalFunction {

    public static final MapCodec<RandomThreadPageFunction> CODEC =
            RecordCodecBuilder.mapCodec(
                    p_340803_ ->
                            commonFields(p_340803_)
                                    .and(
                                            RegistryFixedCodec.create(MagitechRegistries.Keys.SPELL)
                                                    .listOf()
                                                    .fieldOf("spells")
                                                    .forGetter(RandomThreadPageFunction::getSpells))
                                    .apply(p_340803_, RandomThreadPageFunction::new));

    protected final List<Holder<Spell>> spells;

    private static @NotNull List<Holder<Spell>> getAllSpells() {
        return MagitechRegistries.SPELL.holders().map(holder -> (Holder<Spell>) holder).toList();
    }

    public RandomThreadPageFunction(List<LootItemCondition> lootItemConditions) {
        this(lootItemConditions, getAllSpells());
    }

    protected RandomThreadPageFunction(
            List<LootItemCondition> conditions, List<Holder<Spell>> spells) {
        super(conditions);
        if (spells.isEmpty()) {
            this.spells = getAllSpells();
        } else {
            this.spells = spells;
        }
    }

    public List<Holder<Spell>> getSpells() {
        return spells;
    }

    @Override
    protected @NotNull ItemStack run(@NotNull ItemStack stack, @NotNull LootContext context) {
        return spells.stream()
                .findAny()
                .map(
                        holder -> {
                            if (holder.isBound()) {
                                ComponentHelper.setThreadPage(stack, holder.value());
                            }
                            return stack;
                        })
                .orElse(stack);
    }

    public static LootItemFunction.Builder builder() {
        return simpleBuilder(RandomThreadPageFunction::new);
    }

    @Override
    public @NotNull LootItemFunctionType<? extends LootItemConditionalFunction> getType() {
        return MagitechLootFunctions.RANDOM_THREAD_PAGE.get(); // 登録済みである必要あり
    }
}
