package elocindev.deathknights.spells.frost;

import elocindev.deathknights.api.core.SpellEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;

//? if 1.20.1 {
import net.minecraft.entity.attribute.AttributeContainer;
//?}

public class FrostStrike extends SpellEffect {
    public FrostStrike() {
        super(StatusEffectCategory.HARMFUL,
        0x330066); 
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity,
    //? if 1.20.1 {
    AttributeContainer attributes,
    //?}
    int amplifier) {
        super.onApplied(entity, 
        //? if 1.20.1 {
        attributes,
        //?}
        amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }
}
