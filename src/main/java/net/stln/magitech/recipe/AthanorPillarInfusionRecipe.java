package net.stln.magitech.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.stln.magitech.init.MagitechRecipes;
import net.stln.magitech.recipe.input.GroupedMultiStackRecipeInput;
import net.stln.magitech.util.StreamUtil;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record AthanorPillarInfusionRecipe(
        String group,
        Ingredient base,
        List<List<Ingredient>> ingredients,
        ItemStack result,
        int mana)
        implements Recipe<GroupedMultiStackRecipeInput> {
    public static final MapCodec<AthanorPillarInfusionRecipe> CODEC =
            RecordCodecBuilder.mapCodec(
                    instance ->
                            instance.group(
                                            Codec.STRING
                                                    .optionalFieldOf("group", "")
                                                    .forGetter(AthanorPillarInfusionRecipe::group),
                                            Ingredient.CODEC_NONEMPTY
                                                    .fieldOf("base")
                                                    .forGetter(AthanorPillarInfusionRecipe::base),
                                            Ingredient.LIST_CODEC
                                                    .listOf()
                                                    .fieldOf("ingredients")
                                                    .forGetter(
                                                            AthanorPillarInfusionRecipe
                                                                    ::ingredients),
                                            ItemStack.STRICT_CODEC
                                                    .fieldOf("result")
                                                    .forGetter(AthanorPillarInfusionRecipe::result),
                                            Codec.INT
                                                    .optionalFieldOf("mana", 0)
                                                    .forGetter(AthanorPillarInfusionRecipe::mana))
                                    .apply(instance, AthanorPillarInfusionRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, AthanorPillarInfusionRecipe>
            STREAM_CODEC =
                    StreamCodec.composite(
                            ByteBufCodecs.STRING_UTF8,
                            AthanorPillarInfusionRecipe::group,
                            Ingredient.CONTENTS_STREAM_CODEC,
                            AthanorPillarInfusionRecipe::base,
                            Ingredient.CONTENTS_STREAM_CODEC
                                    .apply(ByteBufCodecs.list())
                                    .apply(ByteBufCodecs.list()),
                            AthanorPillarInfusionRecipe::ingredients,
                            ItemStack.STREAM_CODEC,
                            AthanorPillarInfusionRecipe::result,
                            ByteBufCodecs.INT,
                            AthanorPillarInfusionRecipe::mana,
                            AthanorPillarInfusionRecipe::new);

    public @NotNull List<Ingredient> getInnerIngredients(int index) {
        return List.copyOf(ingredients.get(index));
    }

    @Override
    public boolean matches(@NotNull GroupedMultiStackRecipeInput input, @NotNull Level level) {
        if (input.outerSize() != this.ingredients.size()) {
            return false;
        } else {
            for (int i = 0; i < input.outerSize(); i++) {
                List<ItemStack> group = input.stacks().get(i);
                if (i >= this.ingredients.size()
                        || (group.size() != this.ingredients.get(i).size()
                                && !this.ingredients.get(i).isEmpty())) {
                    return false;
                }
                var nonEmptyItems = new ArrayList<ItemStack>(input.ingredientCount());
                for (var item : group) {
                    if (!item.isEmpty()) {
                        nonEmptyItems.add(item);
                    }
                }
                List<Ingredient> nonEmptyIngredients = new ArrayList<>();
                for (var item : this.ingredients.get(i)) {
                    if (!item.isEmpty() && !item.getItems()[0].is(Items.AIR)) {
                        nonEmptyIngredients.add(item);
                    }
                }
                if (RecipeMatcher.findMatches(nonEmptyItems, nonEmptyIngredients) == null
                        && (!nonEmptyItems.isEmpty() || !nonEmptyIngredients.isEmpty())) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public @NotNull ItemStack assemble(
            @NotNull GroupedMultiStackRecipeInput input,
            HolderLookup.@NotNull Provider registries) {
        return getResultItem(registries);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registries) {
        return result.copy();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return ingredients().stream()
                .flatMap(List::stream)
                .collect(StreamUtil.nonNullListCollector());
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return MagitechRecipes.ATHANOR_PILLAR_INFUSION_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return MagitechRecipes.ATHANOR_PILLAR_INFUSION_TYPE.get();
    }
}
