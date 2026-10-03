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

public class ZardiusCrucibleRecipe extends LodestoneInWorldRecipe<CrucibleRecipeInput> {
    public static final MapCodec<ZardiusCrucibleRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
            SizedIngredient.NESTED_CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("ingredients").forGetter(r -> r.ingredients),
            SizedFluidIngredient.FLAT_CODEC.fieldOf("fluid_ingredient").forGetter(r -> r.fluidIngredient),
            Codec.LONG.optionalFieldOf("mana", 0L).forGetter(r -> r.mana),
            ItemStack.STRICT_CODEC.optionalFieldOf("result", ItemStack.EMPTY).forGetter(r -> r.output),
            FluidStack.CODEC.optionalFieldOf("fluid_result", FluidStack.EMPTY).forGetter(r -> r.resultFluid)
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
            ItemStack.STREAM_CODEC,
            r -> r.output,
            FluidStack.STREAM_CODEC,
            r -> r.resultFluid,
            ZardiusCrucibleRecipe::new
    );
    
    protected final @NotNull List<SizedIngredient> ingredients;

    protected final @NotNull SizedFluidIngredient fluidIngredient;
    protected final @NotNull FluidStack resultFluid;
    protected final @NotNull ItemStack optionalResult;
    protected final long mana;
    protected final @NotNull String group;

    public ZardiusCrucibleRecipe(@NotNull String group, @NotNull List<SizedIngredient> ingredients, @NotNull SizedFluidIngredient fluidIngredient, long mana, @NotNull ItemStack result, @NotNull FluidStack resultFluid) {
        super(RecipeInit.ZARDIUS_CRUCIBLE_SERIALIZER.get(), RecipeInit.ZARDIUS_CRUCIBLE_TYPE.get(), result);
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
        return resultFluid.copy();
    }

    public long getMana() {
        return mana;
    }
}
