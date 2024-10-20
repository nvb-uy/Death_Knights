package elocindev.deathknights.registry;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.spell_engine.client.particle.SpellFlameParticle;

public class ClientParticleRegistry {
    public static void register() {
        var instance = ParticleFactoryRegistry.getInstance();

        instance.register(ParticleRegistry.UNHOLY_HIT, SpellFlameParticle.FlameFactory::new);
        instance.register(ParticleRegistry.UNHOLY_SMOKE, SpellFlameParticle.MediumFlameFactory::new);
        instance.register(ParticleRegistry.BLOOD_HIT, SpellFlameParticle.FlameFactory::new);
        instance.register(ParticleRegistry.BLOOD_BOIL, SpellFlameParticle.MediumFlameFactory::new);     
    }
}
