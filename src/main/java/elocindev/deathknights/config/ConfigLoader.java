package elocindev.deathknights.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.config.entries.ClientConfig;
import elocindev.deathknights.config.entries.spells.frost.BreathOfAgonyConfig;
import elocindev.deathknights.config.entries.spells.frost.ObliterateConfig;
import elocindev.deathknights.config.entries.spells.frost.RemorselessWinterConfig;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigLoader {
    public static void init() {
        Configs.Items.ARMORS.refresh();
        
        // FROST SPELLS
        NecConfigAPI.registerConfig(ObliterateConfig.class);
        NecConfigAPI.registerConfig(RemorselessWinterConfig.class);
        NecConfigAPI.registerConfig(BreathOfAgonyConfig.class);
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
