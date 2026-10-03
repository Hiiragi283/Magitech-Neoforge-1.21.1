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
import org.jetbrains.annotations.UnmodifiableView;
import team.lodestar.lodestone.systems.recipe.LodestoneInWorldRecipe;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
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
            ByteBufCodecs.optional(ItemStack.STREAM_CODEC),
            r -> r.optionalResult,
            ByteBufCodecs.optional(FluidStack.STREAM_CODEC),
            r -> r.resultFluid,
            ZardiusCrucibleRecipe::new
    );
    
    protected final @NotNull List<SizedIngredient> ingredients;

    protected final @NotNull SizedFluidIngredient fluidIngredient;
    protected final @NotNull Optional<FluidStack> resultFluid;
    protected final @NotNull Optional<ItemStack> optionalResult;
    protected final long mana;
    protected final @NotNull String group;

    public ZardiusCrucibleRecipe(@NotNull String group, @NotNull List<SizedIngredient> ingredients, @NotNull SizedFluidIngredient fluidIngredient, long mana, @NotNull Optional<ItemStack> result, @NotNull Optional<FluidStack> resultFluid) {
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

    @Override
    public boolean isSpecial() {
        return true;
    }

    public @UnmodifiableView @NotNull List<SizedIngredient> getSizedIngredients() {
        return List.copyOf(ingredients);
    }

    public @NotNull SizedFluidIngredient getFluidIngredient() {
        return fluidIngredient;
    }

    public @NotNull FluidStack getResultFluid() {
        return resultFluid.orElse(FluidStack.EMPTY);
    }

    public long getMana() {
        return mana;
    }
}
