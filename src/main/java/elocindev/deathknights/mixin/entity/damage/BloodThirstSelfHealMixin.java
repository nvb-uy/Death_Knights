package elocindev.deathknights.mixin.entity.damage;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import elocindev.deathknights.config.Configs;
import elocindev.deathknights.registry.SpellRegistry;
import elocindev.eternal_attributes.registry.AttributeRegistry;
import elocindev.eternal_attributes.registry.SpellSchoolRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.spell_power.api.SpellDamageSource;

@Mixin(value = LivingEntity.class, priority = 1)
public abstract class BloodThirstSelfHealMixin {
    
    @ModifyVariable(
        method = "damage",
        at = @At("HEAD"),
        argsOnly = true
    )
    private float death_knights$decayingDamageMult(float amount, DamageSource source) {
        if (source.getAttacker() == null || !(source.getAttacker() instanceof PlayerEntity player)) return amount;

        if (player.getWorld().isClient()) return amount;

        if (source.getType().equals(SpellDamageSource.player(SpellSchoolRegistry.BLOOD, player).getType())) {
            if (player.hasStatusEffect(SpellRegistry.BLOOD_THIRST)) {
                player.heal(calculateHealAmount(player) * player.getMaxHealth());
            }
        }

        return amount;
    }

    private float calculateHealAmount(PlayerEntity player) {
        double bldpwr = player.getAttributeInstance(AttributeRegistry.BLOOD_POWER).getValue();

        return (0.01f * (float) (bldpwr / Configs.Spells.Blood.BLOOD_THIRST.oncepercent_heal_amount));
    }
}