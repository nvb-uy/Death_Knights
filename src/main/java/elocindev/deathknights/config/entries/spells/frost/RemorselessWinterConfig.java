package elocindev.deathknights.config.entries.spells.frost;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class RemorselessWinterConfig {
    @NecConfig
    public static RemorselessWinterConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("remorseless_winter.json5", "spells/frost");
    }

    @Comment("How frequently the spell damages entities in ticks. 20 ticks is 1 second.")
    @Comment("Default: 5 ticks")
    public int tick_rate = 5;
    @Comment("The radius of the spell. This applies for both the particles and damage.")
    @Comment("Default: 4 blocks")
    public double radius = 4;
    @Comment("The damage scaling of the spell. This multiplies the base frost power of the caster to output damage.")
    @Comment("Default: 0.5x")
    public float damage_frost_scaling = 0.25f;
    @Comment("The multiplier for critical damage. This is multiplied to the damage if the spell crits.")
    @Comment("Default: 2.0x")
    public float damage_critical_scaling = 2f;
    @Comment("The amount of ticks the victim gets frozen after being hit. 20 ticks is 1 second.")
    @Comment("Default: 20 ticks")
    public int frozen_ticks = 20;
    @Comment("Chance per tick to apply frozen to the target")
    @Comment("Default: 0.10 (10%)")
    public float frozen_chance = 0.10f;

}
