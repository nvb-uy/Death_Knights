package elocindev.deathknights.mixin.entity.damage;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.deathknights.config.Configs;
import elocindev.deathknights.registry.SpellRegistry;
import elocindev.eternal_attributes.registry.AttributeRegistry;
import elocindev.eternal_attributes.registry.SpellSchoolRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.spell_power.api.SpellDamageSource;

// TODO: IMPLEMENT ON 1.21.1
//? if 1.20.1 {
@Mixin(value = LivingEntity.class, priority = 1)
public abstract class BloodThirstSelfHealMixin {
    
    @Inject(
        method = "damage",
        at = @At("RETURN")
    )
    private void death_knights$healAfterSuccessfulBloodDamage(DamageSource source, float amount,
            CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() || !(source.getAttacker() instanceof PlayerEntity player)) return;

        if (player.getWorld().isClient()) return;

        if (source.getType().equals(SpellDamageSource.player(SpellSchoolRegistry.BLOOD, player).getType())) {
            if (player.hasStatusEffect(SpellRegistry.BLOOD_THIRST)) {
                player.heal(calculateHealAmount(player) * player.getMaxHealth());
            }
        }

    }

    private float calculateHealAmount(PlayerEntity player) {
        if (Configs.Spells.Blood.BLOOD_THIRST.oncepercent_heal_amount <= 0) return 0;
        double bldpwr = player.getAttributeInstance(AttributeRegistry.BLOOD_POWER).getValue();

        return (0.01f * (float) (bldpwr / Configs.Spells.Blood.BLOOD_THIRST.oncepercent_heal_amount));
    }
}
//?}
