package elocindev.deathknights.config.entries.enchants;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class DecayingEnchantConfig {
    @NecConfig
    public static DecayingEnchantConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("decaying.json5", "enchants");
    }

    @Comment("Decaying Enchantment.")
    @Comment("This enchantment increases the damage dealt by Unholy and Blood spells.")
    @Comment(" ")
    @Comment("Whether this enchantment should be enabled or not.")
    public boolean enabled = true;

    @Comment("Maximum level of the enchantment.")
    public int max_level = 5;

    @Comment("The minimum cost of the enchantment; at level 1.")
    public int min_cost = 10;

    @Comment("The additional cost per level, after level 1.")
    public int step_cost = 9;

    @Comment("The bonus damage per level.")
    public float bonus_per_level = 0.03f;

}
