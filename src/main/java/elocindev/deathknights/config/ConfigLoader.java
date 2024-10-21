package elocindev.deathknights.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.config.entries.ClientConfig;
import elocindev.deathknights.config.entries.enchants.DecayingEnchantConfig;
import elocindev.deathknights.config.entries.loot.JewelryLootConfig;
import elocindev.deathknights.config.entries.spells.blood.BloodBoilConfig;
import elocindev.deathknights.config.entries.spells.blood.DeathStrikeConfig;
import elocindev.deathknights.config.entries.spells.blood.MarrowrendConfig;
import elocindev.deathknights.config.entries.spells.frost.BreathOfAgonyConfig;
import elocindev.deathknights.config.entries.spells.frost.ObliterateConfig;
import elocindev.deathknights.config.entries.spells.frost.RemorselessWinterConfig;
import elocindev.deathknights.config.entries.spells.unholy.DeathCoilConfig;
import elocindev.deathknights.config.entries.spells.unholy.DeathGripConfig;
import elocindev.deathknights.config.entries.spells.unholy.EpidemicConfig;
import elocindev.deathknights.config.entries.spells.unholy.PlaguesConfig;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigLoader {
    public static void init() {
        Configs.Items.ARMORS.refresh();
        Configs.Items.JEWELRY.refresh();

        // ENCHANTS
        NecConfigAPI.registerConfig(DecayingEnchantConfig.class);

        // LOOT
        NecConfigAPI.registerConfig(JewelryLootConfig.class);
        
        // FROST SPELLS
        NecConfigAPI.registerConfig(ObliterateConfig.class);
        NecConfigAPI.registerConfig(RemorselessWinterConfig.class);
        NecConfigAPI.registerConfig(BreathOfAgonyConfig.class);

        // UNHOLY SPELLS
        NecConfigAPI.registerConfig(PlaguesConfig.class);
        NecConfigAPI.registerConfig(DeathCoilConfig.class);
        NecConfigAPI.registerConfig(EpidemicConfig.class);
        NecConfigAPI.registerConfig(DeathGripConfig.class);

        // BLOOD SPELLS
        NecConfigAPI.registerConfig(DeathStrikeConfig.class);
        NecConfigAPI.registerConfig(MarrowrendConfig.class);
        NecConfigAPI.registerConfig(BloodBoilConfig.class);
    }

    public static void initClient() {
        NecConfigAPI.registerConfig(ClientConfig.class);
    }

    public static void initDatapack(boolean started) {
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) ->  {
			if (started) {
                ConfigLoader.init();
			}
		});
		
        DeathKnights.LOGGER.info("Death Knights Config Loaded!");
    }

    public static String getFile(String file) {
        Path folder = FabricLoader.getInstance().getConfigDir().resolve(DeathKnights.MODID);

        try {
            Files.createDirectories(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return folder.resolve(file).toString();
    }

    public static String getNestedFile(String file, String folder) {
        Path cfg = FabricLoader.getInstance().getConfigDir();
        Path eeFolder = cfg.resolve(DeathKnights.MODID);
        Path nestedFolder = eeFolder.resolve(folder);

        try {
            Files.createDirectories(nestedFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nestedFolder.resolve(file).toString();
    }
}
