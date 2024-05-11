package elocindev.deathknights.api;

import java.util.UUID;
import java.util.Vector;

import com.google.common.collect.ImmutableMultimap;

import elocindev.deathknights.api.types.RunebladeType;
import elocindev.deathknights.registry.SpellSchoolRegistry;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;

public class DKAttributeAPI {
    public static void buildMagicAttributes(ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> modifiers, RunebladeType type, UUID uuid, EntityAttributeModifier.Operation operator, float amount) {
        Vector<SpellSchool> powers = new Vector<SpellSchool>();

        applyPowers(powers, type);

        for (SpellSchool power : powers) {
            modifiers.put(
                power.attribute,
                new EntityAttributeModifier(
                    uuid,
                    "Runeblade Modifier", 
                    amount, 
                    operator
                )
            );
        }
    }

    private static void applyPowers(Vector<SpellSchool> powers, RunebladeType type) {
        switch (type) {
            case BLOOD:
                powers.add(SpellSchoolRegistry.BLOOD);
                break;
            case UNHOLY:
                powers.add(SpellSchoolRegistry.UNHOLY);
                break;
            case FROST:
                powers.add(SpellSchools.FROST);
                break;
            case ALL:
                addPowers(powers, SpellSchoolRegistry.BLOOD, SpellSchoolRegistry.UNHOLY, SpellSchools.FROST);
                break;
        }
    }

    private static void addPowers(Vector<SpellSchool> powers, SpellSchool... schools) {
        for (SpellSchool school : schools) {
            powers.add(school);
        }
    }
}
