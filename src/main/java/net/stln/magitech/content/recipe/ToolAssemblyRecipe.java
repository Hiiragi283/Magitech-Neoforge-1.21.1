package net.stln.magitech.content.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.stln.magitech.content.item.component.ComponentInit;
import net.stln.magitech.content.item.component.PartMaterialComponent;
import net.stln.magitech.content.item.tool.partitem.PartItem;
import net.stln.magitech.content.item.tool.toolitem.SynthesisedToolItem;
import net.stln.magitech.content.recipe.input.MultiStackRecipeInput;
import net.stln.magitech.feature.tool.material.ToolMaterial;
import net.stln.magitech.feature.tool.part.ToolPart;
import net.stln.magitech.feature.tool.tool_type.ToolType;
import net.stln.magitech.helper.ComponentHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record ToolAssemblyRecipe(String group, List<Ingredient> ingredients,
                                 ItemStack result) implements Recipe<MultiStackRecipeInput> {
    public static final MapCodec<ToolAssemblyRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(ToolAssemblyRecipe::group),
                    Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").forGetter(ToolAssemblyRecipe::ingredients),
                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(ToolAssemblyRecipe::result)
            ).apply(instance, ToolAssemblyRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, ToolAssemblyRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            ToolAssemblyRecipe::group,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            ToolAssemblyRecipe::ingredients,
            ItemStack.STREAM_CODEC,
            ToolAssemblyRecipe::result,
            ToolAssemblyRecipe::new
    );

    @Override
    public boolean matches(@NotNull MultiStackRecipeInput input, @NotNull Level level) {
        if (!(result.getItem() instanceof SynthesisedToolItem)) {
            throw new IllegalArgumentException("the result item expected child be a PartToolItem");
        }
        ToolType type = ((SynthesisedToolItem) result.getItem()).getToolType();
        if (isCorrectTypesForTool(input, type).stream().allMatch(Objects::isNull)) {
            return false;
        }
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        } else if (!ingredients.stream().allMatch(Ingredient::isSimple)) {
            var nonEmptyItems = new ArrayList<ItemStack>(input.ingredientCount());
            for (var item : input.stacks())
                if (!item.isEmpty())
                    nonEmptyItems.add(item);
            return RecipeMatcher.findMatches(nonEmptyItems, this.ingredients) != null;
        } else {
            return input.size() == 1 && this.ingredients.size() == 1
                    ? this.ingredients.getFirst().test(input.getItem(0))
                    : input.stackedContents().canCraft(this, null);
        }
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull MultiStackRecipeInput input, HolderLookup.@NotNull Provider registries) {
        if (!(result.getItem() instanceof SynthesisedToolItem)) {
            throw new IllegalArgumentException("the result item expected child be a PartToolItem");
        }
        ToolType type = ((SynthesisedToolItem) result.getItem()).getToolType();
        List<ToolMaterial> toolMaterials = isCorrectTypesForTool(input, type);
        if (!toolMaterials.isEmpty()) {
            ItemStack stack = result.copy();
            stack.set(ComponentInit.PART_MATERIAL_COMPONENT, new PartMaterialComponent(toolMaterials));
            return stack;
        }
        return ItemStack.EMPTY;
    }

    private List<ToolMaterial> isCorrectTypesForTool(MultiStackRecipeInput input, ToolType type) {
        int size = type.parts().size();
        if (input.size() == size) {
            boolean flag = true;
            List<ToolMaterial> result = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                result.add(null);
            }
            List<ToolPart> partList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                partList.add(type.parts().get(i).part().asToolPart());
            }
            for (int i = 0; i < input.size(); i++) {
                if (input.getItem(i).getItem() instanceof PartItem partItem && input.getItem(i).has(ComponentInit.MATERIAL_COMPONENT.get())) {
                    boolean found = partList.contains(partItem.getPart());
                    int index = partList.indexOf(partItem.getPart());
                    if (found) {
                        ComponentHelper.getMaterial(input.getItem(i)).ifPresent(material -> {
                            result.set(index, material);
                            partList.set(index, null);
                        });
                    }
                    flag &= found;
                }
            }
            if (flag && partList.stream().allMatch(Objects::isNull)) {
                return result;
            }
        }
        return new ArrayList<>();
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
        return NonNullList.copyOf(ingredients);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return RecipeInit.TOOL_ASSEMBLY_TYPE.get();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return RecipeInit.TOOL_ASSEMBLY_SERIALIZER.get();
    }
}
