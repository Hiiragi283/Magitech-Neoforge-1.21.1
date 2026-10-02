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

public class CrushingRecipe extends LodestoneInWorldRecipe<SingleRecipeInput> {
    public static final MapCodec<CrushingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            p_340781_ -> p_340781_.group(
                            Codec.STRING.optionalFieldOf("group", "").forGetter(p_300947_ -> p_300947_.group),
                            SizedIngredient.NESTED_CODEC.fieldOf("ingredient").forGetter(p_300947_ -> p_300947_.ingredient),
                            ItemStack.STRICT_CODEC.fieldOf("result").forGetter(p_302316_ -> p_302316_.output)
                    )
                    .apply(p_340781_, CrushingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, CrushingRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            r -> r.group,
            SizedIngredient.STREAM_CODEC,
            r -> r.ingredient,
            ItemStack.STREAM_CODEC,
            r -> r.output,
            CrushingRecipe::new
    );
    
    protected final SizedIngredient ingredient;
    protected final String group;

    public CrushingRecipe(String group, SizedIngredient ingredient, ItemStack result) {
        super(RecipeInit.CRUSHING_SERIALIZER.get(), RecipeInit.CRUSHING_TYPE.get(), result);
        this.ingredient = ingredient;
        this.group = group;
    }

    @Override
    public boolean matches(SingleRecipeInput input, @NotNull Level level) {
        return ingredient.test(input.item());
    }

    public SizedIngredient getSizedIngredient() {
        return ingredient;
    }
}
