package net.stln.magitech.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechDataComponents;
import net.stln.magitech.init.MagitechSpells;
import net.stln.magitech.init.MagitechToolMaterials;
import net.stln.magitech.item.ItemInit;
import net.stln.magitech.item.component.*;

import vazkii.patchouli.common.item.PatchouliDataComponents;

@EventBusSubscriber(modid = Magitech.MOD_ID)
public class ModifyDefaultComponentsEvent {

    @SubscribeEvent
    public static void modifyDefault(
            net.neoforged.neoforge.event.ModifyDefaultComponentsEvent event) {
        event.modify(
                ItemInit.DAGGER,
                builder ->
                        builder.set(
                                        MagitechDataComponents.PART_MATERIAL_COMPONENT.get(),
                                        new PartMaterialComponent(
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON))
                                .build());
        event.modify(
                ItemInit.LIGHT_SWORD,
                builder ->
                        builder.set(
                                        MagitechDataComponents.PART_MATERIAL_COMPONENT.get(),
                                        new PartMaterialComponent(
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON))
                                .build());
        event.modify(
                ItemInit.HEAVY_SWORD,
                builder ->
                        builder.set(
                                        MagitechDataComponents.PART_MATERIAL_COMPONENT.get(),
                                        new PartMaterialComponent(
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON))
                                .build());
        event.modify(
                ItemInit.PICKAXE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.PART_MATERIAL_COMPONENT.get(),
                                        new PartMaterialComponent(
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON))
                                .build());
        event.modify(
                ItemInit.HAMMER,
                builder ->
                        builder.set(
                                        MagitechDataComponents.PART_MATERIAL_COMPONENT.get(),
                                        new PartMaterialComponent(
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON))
                                .build());
        event.modify(
                ItemInit.AXE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.PART_MATERIAL_COMPONENT.get(),
                                        new PartMaterialComponent(
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON))
                                .build());
        event.modify(
                ItemInit.SHOVEL,
                builder ->
                        builder.set(
                                        MagitechDataComponents.PART_MATERIAL_COMPONENT.get(),
                                        new PartMaterialComponent(
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON))
                                .build());
        event.modify(
                ItemInit.SCYTHE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.PART_MATERIAL_COMPONENT.get(),
                                        new PartMaterialComponent(
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON))
                                .build());
        event.modify(
                ItemInit.WAND,
                builder ->
                        builder.set(
                                        MagitechDataComponents.PART_MATERIAL_COMPONENT.get(),
                                        new PartMaterialComponent(
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON))
                                .build());

        event.modify(
                ItemInit.LIGHT_BLADE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                ItemInit.HEAVY_BLADE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                ItemInit.LIGHT_HANDLE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                ItemInit.HEAVY_HANDLE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                ItemInit.TOOL_BINDING,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                ItemInit.HANDGUARD,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                ItemInit.STRIKE_HEAD,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                ItemInit.SPIKE_HEAD,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                ItemInit.REINFORCED_STICK,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                ItemInit.PLATE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                ItemInit.CATALYST,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                ItemInit.CONDUCTOR,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());

        if (ModList.get().isLoaded("patchouli")) {
            event.modify(
                    ItemInit.GLISTENING_LEXICON,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .set(
                                            PatchouliDataComponents.BOOK,
                                            Magitech.id("glistening_lexicon"))
                                    .build());
            event.modify(
                    ItemInit.THE_FIRE_THAT_THINKS,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .set(
                                            PatchouliDataComponents.BOOK,
                                            Magitech.id("the_fire_that_thinks"))
                                    .build());
            event.modify(
                    ItemInit.ARCANE_ENGINEERING_COMPENDIUM,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .build());
        } else {
            event.modify(
                    ItemInit.GLISTENING_LEXICON,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .build());
            event.modify(
                    ItemInit.THE_FIRE_THAT_THINKS,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .build());
            event.modify(
                    ItemInit.ARCANE_ENGINEERING_COMPENDIUM,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .build());
        }
        event.modify(
                ItemInit.THREAD_PAGE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.THREAD_PAGE_COMPONENT.get(),
                                        new ThreadPageComponent(MagitechSpells.ENERCRUX))
                                .build());
    }
}
