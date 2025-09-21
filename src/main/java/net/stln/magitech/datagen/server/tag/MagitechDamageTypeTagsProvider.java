package net.stln.magitech.datagen.server.tag;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.stln.magitech.Magitech;
import net.stln.magitech.init.MagitechDamageTypes;

import org.jetbrains.annotations.NotNull;

public class MagitechDamageTypeTagsProvider extends TagsProvider<DamageType> {
    public MagitechDamageTypeTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper fileHelper) {
        super(output, Registries.DAMAGE_TYPE, lookupProvider, Magitech.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(DamageTypeTags.BYPASSES_SHIELD).add(MagitechDamageTypes.MANA_BERRY_BUSH);
        tag(DamageTypeTags.NO_KNOCKBACK).add(MagitechDamageTypes.MANA_BERRY_BUSH);

        for (ResourceKey<DamageType> key : MagitechDamageTypes.MAGICAL) {
            tag(Tags.DamageTypes.IS_MAGIC).add(key);
        }
    }
}
