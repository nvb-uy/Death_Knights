package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.api.core.SpellEffect;
import elocindev.deathknights.spells.frost.BreathOfAgony;
import elocindev.deathknights.spells.frost.Obliterate;
import elocindev.deathknights.spells.frost.RemorselessWinter;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SpellRegistry {
    public static final Obliterate OBLITERATED_AGONY = reg(new Obliterate(), "obliterate");
    public static final RemorselessWinter REMORSELESS_WINTER = reg(new RemorselessWinter(), "remorseless_winter");
    public static final BreathOfAgony BREATH_OF_AGONY = reg(new BreathOfAgony(), "breath_of_agony");

    public static void register() {}

    public static <T extends SpellEffect> T reg(T spell, String id) {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(DeathKnights.MODID, id), (SpellEffect) spell);
        
        return spell;
    }
}