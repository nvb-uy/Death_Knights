package elocindev.deathknights.config;

import elocindev.deathknights.config.entries.ArmorConfig;
import elocindev.deathknights.config.entries.ClientConfig;
import elocindev.deathknights.config.entries.JewelryConfig;
import elocindev.deathknights.config.entries.enchants.DecayingEnchantConfig;
import elocindev.deathknights.config.entries.loot.JewelryLootConfig;
import elocindev.deathknights.config.entries.spells.frost.BreathOfAgonyConfig;
import elocindev.deathknights.config.entries.spells.frost.FrostStrikeConfig;
import elocindev.deathknights.config.entries.spells.frost.ObliterateConfig;
import elocindev.deathknights.config.entries.spells.frost.RemorselessWinterConfig;
import elocindev.deathknights.config.entries.spells.unholy.DeathCoilConfig;
import elocindev.deathknights.config.entries.spells.unholy.DeathGripConfig;
import elocindev.deathknights.config.entries.spells.unholy.EpidemicConfig;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig;
import elocindev.deathknights.config.entries.spells.blood.BloodBoilConfig;
import elocindev.deathknights.config.entries.spells.blood.DeathStrikeConfig;
import elocindev.deathknights.config.entries.spells.blood.MarrowrendConfig;
import net.spell_engine.api.item.ItemConfig;
import net.tinyconfig.ConfigManager;

public class Configs {   
    public class Client {
        public static ClientConfig                  CONFIG                =      ClientConfig.INSTANCE;
    }

    public class Spells {
        public class Frost {
            public static ObliterateConfig          OBLITERATE            =      ObliterateConfig.INSTANCE;
            public static FrostStrikeConfig         FROST_STRIKE          =      FrostStrikeConfig.INSTANCE;
            public static RemorselessWinterConfig   REMORSELESS_WINTER    =      RemorselessWinterConfig.INSTANCE;
            public static BreathOfAgonyConfig       BREATH_OF_AGONY       =      BreathOfAgonyConfig.INSTANCE;
        }

        public class Unholy {
            public static PlaguesConfig             PLAGUES               =      PlaguesConfig.INSTANCE;
            public static DeathCoilConfig           DEATH_COIL            =      DeathCoilConfig.INSTANCE;
            public static EpidemicConfig            EPIDEMIC              =      EpidemicConfig.INSTANCE;
            public static DeathGripConfig           DEATH_GRIP            =      DeathGripConfig.INSTANCE;
        }

        public class Blood {
            public static DeathStrikeConfig         DEATH_STRIKE          =      DeathStrikeConfig.INSTANCE;
            public static MarrowrendConfig          MARROWREND            =      MarrowrendConfig.INSTANCE;
            public static BloodBoilConfig           BLOOD_BOIL            =      BloodBoilConfig.INSTANCE;
        }
    }

    public class Items {
        public static ConfigManager<ItemConfig> ARMORS = new ConfigManager<ItemConfig>("armor_sets", ArmorConfig.INSTANCE).builder().setDirectory("death_knights/items").sanitize(true).build();
        public static ConfigManager<JewelryConfig> JEWELRY = new ConfigManager<>("jewelry", JewelryConfig.INSTANCE).builder().setDirectory("death_knights/items").sanitize(true).build();
    }

    public class Enchantments {
        public static DecayingEnchantConfig DECAYING = DecayingEnchantConfig.INSTANCE;
    }

    public class Loot {
        public static JewelryLootConfig JEWELRY = JewelryLootConfig.INSTANCE;
    }
}
