package elocindev.deathknights.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;

public class EffectUtils {
    public static boolean hasStatusEffect(LivingEntity entity, StatusEffect effect) {
        //? if 1.20.1 {
        /*return entity.hasStatusEffect(effect);
        *///?} else {
        return entity.hasStatusEffect(RegistryEntry.of(effect));
        //? }
    }

    public static StatusEffectInstance getStatusEffect(LivingEntity entity, StatusEffect effect) {
        //? if 1.20.1 {
        /*return entity.getStatusEffect(effect);
        *///?} else {
        return entity.getStatusEffect(RegistryEntry.of(effect));
        //? }
    }

    public static StatusEffectInstance getStatusEffect(LivingEntity entity, RegistryEntry<StatusEffect> effect) {
        return entity.getStatusEffect(effect);
    }

    public static
    //? if 1.20.1 { 
    /*StatusEffect
    *///?} else {
    RegistryEntry<StatusEffect>
    //? }
    create(StatusEffect effect) {
        //? if 1.20.1 {
        /*return effect;
        *///?} else {
        return RegistryEntry.of(effect);
        //? }
    }
}
