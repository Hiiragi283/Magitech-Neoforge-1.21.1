package net.stln.magitech.item.tool.model;

import java.util.List;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelResolver;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.stln.magitech.Magitech;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.item.tool.material.ToolMaterial;

import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class PartToolItemModelRegister {

    private static final List<String> toolTypes =
            List.of(
                    "dagger",
                    "light_sword",
                    "heavy_sword",
                    "pickaxe",
                    "hammer",
                    "axe",
                    "shovel",
                    "scythe",
                    "spear",
                    "wand",
                    "staff");
    private static final List<String> partTypes =
            List.of(
                    "light_blade",
                    "heavy_blade",
                    "light_handle",
                    "heavy_handle",
                    "tool_binding",
                    "handguard",
                    "strike_head",
                    "spike_head",
                    "reinforced_stick",
                    "plate",
                    "catalyst",
                    "conductor");
    public static ModelResolver ToolPartItemModelResolver =
            context -> {
                if (context.id().getNamespace().equals(Magitech.MOD_ID)) {
                    if (context.id().getPath().contains("heavy_sword")
                            || context.id().getPath().contains("hammer")
                            || context.id().getPath().contains("scythe")
                            || context.id().getPath().contains("spear")
                            || context.id().getPath().contains("staff")) {
                        return getHeavyToolUnbakedModel(context.id());
                    }
                    return getUnbakedModel(context.id());
                }
                return null;
            };

    public static void register() {
        ModelLoadingPlugin.register(
                pluginContext -> {
                    MagitechRegistries.TOOL_MATERIAL.stream()
                            .forEach(
                                    toolMaterial -> {
                                        for (String toolType : toolTypes) {
                                            for (String partType : partTypes) {
                                                pluginContext.addModels(
                                                        getPartModelId(
                                                                toolMaterial, toolType, partType));
                                            }
                                        }
                                    });
                    Event<ModelResolver> resolverEvent = pluginContext.resolveModel();

                    resolverEvent.register(ToolPartItemModelResolver);
                });
    }

    private static @NotNull net.minecraft.client.resources.model.UnbakedModel
            getHeavyToolUnbakedModel(ResourceLocation identifier) {
        return BlockModel.fromString(
                "{\n"
                        + "    \"parent\": \"magitech:item/heavy_tool\",\n"
                        + "    \"textures\": {\n"
                        + "        \"layer0\": \""
                        + identifier.toString()
                        + "\"\n"
                        + "    }\n"
                        + "}");
    }

    private static @NotNull net.minecraft.client.resources.model.UnbakedModel getUnbakedModel(
            ResourceLocation identifier) {
        return BlockModel.fromString(
                "{\n"
                        + "    \"parent\": \"item/handheld\",\n"
                        + "    \"textures\": {\n"
                        + "        \"layer0\": \""
                        + identifier.toString()
                        + "\"\n"
                        + "    }\n"
                        + "}");
    }

    private static ResourceLocation getPartModelId(
            ToolMaterial toolMaterial, String toolType, String part) {
        return ResourceLocation.fromNamespaceAndPath(
                toolMaterial.getId().getNamespace(),
                "item/tool/" + toolType + "/" + toolMaterial.getId().getPath() + "_" + part);
    }
}
