package elocindev.deathknights.spells.blood.buff;

import elocindev.deathknights.api.core.SpellEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffectCategory;


public class BloodThirst extends SpellEffect {
    public BloodThirst() {
        super(StatusEffectCategory.BENEFICIAL, 0xb31d2c);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier); 
    }
}