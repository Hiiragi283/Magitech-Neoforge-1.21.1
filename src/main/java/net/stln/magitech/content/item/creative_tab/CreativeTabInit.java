package net.stln.magitech.content.item.creative_tab;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.holdersets.AnyHolderSet;
import net.stln.magitech.Magitech;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.content.block.BlockInit;
import net.stln.magitech.content.item.ItemInit;
import net.stln.magitech.content.item.ThreadboundGenerator;
import net.stln.magitech.content.item.tool.toolitem.SynthesisedToolGenerator;
import net.stln.magitech.feature.magic.spell.ISpell;
import net.stln.magitech.feature.magic.spell.SpellInit;
import net.stln.magitech.feature.tool.material.MaterialInit;
import net.stln.magitech.feature.tool.material.ToolMaterialLike;
import net.stln.magitech.feature.tool.part.ToolPartInit;
import net.stln.magitech.feature.tool.part.ToolPartLike;
import net.stln.magitech.feature.tool.tool_type.ToolTypeInit;
import net.stln.magitech.feature.tool.tool_type.ToolTypeLike;
import net.stln.magitech.registry.RegistryHelper;

public class CreativeTabInit {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Magitech.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAGITECH_TAB = CREATIVE_MODE_TABS.register("magitech_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("tab.magitech.magitech"))
            .icon(ItemInit.GLISTENING_LEXICON::toStack)
            .displayItems((parameters, output) -> {
                HolderSet<ISpell> allSpells = new AnyHolderSet<>(parameters.holders().lookupOrThrow(MagitechRegistries.Keys.SPELL));

                output.accept(ThreadboundGenerator.generateThreadbound(ItemInit.GLISTENING_LEXICON.get(), allSpells));
                output.accept(ThreadboundGenerator.generateThreadbound(ItemInit.MATERIALS_AND_TOOLCRAFT_DESIGN.get(), allSpells));
                output.accept(ThreadboundGenerator.generateThreadbound(ItemInit.THE_FIRE_THAT_THINKS.get(), allSpells));
                output.accept(ThreadboundGenerator.generateThreadbound(ItemInit.APPLIED_ARCANE_CIRCUITRY.get(), allSpells));
                output.accept(ThreadboundGenerator.generateThreadbound(ItemInit.ARCANE_ENGINEERING_COMPENDIUM.get(), allSpells));
                output.accept(ItemInit.AETHER_LIFTER);
                output.accept(ItemInit.FLAMGLIDE_STRIDER);
                output.accept(ItemInit.SPECTACLES_OF_INSPECTION);
                output.accept(ItemInit.FLUXIUM_RING);
                output.accept(ItemInit.MANA_RING);
                output.accept(ItemInit.ARDOR_RING);
                output.accept(ItemInit.QUENCH_RING);
                output.accept(ItemInit.CHARGEBIND_RING);
                output.accept(ItemInit.CELERITAS_RING);
                output.accept(ItemInit.CRACK_RING);
                output.accept(ItemInit.PROTECTION_RING);
                output.accept(ItemInit.UPDRAFT_RING);
                output.accept(ItemInit.DISTORTION_RING);
                output.accept(ItemInit.UMBRAL_RING);
                output.accept(ItemInit.DAWN_RING);
                output.accept(ItemInit.FLUXBOUND_RING);
                output.accept(ItemInit.TOOL_BELT);
                output.accept(ItemInit.ALCHAEFABRIC);
                output.accept(ItemInit.AEGIS_WEAVE);
                output.accept(ItemInit.FLUORITE);
                output.accept(ItemInit.MANA_CHARGED_FLUORITE);
                output.accept(ItemInit.HIGH_PURITY_FLUORITE);
                output.accept(ItemInit.TOURMALINE);
                output.accept(ItemInit.EMBER_CRYSTAL);
                output.accept(ItemInit.GLACE_CRYSTAL);
                output.accept(ItemInit.SURGE_CRYSTAL);
                output.accept(ItemInit.PHANTOM_CRYSTAL);
                output.accept(ItemInit.TREMOR_CRYSTAL);
                output.accept(ItemInit.MAGIC_CRYSTAL);
                output.accept(ItemInit.FLOW_CRYSTAL);
                output.accept(ItemInit.HOLLOW_CRYSTAL);
                output.accept(ItemInit.AGGREGATED_NOCTIS);
                output.accept(ItemInit.AGGREGATED_LUMINIS);
                output.accept(ItemInit.AGGREGATED_FLUXIA);
                output.accept(ItemInit.CITRINE);
                output.accept(ItemInit.RAW_ZINC);
                output.accept(ItemInit.ZINC_INGOT);
                output.accept(ItemInit.FLUXIUM_INGOT);
                output.accept(ItemInit.FLUXIUM_NUGGET);
                output.accept(ItemInit.REDSTONE_CRYSTAL);
                output.accept(ItemInit.SULFUR);
                output.accept(ItemInit.ENDER_METAL_INGOT);
                output.accept(ItemInit.NETHER_STAR_BRILLIANCE);
                output.accept(ItemInit.RADIANT_STEEL_INGOT);
                output.accept(ItemInit.SULFURIC_ACID_BATTERY);
                output.accept(ItemInit.MANA_CELL);
                output.accept(ItemInit.QUARTZ_PLANT);
                output.accept(ItemInit.RESTRAINT_QUARTZ_DUST);
                output.accept(ItemInit.RESTRAINT_QUARTZ);
                output.accept(ItemInit.MANA_BERRIES);
                output.accept(ItemInit.MANA_PIE);
                output.accept(ItemInit.ALCHEMICAL_FLASK);
                output.accept(ItemInit.WATER_FLASK);
                output.accept(ItemInit.LAVA_FLASK);
                output.accept(ItemInit.SULFURIC_ACID_FLASK);
                output.accept(ItemInit.MANA_POTION_FLASK);
                output.accept(ItemInit.HEALING_POTION_FLASK);
                output.accept(ItemInit.EMBER_POTION_FLASK);
                output.accept(ItemInit.GLACE_POTION_FLASK);
                output.accept(ItemInit.SURGE_POTION_FLASK);
                output.accept(ItemInit.PHANTOM_POTION_FLASK);
                output.accept(ItemInit.TREMOR_POTION_FLASK);
                output.accept(ItemInit.MAGIC_POTION_FLASK);
                output.accept(ItemInit.FLOW_POTION_FLASK);
                output.accept(ItemInit.HOLLOW_POTION_FLASK);
                output.accept(ItemInit.CELIFERN_BOAT);
                output.accept(ItemInit.CELIFERN_CHEST_BOAT);
                output.accept(ItemInit.CHARCOAL_BIRCH_BOAT);
                output.accept(ItemInit.CHARCOAL_BIRCH_CHEST_BOAT);
                output.accept(ItemInit.MYSTWOOD_BOAT);
                output.accept(ItemInit.MYSTWOOD_CHEST_BOAT);
                output.accept(ItemInit.WEAVER_SPAWN_EGG);
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAGITECH_BLOCK_TAB = CREATIVE_MODE_TABS.register("magitech_block_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("tab.magitech.magitech_block"))
            .icon(BlockInit.ALCHECRYSITE_ITEM::toStack)
            .withTabsBefore(MAGITECH_TAB.getKey())
            .displayItems((parameters, output) -> {
                output.accept(BlockInit.ENGINEERING_WORKBENCH_ITEM);
                output.accept(BlockInit.ASSEMBLY_WORKBENCH_ITEM);
                output.accept(BlockInit.REPAIRING_WORKBENCH_ITEM);
                output.accept(BlockInit.UPGRADE_WORKBENCH_ITEM);
                output.accept(BlockInit.TOOL_HANGER_ITEM);
                output.accept(BlockInit.FLUORITE_ORE_ITEM);
                output.accept(BlockInit.DEEPSLATE_FLUORITE_ORE_ITEM);
                output.accept(BlockInit.ZINC_ORE_ITEM);
                output.accept(BlockInit.DEEPSLATE_ZINC_ORE_ITEM);
                output.accept(BlockInit.RAW_ZINC_BLOCK_ITEM);
                output.accept(BlockInit.ZINC_BLOCK_ITEM);
                output.accept(BlockInit.FLUXIUM_BLOCK_ITEM);
                output.accept(BlockInit.FLUXIUM_ENCLOSURE_ITEM);
                output.accept(BlockInit.TOURMALINE_ORE_ITEM);
                output.accept(BlockInit.DEEPSLATE_TOURMALINE_ORE_ITEM);
                output.accept(BlockInit.FLUORITE_CRYSTAL_CLUSTER_ITEM);
                output.accept(BlockInit.REDSTONE_CRYSTAL_CLUSTER_ITEM);
                output.accept(BlockInit.SULFUR_CRYSTAL_CLUSTER_ITEM);
                output.accept(BlockInit.SULFUR_BLOCK_ITEM);
                output.accept(BlockInit.MISTALIA_PETALS_ITEM);
                output.accept(BlockInit.ALCHECRYSITE_ITEM);
                output.accept(BlockInit.ALCHECRYSITE_STAIRS_ITEM);
                output.accept(BlockInit.ALCHECRYSITE_SLAB_ITEM);
                output.accept(BlockInit.ALCHECRYSITE_WALL_ITEM);
                output.accept(BlockInit.POLISHED_ALCHECRYSITE_ITEM);
                output.accept(BlockInit.POLISHED_ALCHECRYSITE_STAIRS_ITEM);
                output.accept(BlockInit.POLISHED_ALCHECRYSITE_SLAB_ITEM);
                output.accept(BlockInit.POLISHED_ALCHECRYSITE_WALL_ITEM);
                output.accept(BlockInit.ALCHECRYSITE_BRICKS_ITEM);
                output.accept(BlockInit.ALCHECRYSITE_BRICK_STAIRS_ITEM);
                output.accept(BlockInit.ALCHECRYSITE_BRICK_SLAB_ITEM);
                output.accept(BlockInit.ALCHECRYSITE_BRICK_WALL_ITEM);
                output.accept(BlockInit.ALCHECRYSITE_TILES_ITEM);
                output.accept(BlockInit.VESPERITE_ITEM);
                output.accept(BlockInit.VESPERITE_STAIRS_ITEM);
                output.accept(BlockInit.VESPERITE_SLAB_ITEM);
                output.accept(BlockInit.VESPERITE_WALL_ITEM);
                output.accept(BlockInit.POLISHED_VESPERITE_ITEM);
                output.accept(BlockInit.POLISHED_VESPERITE_STAIRS_ITEM);
                output.accept(BlockInit.POLISHED_VESPERITE_SLAB_ITEM);
                output.accept(BlockInit.POLISHED_VESPERITE_WALL_ITEM);
                output.accept(BlockInit.VESPERITE_BRICKS_ITEM);
                output.accept(BlockInit.VESPERITE_BRICK_STAIRS_ITEM);
                output.accept(BlockInit.VESPERITE_BRICK_SLAB_ITEM);
                output.accept(BlockInit.VESPERITE_BRICK_WALL_ITEM);
                output.accept(BlockInit.CUT_VESPERITE_ITEM);
                output.accept(BlockInit.CUT_VESPERITE_STAIRS_ITEM);
                output.accept(BlockInit.CUT_VESPERITE_SLAB_ITEM);
                output.accept(BlockInit.CUT_VESPERITE_WALL_ITEM);
                output.accept(BlockInit.FLUORITE_BLOCK_ITEM);
                output.accept(BlockInit.FLUORITE_BRICKS_ITEM);
                output.accept(BlockInit.FLUORITE_BRICK_STAIRS_ITEM);
                output.accept(BlockInit.FLUORITE_BRICK_SLAB_ITEM);
                output.accept(BlockInit.FLUORITE_BRICK_WALL_ITEM);
                output.accept(BlockInit.TOURMALINE_BLOCK_ITEM);
                output.accept(BlockInit.TOURMALINE_BRICKS_ITEM);
                output.accept(BlockInit.TOURMALINE_BRICK_STAIRS_ITEM);
                output.accept(BlockInit.TOURMALINE_BRICK_SLAB_ITEM);
                output.accept(BlockInit.TOURMALINE_BRICK_WALL_ITEM);
                output.accept(BlockInit.MANA_INSULATING_GLASS_ITEM);
                output.accept(BlockInit.CELIFERN_LOG_ITEM);
                output.accept(BlockInit.CELIFERN_WOOD_ITEM);
                output.accept(BlockInit.STRIPPED_CELIFERN_LOG_ITEM);
                output.accept(BlockInit.STRIPPED_CELIFERN_WOOD_ITEM);
                output.accept(BlockInit.CELIFERN_PLANKS_ITEM);
                output.accept(BlockInit.CELIFERN_STAIRS_ITEM);
                output.accept(BlockInit.CELIFERN_SLAB_ITEM);
                output.accept(BlockInit.CELIFERN_FENCE_ITEM);
                output.accept(BlockInit.CELIFERN_FENCE_GATE_ITEM);
                output.accept(BlockInit.CELIFERN_DOOR_ITEM);
                output.accept(BlockInit.CELIFERN_TRAPDOOR_ITEM);
                output.accept(BlockInit.CELIFERN_PRESSURE_PLATE_ITEM);
                output.accept(BlockInit.CELIFERN_BUTTON_ITEM);
                output.accept(BlockInit.CELIFERN_LEAVES_ITEM);
                output.accept(BlockInit.CELIFERN_SAPLING_ITEM);
                output.accept(BlockInit.CELIFERN_SIGN_ITEM);
                output.accept(BlockInit.CELIFERN_HANGING_SIGN_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_LOG_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_WOOD_ITEM);
                output.accept(BlockInit.STRIPPED_CHARCOAL_BIRCH_LOG_ITEM);
                output.accept(BlockInit.STRIPPED_CHARCOAL_BIRCH_WOOD_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_PLANKS_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_STAIRS_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_SLAB_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_FENCE_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_FENCE_GATE_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_DOOR_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_TRAPDOOR_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_PRESSURE_PLATE_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_BUTTON_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_LEAVES_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_SAPLING_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_SIGN_ITEM);
                output.accept(BlockInit.CHARCOAL_BIRCH_HANGING_SIGN_ITEM);
                output.accept(BlockInit.MYSTWOOD_LOG_ITEM);
                output.accept(BlockInit.MYSTWOOD_WOOD_ITEM);
                output.accept(BlockInit.STRIPPED_MYSTWOOD_LOG_ITEM);
                output.accept(BlockInit.STRIPPED_MYSTWOOD_WOOD_ITEM);
                output.accept(BlockInit.MYSTWOOD_PLANKS_ITEM);
                output.accept(BlockInit.MYSTWOOD_ENCLOSURE_ITEM);
                output.accept(BlockInit.MYSTWOOD_STAIRS_ITEM);
                output.accept(BlockInit.MYSTWOOD_SLAB_ITEM);
                output.accept(BlockInit.MYSTWOOD_FENCE_ITEM);
                output.accept(BlockInit.MYSTWOOD_FENCE_GATE_ITEM);
                output.accept(BlockInit.MYSTWOOD_DOOR_ITEM);
                output.accept(BlockInit.MYSTWOOD_TRAPDOOR_ITEM);
                output.accept(BlockInit.MYSTWOOD_PRESSURE_PLATE_ITEM);
                output.accept(BlockInit.MYSTWOOD_BUTTON_ITEM);
                output.accept(BlockInit.MYSTWOOD_SIGN_ITEM);
                output.accept(BlockInit.MYSTWOOD_HANGING_SIGN_ITEM);
                output.accept(BlockInit.SCORCHED_GRASS_SOIL_ITEM);
                output.accept(BlockInit.SCORCHED_SOIL_ITEM);
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAGITECH_MACHINE_TAB = CREATIVE_MODE_TABS.register("magitech_machine_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("tab.magitech.magitech_machine"))
            .icon(BlockInit.INFUSION_ALTAR_ITEM::toStack)
            .withTabsBefore(MAGITECH_BLOCK_TAB.getKey())
            .displayItems((parameters, output) -> {
                output.accept(BlockInit.ZARDIUS_CRUCIBLE_ITEM);
                output.accept(BlockInit.INFUSION_ALTAR_ITEM);
                output.accept(BlockInit.PEDESTAL_PYLON_ITEM);
                output.accept(BlockInit.MANA_NODE_ITEM);
                output.accept(BlockInit.MANA_RELAY_ITEM);
                output.accept(BlockInit.MANA_VESSEL_ITEM);
                output.accept(BlockInit.MANA_STRANDER_ITEM);
                output.accept(BlockInit.MANA_RECEIVER_ITEM);
                output.accept(BlockInit.MANA_COLLECTOR_ITEM);
                output.accept(BlockInit.INFUSER_ITEM);
                output.accept(BlockInit.CRUSHER_ITEM);
                output.accept(BlockInit.COMPRESSOR_ITEM);
                output.accept(BlockInit.CHILLER_ITEM);
                output.accept(BlockInit.HEAT_BURNER_ITEM);
                output.accept(BlockInit.ENVIROMETER_ITEM);
                output.accept(BlockInit.THERMAL_MANA_FURNACE_ITEM);
                output.accept(BlockInit.ITEM_COLLECTOR_ITEM);
                output.accept(BlockInit.MANA_JUNCTION_ITEM);
                output.accept(BlockInit.ENTANGLER_ITEM);
                output.accept(BlockInit.DETANGLER_ITEM);
                output.accept(BlockInit.MANA_PUMP_ITEM);
                output.accept(BlockInit.ENHANCED_MANA_NODE_ITEM);
                output.accept(BlockInit.ENHANCED_MANA_RELAY_ITEM);
                output.accept(BlockInit.ENHANCED_MANA_VESSEL_ITEM);
                output.accept(BlockInit.TRAP_HATCH_ITEM);
                output.accept(BlockInit.CREATIVE_MANA_SOURCE_ITEM);
                output.accept(BlockInit.CREATIVE_MANA_SINK_ITEM);
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAGITECH_TOOL_TAB = CREATIVE_MODE_TABS.register("magitech_tool_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("tab.magitech.magitech_tool"))
            .icon(() -> SynthesisedToolGenerator.generateTool(ToolTypeInit.LIGHT_SWORD.asToolType(), MaterialInit.WOOD, MaterialInit.FLUORITE, MaterialInit.DEEPSLATE, MaterialInit.GOLD))
            .withTabsBefore(MAGITECH_MACHINE_TAB.getKey())
            .displayItems((parameters, output) -> {
                for (ToolTypeLike type : RegistryHelper.registeredToolTypes()) {
                    for (ToolMaterialLike material : RegistryHelper.registeredToolMaterials()) {
                        output.accept(SynthesisedToolGenerator.generateTool(type.asToolType(), material.asToolMaterial()));
                    }
                }
            }).build());


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAGITECH_PART_TAB = CREATIVE_MODE_TABS.register("magitech_part_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("tab.magitech.magitech_part"))
            .icon(() -> SynthesisedToolGenerator.generatePart(ToolPartInit.LIGHT_BLADE.asToolPart(), MaterialInit.FLUORITE))
            .withTabsBefore(MAGITECH_TOOL_TAB.getKey())
            .displayItems((parameters, output) -> {
                for (ToolPartLike part : RegistryHelper.registeredToolParts()) {
                    for (ToolMaterialLike material : RegistryHelper.registeredToolMaterials()) {
                        output.accept(SynthesisedToolGenerator.generatePart(part.asToolPart(), material.asToolMaterial()));
                    }
                }
            }).build());


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAGITECH_SPELL_TAB = CREATIVE_MODE_TABS.register("magitech_spell_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("tab.magitech.magitech_spell"))
            .icon(() -> ThreadboundGenerator.generateThreadPage(SpellInit.ENERCRUX))
            .withTabsBefore(MAGITECH_PART_TAB.getKey())
            .displayItems((parameters, output) -> {
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.IGNISCA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.PYROLUX));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.FLUVALEN));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.BLAZEWEND));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.VOLKARIN));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.ARDOVITAE));

                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.FRIGALA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.CRYOLUXA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.NIVALUNE));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.GLISTELDA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.FROSBLAST));

                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.VOLTARIS));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.FULGENZA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.SPARKION));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.ARCLUME));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.ELECTROIDE));

                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.MIRAZIEN));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.PHANTASTRA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.VEILMIST));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.FADANCEA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.ILLUSFLARE));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.LUXGRAIL));

                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.TREMIVOX));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.OSCILBEAM));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.SONISTORM));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.QUAVERIS));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.SHOCKVANE));

                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.ARCALETH));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.MYSTAVEN));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.GLYMORA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.ENVISTRA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.HEXFLARE));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.MYSTPHEL));

                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.AELTHERIN));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.FLUVINAE));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.MISTRELUNE));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.SYLLAEZE));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.HYDRELUX));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.NYMPHORA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.HYDRAERUN));

                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.NULLIXIS));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.VOIDLANCE));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.TENEBRISOL));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.DISPARUNDRA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.NIHILFLARE));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.TENEBPORT));

                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.AETHERIX));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.THAUMIRIA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.ESFOUNTIA));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.QUINTEX));
                output.accept(ThreadboundGenerator.generateThreadPage(SpellInit.ENERCRUX));

            }).build());


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAGITECH_TEST_TAB = CREATIVE_MODE_TABS.register("magitech_test_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("tab.magitech.magitech_test"))
            .icon(ItemInit.POLISHED_ARCEUM::toStack)
            .withTabsBefore(MAGITECH_SPELL_TAB.getKey())
            .displayItems((parameters, output) -> {
                output.accept(ItemInit.POLISHED_REDSTONE_CRYSTAL);
                output.accept(ItemInit.VULCANITE);
                output.accept(ItemInit.POLISHED_VULCANITE);
                output.accept(ItemInit.FRIGIDITE);
                output.accept(ItemInit.POLISHED_FRIGIDITE);
                output.accept(ItemInit.FULMINITE);
                output.accept(ItemInit.POLISHED_FULMINITE);
                output.accept(ItemInit.TRANSLUCIUM);
                output.accept(ItemInit.POLISHED_TRANSLUCIUM);
                output.accept(ItemInit.RESONITE);
                output.accept(ItemInit.POLISHED_RESONITE);
                output.accept(ItemInit.ARCEUM);
                output.accept(ItemInit.POLISHED_ARCEUM);
                output.accept(ItemInit.TERRADITE);
                output.accept(ItemInit.POLISHED_TERRADITE);
                output.accept(ItemInit.ABYSSITE);
                output.accept(ItemInit.POLISHED_ABYSSITE);
            }).build());

    public static void registerCreativeTabs(IEventBus eventBus) {
        Magitech.LOGGER.info("Registering Creative Tabs for" + Magitech.MOD_ID);
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
