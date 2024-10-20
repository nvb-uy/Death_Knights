package elocindev.deathknights.spells.blood;

import net.spell_engine.api.event.CombatEvents;

import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.spells.blood.BloodBoilConfig;
import elocindev.deathknights.registry.SpellRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;

public class BloodBoilHandler {
    private static final BloodBoilConfig CONFIG = Configs.Spells.Blood.BLOOD_BOIL;

    public static void register() {
        CombatEvents.SPELL_CAST.register(
            args -> {
                PlayerEntity caster = args.caster();
                if (caster == null) return;

                if (args.spell().id().toString().equals("death_knights:blood_boil")) {
                    for (Entity target : args.targets()) {
                        if (target instanceof LivingEntity livingTarget) {
                            applyDebuff(caster, livingTarget);
                        }
                    }
                }
            }
        );
    }

    private static void applyDebuff(PlayerEntity caster, LivingEntity target) {
        StatusEffectInstance currentEffect = target.getStatusEffect(SpellRegistry.BLOOD_PLAGUE);
        int currentAmplifier = currentEffect != null ? currentEffect.getAmplifier() : -1;

        if (currentAmplifier < CONFIG.max_stacks - 1) {
            target.addStatusEffect(new StatusEffectInstance(SpellRegistry.BLOOD_PLAGUE, currentEffect != null ? currentEffect.getDuration() : CONFIG.duration_ticks, currentAmplifier + 1));
        } else if (CONFIG.reset_duration_if_max_stacks) {
            target.addStatusEffect(new StatusEffectInstance(SpellRegistry.BLOOD_PLAGUE, CONFIG.duration_ticks, currentAmplifier));
        }
    }
}