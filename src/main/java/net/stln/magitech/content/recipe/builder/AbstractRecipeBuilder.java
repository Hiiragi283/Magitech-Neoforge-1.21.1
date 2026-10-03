package net.stln.magitech.content.recipe.builder;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.stln.magitech.Magitech;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Magitechで使用される[Recipe]のビルダークラスです。
 *
 * @param <RECIPE> 生成するレシピのクラス
 * @author Hiiragi Tsubasa
 */
public abstract class AbstractRecipeBuilder<RECIPE extends Recipe<?>> {
    private final String prefix;

    public AbstractRecipeBuilder(String prefix) {
        this.prefix = prefix;
    }

    /**
     * デフォルトのIDを取得します。
     */
    protected abstract @Nullable ResourceLocation getRecipeId();

    /**
     * レシピを生成します。
     */
    protected abstract @NotNull RECIPE createRecipe();

    //    Conditions    //

    private final List<ICondition> conditions = new ArrayList<>();

    public @NotNull AbstractRecipeBuilder<RECIPE> condition(@NotNull ICondition... conditions) {
        this.conditions.addAll(Arrays.asList(conditions));
        return this;
    }

    //    Save    //

    final public void save(@NotNull RecipeOutput output) {
        save(output, getRecipeIdOrThrow());
    }

    final public void savePrefixed(@NotNull RecipeOutput output, @NotNull String prefix) {
        save(output, getRecipeIdOrThrow().withPrefix(prefix));
    }

    final public void saveSuffixed(@NotNull RecipeOutput output, @NotNull String suffix) {
        save(output, getRecipeIdOrThrow().withSuffix(suffix));
    }

    private @NotNull ResourceLocation getRecipeIdOrThrow() {
        return Magitech.id(Objects.requireNonNull(getRecipeId(), "Could not generate default recipe id").getPath());
    }

    private void save(@NotNull RecipeOutput output, @NotNull ResourceLocation recipeId) {
        output.accept(recipeId.withPrefix("%s/".formatted(prefix)), createRecipe(), null, conditions.toArray(ICondition[]::new));
    }
}
