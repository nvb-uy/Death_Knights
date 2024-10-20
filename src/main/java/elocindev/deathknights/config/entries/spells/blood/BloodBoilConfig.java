package elocindev.deathknights.config.entries.spells.blood;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class BloodBoilConfig {
    @NecConfig
    public static BloodBoilConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("blood_boil.json5", "spells/blood");
    }

    @Comment("How frequently the spell damages victims in ticks. 20 ticks is 1 second.")
    @Comment("Default: 40 ticks")
    public int tick_rate = 40;
    @Comment("The maximum stacks of Blood Plague that Blood Boil will apply.")
    @Comment("Default: 2 stacks")
    public int max_stacks = 2;
    @Comment("Whether or not the duration of Blood Plague should be reset if the max stacks are reached.")
    @Comment("Default: true")
    public boolean reset_duration_if_max_stacks = true;
    @Comment("The radius the death knight has to be for the victim to receive damage when it ticks.")
    @Comment("If there are multiple blood death knights within the radius, the victim will only take damage from the closest one.")
    @Comment("Default: 5 blocks")
    public double radius = 5;
    @Comment("The duration of the Blood Plague debuff in ticks. 20 ticks is 1 second.")
    @Comment("Default: 600 ticks (30 seconds)")
    public int duration_ticks = 600;
    @Comment("The damage scaling of the spell. This multiplies the base blood power of the caster to output damage.")
    @Comment("Default: 0.1x")
    public float damage_blood_scaling = 0.10f;
}
