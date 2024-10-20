package elocindev.deathknights.spells.blood.debuff;

import elocindev.deathknights.api.core.SpellEffect;
import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.spells.blood.BloodBoilConfig;
import elocindev.deathknights.registry.SpellRegistry;
import elocindev.deathknights.registry.SpellSchoolRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellDamageSource;


public class BloodPlague extends SpellEffect {
    public static BloodBoilConfig CONFIG = Configs.Spells.Blood.BLOOD_BOIL;

    public BloodPlague() {
        super(StatusEffectCategory.HARMFUL, 0xb31d2c);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        if (entity.getWorld().isClient() || entity.age % CONFIG.tick_rate != 0) return;

        for (PlayerEntity e : entity.getEntityWorld().getEntitiesByClass(PlayerEntity.class, entity.getBoundingBox().expand(CONFIG.radius*2), (e) -> e.hasStatusEffect(SpellRegistry.BLOOD_THIRST))) {
            if (TargetHelper.allowedToHurt(e, entity) && !(entity instanceof HorseEntity)) {
                entity.damage(SpellDamageSource.create(SpellSchoolRegistry.BLOOD, e), ((float) e.getAttributeValue(SpellSchoolRegistry.BLOOD.attribute) * CONFIG.damage_blood_scaling) * (amplifier));
                break;
            }
        }
    }
}