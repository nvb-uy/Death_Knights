package elocindev.deathknights.spells.unholy.debuff;

import elocindev.deathknights.api.core.SpellEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffectCategory;


public class GreviousPlague extends SpellEffect {
    public GreviousPlague() {
        super(StatusEffectCategory.HARMFUL, 0x4b3478);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
    }
}