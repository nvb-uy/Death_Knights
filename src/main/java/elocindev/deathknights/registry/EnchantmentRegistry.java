package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.enchant.DecayingEnchantment;
import elocindev.necronomicon.api.ResourceIdentifier;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.spell_power.internals.AmplifierEnchantment.Operation;

public class EnchantmentRegistry {
    public static final DecayingEnchantment DECAYING = new DecayingEnchantment(Rarity.RARE, Operation.MULTIPLY);

    public static void register() {
        Registry.register(Registries.ENCHANTMENT, ResourceIdentifier.get(DeathKnights.MODID, "decaying"), DECAYING);
    }
}
