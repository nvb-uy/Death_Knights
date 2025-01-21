package elocindev.deathknights.config.entries.spells.blood;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class BloodThirstConfig {
    @NecConfig
    public static BloodThirstConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("blood_thirst.json5", "spells/blood/passive");
    }

    @Comment("Defines how much blood power is needed to heal 1% of the maximum health of the player.")
    @Comment("Default: 2.5 Blood Power = 1% max health healing")
    public float oncepercent_heal_amount = 2.5f;
}
