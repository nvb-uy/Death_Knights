package elocindev.deathknights.mixin.client.animation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.bawnorton.mixinsquared.TargetHandler;

import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.config.Configs;
import net.bettercombat.BetterCombat;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.spell_engine.client.animation.AnimationRegistry;
import net.spell_engine.internals.casting.SpellCasterEntity;

@Mixin(value = AbstractClientPlayerEntity.class, priority = 1500)
public class AnimationSpeedPatcherMixin {
    @TargetHandler(
        mixin = "net.spell_engine.mixin.client.AbstractClientPlayerEntityMixin", 
        name = "playSpellAnimation"
    )
    @ModifyVariable(method = "@MixinSquared:Handler", at = @At("HEAD"), ordinal = 0)
    private float death_knights$updateSpellCastAnimationsOnTick(float speed) {    
        PlayerEntity player = (PlayerEntity)(Object)this;

        var spellCaster = ((SpellCasterEntity) player);
        var spell = spellCaster.getCurrentSpell(); if (spell == null) return speed;
        
        if (shouldPatchAnimation(spell.release.animation))
            if (DeathKnights.BETTERCOMBAT_ENABLED) {
                KeyframeAnimation animation = (KeyframeAnimation)AnimationRegistry.animations.get(spell.release.animation);

                float syncedSpeed = (float) animation.endTick / (animation.getLength());
                float upswingSpeed = syncedSpeed / BetterCombat.config.getUpswingMultiplier();

                return upswingSpeed;
            }
            else 
                return 3f;
        
        return speed;
    }

    public boolean shouldPatchAnimation(String animationid) {
        for (String anim : Configs.Client.CONFIG.patched_animations) {
            if (animationid.equals(anim)) {
                return true;
            }
        }

        return false;
    }
}
