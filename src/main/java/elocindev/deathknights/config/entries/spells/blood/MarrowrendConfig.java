package elocindev.deathknights.config.entries.spells.blood;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class MarrowrendConfig {
    @NecConfig
    public static MarrowrendConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("marrowrend.json5", "spells/blood");
    }

    @Comment("The effect to apply to the Death Knight when hitting an enemy.")
    @Comment("Default: minecraft:resistance")
    public String effect_to_apply = "minecraft:resistance";

    @Comment("The amount of the effect's stacks given when hitting an enemy.")
    @Comment("Default: 3 stacks (Resistance III)")
    public int stack_amount = 3;

    @Comment("The maximum amount of the effect that Marrowrend should stack up to.")
    @Comment("Default: 4 stacks")
    public int max_stacks = 4;

    @Comment("The duration of the resistance effect in ticks. 20 ticks is 1 second.")
    @Comment("Default: 200 ticks (10 seconds)")
    public int effect_duration = 200;

    @Comment("The chance to reduce the stack count by 1 when an enemy hits the Death Knight.")
    @Comment("Default: 1.00 (100% chance)")
    public float stack_reduction_chance = 1.00f;
}
