package elocindev.deathknights.spells.unholy;

import java.util.List;

import elocindev.deathknights.config.entries.spells.unholy.EpidemicConfig;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig.PlagueProperty;
import elocindev.deathknights.registry.SpellSchoolRegistry;
import elocindev.deathknights.util.EffectUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import elocindev.necronomicon.api.ResourceIdentifier;
import net.spell_engine.api.event.CombatEvents;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellDamageSource;
import net.spell_power.api.SpellPower;

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
                            StatusEffect plagueEffect = Registries.STATUS_EFFECT.get(ResourceIdentifier.get(plague.effect_id));
                            if (plagueEffect != null && EffectUtils.hasStatusEffect(livingTarget, plagueEffect)) {
                                activePlague = plague;
                                activeEffect = plagueEffect;
                                break;
                            }
                        }

                        if (activePlague != null && activeEffect != null) {
                            StatusEffectInstance plagueInstance = EffectUtils.getStatusEffect(livingTarget, activeEffect);
                            if (plagueInstance != null) {
                                int currentStacks = plagueInstance.getAmplifier() + 1;
                                int stacksToExplode = Math.min(currentStacks, CONFIG.plague_stacks);
                                int remainingStacks = currentStacks - stacksToExplode;

                                float damagePerStack = (float) (SpellPower
                                        .getSpellPower(SpellSchoolRegistry.UNHOLY, caster).baseValue()
                                        * CONFIG.unholy_coefficent);
                                livingTarget.damage(SpellDamageSource.create(SpellSchoolRegistry.UNHOLY, caster), damagePerStack * stacksToExplode);

                                if (remainingStacks > 0) {
                                    livingTarget.addStatusEffect(new StatusEffectInstance(EffectUtils.create(activeEffect), activePlague.duration_ticks, remainingStacks - 1));
                                } else {
                                    livingTarget.removeStatusEffect(EffectUtils.create(activeEffect));
                                }

                                List<LivingEntity> nearbyEntities = livingTarget.getWorld().getEntitiesByClass(LivingEntity.class, livingTarget.getBoundingBox().expand(CONFIG.epidemic_radius), e -> e != livingTarget);
                                
                                for (LivingEntity ent : nearbyEntities) {
                                    StatusEffectInstance nearbyPlagueInstance = ent.getStatusEffect(EffectUtils.create(activeEffect));
                                    if (ent.equals(caster) || ent instanceof PlayerEntity playerVictim && (playerVictim.isCreative() || playerVictim.isSpectator() || !TargetHelper.allowedToHurt(caster, playerVictim))) continue;
            
                                    
                                    if (nearbyPlagueInstance != null) {
                                        int nearbyStacks = nearbyPlagueInstance.getAmplifier() + 1;
                                        int nearbyStacksToExplode = Math.min(nearbyStacks, CONFIG.plague_stacks);
                                        int nearbyRemainingStacks = nearbyStacks - nearbyStacksToExplode;

                                        ent.damage(SpellDamageSource.create(SpellSchoolRegistry.UNHOLY, caster), damagePerStack * nearbyStacksToExplode);

                                        if (nearbyRemainingStacks > 0) {
                                            ent.addStatusEffect(new StatusEffectInstance(EffectUtils.create(activeEffect), activePlague.duration_ticks, nearbyRemainingStacks - 1));
                                        } else {
                                            ent.removeStatusEffect(EffectUtils.create(activeEffect));
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
