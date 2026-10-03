package net.stln.magitech.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.systems.recipe.LodestoneInWorldRecipe;

public class CompressingRecipe extends LodestoneInWorldRecipe<SingleRecipeInput> {
    public static final MapCodec<CompressingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(p_300947_ -> p_300947_.group),
                    SizedIngredient.NESTED_CODEC.fieldOf("ingredient").forGetter(p_300947_ -> p_300947_.ingredient),
                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(p_302316_ -> p_302316_.output)
            ).apply(instance, CompressingRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, CompressingRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            r -> r.group,
            SizedIngredient.STREAM_CODEC,
            r -> r.ingredient,
            ItemStack.STREAM_CODEC,
            r -> r.output,
            CompressingRecipe::new
    );

    protected final SizedIngredient ingredient;
    protected final String group;

    public CompressingRecipe(String group, SizedIngredient ingredient, ItemStack result) {
        super(RecipeInit.COMPRESSING_SERIALIZER.get(), RecipeInit.COMPRESSING_TYPE.get(), result);
        this.ingredient = ingredient;
        this.group = group;
    }

    @Override
    public boolean matches(SingleRecipeInput input, @NotNull Level level) {
        return ingredient.test(input.item());
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public SizedIngredient getSizedIngredient() {
        return ingredient;
    }
}
