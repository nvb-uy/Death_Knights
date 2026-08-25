package elocindev.deathknights.spells.unholy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import elocindev.deathknights.config.entries.spells.unholy.EpidemicConfig;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig.PlagueProperty;
import elocindev.deathknights.registry.SpellSchoolRegistry;
import elocindev.deathknights.util.EffectUtils;
import elocindev.necronomicon.api.ResourceIdentifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.spell_engine.api.event.CombatEvents;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellDamageSource;

public class EpidemicHandler {
    private static PlaguesConfig PLAGUE_CONFIG = PlaguesConfig.INSTANCE;
    private static EpidemicConfig CONFIG = EpidemicConfig.INSTANCE;

    private record ActivePlague(PlagueProperty property, StatusEffect effect, StatusEffectInstance instance) {
    }

    public static void register() {
        CombatEvents.SPELL_CAST.register(args -> {
            PlayerEntity caster = args.caster();
            if (caster == null || !args.spell().id().toString().equals("death_knights:epidemic")) return;

            Map<LivingEntity, ActivePlague> explosions = new LinkedHashMap<>();
            for (Entity target : args.targets()) {
                if (!(target instanceof LivingEntity livingTarget)) continue;

                ActivePlague active = findActivePlague(livingTarget);
                if (active == null) continue;
                explosions.putIfAbsent(livingTarget, active);

                List<LivingEntity> nearbyEntities = livingTarget.getWorld().getEntitiesByClass(
                        LivingEntity.class,
                        livingTarget.getBoundingBox().expand(CONFIG.epidemic_radius),
                        entity -> entity != livingTarget);

                for (LivingEntity nearby : nearbyEntities) {
                    if (nearby.equals(caster)
                            || nearby instanceof PlayerEntity playerVictim
                                    && (playerVictim.isCreative() || playerVictim.isSpectator()
                                            || !TargetHelper.allowedToHurt(caster, playerVictim))) {
                        continue;
                    }

                    StatusEffectInstance instance = EffectUtils.getStatusEffect(nearby, active.effect());
                    if (instance != null) {
                        explosions.putIfAbsent(nearby,
                                new ActivePlague(active.property(), active.effect(), instance));
                    }
                }
            }

            float damagePerStack = (float)
            //? if 1.20.1 {
            (caster.getAttributeValue(SpellSchoolRegistry.UNHOLY.attribute)
            //?} else {
            /*(caster.getAttributeValue(SpellSchoolRegistry.UNHOLY.attributeEntry)
            *///?}
            * CONFIG.unholy_coefficent);

            for (Map.Entry<LivingEntity, ActivePlague> entry : explosions.entrySet()) {
                explode(caster, entry.getKey(), entry.getValue(), damagePerStack);
            }
        });
    }

    private static ActivePlague findActivePlague(LivingEntity entity) {
        for (PlagueProperty plague : PLAGUE_CONFIG.plagues) {
            StatusEffect effect = Registries.STATUS_EFFECT.get(ResourceIdentifier.get(plague.effect_id));
            StatusEffectInstance instance = effect == null ? null : EffectUtils.getStatusEffect(entity, effect);
            if (instance != null) return new ActivePlague(plague, effect, instance);
        }
        return null;
    }

    private static void explode(PlayerEntity caster, LivingEntity target,
            ActivePlague active, float damagePerStack) {
        int currentStacks = active.instance().getAmplifier() + 1;
        int stacksToExplode = Math.min(currentStacks, CONFIG.plague_stacks);
        int remainingStacks = currentStacks - stacksToExplode;

        target.damage(SpellDamageSource.create(SpellSchoolRegistry.UNHOLY, caster),
                damagePerStack * stacksToExplode);

        if (remainingStacks > 0) {
            target.addStatusEffect(new StatusEffectInstance(EffectUtils.create(active.effect()),
                    active.instance().getDuration(), remainingStacks - 1));
        } else {
            target.removeStatusEffect(EffectUtils.create(active.effect()));
        }
    }
}
