package elocindev.deathknights.spells.frost;

import elocindev.deathknights.api.core.SpellEffect;
import elocindev.deathknights.config.entries.spells.frost.ObliterateConfig;
import elocindev.deathknights.registry.SpellRegistry;
import elocindev.deathknights.util.EffectUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

//? if 1.20.1 {
import net.minecraft.entity.attribute.AttributeContainer;
//?} else {
/*import net.minecraft.registry.entry.RegistryEntry;
*///?}
 
public class Obliterate extends SpellEffect {
    public Obliterate() {
        super(StatusEffectCategory.BENEFICIAL,
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

        var breath = SpellRegistry.BREATH_OF_AGONY;
        var winter = SpellRegistry.REMORSELESS_WINTER;
        ObliterateConfig config = ObliterateConfig.INSTANCE;
        
        if (EffectUtils.hasStatusEffect(entity, breath)) {
            entity.addStatusEffect(new StatusEffectInstance(
                //? if 1.20.1 {
                breath,
                //?} else {
                /*RegistryEntry.of(breath),
                *///?}
            EffectUtils.getStatusEffect(entity, breath).getDuration() + config.breath_of_agony_extension_ticks, 0, false, false, true));
        }

        if (EffectUtils.hasStatusEffect(entity, winter)) {
            entity.addStatusEffect(new StatusEffectInstance(
                //? if 1.20.1 {
                winter,
                //?} else {
                /*RegistryEntry.of(winter),
                *///?}
            EffectUtils.getStatusEffect(entity, winter).getDuration() + config.remorseless_winter_extension_ticks, 0, false, false, true));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }
    
}
