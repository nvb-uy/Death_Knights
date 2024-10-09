package elocindev.deathknights.spells.unholy;

import elocindev.deathknights.api.core.SpellEffect;
import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig.PlagueProperty;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.List;

public class FesteringStrike extends SpellEffect {
    public static PlaguesConfig CONFIG = Configs.Spells.Unholy.PLAGUES;
    private static final Random RANDOM = Random.create();

    public FesteringStrike() {
        super(StatusEffectCategory.HARMFUL, 0x6fa64c);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);

        List<PlagueProperty> plagues = CONFIG.plagues;

        PlagueProperty activePlague = null;
        StatusEffect activeEffect = null;

        for (PlagueProperty plague : plagues) {
            StatusEffect plagueEffect = Registries.STATUS_EFFECT.get(new Identifier(plague.effect_id));
            if (plagueEffect != null && entity.hasStatusEffect(plagueEffect)) {
                activePlague = plague;
                activeEffect = plagueEffect;
                break;
            }
        }

        if (activePlague != null && activeEffect != null) {
            StatusEffectInstance currentInstance = entity.getStatusEffect(activeEffect);

            if (currentInstance != null) {
                int currentStacks = currentInstance.getAmplifier() + 1;
                int newStacks = Math.min(currentStacks + 2, activePlague.max_stacks);
                entity.addStatusEffect(new StatusEffectInstance(activeEffect, activePlague.duration_ticks, newStacks - 1));
            }
        } else {
            PlagueProperty randomPlague = getWeightedPlague(plagues);
            StatusEffect randomEffect = Registries.STATUS_EFFECT.get(new Identifier(randomPlague.effect_id));
            
            if (randomEffect != null) {
                entity.addStatusEffect(new StatusEffectInstance(randomEffect, randomPlague.duration_ticks, 1)); // 2 stacks = amplifier 1
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }

    private PlagueProperty getWeightedPlague(List<PlagueProperty> plagues) {
        int totalWeight = plagues.stream().mapToInt(plague -> plague.weight).sum();
        int randomValue = RANDOM.nextInt(totalWeight);

        int cumulativeWeight = 0;

        for (PlagueProperty plague : plagues) {
            cumulativeWeight += plague.weight;

            if (randomValue < cumulativeWeight) {
                return plague;
            }
        }

        return plagues.get(0);
    }
}