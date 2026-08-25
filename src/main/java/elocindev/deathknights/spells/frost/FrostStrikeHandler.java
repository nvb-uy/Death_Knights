package elocindev.deathknights.spells.frost;

import elocindev.deathknights.config.entries.spells.frost.FrostStrikeConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.spell_engine.api.event.CombatEvents;
import net.spell_engine.particle.Particles;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellDamageSource;
import net.spell_power.api.SpellPower;
import net.spell_power.api.SpellSchools;

public final class FrostStrikeHandler {
    private static final String SPELL_ID = "death_knights:frost_strike";

    private FrostStrikeHandler() {
    }

    public static void register() {
        CombatEvents.SPELL_CAST.register(args -> {
            PlayerEntity caster = args.caster();
            FrostStrikeConfig config = FrostStrikeConfig.INSTANCE;
            if (caster == null || config == null || caster.getWorld().isClient()
                    || !SPELL_ID.equals(args.spell().id().toString())) {
                return;
            }

            SpellPower.Result spellPower = SpellPower.getSpellPower(SpellSchools.FROST, caster);
            for (Entity target : args.targets()) {
                if (target instanceof LivingEntity livingTarget && livingTarget.isFrozen()) {
                    detonate(caster, livingTarget, spellPower, config);
                }
            }
        });
    }

    private static void detonate(PlayerEntity caster, LivingEntity frozenTarget,
            SpellPower.Result spellPower, FrostStrikeConfig config) {
        frozenTarget.setFrozenTicks(0);

        for (LivingEntity nearby : caster.getWorld().getEntitiesByClass(LivingEntity.class,
                frozenTarget.getBoundingBox().expand(config.icicles_radius),
                entity -> entity != frozenTarget && TargetHelper.allowedToHurt(caster, entity))) {
            float damage = (float) (spellPower.baseValue() * config.frost_scaling_icicles);
            if (caster.getRandom().nextDouble() < spellPower.criticalChance()) {
                damage *= 2;
            }

            nearby.damage(SpellDamageSource.create(SpellSchools.FROST, caster), damage);
            caster.getWorld().addParticle(Particles.frost_hit.particleType,
                    nearby.getX(), nearby.getY() + 1, nearby.getZ(), 0, -0.1, 0);
        }
    }
}
