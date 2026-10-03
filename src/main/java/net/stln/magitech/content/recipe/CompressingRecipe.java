package net.stln.magitech.content.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

public class CompressingRecipe extends SingleLodestoneInWorldRecipe {
    public static final MapCodec<CompressingRecipe> CODEC = codec(CompressingRecipe::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, CompressingRecipe> STREAM_CODEC = streamCodec(CompressingRecipe::new);

    protected final SizedIngredient ingredient;
    protected final String group;

    public CompressingRecipe(String group, SizedIngredient ingredient, ItemStack result) {
        super(RecipeInit.COMPRESSING_SERIALIZER.get(), RecipeInit.COMPRESSING_TYPE.get(), group, ingredient, result);
        this.ingredient = ingredient;
        this.group = group;
    }
}
