package elocindev.deathknights.config.entries.spells.frost;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class FrostStrikeConfig {
    @NecConfig
    public static FrostStrikeConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("frost_strike.json5", "spells/frost");
    }

    @Comment("Radius of the icicles when the target hit is frozen.")
    @Comment("Default: 2 blocks")
    public float icicles_radius = 2.0f;
    @Comment("How much frost damage scaling should the icicles have.")
    @Comment("Default: 1.0x")
    public float frost_scaling_icicles = 1.0f;
}