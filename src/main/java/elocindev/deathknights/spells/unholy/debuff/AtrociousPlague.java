package elocindev.deathknights.spells.unholy.debuff;

import elocindev.deathknights.api.core.SpellEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffectCategory;


public class AtrociousPlague extends SpellEffect {
    public AtrociousPlague() {
        super(StatusEffectCategory.HARMFUL, 0xa8672d);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
    }
}