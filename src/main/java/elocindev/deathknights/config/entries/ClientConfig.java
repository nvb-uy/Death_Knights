package elocindev.deathknights.config.entries;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class ClientConfig {
    @NecConfig
    public static ClientConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getFile("client.json5");
    }

    @Comment("If true, the player will stop attacking when casting an ability.")
    @Comment("This is recommended to make the ability's animation feel more natural.")
    public boolean should_stop_attacking_on_ability = true;
    @Comment("Enables clientside compatibility with better combat if the mod is loaded.")
    public boolean enable_bettercombat_compatibility = true;
    @Comment("Patches melee animations for Spell Engine.")
    @Comment("If you're making a compatible datapack and added custom animations, add them here if you have issues with their playback speed.")
    public String[] patched_animations = new String[] {
        "death_knights:obliterate",
        "death_knights:death_strike"
    };
}
