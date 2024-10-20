package elocindev.deathknights.config.entries.spells.blood;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class DeathStrikeConfig {
    @NecConfig
    public static DeathStrikeConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("death_strike.json5", "spells/blood");
    }

    @Comment("The amount of health the death knight will heal when Death Strike is used.")
    @Comment("Default: 0.10, (10%) of the Death Knight's Max Health")
    public float max_health_healing = 0.10f;

    @Comment("If the death knight's health is full, should the spell apply an absorption effect instead?")
    @Comment("Default: true")
    public boolean apply_absorption_if_full = true;

    @Comment("The duration of the absorption effect in ticks. 20 ticks is 1 second.")
    @Comment("Default: 160 ticks (8 seconds)")
    public int absorption_duration = 160;
}
