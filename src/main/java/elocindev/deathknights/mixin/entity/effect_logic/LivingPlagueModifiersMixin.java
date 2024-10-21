package elocindev.deathknights.mixin.entity.effect_logic;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.deathknights.registry.SpellRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

@Mixin(value = LivingEntity.class, priority = 1000)
public abstract class LivingPlagueModifiersMixin {
    
    @Shadow protected float lastDamageTaken;
    @Shadow private long lastDamageTime;
    @Shadow public abstract float getMaxHealth();
    @Shadow @Nullable public abstract LivingEntity getAttacker();
    @Shadow public abstract float getHealth();
    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Inject(method = "modifyAppliedDamage", at = @At("RETURN"), cancellable = true)
    protected void death_knights$modifyAppliedDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        LivingEntity attacker = entity.getAttacker();

        float newAmount = amount;

        if (attacker == null) return;

        if (attacker.hasStatusEffect(SpellRegistry.ATROCIOUS_PLAGUE)) {
            newAmount = amount * (1.0f - (0.10f * (attacker.getStatusEffect(SpellRegistry.ATROCIOUS_PLAGUE).getAmplifier() + 1)));
        }

        cir.setReturnValue(newAmount);;
    }

    @Inject(method = "heal", at = @At("RETURN"), cancellable = true)
    protected void death_knights$heal(float amount, CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        float newAmount = amount;

        if (entity.hasStatusEffect(SpellRegistry.GREVIOUS_PLAGUE)) {
            newAmount = amount * (1.0f - (0.10f * (entity.getStatusEffect(SpellRegistry.GREVIOUS_PLAGUE).getAmplifier() + 1)));
        }

        cir.setReturnValue(newAmount);;
    }
}
