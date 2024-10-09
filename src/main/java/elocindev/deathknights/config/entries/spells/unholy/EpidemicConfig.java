package elocindev.deathknights.config.entries.spells.unholy;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class EpidemicConfig {
    @NecConfig
    public static EpidemicConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("death_coil.json5", "spells/unholy");
    }

    @Comment("The unholy power multiplier that Epidemic's plague explosion will deal, per stack.")
    public float unholy_coefficent = 0.35f;

    @Comment("The EXTRA radius of the Epidemic spread once stacks explode. This is a radius located at each target's location.")
    public double epidemic_radius = 5.0;

    @Comment("The maxmimum plague stacks that Epidemic will explode.")
    public int plague_stacks = 4;
}