package elocindev.deathknights.mixin.entity.effect_logic;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.spells.blood.MarrowrendConfig;
import elocindev.deathknights.registry.SpellRegistry;
import elocindev.deathknights.util.EffectUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier; import elocindev.necronomicon.api.ResourceIdentifier;

//? if 1.21.1 {
/*import net.minecraft.registry.entry.RegistryEntry;
*///?}

@Mixin(value = LivingEntity.class, priority = 1000)
public abstract class LivingEffectModifiersMixin {
    
    @Shadow protected float lastDamageTaken;
    @Shadow private long lastDamageTime;
    @Shadow public abstract float getMaxHealth();
    @Shadow @Nullable public abstract LivingEntity getAttacker();
    @Shadow public abstract float getHealth();
    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Inject(method = "damage", at = @At("RETURN"), cancellable = true)
    protected void death_knights(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        LivingEntity attacker = entity.getAttacker();

        MarrowrendConfig CONFIG = Configs.Spells.Blood.MARROWREND;

        if (attacker == null) return;

        Identifier effectId = ResourceIdentifier.get(CONFIG.effect_to_apply);
        //? if 1.20.1 {
        StatusEffect effect = Registries.STATUS_EFFECT.get(effectId);
        //?} else {
        /*RegistryEntry<StatusEffect> effect = RegistryEntry.of(Registries.STATUS_EFFECT.get(effectId));
        *///?}

        if (effect == null) {
            effect = StatusEffects.RESISTANCE;
        }

        if (attacker.hasStatusEffect(effect)) {
            StatusEffectInstance effectInstance = attacker.getStatusEffect(effect);

            if (effectInstance != null) {
                int currentAmplifier = effectInstance.getAmplifier();
                float chance = CONFIG.stack_reduction_chance;
                
                if (attacker.getWorld().getRandom().nextFloat() < chance) {
                    if (currentAmplifier > 0) {
                        attacker.addStatusEffect(new StatusEffectInstance(
                            effect,
                            CONFIG.effect_duration,
                            currentAmplifier - 1
                        ));
                    } else {
                        attacker.removeStatusEffect(effect);
                    }
                }
            }
        }
    }

    @Inject(method = "modifyAppliedDamage", at = @At("RETURN"), cancellable = true)
    protected void death_knights$modifyAppliedDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        float newAmount = cir.getReturnValue();

        if (!(source.getAttacker() instanceof LivingEntity attacker)) return;

        if (EffectUtils.hasStatusEffect(attacker, SpellRegistry.ATROCIOUS_PLAGUE)) {
            newAmount *= 1.0f - (0.10f * (EffectUtils.getStatusEffect(attacker, SpellRegistry.ATROCIOUS_PLAGUE).getAmplifier() + 1));
        }

        if (EffectUtils.hasStatusEffect(attacker, SpellRegistry.ENRAGED)) {
            newAmount *= 1f - (0.20f * (EffectUtils.getStatusEffect(attacker, SpellRegistry.ENRAGED).getAmplifier() + 1));   
        }

        cir.setReturnValue(newAmount);
    }

    @ModifyVariable(method = "heal", at = @At("HEAD"), argsOnly = true)
    private float death_knights$modifyHealAmount(float amount) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (EffectUtils.hasStatusEffect(entity, SpellRegistry.GREVIOUS_PLAGUE)) {
            amount = amount * (1.0f - (0.10f * (EffectUtils.getStatusEffect(entity, SpellRegistry.GREVIOUS_PLAGUE).getAmplifier() + 1)));
        }

        return amount;
    }
}
