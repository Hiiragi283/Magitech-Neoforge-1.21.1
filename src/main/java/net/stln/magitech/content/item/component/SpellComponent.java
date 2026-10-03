package net.stln.magitech.content.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.stln.magitech.feature.magic.spell.ISpell;
import net.stln.magitech.feature.magic.spell.SpellLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public record SpellComponent(@NotNull List<ISpell> spells, int selected) {
    public static final Codec<SpellComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ISpell.CODEC.listOf().fieldOf("spells").forGetter(SpellComponent::spells),
            Codec.INT.fieldOf("selected").forGetter(SpellComponent::selected)
    ).apply(instance, SpellComponent::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SpellComponent> STREAM_CODEC = StreamCodec.composite(
            ISpell.STREAM_CODEC.apply(ByteBufCodecs.list()),
            SpellComponent::spells,
            ByteBufCodecs.INT,
            SpellComponent::selected,
            SpellComponent::new
    );

    public static final SpellComponent EMPTY = new SpellComponent(List.of(), 0);

    public SpellComponent(@NotNull Collection<? extends SpellLike> spells) {
        this(spells.stream().map(SpellLike::asSpell).toList(), 0);
    }

    public @Nullable ISpell getSelectedSpell() {
        return spells.get(selected);
    }

    public @NotNull SpellComponent setSelected(int selected) {
        return new SpellComponent(this.spells, selected);
    }
}
