package elocindev.deathknights.spells.blood.debuff;

import elocindev.deathknights.api.core.SpellEffect;
import elocindev.deathknights.config.entries.spells.blood.BloodBoilConfig;
import elocindev.deathknights.registry.SpellRegistry;
import elocindev.deathknights.registry.SpellSchoolRegistry;
import elocindev.deathknights.util.EffectUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellDamageSource;


public class BloodPlague extends SpellEffect {
    public BloodPlague() {
        super(StatusEffectCategory.HARMFUL, 0xb31d2c);
    }

    
    @Override public
    //? if 1.20.1 {
    void
    //?} else {
    /*boolean
    *///?}
    applyUpdateEffect(LivingEntity entity, int amplifier) {
        BloodBoilConfig config = BloodBoilConfig.INSTANCE;
        if (entity.getWorld().isClient() || entity.age % config.tick_rate != 0) return
        //? if 1.21.1 {
        /*super.applyUpdateEffect(entity, amplifier)
        *///?}
        ;

        for (PlayerEntity e : entity.getEntityWorld().getEntitiesByClass(PlayerEntity.class, entity.getBoundingBox().expand(config.radius*2), (e) -> EffectUtils.hasStatusEffect(e, SpellRegistry.BLOOD_THIRST))) {
            if (TargetHelper.allowedToHurt(e, entity) && !(entity instanceof HorseEntity)) {
                entity.damage(SpellDamageSource.create(SpellSchoolRegistry.BLOOD, e), ((float) e.getAttributeValue(
                //? if 1.20.1 {
                SpellSchoolRegistry.BLOOD.attribute
                //?} else {
                /*SpellSchoolRegistry.BLOOD.attributeEntry
                *///?}
                ) * config.damage_blood_scaling) * (amplifier));
                break;
            }
        }

        return
        //? if 1.21.1 {
        /*super.applyUpdateEffect(entity, amplifier)
        *///?}
        ;
    }
}
