package net.stln.magitech.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.stln.magitech.content.recipe.input.BaseAndIngredientsRecipeInput;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.systems.recipe.LodestoneInWorldRecipe;

import java.util.List;

public class InfusionRecipe extends LodestoneInWorldRecipe<BaseAndIngredientsRecipeInput> {
    public static final MapCodec<InfusionRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            Codec.STRING.optionalFieldOf("group", "").forGetter(p_300947_ -> p_300947_.group),
                            SizedIngredient.NESTED_CODEC.fieldOf("base").forGetter(p_300947_ -> p_300947_.base),
                            SizedIngredient.NESTED_CODEC.listOf().optionalFieldOf("ingredients", List.of()).forGetter(p_300947_ -> p_300947_.ingredients),
                            Codec.LONG.optionalFieldOf("mana", 0L).forGetter(p_300947_ -> p_300947_.mana),
                            ItemStack.STRICT_CODEC.fieldOf("result").forGetter(p_302316_ -> p_302316_.output)
                    )
                    .apply(instance, InfusionRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, InfusionRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            r -> r.group,
            SizedIngredient.STREAM_CODEC,
            r -> r.base,
            SizedIngredient.STREAM_CODEC.apply(ByteBufCodecs.list()),
            r -> r.ingredients,
            ByteBufCodecs.VAR_LONG,
            r -> r.mana,
            ItemStack.STREAM_CODEC,
            r -> r.output,
            InfusionRecipe::new
    );
    
    protected final SizedIngredient base;
    protected final List<SizedIngredient> ingredients;
    protected final long mana;
    protected final String group;

    public InfusionRecipe(String group, SizedIngredient base, List<SizedIngredient> ingredients, long mana, ItemStack result) {
        super(RecipeInit.INFUSION_SERIALIZER.get(), RecipeInit.INFUSION_TYPE.get(), result);
        this.ingredients = ingredients;
        this.mana = mana;
        this.base = base;
        this.group = group;
    }

    @Override
    public boolean matches(BaseAndIngredientsRecipeInput input, @NotNull Level level) {
        if (input.size() != ingredients.size()) return false;
        if (input.base() == null || !base.test(input.base())) return false;
        return input.test(this.base, this.ingredients);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public SizedIngredient getBase() {
        return base;
    }

    public List<SizedIngredient> getSizedIngredients() {
        return ingredients;
    }

    public long getMana() {
        return mana;
    }
}
