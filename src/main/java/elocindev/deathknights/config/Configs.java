package elocindev.deathknights.config;

import elocindev.deathknights.config.entries.ArmorConfig;
import elocindev.deathknights.config.entries.ClientConfig;
import elocindev.deathknights.config.entries.spells.frost.BreathOfAgonyConfig;
import elocindev.deathknights.config.entries.spells.frost.ObliterateConfig;
import elocindev.deathknights.config.entries.spells.frost.RemorselessWinterConfig;
import net.spell_engine.api.item.ItemConfig;
import net.tinyconfig.ConfigManager;

public class Configs {   
    public class Client {
        public static ClientConfig                  CONFIG                =      ClientConfig.INSTANCE;
    }

    public class Spells {
        public class Frost {
            public static ObliterateConfig          OBLITERATE            =      ObliterateConfig.INSTANCE;
            public static RemorselessWinterConfig   REMORSELESS_WINTER    =      RemorselessWinterConfig.INSTANCE;
            public static BreathOfAgonyConfig       BREATH_OF_AGONY       =      BreathOfAgonyConfig.INSTANCE;
        }

        public class Unholy {
            //
        }

        public class Blood {
            //
        }
    }

    public class Items {
        public static ConfigManager<ItemConfig> ARMORS = new ConfigManager<ItemConfig>("armor_sets", ArmorConfig.INSTANCE).builder().setDirectory("death_knights").sanitize(true).build();
    }
}
