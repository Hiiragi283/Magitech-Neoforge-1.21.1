package net.stln.magitech.recipe;

import java.util.Optional;

import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.stln.magitech.init.MagitechBlocks;
import net.stln.magitech.init.MagitechDataComponents;
import net.stln.magitech.init.MagitechRecipes;
import net.stln.magitech.item.component.MaterialComponent;
import net.stln.magitech.util.ClientHelper;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record PartCuttingRecipe(String group, int inputCount, ItemStack result)
        implements Recipe<SingleRecipeInput> {

    public static final MapCodec<PartCuttingRecipe> CODEC =
            RecordCodecBuilder.mapCodec(
                    instance ->
                            instance.group(
                                            Codec.STRING
                                                    .optionalFieldOf("group", "")
                                                    .forGetter(PartCuttingRecipe::group),
                                            ExtraCodecs.POSITIVE_INT
                                                    .optionalFieldOf("input_count", 1)
                                                    .forGetter(PartCuttingRecipe::inputCount),
                                            ItemStack.STRICT_CODEC
                                                    .fieldOf("result")
                                                    .forGetter(PartCuttingRecipe::result))
                                    .apply(instance, PartCuttingRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, PartCuttingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,
                    PartCuttingRecipe::group,
                    ByteBufCodecs.VAR_INT,
                    PartCuttingRecipe::inputCount,
                    ItemStack.STREAM_CODEC,
                    PartCuttingRecipe::result,
                    PartCuttingRecipe::new);

    @Override
    public boolean matches(@NotNull SingleRecipeInput input, @NotNull Level level) {
        return input.item().getCount() >= inputCount;
    }

    @Override
    public @NotNull ItemStack assemble(
            @NotNull SingleRecipeInput input, HolderLookup.@NotNull Provider registries) {
        ItemStack itemStack = this.result.copy();
        var level =
                Optional.ofNullable(ServerLifecycleHooks.getCurrentServer())
                        .map(MinecraftServer::overworld)
                        .map(serverLevel -> (Level) serverLevel)
                        .orElseGet(ClientHelper::getLevel);
        if (level != null) {
            level
                    .getRecipeManager()
                    .getRecipesFor(MagitechRecipes.TOOL_MATERIAL_TYPE.get(), input, level)
                    .stream()
                    .findFirst()
                    .map(RecipeHolder::value)
                    .map(ToolMaterialRecipe::getToolMaterial)
                    .ifPresent(
                            toolMaterial ->
                                    itemStack.set(
                                            MagitechDataComponents.MATERIAL_COMPONENT,
                                            new MaterialComponent(toolMaterial)));
        }
        return itemStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registries) {
        return result().copy();
    }

    @Override
    public @NotNull String getGroup() {
        return group;
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return MagitechBlocks.ENGINEERING_WORKBENCH.toStack();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return MagitechRecipes.PART_CUTTING_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return MagitechRecipes.PART_CUTTING_TYPE.get();
    }
}
