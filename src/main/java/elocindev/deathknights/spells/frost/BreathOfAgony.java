package elocindev.deathknights.spells.frost;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.api.core.SpellEffect;
import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.spells.frost.BreathOfAgonyConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.spell_engine.particle.Particles;
import net.spell_power.api.SpellDamageSource;
import net.spell_power.api.SpellPowerMechanics;
import net.spell_power.api.SpellSchools;

public class BreathOfAgony extends SpellEffect {
    public static final RegistryKey<DamageType> DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(DeathKnights.MODID, "breath_of_agony"));
    public static BreathOfAgonyConfig CONFIG = Configs.Spells.Frost.BREATH_OF_AGONY;

    public BreathOfAgony() {
        super(StatusEffectCategory.BENEFICIAL,
        0x330066); 
    }
    
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();
        Random random = world.getRandom();
        double length = CONFIG.length;

        ParticleEffect particleEffect = Particles.frost_hit.particleType;
        
        double coneAngle = Math.toRadians(45);
        int particlesPerTick = 2;

        Vec3d direction = entity.getRotationVec(1.0f);
        Vec3d headPosition = entity.getPos().add(0, entity.getEyeHeight(entity.getPose()), 0);
        Vec3d mouthOffset = new Vec3d(direction.x * 0.2, direction.y * 0.2 - 0.2, direction.z * 0.2);
        Vec3d basePosition = headPosition.add(mouthOffset);

        for (int i = 0; i < particlesPerTick; i++) {
            double yaw = random.nextDouble() * coneAngle - coneAngle / 2;
            double pitch = random.nextDouble() * coneAngle - coneAngle / 2;

            Vec3d yawRotated = direction.rotateY((float) yaw);
            Vec3d pitchRotated = new Vec3d(yawRotated.x, yawRotated.y * Math.cos(pitch) - yawRotated.z * Math.sin(pitch), yawRotated.y * Math.sin(pitch) + yawRotated.z * Math.cos(pitch));

            double speed = 1.5 + random.nextDouble() * 0.2;

            Vec3d particleVelocity = pitchRotated.multiply(speed);

            world.addParticle(particleEffect, basePosition.x, basePosition.y, basePosition.z, particleVelocity.x, 0, particleVelocity.z);
        }

        float damage = (float)(entity.getAttributeValue(SpellSchools.FROST.attribute) * CONFIG.damage_frost_scaling);
        double critChance = entity.getAttributeValue(SpellPowerMechanics.CRITICAL_CHANCE.attribute) / 100;

        if (random.nextDouble() < critChance) damage *= CONFIG.damage_critical_scaling;

        if (entity.age % CONFIG.tick_rate == 0) {
            Vec3d startPosition = entity.getPos();
            Vec3d endPosition = startPosition.add(direction.x * length, entity.getHeight(), direction.z * length);

            Box frontBoundingBox = new Box(startPosition, endPosition).stretch(0.5, 0.5, 0.5);

            var nearby = world.getEntitiesByClass(LivingEntity.class, frontBoundingBox, (e) -> e != entity);
            
            if (nearby.isEmpty()) {
                entity.removeStatusEffect(this);
                return;
            }

            for (LivingEntity victim : nearby) {
                if (victim == entity) continue;
                
                victim.damage(SpellDamageSource.create(SpellSchools.FROST, entity), damage);
                world.addParticle(Particles.frost_hit.particleType, victim.getX(), victim.getY()+1, victim.getZ(), 0, -0.1, 0);
                victim.setFrozenTicks(CONFIG.frozen_ticks);
            }
        }
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}