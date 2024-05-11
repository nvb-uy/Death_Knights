package elocindev.deathknights.config.entries.spells.frost;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class ObliterateConfig {
    @NecConfig
    public static ObliterateConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("obliterate.json5", "spells/frost");
    }

    @Comment("Defines the extension of the Remorseless Winter spell in ticks. 20 ticks is 1 second.")
    @Comment("Default: 20 ticks")
    public int remorseless_winter_extension_ticks = 20;
    @Comment("Defines the extension of the Breath of Agony spell in ticks. 20 ticks is 1 second.")
    @Comment("Default: 40 ticks")
    public int breath_of_agony_extension_ticks = 40;
}