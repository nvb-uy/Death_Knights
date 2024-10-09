package elocindev.deathknights.config.entries.spells.unholy;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

import java.util.List;

public class PlaguesConfig {
    @NecConfig
    public static PlaguesConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("plagues.json5", "spells/unholy");
    }

    @Comment("List of plagues that Unholy Death Knights can apply.")
    @Comment("Any effect from any mod can be a plague.")
    @Comment("The weight is used to determine the chance of applying the plague, the higher the weight, the higher the chance.")
    @Comment("The max stacks is the maximum amount of stacks the plague can have when applied by an ability.")
    @Comment("The duration is how long the plague lasts in ticks, applied stacks won't extend this duration.")
    public List<PlagueProperty> plagues = List.of(
        new PlagueProperty("death_knights:grevious_plague", 4, 400, 50),
        new PlagueProperty("death_knights:atrocious_plague", 4, 400, 50)
    );

    public class PlagueProperty {
        public PlagueProperty(String effect_id, int stacks, int duration, int weight) {
            this.effect_id = effect_id;
            this.max_stacks = stacks;
            this.duration_ticks = duration;
            this.weight = weight;
        }

        public String effect_id;
        public int max_stacks;
        public int duration_ticks;
        public int weight;
    }
}