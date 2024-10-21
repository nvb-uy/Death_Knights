package elocindev.deathknights.spells.blood;

import net.spell_engine.api.event.CombatEvents;
import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.spells.blood.MarrowrendConfig;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class MarrowrendHandler {
    private static final MarrowrendConfig CONFIG = Configs.Spells.Blood.MARROWREND;

    public static void register() {
        CombatEvents.SPELL_CAST.register(
            args -> {
                PlayerEntity caster = args.caster();
                if (caster == null) return;
                
                if (args.spell().id().toString().equals("death_knights:marrowrend")) {
                    applyEffectToCaster(caster);
                }
            }
        );
    }

    private static void applyEffectToCaster(PlayerEntity caster) {
        Identifier effectId = new Identifier(CONFIG.effect_to_apply);
        StatusEffect effect = Registries.STATUS_EFFECT.get(effectId);

        if (effect == null) {
            effect = StatusEffects.RESISTANCE;
        }

        StatusEffectInstance currentEffect = caster.getStatusEffect(effect);
        int currentAmplifier = currentEffect != null ? currentEffect.getAmplifier() : -1;
        int newAmplifier = Math.min(currentAmplifier + CONFIG.stack_amount, CONFIG.max_stacks - 1);

        StatusEffectInstance effectInstance = new StatusEffectInstance(
            effect,
            CONFIG.effect_duration,
            newAmplifier
        );

        caster.addStatusEffect(effectInstance);
    }
}