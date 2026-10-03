package net.stln.magitech.content.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.MagitechRegistries;
import net.stln.magitech.feature.magic.spell.ISpell;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public record SpellComponent(@NotNull HolderSet<ISpell> spells, int selected) {
    public static final Codec<SpellComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ISpell.HOLDER_SET_CODEC.fieldOf("spells").forGetter(SpellComponent::spells),
            Codec.INT.fieldOf("selected").forGetter(SpellComponent::selected)
    ).apply(instance, SpellComponent::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SpellComponent> STREAM_CODEC = StreamCodec.composite(
            ISpell.HOLDER_SET_STREAM_CODEC,
            SpellComponent::spells,
            ByteBufCodecs.INT,
            SpellComponent::selected,
            SpellComponent::new
    );

    public static final SpellComponent EMPTY = new SpellComponent(HolderSet.empty(), 0);

    public SpellComponent(@NotNull HolderSet<ISpell> spells) {
        this(spells, 0);
    }

    public SpellComponent(@NotNull Collection<? extends ISpell> spells) {
        this(HolderSet.direct(MagitechRegistries.SPELL::wrapAsHolder, spells.stream().toList()), 0);
    }

    public Holder<ISpell> getSelectedSpell() {
        return spells.get(selected);
    }

    public @NotNull SpellComponent setSelected(int selected) {
        return new SpellComponent(this.spells, selected);
    }
}
