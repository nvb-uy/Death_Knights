package elocindev.deathknights.mixin.entity.debuff_logic;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

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

    @ModifyVariable(method = "modifyAppliedDamage", at = @At("RETURN"), ordinal = 0)
    private float death_knights$applyAtrociousPlagueModifier(float amount, DamageSource source) {
        LivingEntity entity = (LivingEntity) (Object) this;
        LivingEntity attacker = entity.getAttacker();

        float newAmount = amount;

        if (attacker == null) return newAmount;

        if (attacker.hasStatusEffect(SpellRegistry.ATROCIOUS_PLAGUE)) {
            newAmount = amount * (1.0f - (0.10f * (attacker.getStatusEffect(SpellRegistry.ATROCIOUS_PLAGUE).getAmplifier() + 1)));
        }

        return newAmount;
    }

    @ModifyVariable(method = "heal", at = @At("HEAD"), ordinal = 0)
    private float death_knights$applyGreviousPlagueModifier(float amount) {
        LivingEntity entity = (LivingEntity) (Object) this;

        float newAmount = amount;

        if (entity.hasStatusEffect(SpellRegistry.GREVIOUS_PLAGUE)) {
            newAmount = amount * (1.0f - (0.10f * (entity.getStatusEffect(SpellRegistry.GREVIOUS_PLAGUE).getAmplifier() + 1)));
        }

        return newAmount;
    }
}
