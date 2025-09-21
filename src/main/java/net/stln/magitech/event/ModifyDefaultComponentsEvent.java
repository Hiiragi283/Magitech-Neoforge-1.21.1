package net.stln.magitech.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechDataComponents;
import net.stln.magitech.init.MagitechItems;
import net.stln.magitech.init.MagitechSpells;
import net.stln.magitech.init.MagitechToolMaterials;
import net.stln.magitech.item.component.*;

import vazkii.patchouli.common.item.PatchouliDataComponents;

@EventBusSubscriber(modid = Magitech.MOD_ID)
public class ModifyDefaultComponentsEvent {

    @SubscribeEvent
    public static void modifyDefault(
            net.neoforged.neoforge.event.ModifyDefaultComponentsEvent event) {
        event.modify(
                MagitechItems.DAGGER,
                builder ->
                        builder.set(
                                        MagitechDataComponents.PART_MATERIAL_COMPONENT.get(),
                                        new PartMaterialComponent(
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON))
                                .build());
        event.modify(
                MagitechItems.LIGHT_SWORD,
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
                MagitechItems.HEAVY_SWORD,
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
                MagitechItems.PICKAXE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.PART_MATERIAL_COMPONENT.get(),
                                        new PartMaterialComponent(
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON,
                                                MagitechToolMaterials.IRON))
                                .build());
        event.modify(
                MagitechItems.HAMMER,
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
                MagitechItems.AXE,
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
                MagitechItems.SHOVEL,
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
                MagitechItems.SCYTHE,
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
                MagitechItems.WAND,
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
                MagitechItems.LIGHT_BLADE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                MagitechItems.HEAVY_BLADE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                MagitechItems.LIGHT_HANDLE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                MagitechItems.HEAVY_HANDLE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                MagitechItems.TOOL_BINDING,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                MagitechItems.HANDGUARD,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                MagitechItems.STRIKE_HEAD,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                MagitechItems.SPIKE_HEAD,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                MagitechItems.REINFORCED_STICK,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                MagitechItems.PLATE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                MagitechItems.CATALYST,
                builder ->
                        builder.set(
                                        MagitechDataComponents.MATERIAL_COMPONENT.get(),
                                        new MaterialComponent(MagitechToolMaterials.IRON))
                                .set(
                                        MagitechDataComponents.UPGRADE_COMPONENT.get(),
                                        UpgradeComponent.EMPTY)
                                .build());
        event.modify(
                MagitechItems.CONDUCTOR,
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
                    MagitechItems.GLISTENING_LEXICON,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .set(
                                            PatchouliDataComponents.BOOK,
                                            Magitech.id("glistening_lexicon"))
                                    .build());
            event.modify(
                    MagitechItems.THE_FIRE_THAT_THINKS,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .set(
                                            PatchouliDataComponents.BOOK,
                                            Magitech.id("the_fire_that_thinks"))
                                    .build());
            event.modify(
                    MagitechItems.ARCANE_ENGINEERING_COMPENDIUM,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .build());
        } else {
            event.modify(
                    MagitechItems.GLISTENING_LEXICON,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .build());
            event.modify(
                    MagitechItems.THE_FIRE_THAT_THINKS,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .build());
            event.modify(
                    MagitechItems.ARCANE_ENGINEERING_COMPENDIUM,
                    builder ->
                            builder.set(
                                            MagitechDataComponents.SPELL_COMPONENT.get(),
                                            SpellComponent.EMPTY)
                                    .build());
        }
        event.modify(
                MagitechItems.THREAD_PAGE,
                builder ->
                        builder.set(
                                        MagitechDataComponents.THREAD_PAGE_COMPONENT.get(),
                                        new ThreadPageComponent(MagitechSpells.ENERCRUX))
                                .build());
    }
}
