package elocindev.deathknights.config.entries.spells.unholy;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;


public class DeathCoilConfig {
    @NecConfig
    public static DeathCoilConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("death_coil.json5", "spells/unholy");
    }

    @Comment("The unholy power multiplier that Death Coil's plague explosion will deal, per stack.")
    public float unholy_coefficent = 0.35f;

    @Comment("The maxmimum plague stacks that Death Coil will explode.")
    public int plague_stacks = 4;

    @Comment("The radius of the plague spread once stacks explode.")
    public double spread_radius = 5.0;
}