package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.spell_power.internals.SpellStatusEffect;

public class StatusEffectRegistry {
    public static final SpellStatusEffect BLOOD_POWER = new SpellStatusEffect(StatusEffectCategory.BENEFICIAL, 0x800000);
    public static final SpellStatusEffect UNHOLY_POWER = new SpellStatusEffect(StatusEffectCategory.BENEFICIAL, 0x2abf6d);

    public static void register() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(DeathKnights.MODID, "blood_power"), BLOOD_POWER);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(DeathKnights.MODID, "unholy_power"), UNHOLY_POWER);
    }
}
