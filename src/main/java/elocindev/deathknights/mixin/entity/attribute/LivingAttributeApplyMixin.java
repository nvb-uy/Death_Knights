package elocindev.deathknights.mixin.entity.attribute;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.deathknights.registry.AttributeRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;

@Mixin(value = LivingEntity.class, priority = 1000)
public class LivingAttributeApplyMixin {
    @Inject(method = "createLivingAttributes", at = @At("RETURN"), cancellable = true) 
    private static void death_knights$registerLivingEntityAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        ((DefaultAttributeContainer.Builder)cir.getReturnValue())
            .add(AttributeRegistry.BLOOD_POWER)
            .add(AttributeRegistry.UNHOLY_POWER);
    }
}