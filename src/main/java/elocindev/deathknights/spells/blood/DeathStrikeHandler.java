package elocindev.deathknights.spells.blood;

import net.spell_engine.api.event.CombatEvents;
import elocindev.deathknights.config.entries.spells.blood.DeathStrikeConfig;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class DeathStrikeHandler {
    public static void register() {
        CombatEvents.SPELL_CAST.register(
            args -> {
                PlayerEntity caster = args.caster();
                if (caster == null) return;

                if (args.spell().id().toString().equals("death_knights:death_strike")) {
                    applyHealingOrAbsorption(caster);
                }
            }
        );
    }

    private static void applyHealingOrAbsorption(PlayerEntity caster) {
        DeathStrikeConfig config = DeathStrikeConfig.INSTANCE;
        float maxHealth = caster.getMaxHealth();
        float healAmount = maxHealth * config.max_health_healing;

        if (caster.getHealth() < maxHealth) {
            caster.heal(healAmount);
        } else if (config.apply_absorption_if_full) {
            int absorptionAmount = Math.max(4, Math.round(healAmount));
            StatusEffectInstance currentEffect = caster.getStatusEffect(StatusEffects.ABSORPTION);
            int currentAmplifier = currentEffect != null ? currentEffect.getAmplifier() : -1;
            int newAmplifier = (absorptionAmount / 4) - 1;

            if (currentEffect == null || currentAmplifier < newAmplifier) {
                caster.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, config.absorption_duration, newAmplifier, false, false, true));
            } else {
                caster.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, config.absorption_duration, currentAmplifier, false, false, true));
            }
        }
    }
}
