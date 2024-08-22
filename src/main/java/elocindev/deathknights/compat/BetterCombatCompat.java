package elocindev.deathknights.compat;

import net.bettercombat.BetterCombat;

public class BetterCombatCompat {
    public static float getUpswing() {
        return BetterCombat.config.getUpswingMultiplier();
    }
}
