package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.enchant.DecayingEnchantment;
import elocindev.necronomicon.api.ResourceIdentifier;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchool.QueryArgs;
import net.spell_power.api.enchantment.SpellPowerEnchanting;
import net.spell_power.internals.AmplifierEnchantment.Operation;

public class EnchantmentRegistry {
    public static final DecayingEnchantment DECAYING = new DecayingEnchantment(Rarity.RARE, Operation.MULTIPLY);

    public static void register() {
        Registry.register(Registries.ENCHANTMENT, ResourceIdentifier.get(DeathKnights.MODID, "decaying"), DECAYING);
        registerEffects();
    }

    public static double getDecaying(QueryArgs query) {
        double value = 0;
        var level = SpellPowerEnchanting.getEnchantmentLevel(DECAYING, query.entity(), null);
        value = DECAYING.amplified(value, level);
        return value;
    }

    public static void registerEffects() {
        SpellSchoolRegistry.UNHOLY.addSource(SpellSchool.Trait.POWER, new SpellSchool.Source(SpellSchool.Apply.MULTIPLY, EnchantmentRegistry::getDecaying));
        SpellSchoolRegistry.BLOOD.addSource(SpellSchool.Trait.POWER, new SpellSchool.Source(SpellSchool.Apply.MULTIPLY, EnchantmentRegistry::getDecaying));
    }
}
