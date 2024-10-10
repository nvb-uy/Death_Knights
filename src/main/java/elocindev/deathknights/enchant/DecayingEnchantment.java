package elocindev.deathknights.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.enchantment.SpellPowerEnchanting;
import net.spell_power.internals.SchoolFilteredEnchantment;

import java.util.Set;

import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.enchants.DecayingEnchantConfig;
import elocindev.deathknights.registry.SpellSchoolRegistry;
import elocindev.necronomicon.api.ResourceIdentifier;

public class DecayingEnchantment extends SchoolFilteredEnchantment {
    private static final DecayingEnchantConfig CONFIG = Configs.Enchantments.DECAYING;

    private static final EquipmentSlot[] EQUIPMENT_SLOTS = {
        EquipmentSlot.HEAD,
        EquipmentSlot.CHEST,
        EquipmentSlot.LEGS,
        EquipmentSlot.FEET
    };

    public DecayingEnchantment(Rarity weight, Operation operation) {
        super(weight, operation, null, Set.of(SpellSchoolRegistry.BLOOD, SpellSchoolRegistry.UNHOLY), EnchantmentTarget.ARMOR, EQUIPMENT_SLOTS);
        this.tagId = ResourceIdentifier.get("spell_power:enchantable/spell_power_specialized");
    }

    public DecayingEnchantment requireTag(Identifier tagId) {
        this.tagId = tagId;
        return this;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof DecayingEnchantment) && super.canAccept(other);
    }

    @Override
    public boolean requiresRelatedAttributes() {
        return true;
    }

    @Override
    public double amplified(double value, int level) {
        switch (operation) {
            case ADD:
                return value + (double) level * CONFIG.bonus_per_level;
            case MULTIPLY:
                return value * (1.0 + (double) level * CONFIG.bonus_per_level);
            default:
                return 0.0;
        }
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return CONFIG.enabled;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return CONFIG.enabled;
    }

    @Override
    public int getMaxLevel() {
        return !CONFIG.enabled ? 0 : CONFIG.max_level;
    }

    @Override
    public int getMinPower(int level) {
        return CONFIG.min_cost + ((level - 1) * CONFIG.step_cost);
    }

    @Override
    public int getMaxPower(int level) {
        return getMinPower(level) + 50;
    }

    @Override
    public boolean matchesRequiredTag(ItemStack stack) {
        return this.tagId == null || stack.isIn(TagKey.of(RegistryKeys.ITEM, this.tagId));
    }

    public static boolean schoolsIntersect(Set<SpellSchool> schools, ItemStack stack) {
        var itemStackSchools = SpellPowerEnchanting.relevantSchools(stack);
        for (var school : itemStackSchools) {
            if (schools.contains(school)) {
                return true;
            }
        }
        return false;
    }
}