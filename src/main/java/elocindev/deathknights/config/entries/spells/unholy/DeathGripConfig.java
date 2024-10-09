package elocindev.deathknights.config.entries.spells.unholy;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

import java.util.List;

public class DeathGripConfig {
    @NecConfig
    public static DeathGripConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("death_grip.json5", "spells/unholy");
    }

    @Comment("The speed at which the target is pulled towards the caster.")
    public double pull_speed = 1.8;

    @Comment("A list of entities' ids that cannot be gripped.")
    public List<String> entity_blacklist = List.of("minecraft:warden", "minecraft:ender_dragon", "minecraft:wither");

    @Comment("A multiplier of health to not grip the target. For example, at 3.0x, if the target has triple the health of the caster, the target will not be gripped.")
    public double health_threshold = 3.0;

    @Comment("A list of effects to apply to the target after it has been gripped. Can be left empty to disable this behavior.")
    public List<EffectHolder> effects =
        List.of(
                new EffectHolder("minecraft:slowness", 80, 2)
        );

    @Comment("The maximum time in ticks that the pull effect can last.")
    public double max_pull_time = 100;

    @Comment("The minimum distance the target must be from the caster for the spell to know the target was gripped.")
    @Comment("If after max_pull_time passes and the target hasn't gotten this close to the caster, the target will get teleported as a last resort.")
    public double min_distance = 1.0;

    public class EffectHolder {
        public String effect_id;
        public double duration;
        public double amplifier;

        public EffectHolder(String effect_id, double duration, double amplifier) {
            this.effect_id = effect_id;
            this.duration = duration;
            this.amplifier = amplifier;
        }
    }
}