package net.stln.magitech.content.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

public class CrushingRecipe extends SingleLodestoneInWorldRecipe {
    public static final MapCodec<CrushingRecipe> CODEC = codec(CrushingRecipe::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, CrushingRecipe> STREAM_CODEC = streamCodec(CrushingRecipe::new);
    
    protected final SizedIngredient ingredient;
    protected final String group;

    public CrushingRecipe(String group, SizedIngredient ingredient, ItemStack result) {
        super(RecipeInit.CRUSHING_SERIALIZER.get(), RecipeInit.CRUSHING_TYPE.get(), group, ingredient, result);
        this.ingredient = ingredient;
        this.group = group;
    }
}
