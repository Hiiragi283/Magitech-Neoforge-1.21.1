package net.stln.magitech.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.systems.recipe.LodestoneInWorldRecipe;

public class SingleLodestoneInWorldRecipe extends LodestoneInWorldRecipe<SingleRecipeInput> {
    public static <T extends SingleLodestoneInWorldRecipe> MapCodec<T> codec(Factory<T> factory) {
        return RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(SingleLodestoneInWorldRecipe::getGroup),
                SizedIngredient.NESTED_CODEC.fieldOf("ingredient").forGetter(SingleLodestoneInWorldRecipe::getIngredient),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(SingleLodestoneInWorldRecipe::getResult)
        ).apply(instance, factory::create));
    }

    public static <T extends SingleLodestoneInWorldRecipe> StreamCodec<RegistryFriendlyByteBuf, T> streamCodec(Factory<T> factory) {
        return StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8,
                SingleLodestoneInWorldRecipe::getGroup,
                SizedIngredient.STREAM_CODEC,
                SingleLodestoneInWorldRecipe::getIngredient,
                ItemStack.STREAM_CODEC,
                SingleLodestoneInWorldRecipe::getResult,
                factory::create 
        );
    }

    protected final String group;
    protected final SizedIngredient ingredient;

    public SingleLodestoneInWorldRecipe(RecipeSerializer<?> recipeSerializer, RecipeType<?> recipeType, String group, SizedIngredient ingredient, ItemStack output) {
        super(recipeSerializer, recipeType, output);
        this.group = group;
        this.ingredient = ingredient;
    }

    @Override
    final public boolean matches(@NotNull SingleRecipeInput input, @NotNull Level level) {
        return ingredient.test(input.item());
    }

    @Override
    final public boolean isSpecial() {
        return true;
    }

    @Override
    public @NotNull String getGroup() {
        return group;
    }

    public @NotNull SizedIngredient getIngredient() {
        return ingredient;
    }

    public @NotNull ItemStack getResult() {
        return output.copy();
    }

    @FunctionalInterface
    public interface Factory<T extends SingleLodestoneInWorldRecipe> {
        @NotNull T create(@NotNull String group, @NotNull SizedIngredient ingredient, @NotNull ItemStack result);
    }
}
