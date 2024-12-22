package elocindev.deathknights.registry;

//? if 1.20.1 {
import elocindev.deathknights.DeathKnights;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import elocindev.necronomicon.api.ResourceIdentifier;
//? else {
/*import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.spell_engine.SpellEngineMod;

import java.util.ArrayList;
import java.util.List;
*///?

public class ParticleRegistry {
    //? if 1.20.1 {
    public static final DefaultParticleType UNHOLY_HIT = FabricParticleTypes.simple();
    public static final DefaultParticleType UNHOLY_SMOKE = FabricParticleTypes.simple();
    public static final DefaultParticleType BLOOD_HIT = FabricParticleTypes.simple();
    public static final DefaultParticleType BLOOD_BOIL = FabricParticleTypes.simple();
    //?} else {

    /*private static class Helper extends SimpleParticleType {
        protected Helper(boolean alwaysShow) {
            super(alwaysShow);
        }
    }
    private static SimpleParticleType createSimple() {
        return new Helper(false);
    }

    public static class ParticleEntry {
        public final Identifier id;
        public final SimpleParticleType particleType = createSimple();
        public boolean usesCustomTexture = false;
        public ParticleEntry(String name) {
            this.id =  Identifier.of(SpellEngineMod.ID, name);
        }
        public ParticleEntry customTexture() {
            this.usesCustomTexture = true;
            return this;
        }
    }

    private static final ArrayList<ParticleEntry> all = new ArrayList<>();
    public static List<ParticleEntry> all() {
        return all;
    }

    private static ParticleEntry particle(String name) {
        var entry = new ParticleEntry(name);
        all.add(entry);
        return entry;
    }

    public static final ParticleEntry UNHOLY_HIT = particle("unholy_hit").customTexture();
    public static final ParticleEntry UNHOLY_SMOKE = particle("unholy_smoke").customTexture();
    public static final ParticleEntry BLOOD_HIT = particle("blood_hit").customTexture();
    public static final ParticleEntry BLOOD_BOIL = particle("blood_boil").customTexture();
    
    *///?} 

    public static void register() {
        //? if 1.20.1 {
        Registry.register(Registries.PARTICLE_TYPE, ResourceIdentifier.get(DeathKnights.MODID, "unholy_hit"), UNHOLY_HIT);
        Registry.register(Registries.PARTICLE_TYPE, ResourceIdentifier.get(DeathKnights.MODID, "unholy_smoke"), UNHOLY_SMOKE);
        Registry.register(Registries.PARTICLE_TYPE, ResourceIdentifier.get(DeathKnights.MODID, "blood_hit"), BLOOD_HIT);
        Registry.register(Registries.PARTICLE_TYPE, ResourceIdentifier.get(DeathKnights.MODID, "blood_boil"), BLOOD_BOIL);
        //? else {
        /*for(var entry: all) {
            Registry.register(Registries.PARTICLE_TYPE, entry.id, entry.particleType);
        }
        *///?
    }  
}