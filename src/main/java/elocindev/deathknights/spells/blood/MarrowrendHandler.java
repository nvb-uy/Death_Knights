package elocindev.deathknights.spells.blood;

import net.spell_engine.api.event.CombatEvents;
import elocindev.deathknights.config.entries.spells.blood.MarrowrendConfig;
import elocindev.deathknights.util.EffectUtils;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier; import elocindev.necronomicon.api.ResourceIdentifier;

//? if 1.21.1 {
/*import net.minecraft.registry.entry.RegistryEntry;
*///?}

public class MarrowrendHandler {
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
        MarrowrendConfig config = MarrowrendConfig.INSTANCE;
        Identifier effectId = ResourceIdentifier.get(config.effect_to_apply);
        //? if 1.20.1 {
        StatusEffect effect = Registries.STATUS_EFFECT.get(effectId);
        //?} else {
        /*RegistryEntry<StatusEffect> effect = RegistryEntry.of(Registries.STATUS_EFFECT.get(effectId));
        *///?}

        if (effect == null) {
            effect = StatusEffects.RESISTANCE;
        }

        StatusEffectInstance currentEffect = EffectUtils.getStatusEffect(caster, effect);
        int currentAmplifier = currentEffect != null ? currentEffect.getAmplifier() : -1;
        int newAmplifier = Math.min(currentAmplifier + config.stack_amount, config.max_stacks - 1);

        StatusEffectInstance effectInstance = new StatusEffectInstance(
            effect,
            config.effect_duration,
            newAmplifier
        );

        caster.addStatusEffect(effectInstance);
    }
}
