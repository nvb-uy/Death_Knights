package elocindev.deathknights.spells.frost;

import elocindev.deathknights.api.core.SpellEffect;
import elocindev.deathknights.config.entries.spells.frost.FrostStrikeConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.spell_engine.particle.Particles;
import net.spell_power.api.SpellDamageSource;
import net.spell_power.api.SpellPowerMechanics;
import net.spell_power.api.SpellSchools;

public class FrostStrike extends SpellEffect {
    public static FrostStrikeConfig CONFIG = FrostStrikeConfig.INSTANCE;

    public FrostStrike() {
        super(StatusEffectCategory.HARMFUL,
        0x330066); 
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        World world = entity.getWorld();

        if (entity.isFrozen()) {
            entity.setFrozenTicks(0);

            float icicle_damage = (float)(entity.getAttributeValue(SpellSchools.FROST.attribute) * CONFIG.frost_scaling_icicles);
            double critChance = entity.getAttributeValue(SpellPowerMechanics.CRITICAL_CHANCE.attribute) / 100;

            for (LivingEntity e : world.getEntitiesByClass(LivingEntity.class, entity.getBoundingBox().expand(CONFIG.icicles_radius, CONFIG.icicles_radius, CONFIG.icicles_radius), (e) -> e != entity && e instanceof PlayerEntity == false)) {
                if (Math.random() < critChance)
                    icicle_damage *= 2;  
                                    
                e.damage(SpellDamageSource.create(SpellSchools.FROST, entity), icicle_damage);

                entity.getWorld().addParticle(Particles.frost_hit.particleType, e.getX(), e.getY()+1, e.getZ(), 0, -0.1, 0);
            }
        }

        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }
}
