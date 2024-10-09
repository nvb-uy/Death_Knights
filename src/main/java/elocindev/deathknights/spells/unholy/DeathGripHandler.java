package elocindev.deathknights.spells.unholy;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.spell_engine.api.event.CombatEvents;
import net.spell_engine.internals.SpellHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.spells.unholy.DeathGripConfig;
import elocindev.necronomicon.api.NecUtilsAPI;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;

public class DeathGripHandler {
    private static final DeathGripConfig CONFIG = Configs.Spells.Unholy.DEATH_GRIP;

    private static final Map<LivingEntity, PullInfoHolder> grippedEntities = new HashMap<>();

    private static class PullInfoHolder {
        public final PlayerEntity caster;
        public int timeElapsed;

        public PullInfoHolder(PlayerEntity caster) {
            this.caster = caster;
            this.timeElapsed = 0;
        }
    }

    public static void register() {
        CombatEvents.SPELL_CAST.register(
            args -> {
                PlayerEntity caster = args.caster();
                if (caster == null) return;

                if (args.spell().id().toString().equals("death_knights:death_grip")) {
                    for (Entity target : args.targets()) {
                        if (target instanceof LivingEntity livingTarget) {
                            if (CONFIG.entity_blacklist.contains(NecUtilsAPI.getEntityId(livingTarget))) {
                                continue;
                            }
                            
                            if (livingTarget.getHealth() >= caster.getHealth() * CONFIG.health_threshold) {
                                continue;
                            }

                            startDeathGripPull(livingTarget, caster);

                            SpellHelper.imposeCooldown(caster, null, null, 0);
                        }
                    }
                }
            }
        );
        
        ServerTickEvents.END_WORLD_TICK.register(DeathGripHandler::updateGrippedEntities);
    }

    private static void startDeathGripPull(LivingEntity target, PlayerEntity caster) {
        grippedEntities.put(target, new PullInfoHolder(caster));
        caster.getWorld().playSound(null, caster.getX(), caster.getY(), caster.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    private static void updateGrippedEntities(ServerWorld world) {
        Iterator<Map.Entry<LivingEntity, PullInfoHolder>> iterator = grippedEntities.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<LivingEntity, PullInfoHolder> entry = iterator.next();
            LivingEntity target = entry.getKey();
            PullInfoHolder pullInfo = entry.getValue();
            PlayerEntity caster = pullInfo.caster;

            if (target.isRemoved() || caster.isRemoved() || !target.isAlive()) {
                iterator.remove();
                continue;
            }

            pullInfo.timeElapsed++;

            Vec3d casterPos = caster.getPos();
            Vec3d targetPos = target.getPos();
            Vec3d direction = casterPos.subtract(targetPos).normalize();
            double distance = casterPos.distanceTo(targetPos);

            if (pullInfo.timeElapsed >= CONFIG.max_pull_time) {
                teleportNearPlayer(target, caster);
                iterator.remove();
                continue;
            }

            if (distance <= CONFIG.min_distance) {
                target.setVelocity(0, 0, 0);
                applyEffectsToTarget(target);
                iterator.remove();
                continue;
            }
            
            Vec3d pullVelocity = direction.multiply(CONFIG.pull_speed);

            target.setVelocity(pullVelocity.x, pullVelocity.y, pullVelocity.z);
            target.velocityModified = true;
        }
    }

    private static void teleportNearPlayer(LivingEntity target, PlayerEntity caster) {
        double offsetX = (caster.getRandom().nextDouble() - 0.5) * 2.0;
        double offsetZ = (caster.getRandom().nextDouble() - 0.5) * 2.0;
        target.teleport(caster.getX() + offsetX, caster.getY() + 1.0, caster.getZ() + offsetZ);
        caster.getWorld().playSound(null, caster.getX(), caster.getY(), caster.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    private static void applyEffectsToTarget(LivingEntity target) {
        for (DeathGripConfig.EffectHolder effectHolder : CONFIG.effects) {
            StatusEffect effect = Registries.STATUS_EFFECT.get(new Identifier(effectHolder.effect_id));
            
            if (effect != null) {
                StatusEffectInstance effectInstance = new StatusEffectInstance(effect, (int) effectHolder.duration, (int) effectHolder.amplifier);
                target.addStatusEffect(effectInstance);
            }
        }
    }
}