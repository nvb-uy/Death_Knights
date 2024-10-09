package elocindev.deathknights.mixin.client.animation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.llamalad7.mixinextras.sugar.Local;

import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.client.animation.DeathKnightsAnimations;
import elocindev.deathknights.compat.BetterCombatCompat;
import elocindev.deathknights.config.Configs;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.spell_engine.client.animation.AnimationRegistry;

@Mixin(value = AbstractClientPlayerEntity.class, priority = 1500)
public class AnimationSpeedPatcherMixin {
    @ModifyVariable(method = "playSpellAnimation", at = @At("HEAD"), ordinal = 0)
    private float death_knights$playSpellAnimation(float speed, @Local String animation) {
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) (Object) this;
        
        KeyframeAnimation kfAnim = (KeyframeAnimation) AnimationRegistry.animations.get(animation);
        if (animation == null) return speed;

        for (String anim : Configs.Client.CONFIG.patched_animations) {
            if (animation.equals(anim)) {
                return calculateSpeed(player, kfAnim, animation, speed);
            }
        }

        for (String anim : DeathKnightsAnimations.PATCHED_ANIMATIONS) {
            if (animation.equals(anim)) {
                return calculateSpeed(player, kfAnim, animation, speed);
            }
        }
        
        return speed;
    }

    private float calculateSpeed(AbstractClientPlayerEntity player, KeyframeAnimation kfAnim, String animation, float speed) {
        if (DeathKnights.BETTERCOMBAT_ENABLED && Configs.Client.CONFIG.enable_bettercombat_compatibility) {
            float mult = 1f;

            float syncedSpeed = (float) kfAnim.endTick / (kfAnim.getLength());
            float upswingSpeed = syncedSpeed / BetterCombatCompat.getUpswing();

            float atkSpeed = (float) ((PlayerEntity) player).getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED);

            if (animation.contains("2h")) mult = 0.85f;

            return upswingSpeed + atkSpeed * mult;
        } else {
            return 3f;
        }
    }
}
