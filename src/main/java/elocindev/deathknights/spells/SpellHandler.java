package elocindev.deathknights.spells;

import elocindev.deathknights.spells.blood.BloodBoilHandler;
import elocindev.deathknights.spells.blood.DarkCommandHandler;
import elocindev.deathknights.spells.blood.DeathStrikeHandler;
import elocindev.deathknights.spells.blood.MarrowrendHandler;
import elocindev.deathknights.spells.unholy.DeathCoilHandler;
import elocindev.deathknights.spells.unholy.DeathGripHandler;
import elocindev.deathknights.spells.unholy.EpidemicHandler;

public class SpellHandler {
    public static void registerSpells() {
        GlobalCooldown.register();

        DeathCoilHandler.register();
        EpidemicHandler.register();
        DeathGripHandler.register();

        DeathStrikeHandler.register();
        MarrowrendHandler.register();
        BloodBoilHandler.register();
        DarkCommandHandler.register();
    }
}
