package elocindev.deathknights.compat;

//? if 1.20.1 {
/*import net.bettercombat.BetterCombat;
*///?} else {
import net.bettercombat.BetterCombatMod;
//?}

public class BetterCombatCompat {
    public static float getUpswing() {
        return
        //? if 1.20.1 {
        /*BetterCombat
        *///?} else {
        BetterCombatMod
        //?}
        .config.getUpswingMultiplier();
    }
}
