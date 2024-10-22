package elocindev.deathknights.spells.blood;

import net.spell_engine.api.event.CombatEvents;
import elocindev.deathknights.registry.SpellRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

public class DarkCommandHandler {
    public static void register() {
        CombatEvents.SPELL_CAST.register(
            args -> {
                PlayerEntity caster = args.caster();
                if (caster == null) return;
                
                if (args.spell().id().toString().equals("death_knights:dark_command")) {
                    for (var target : args.targets()) {
                        if (target instanceof LivingEntity ent) {
                            ent.setAttacking(caster);
                            ent.addStatusEffect(new StatusEffectInstance(SpellRegistry.ENRAGED, 100, 0));
                        } if (target instanceof MobEntity mob) {
                            mob.setTarget(caster);
                        }
                    }
                }
            }
        );
    }
}