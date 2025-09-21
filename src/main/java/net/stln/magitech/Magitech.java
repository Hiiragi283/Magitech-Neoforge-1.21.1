package net.stln.magitech;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.stln.magitech.compat.curios.CuriosInit;
import net.stln.magitech.data.DataMapTypeInit;
import net.stln.magitech.element.Element;
import net.stln.magitech.init.MagitechAttributes;
import net.stln.magitech.init.MagitechBiomes;
import net.stln.magitech.init.MagitechBlockEntityTypes;
import net.stln.magitech.init.MagitechBlocks;
import net.stln.magitech.init.MagitechCreativeTabs;
import net.stln.magitech.init.MagitechCriteria;
import net.stln.magitech.init.MagitechDataComponents;
import net.stln.magitech.init.MagitechEntities;
import net.stln.magitech.init.MagitechLootFunctions;
import net.stln.magitech.init.MagitechMenuTypes;
import net.stln.magitech.init.MagitechMobEffects;
import net.stln.magitech.init.MagitechParticleTypes;
import net.stln.magitech.init.MagitechRecipes;
import net.stln.magitech.init.MagitechSounds;
import net.stln.magitech.init.MagitechSpells;
import net.stln.magitech.init.MagitechToolMaterials;
import net.stln.magitech.item.ItemInit;
import net.stln.magitech.item.tool.register.ToolMaterialRegister;
import net.stln.magitech.item.tool.upgrade.UpgradeInit;
import net.stln.magitech.worldgen.WorldGenInit;
import net.stln.magitech.worldgen.tree.TreeGrowerInit;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Magitech.MOD_ID)
public class Magitech {

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "magitech";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    // The constructor for the mod class is the first code that is run when your mod
    // is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and
    // pass them in
    // automatically.
    public Magitech(IEventBus modEventBus, ModContainer modContainer) {
        MagitechRegistries.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config
        // file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        MagitechDataComponents.registerComponents(modEventBus);

        MagitechBlocks.registerBlocks(modEventBus);
        MagitechBlockEntityTypes.register(modEventBus);
        ItemInit.registerItems(modEventBus);

        MagitechAttributes.registerEntityAttributes(modEventBus);
        MagitechCreativeTabs.registerCreativeTabs(modEventBus);
        MagitechCriteria.registerCriteria(modEventBus);
        Element.registerElements();
        DataMapTypeInit.registerDataMapTypes(modEventBus);
        MagitechEntities.registerModEntities(modEventBus);
        MagitechMenuTypes.registerMenus(modEventBus);
        MagitechLootFunctions.registerFunctions(modEventBus);
        MagitechToolMaterials.registerMaterials(modEventBus);
        MagitechMobEffects.registerMobEffects(modEventBus);
        MagitechParticleTypes.registerParticleClient(modEventBus);
        MagitechRecipes.registerRecipes(modEventBus);
        MagitechSounds.registerSoundEvents(modEventBus);
        MagitechSpells.registerSpells(modEventBus);
        ToolMaterialRegister.init();
        TreeGrowerInit.registerTrunkPlacerTypes(modEventBus);
        UpgradeInit.registerUpgrades();
        CuriosInit.registerValidators();
        WorldGenInit.registerFeatures(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod)
        // to respond
        // directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in
        // this class, like
        // onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
        // WorldGenInit.registerBiomeModifiers();
        MagitechBlocks.registerStrippableBlocks();
        MagitechBiomes.registerBiomeRegions(event);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {}
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }
}
