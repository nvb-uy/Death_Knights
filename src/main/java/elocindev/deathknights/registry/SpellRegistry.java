package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.api.core.SpellEffect;
import elocindev.deathknights.spells.frost.BreathOfAgony;
import elocindev.deathknights.spells.frost.FrostStrike;
import elocindev.deathknights.spells.frost.Obliterate;
import elocindev.deathknights.spells.frost.RemorselessWinter;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SpellRegistry {
    public static final Obliterate OBLITERATED_AGONY = reg(new Obliterate(), "obliterate");
    public static final FrostStrike FROST_STRIKE = reg(new FrostStrike(), "frost_strike");
    public static final RemorselessWinter REMORSELESS_WINTER = reg(new RemorselessWinter(), "remorseless_winter");
    public static final BreathOfAgony BREATH_OF_AGONY = reg(new BreathOfAgony(), "breath_of_agony");

    public static final Obliterate FESTERING_STRIKE = reg(new Obliterate(), "festering_strike");


    public static void register() {}

    public static <S extends SpellEffect> S reg(S spell, String id) {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(DeathKnights.MODID, id), (SpellEffect) spell);
        
        return spell;
    }
}
