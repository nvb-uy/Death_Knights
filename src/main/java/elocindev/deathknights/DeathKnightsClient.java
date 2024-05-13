package elocindev.deathknights;

import net.fabricmc.api.ClientModInitializer;

import elocindev.deathknights.config.ConfigLoader;
public class DeathKnightsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ConfigLoader.initClient();
	}
}