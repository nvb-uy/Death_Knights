package elocindev.deathknights.config;

import net.runes.tinyconfig.ConfigManager;
import net.spell_engine.api.item.ItemConfig;

public class Configs {
    public static ConfigManager<ItemConfig> itemConfig = new ConfigManager<ItemConfig>
            ("armor", ArmorConfig.INSTANCE)
            .builder()
            .setDirectory("death_knights")
            .sanitize(true)
            .build();
    

    public static void init() {

    }
}
