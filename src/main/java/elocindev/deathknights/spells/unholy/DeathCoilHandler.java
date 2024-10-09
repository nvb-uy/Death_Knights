package elocindev.deathknights.spells.unholy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.spell_engine.api.event.CombatEvents;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellDamageSource;

import java.util.List;

import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.spells.unholy.DeathCoilConfig;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig.PlagueProperty;
import elocindev.deathknights.registry.SpellSchoolRegistry;

public class DeathCoilHandler {
    private static DeathCoilConfig CONFIG = Configs.Spells.Unholy.DEATH_COIL;

    public static void register() {
        CombatEvents.SPELL_CAST.register(
            args -> {
                PlayerEntity caster = args.caster();
                if (caster == null) return;

                if (args.spell().id().toString().equals("death_knights:death_coil")) {
                    List<Entity> targets = args.targets();

                    for (Entity target : targets) {
                        if (!(target instanceof LivingEntity)) continue;
                        LivingEntity livingTarget = (LivingEntity) target;

                        PlagueProperty activePlague = null;
                        StatusEffect activeEffect = null;

                        for (PlagueProperty plague : PlaguesConfig.INSTANCE.plagues) {
                            StatusEffect plagueEffect = Registries.STATUS_EFFECT.get(new Identifier(plague.effect_id));
                            if (plagueEffect != null && livingTarget.hasStatusEffect(plagueEffect)) {
                                activePlague = plague;
                                activeEffect = plagueEffect;
                                break;
                            }
                        }

                        if (activePlague != null && activeEffect != null) {
                            StatusEffectInstance plagueInstance = livingTarget.getStatusEffect(activeEffect);
                            if (plagueInstance != null) {
                                int currentStacks = plagueInstance.getAmplifier() + 1;

                                int stacksToExplode = Math.min(currentStacks, CONFIG.plague_stacks);
                                int remainingStacks = currentStacks - stacksToExplode;

                                float damagePerStack = (float) (caster.getAttributeValue(SpellSchoolRegistry.UNHOLY.attribute) * CONFIG.unholy_coefficent);
                                livingTarget.damage(SpellDamageSource.create(SpellSchoolRegistry.UNHOLY, caster), damagePerStack * stacksToExplode);

                                if (remainingStacks > 0) {
                                    livingTarget.addStatusEffect(new StatusEffectInstance(activeEffect, activePlague.duration_ticks, remainingStacks - 1));
                                } else {
                                    livingTarget.removeStatusEffect(activeEffect);
                                }

                                applyPlagueToNearbyTargets(livingTarget, caster, activeEffect, activePlague);
                            }
                        }
                    }
                }
            }
        );
    }

    private static void applyPlagueToNearbyTargets(LivingEntity target, PlayerEntity caster, StatusEffect plagueEffect, PlagueProperty plague) {
        List<LivingEntity> nearbyEntities = target.getWorld().getEntitiesByClass(LivingEntity.class, target.getBoundingBox().expand(CONFIG.spread_radius), e -> e != target);
    
        for (LivingEntity ent : nearbyEntities) {
            StatusEffectInstance currentEffect = ent.getStatusEffect(plagueEffect);
            if (ent.equals(caster) || ent instanceof PlayerEntity playerVictim && (playerVictim.isCreative() || playerVictim.isSpectator() || !TargetHelper.allowedToHurt(caster, playerVictim))) continue;
            
    
            if (currentEffect != null) {
                int currentStacks = currentEffect.getAmplifier() + 1;
                int newStacks = Math.min(currentStacks + 1, plague.max_stacks);
    
                ent.addStatusEffect(new StatusEffectInstance(plagueEffect, plague.duration_ticks, newStacks - 1));
            } else {
                ent.addStatusEffect(new StatusEffectInstance(plagueEffect, plague.duration_ticks, 0));
            }
        }
    }
}
