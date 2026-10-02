package net.stln.magitech.compat.jei;

import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.stln.magitech.Magitech;
import net.stln.magitech.compat.jei.category.FieldEffectCompositionJeiRecipe;
import net.stln.magitech.compat.jei.category.FieldInfluenceSourceJeiRecipe;
import net.stln.magitech.content.recipe.*;

public final class JeiRecipeTypeInit {
    private JeiRecipeTypeInit() {
    }

    public static final RecipeType<RecipeHolder<PartCuttingRecipe>> PART_CUTTING_TYPE = RecipeType.createFromVanilla(RecipeInit.PART_CUTTING_TYPE.get());
    public static final RecipeType<RecipeHolder<SpellConversionRecipe>> SPELL_CONVERSION_TYPE = RecipeType.createFromVanilla(RecipeInit.SPELL_CONVERSION_TYPE.get());
    public static final RecipeType<RecipeHolder<ToolAssemblyRecipe>> TOOL_ASSEMBLY_TYPE = RecipeType.createFromVanilla(RecipeInit.TOOL_ASSEMBLY_TYPE.get());
    public static final RecipeType<RecipeHolder<ToolMaterialRecipe>> TOOL_MATERIAL_TYPE = RecipeType.createFromVanilla(RecipeInit.TOOL_MATERIAL_TYPE.get());
    public static final RecipeType<RecipeHolder<ZardiusCrucibleRecipe>> ZARDIUS_CRUCIBLE_TYPE = RecipeType.createFromVanilla(RecipeInit.ZARDIUS_CRUCIBLE_TYPE.get());
    public static final RecipeType<RecipeHolder<InfusionRecipe>> INFUSION_TYPE = RecipeType.createFromVanilla(RecipeInit.INFUSION_TYPE.get());
    public static final RecipeType<RecipeHolder<CrushingRecipe>> CRUSHING_TYPE = RecipeType.createFromVanilla(RecipeInit.CRUSHING_TYPE.get());
    public static final RecipeType<RecipeHolder<CompressingRecipe>> COMPRESSING_TYPE = RecipeType.createFromVanilla(RecipeInit.COMPRESSING_TYPE.get());
    public static final RecipeType<RecipeHolder<FieldEffectRecipe>> FIELD_EFFECT_TYPE = RecipeType.createFromVanilla(RecipeInit.FIELD_EFFECT_TYPE.get());
    public static final RecipeType<FieldEffectCompositionJeiRecipe> FIELD_EFFECT_COMPOSITION_TYPE = RecipeType.create(Magitech.MOD_ID, "field_effect_composition", FieldEffectCompositionJeiRecipe.class);
    public static final RecipeType<FieldInfluenceSourceJeiRecipe> FIELD_INFLUENCE_SOURCE_TYPE = RecipeType.create(Magitech.MOD_ID, "field_influence_source", FieldInfluenceSourceJeiRecipe.class);
}
