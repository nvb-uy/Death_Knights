package elocindev.deathknights.mixin.client.animation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.sugar.Local;

import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.config.Configs;
import net.bettercombat.BetterCombat;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.spell_engine.client.animation.AnimationRegistry;

@Mixin(value = AbstractClientPlayerEntity.class, priority = 1500)
public class AnimationSpeedPatcherMixin {
    @TargetHandler(
        mixin = "net.spell_engine.mixin.client.AbstractClientPlayerEntityMixin", 
        name = "playSpellAnimation"
    )
    @ModifyVariable(method = "@MixinSquared:Handler", at = @At("HEAD"), ordinal = 0)
    private float death_knights$playSpellAnimation(float speed, @Local String animation) {
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) (Object) this;
        
        for (String anim : Configs.Client.CONFIG.patched_animations) {
            if (animation != null && animation.equals(anim)) {
                if (DeathKnights.BETTERCOMBAT_ENABLED && Configs.Client.CONFIG.enable_bettercombat_compatibility) {
                    KeyframeAnimation kfAnim = (KeyframeAnimation)AnimationRegistry.animations.get(animation);
    
                    float syncedSpeed = (float) kfAnim.endTick / (kfAnim.getLength());
                    float upswingSpeed = syncedSpeed / BetterCombat.config.getUpswingMultiplier();
    
                    float atkSpeed = (float) ((PlayerEntity) player).getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED);

                    return upswingSpeed + atkSpeed;
                }
                else return 3f;
            }
        }
        
        return speed;
    }
}
