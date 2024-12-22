package elocindev.deathknights;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;
import net.spell_engine.api.render.CustomModels;
import elocindev.deathknights.config.ConfigLoader;
import elocindev.deathknights.registry.ClientParticleRegistry;
import elocindev.necronomicon.api.ResourceIdentifier;

import java.util.List;

public class DeathKnightsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ConfigLoader.initClient();

		CustomModels.registerModelIds(List.of(
			ResourceIdentifier.get(DeathKnights.MODID, "projectile/unholy_missile")
		));

		ClientParticleRegistry.register();
	}
}