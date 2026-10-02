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
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.stln.magitech.content.recipe.input.CrucibleRecipeInput;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.systems.recipe.LodestoneInWorldRecipe;

import java.util.List;
import java.util.Optional;

public class ZardiusCrucibleRecipe extends LodestoneInWorldRecipe<CrucibleRecipeInput> {
    public static final MapCodec<ZardiusCrucibleRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
            SizedIngredient.NESTED_CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("ingredients").forGetter(r -> r.ingredients),
            SizedFluidIngredient.FLAT_CODEC.fieldOf("fluid_ingredient").forGetter(r -> r.fluidIngredient),
            Codec.LONG.optionalFieldOf("mana", 0L).forGetter(r -> r.mana),
            ItemStack.STRICT_CODEC.optionalFieldOf("result").forGetter(r -> r.optionalResult),
            FluidStack.CODEC.optionalFieldOf("fluid_result").forGetter(r -> r.resultFluid)
    ).apply(instance, ZardiusCrucibleRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ZardiusCrucibleRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            r -> r.group,
            SizedIngredient.STREAM_CODEC.apply(ByteBufCodecs.list()),
            r -> r.ingredients,
            SizedFluidIngredient.STREAM_CODEC,
            r -> r.fluidIngredient,
            ByteBufCodecs.VAR_LONG,
            r -> r.mana,
            ItemStack.STREAM_CODEC.apply(ByteBufCodecs::optional),
            r -> r.optionalResult,
            FluidStack.STREAM_CODEC.apply(ByteBufCodecs::optional),
            r -> r.resultFluid,
            ZardiusCrucibleRecipe::new
    );
    
    protected final List<SizedIngredient> ingredients;

    protected final SizedFluidIngredient fluidIngredient;
    protected final Optional<FluidStack> resultFluid;
    protected final Optional<ItemStack> optionalResult;
    protected final long mana;
    protected final String group;

    public ZardiusCrucibleRecipe(String group, List<SizedIngredient> ingredients, SizedFluidIngredient fluidIngredient, long mana, Optional<ItemStack> result, Optional<FluidStack> resultFluid) {
        super(RecipeInit.ZARDIUS_CRUCIBLE_SERIALIZER.get(), RecipeInit.ZARDIUS_CRUCIBLE_TYPE.get(), result.orElse(ItemStack.EMPTY));
        this.ingredients = ingredients;
        this.mana = mana;
        this.fluidIngredient = fluidIngredient;
        this.resultFluid = resultFluid;
        this.optionalResult = result;
        this.group = group;
    }

    @Override
    public boolean matches(@NotNull CrucibleRecipeInput input, @NotNull Level level) {
        return input.test(ingredients, fluidIngredient);
    }

    public List<SizedIngredient> getSizedIngredients() {
        return ingredients;
    }

    public SizedFluidIngredient getFluidIngredient() {
        return fluidIngredient;
    }

    public FluidStack getResultFluid() {
        return resultFluid.orElse(FluidStack.EMPTY);
    }

    public long getMana() {
        return mana;
    }
}
