package net.stln.magitech.datagen.client;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import net.stln.magitech.Magitech;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.init.MagitechBlocks;
import net.stln.magitech.init.MagitechItems;
import net.stln.magitech.item.tool.model.ModelRegistrar;

import org.jetbrains.annotations.NotNull;

public class MagitechItemModelProvider extends ItemModelProvider {

    public MagitechItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Magitech.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(MagitechItems.GLISTENING_LEXICON);
        basicItem(MagitechItems.THE_FIRE_THAT_THINKS);
        basicItem(MagitechItems.ARCANE_ENGINEERING_COMPENDIUM);
        basicItem(MagitechItems.MANA_RING);
        basicItem(MagitechItems.GALEVENT_RING);
        basicItem(MagitechItems.CHARGEBIND_RING);
        basicItem(MagitechItems.TORSION_RING);
        basicItem(MagitechItems.UMBRAL_RING);
        basicItem(MagitechItems.DAWN_RING);
        basicItem(MagitechItems.FLUXBOUND_RING);
        basicItem(MagitechItems.LIGHT_BLADE);
        basicItem(MagitechItems.HEAVY_BLADE);
        basicItem(MagitechItems.LIGHT_HANDLE);
        basicItem(MagitechItems.HEAVY_HANDLE);
        basicItem(MagitechItems.TOOL_BINDING);
        basicItem(MagitechItems.HANDGUARD);
        basicItem(MagitechItems.STRIKE_HEAD);
        basicItem(MagitechItems.SPIKE_HEAD);
        basicItem(MagitechItems.REINFORCED_STICK);
        basicItem(MagitechItems.PLATE);
        basicItem(MagitechItems.CATALYST);
        basicItem(MagitechItems.CONDUCTOR);
        handheldItem(MagitechItems.DAGGER);
        handheldItem(MagitechItems.LIGHT_SWORD);
        handheldItem(MagitechItems.HEAVY_SWORD);
        handheldItem(MagitechItems.PICKAXE);
        handheldItem(MagitechItems.HAMMER);
        handheldItem(MagitechItems.AXE);
        handheldItem(MagitechItems.SHOVEL);
        handheldItem(MagitechItems.SCYTHE);
        handheldItem(MagitechItems.WAND);
        basicItem(MagitechItems.AETHER_LIFTER);
        basicItem(MagitechItems.FLAMGLIDE_STRIDER);
        basicItem(MagitechItems.ALCHAEFABRIC);
        basicItem(MagitechItems.AEGIS_WEAVE);
        basicItem(MagitechItems.FLUORITE);
        basicItem(MagitechItems.MANA_CHARGED_FLUORITE);
        basicItem(MagitechItems.TOURMALINE);
        basicItem(MagitechItems.EMBER_CRYSTAL);
        basicItem(MagitechItems.GLACE_CRYSTAL);
        basicItem(MagitechItems.SURGE_CRYSTAL);
        basicItem(MagitechItems.PHANTOM_CRYSTAL);
        basicItem(MagitechItems.TREMOR_CRYSTAL);
        basicItem(MagitechItems.MAGIC_CRYSTAL);
        basicItem(MagitechItems.FLOW_CRYSTAL);
        basicItem(MagitechItems.HOLLOW_CRYSTAL);
        basicItem(MagitechItems.AGGREGATED_NOCTIS);
        basicItem(MagitechItems.AGGREGATED_LUMINIS);
        basicItem(MagitechItems.AGGREGATED_FLUXIA);
        basicItem(MagitechItems.CITRINE);
        basicItem(MagitechItems.REDSTONE_CRYSTAL);
        basicItem(MagitechItems.POLISHED_REDSTONE_CRYSTAL);
        basicItem(MagitechItems.SULFUR);
        basicItem(MagitechItems.CHROMIUM_INGOT);
        basicItem(MagitechItems.ENDER_METAL_INGOT);
        basicItem(MagitechItems.NETHER_STAR_BRILLIANCE);
        basicItem(MagitechItems.RADIANT_STEEL_INGOT);
        basicItem(MagitechItems.FRIGIDITE);
        basicItem(MagitechItems.POLISHED_FRIGIDITE);
        basicItem(MagitechItems.TRANSLUCIUM);
        basicItem(MagitechItems.POLISHED_TRANSLUCIUM);
        basicItem(MagitechItems.RESONITE);
        basicItem(MagitechItems.POLISHED_RESONITE);
        basicItem(MagitechItems.ABYSSITE);
        basicItem(MagitechItems.POLISHED_ABYSSITE);
        basicItem(MagitechItems.MANA_DEEXCITER_CORE);
        basicItem(MagitechItems.ASPECT_COLLECTOR);
        basicItem(MagitechItems.BOOTS_FRAME);
        basicItem(MagitechItems.MANA_BERRIES);
        basicItem(MagitechItems.MANA_PIE);

        basicItem(MagitechBlocks.CELIFERN_DOOR_ITEM);
        getBuilder(MagitechBlocks.CELIFERN_SAPLING_ITEM.get().toString())
                .parent(GENERATED)
                .texture(
                        "layer0",
                        Magitech.id(
                                "block/" + MagitechBlocks.CELIFERN_SAPLING_ITEM.getId().getPath()));
        basicItem(MagitechBlocks.CELIFERN_SIGN_ITEM);
        basicItem(MagitechBlocks.CELIFERN_HANGING_SIGN_ITEM);
        basicItem(MagitechBlocks.CHARCOAL_BIRCH_DOOR_ITEM);
        getBuilder(MagitechBlocks.CHARCOAL_BIRCH_SAPLING_ITEM.get().toString())
                .parent(GENERATED)
                .texture(
                        "layer0",
                        Magitech.id(
                                "block/"
                                        + MagitechBlocks.CHARCOAL_BIRCH_SAPLING_ITEM
                                                .getId()
                                                .getPath()));
        basicItem(MagitechBlocks.CHARCOAL_BIRCH_SIGN_ITEM);
        basicItem(MagitechBlocks.CHARCOAL_BIRCH_HANGING_SIGN_ITEM);
        basicItem(MagitechBlocks.MISTALIA_PETALS_ITEM);

        MagitechRegistries.TOOL_MATERIAL.stream()
                .forEach(
                        toolMaterial -> {
                            for (String type : ModelRegistrar.toolTypes) {
                                for (String part : ModelRegistrar.partTypes) {
                                    if (existingFileHelper.exists(
                                            ModelRegistrar.getPartTextureId(
                                                    toolMaterial, type, part),
                                            ModelProvider.TEXTURE)) {
                                        String parent = getString(type);
                                        getBuilder(
                                                        ModelRegistrar.getPartModelName(
                                                                toolMaterial, type, part))
                                                .parent(new ModelFile.UncheckedModelFile(parent))
                                                .texture(
                                                        "layer0",
                                                        ModelRegistrar.getPartTextureId(
                                                                toolMaterial, type, part));
                                    }
                                }
                            }
                        });
        MagitechRegistries.TOOL_MATERIAL.stream()
                .forEach(
                        toolMaterial -> {
                            for (String part : ModelRegistrar.partTypes) {
                                if (existingFileHelper.exists(
                                        ModelRegistrar.getPartItemTextureId(toolMaterial, part),
                                        ModelProvider.TEXTURE)) {
                                    String parent = "item/generated";
                                    getBuilder(
                                                    ModelRegistrar.getPartItemModelName(
                                                            toolMaterial, part))
                                            .parent(new ModelFile.UncheckedModelFile(parent))
                                            .texture(
                                                    "layer0",
                                                    ModelRegistrar.getPartItemTextureId(
                                                            toolMaterial, part));
                                }
                            }
                        });
    }

    private static @NotNull String getString(String type) {
        String parent = "item/handheld";
        if (type.equals("heavy_sword")
                || type.equals("hammer")
                || type.equals("scythe")
                || type.equals("spear")
                || type.equals("staff")) {
            parent = "magitech:item/heavy_tool";
        }
        return parent;
    }

    private static final ModelFile GENERATED = new ModelFile.UncheckedModelFile("item/generated");

    private void basicItem(DeferredItem<?> item) {
        basicItem(item.getId());
    }

    private void handheldItem(DeferredItem<?> item) {
        handheldItem(item.getId());
    }
}
