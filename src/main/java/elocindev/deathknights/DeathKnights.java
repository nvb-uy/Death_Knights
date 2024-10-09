package elocindev.deathknights;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.deathknights.config.entries.ArmorConfig;
import elocindev.deathknights.registry.ArmorRegistry;
import elocindev.deathknights.registry.AttributeRegistry;
import elocindev.deathknights.registry.ItemGroupRegistry;
import elocindev.deathknights.registry.ItemRegistry;
import elocindev.deathknights.registry.SpellRegistry;
import elocindev.deathknights.registry.SpellSchoolRegistry;
import elocindev.deathknights.registry.StatusEffectRegistry;
import elocindev.deathknights.spells.SpellHandler;

public class DeathKnights implements ModInitializer {
	public static final String MODID = "death_knights";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final boolean BETTERCOMBAT_ENABLED = FabricLoader.getInstance().isModLoaded("bettercombat");
	public static final boolean BETTEREND_ENABLED = FabricLoader.getInstance().isModLoaded("betterend");
	public static final boolean BETTERNETHER_ENABLED = FabricLoader.getInstance().isModLoaded("betternether");

	@Override
	public void onInitialize() {
		ConfigLoader.init(); ConfigLoader.initDatapack(true);

		AttributeRegistry.register();
		StatusEffectRegistry.register();
		SpellSchoolRegistry.register();
		SpellRegistry.register();

		ItemRegistry.register();
		ItemGroupRegistry.register();
		ArmorRegistry.register(ArmorConfig.INSTANCE.armor_sets);

		SpellHandler.registerSpells();

		LOGGER.info("Death Knights Initialized!");
	}
}