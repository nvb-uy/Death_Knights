package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleRegistry {
    public static final DefaultParticleType UNHOLY_HIT = FabricParticleTypes.simple();
    public static final DefaultParticleType UNHOLY_SMOKE = FabricParticleTypes.simple();
    public static final DefaultParticleType BLOOD_HIT = FabricParticleTypes.simple();
    public static final DefaultParticleType BLOOD_BOIL = FabricParticleTypes.simple();


    public static void register() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(DeathKnights.MODID, "unholy_hit"), UNHOLY_HIT);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(DeathKnights.MODID, "unholy_smoke"), UNHOLY_SMOKE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(DeathKnights.MODID, "blood_hit"), BLOOD_HIT);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(DeathKnights.MODID, "blood_boil"), BLOOD_BOIL);
    }
}
