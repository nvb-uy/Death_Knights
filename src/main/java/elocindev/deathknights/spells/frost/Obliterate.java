package elocindev.deathknights.spells.frost;

import elocindev.deathknights.api.core.SpellEffect;
import elocindev.deathknights.config.entries.spells.frost.ObliterateConfig;
import elocindev.deathknights.registry.SpellRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

public class Obliterate extends SpellEffect {
    public static ObliterateConfig CONFIG = ObliterateConfig.INSTANCE;

    public Obliterate() {
        super(StatusEffectCategory.BENEFICIAL,
        0x330066); 
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);

        var breath = SpellRegistry.BREATH_OF_AGONY;
        var winter = SpellRegistry.REMORSELESS_WINTER;
        
        if (entity.hasStatusEffect(breath)) {
            entity.addStatusEffect(new StatusEffectInstance(breath, entity.getStatusEffect(breath).getDuration() + CONFIG.breath_of_agony_extension_ticks, 0, false, false, true));
        }

        if (entity.hasStatusEffect(winter)) {
            entity.addStatusEffect(new StatusEffectInstance(winter, entity.getStatusEffect(winter).getDuration() + CONFIG.remorseless_winter_extension_ticks, 0, false, false, true));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }
    
}
