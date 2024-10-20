package elocindev.deathknights.spells;

import elocindev.deathknights.spells.blood.BloodBoilHandler;
import elocindev.deathknights.spells.unholy.DeathCoilHandler;
import elocindev.deathknights.spells.unholy.DeathGripHandler;
import elocindev.deathknights.spells.unholy.EpidemicHandler;

public class SpellHandler {
    public static void registerSpells() {
        DeathCoilHandler.register();
        EpidemicHandler.register();
        DeathGripHandler.register();

        BloodBoilHandler.register();
    }
}
