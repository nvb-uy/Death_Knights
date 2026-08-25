package elocindev.deathknights.registry;

//? if 1.20.1 {
import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.config.entries.enchants.DecayingEnchantConfig;
import elocindev.deathknights.enchant.DecayingEnchantment;
import elocindev.necronomicon.api.ResourceIdentifier;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchool.QueryArgs;
import net.spell_power.api.enchantment.SpellPowerEnchanting;
import net.spell_power.internals.AmplifierEnchantment.Operation;
//?}

public class EnchantmentRegistry {
    //? if 1.20.1 {
    public static final DecayingEnchantment DECAYING = new DecayingEnchantment(Rarity.RARE, Operation.MULTIPLY);

    public static void register() {
        Registry.register(Registries.ENCHANTMENT, ResourceIdentifier.get(DeathKnights.MODID, "decaying"), DECAYING);
        registerEffects();
    }

    public static double getDecaying(QueryArgs query) {
        var level = SpellPowerEnchanting.getEnchantmentLevel(DECAYING, query.entity(), null);
        var config = DecayingEnchantConfig.INSTANCE;
        return config == null || !config.enabled ? 0 : level * config.bonus_per_level;
    }

    public static void registerEffects() {
        SpellSchoolRegistry.UNHOLY.addSource(SpellSchool.Trait.POWER, new SpellSchool.Source(SpellSchool.Apply.MULTIPLY, EnchantmentRegistry::getDecaying));
        SpellSchoolRegistry.BLOOD.addSource(SpellSchool.Trait.POWER, new SpellSchool.Source(SpellSchool.Apply.MULTIPLY, EnchantmentRegistry::getDecaying));
    }
    //?}
}
