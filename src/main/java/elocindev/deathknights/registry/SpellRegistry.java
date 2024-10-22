package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.api.core.SpellEffect;
import elocindev.deathknights.spells.blood.buff.BloodThirst;
import elocindev.deathknights.spells.blood.buff.Marrowshield;
import elocindev.deathknights.spells.blood.debuff.BloodPlague;
import elocindev.deathknights.spells.blood.debuff.Enraged;
import elocindev.deathknights.spells.frost.BreathOfAgony;
import elocindev.deathknights.spells.frost.FrostStrike;
import elocindev.deathknights.spells.frost.Obliterate;
import elocindev.deathknights.spells.frost.RemorselessWinter;
import elocindev.deathknights.spells.unholy.FesteringStrike;
import elocindev.deathknights.spells.unholy.debuff.AtrociousPlague;
import elocindev.deathknights.spells.unholy.debuff.GreviousPlague;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SpellRegistry {
    // FROST
    public static final Obliterate OBLITERATED_AGONY = reg(new Obliterate(), "obliterate");
    public static final FrostStrike FROST_STRIKE = reg(new FrostStrike(), "frost_strike");
    public static final RemorselessWinter REMORSELESS_WINTER = reg(new RemorselessWinter(), "remorseless_winter");
    public static final BreathOfAgony BREATH_OF_AGONY = reg(new BreathOfAgony(), "breath_of_agony");

    // UNHOLY
    public static final FesteringStrike FESTERING_WOUND = reg(new FesteringStrike(), "festering_wound");

    // BLOOD
    public static final Marrowshield MARROWSHIELD = reg(new Marrowshield(), "marrowshield");
    public static final Enraged ENRAGED = reg(new Enraged(), "enraged");

    // Passive effects / debuffs
    public static final AtrociousPlague ATROCIOUS_PLAGUE = reg(new AtrociousPlague(), "atrocious_plague");
    public static final GreviousPlague GREVIOUS_PLAGUE = reg(new GreviousPlague(), "grevious_plague");
    // Blood passives
    public static final BloodPlague BLOOD_PLAGUE = reg(new BloodPlague(), "blood_plague");
    public static final BloodThirst BLOOD_THIRST = reg(new BloodThirst(), "blood_thirst");
    

    public static void register() {}

    public static <S extends SpellEffect> S reg(S spell, String id) {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(DeathKnights.MODID, id), (SpellEffect) spell);
        
        return spell;
    }
}
