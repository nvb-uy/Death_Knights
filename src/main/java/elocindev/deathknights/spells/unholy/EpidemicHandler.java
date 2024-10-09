package elocindev.deathknights.spells.unholy;

import java.util.List;

import elocindev.deathknights.config.entries.spells.unholy.EpidemicConfig;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig.PlagueProperty;
import elocindev.deathknights.registry.SpellSchoolRegistry;
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

public class EpidemicHandler {
    private static PlaguesConfig PLAGUE_CONFIG = PlaguesConfig.INSTANCE;
    private static EpidemicConfig CONFIG = EpidemicConfig.INSTANCE;

    public static void register() {
        CombatEvents.SPELL_CAST.register(
            args -> {
                PlayerEntity caster = args.caster();
                if (caster == null) return;

                if (args.spell().id().toString().equals("death_knights:epidemic")) {
                    List<Entity> targets = args.targets();
                    
                    for (Entity target : targets) {
                        if (!(target instanceof LivingEntity)) continue;
                        LivingEntity livingTarget = (LivingEntity) target;

                        PlagueProperty activePlague = null;
                        StatusEffect activeEffect = null;

                        for (PlagueProperty plague : PLAGUE_CONFIG.plagues) {
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

                                List<LivingEntity> nearbyEntities = livingTarget.getWorld().getEntitiesByClass(LivingEntity.class, livingTarget.getBoundingBox().expand(CONFIG.epidemic_radius), e -> e != livingTarget);
                                
                                for (LivingEntity ent : nearbyEntities) {
                                    StatusEffectInstance nearbyPlagueInstance = ent.getStatusEffect(activeEffect);
                                    if (ent.equals(caster) || ent instanceof PlayerEntity playerVictim && (playerVictim.isCreative() || playerVictim.isSpectator() || !TargetHelper.allowedToHurt(caster, playerVictim))) continue;
            
                                    
                                    if (nearbyPlagueInstance != null) {
                                        int nearbyStacks = nearbyPlagueInstance.getAmplifier() + 1;
                                        int nearbyStacksToExplode = Math.min(nearbyStacks, CONFIG.plague_stacks);
                                        int nearbyRemainingStacks = nearbyStacks - nearbyStacksToExplode;

                                        ent.damage(SpellDamageSource.create(SpellSchoolRegistry.UNHOLY, caster), damagePerStack * nearbyStacksToExplode);

                                        if (nearbyRemainingStacks > 0) {
                                            ent.addStatusEffect(new StatusEffectInstance(activeEffect, activePlague.duration_ticks, nearbyRemainingStacks - 1));
                                        } else {
                                            ent.removeStatusEffect(activeEffect);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        );
    }
}
