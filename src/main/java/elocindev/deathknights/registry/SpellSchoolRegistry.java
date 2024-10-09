package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import net.minecraft.util.Identifier;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;

public class SpellSchoolRegistry {
    
    public static final SpellSchool BLOOD = SpellSchools.register(
        SpellSchools.createMagic(
            new Identifier(DeathKnights.MODID, "blood"),
            0x800000,
            false,
            AttributeRegistry.BLOOD_POWER,
            StatusEffectRegistry.BLOOD_POWER
        )
    );

    public static final SpellSchool UNHOLY = SpellSchools.register(
        SpellSchools.createMagic(
            new Identifier(DeathKnights.MODID, "unholy"),
            0x2abf6d,
            false,
            AttributeRegistry.UNHOLY_POWER,
            StatusEffectRegistry.UNHOLY_POWER
        )
    );

    public static void register() {}
}
