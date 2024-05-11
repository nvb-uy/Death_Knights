package elocindev.deathknights;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.deathknights.config.entries.ArmorConfig;
import elocindev.deathknights.registry.ArmorRegistry;
import elocindev.deathknights.registry.AttributeRegistry;
import elocindev.deathknights.registry.ItemGroupRegistry;
import elocindev.deathknights.registry.ItemRegistry;
import elocindev.deathknights.registry.SpellSchoolRegistry;
import elocindev.deathknights.registry.StatusEffectRegistry;

public class DeathKnights implements ModInitializer {
	public static final String MODID = "death_knights";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		ConfigLoader.init(); ConfigLoader.initDatapack(true);

		AttributeRegistry.register();
		StatusEffectRegistry.register();
		SpellSchoolRegistry.register();

		ItemRegistry.register();
		ItemGroupRegistry.register();
		ArmorRegistry.register(ArmorConfig.INSTANCE.armor_sets);

		LOGGER.info("Death Knights Initialized!");
	}
}