package elocindev.deathknights.spells.frost;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.api.core.SpellEffect;
import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.spells.frost.RemorselessWinterConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.StopSoundS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.spell_engine.particle.Particles;
import net.spell_power.api.SpellDamageSource;
import net.spell_power.api.SpellPowerMechanics;
import net.spell_power.api.SpellSchools;

public class RemorselessWinter extends SpellEffect {
    public static final RegistryKey<DamageType> DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(DeathKnights.MODID, "remorseless_winter"));
    public static RemorselessWinterConfig CONFIG = Configs.Spells.Frost.REMORSELESS_WINTER;

    private boolean hasAmbientPlayed = false;

    public RemorselessWinter() {
        super(StatusEffectCategory.BENEFICIAL,
        0x330066); 
    }
    
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();
        Random random = world.getRandom();

        if (!hasAmbientPlayed) { playAmbient(world, entity); hasAmbientPlayed = true; }

        double height = 2.0;
        int turns = 3;
        double radius1 = CONFIG.radius;
        double radius2 = radius1 / 2;
        int particlesPerTick = 20;

        ParticleEffect particleEffect = ParticleTypes.SNOWFLAKE;

        for (int i = 0; i < particlesPerTick; i++) {
            double randomYOffset = random.nextDouble() * height;

            double angle = 2 * Math.PI * random.nextDouble() + (2 * Math.PI / height) * randomYOffset * turns;
            double xOffset1 = Math.cos(angle) * radius1;
            double zOffset1 = Math.sin(angle) * radius1;

            double xOffset2 = Math.cos(angle) * radius2;
            double zOffset2 = Math.sin(angle) * radius2;

            double velocityX = -Math.sin(angle) * 0.1;
            double velocityZ = Math.cos(angle) * 0.1;

            velocityX += (random.nextDouble() - 0.5) * 0.3;
            float off = (random.nextFloat() - 0.5f) * 0.3f;

            world.addParticle(particleEffect, entity.getX() + xOffset1+off*-1, entity.getY() + randomYOffset, entity.getZ() + zOffset1+off, velocityX, 0, velocityZ);

            if (random.nextBoolean())
                world.addParticle(particleEffect, entity.getX() + xOffset2+off*-1, entity.getY() + randomYOffset/2, entity.getZ() + zOffset2+off, velocityX, 0, velocityZ);
        }

        float damage = (float)(entity.getAttributeValue(SpellSchools.FROST.attribute) * CONFIG.damage_frost_scaling);
        double critChance = entity.getAttributeValue(SpellPowerMechanics.CRITICAL_CHANCE.attribute) / 100;

        if (random.nextDouble() < critChance) damage *= CONFIG.damage_critical_scaling;

        if (entity.age % CONFIG.tick_rate == 0)
            for (LivingEntity e : world.getEntitiesByClass(LivingEntity.class, entity.getBoundingBox().expand(CONFIG.radius, CONFIG.radius, CONFIG.radius), (e) -> e != entity)) {
                e.damage(SpellDamageSource.create(SpellSchools.FROST, entity), damage);

                world.addParticle(Particles.frost_hit.particleType, e.getX(), e.getY()+1, e.getZ(), 0, -0.1, 0);
                
                if (random.nextDouble() < CONFIG.frozen_chance)
                    e.setFrozenTicks(CONFIG.frozen_ticks);
            }
        
        if (entity.age % 40 == 0) {
            playAmbient(world, entity);
        }

        super.applyUpdateEffect(entity, amplifier);
    }

    private static void playAmbient(World world, LivingEntity entity) {
        if (entity instanceof PlayerEntity)
            world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_ELYTRA_FLYING, entity.getSoundCategory(), 1.0F, 1.8F);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        World world = entity.getWorld();

        if (world.isClient()) {
            super.onRemoved(entity, attributes, amplifier);
            return;
        }

        StopSoundS2CPacket stopSoundS2CPacket = new StopSoundS2CPacket(SoundEvents.ITEM_ELYTRA_FLYING.getId(), entity.getSoundCategory());

        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
        serverPlayerEntity.networkHandler.sendPacket(stopSoundS2CPacket);

        hasAmbientPlayed = false;

        super.onRemoved(entity, attributes, amplifier);
    }

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}