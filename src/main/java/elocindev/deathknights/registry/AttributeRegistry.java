package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AttributeRegistry {
    public static final EntityAttribute BLOOD_POWER = new ClampedEntityAttribute("attribute.name.death_knights.blood_power", 0.0D, 0.0D, 2048.0D).setTracked(true);
    public static final EntityAttribute UNHOLY_POWER = new ClampedEntityAttribute("attribute.name.death_knights.unholy_power", 0.0D, 0.0D, 2048.0D).setTracked(true);


    public static void register() {
        Registry.register(Registries.ATTRIBUTE, new Identifier(DeathKnights.MODID, "blood"), BLOOD_POWER);
        Registry.register(Registries.ATTRIBUTE, new Identifier(DeathKnights.MODID, "unholy"), UNHOLY_POWER);
    }
}
