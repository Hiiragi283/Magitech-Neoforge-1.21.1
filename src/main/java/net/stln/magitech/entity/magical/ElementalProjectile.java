package net.stln.magitech.entity.magical;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.level.Level;
import net.stln.magitech.element.Element;

import org.jetbrains.annotations.NotNull;

public interface ElementalProjectile extends TraceableEntity {
    @NotNull Level level();

    @NotNull default Element getElement() {
        return Element.NONE;
    }

    @NotNull default DamageSource getElementalDamageSource() {
        return level().damageSources().source(getElement().getDamageType(), getOwner());
    }

    float getElementalDamageValue(Entity entity);
}
