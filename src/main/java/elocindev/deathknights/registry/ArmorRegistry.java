package elocindev.deathknights.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Supplier;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.item.armor.InitiateArmor;
import elocindev.deathknights.item.armor.TieredArmor;
import elocindev.necronomicon.api.ResourceIdentifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.item.armor.Armor;
import net.spell_power.api.SpellSchools;

public class ArmorRegistry {
    private static final Supplier<Ingredient> INITIATE_INGREDIENTS = () -> { return Ingredient.ofItems(
                Items.CHAIN
            );
    };

	private static final Supplier<Ingredient> TIER_1_INGREDIENTS = () -> { return Ingredient.ofItems(
			Items.IRON_INGOT
		);
	};

    public static final ArrayList<Armor.Entry> entries = new ArrayList<>();
    public static final List<Identifier> initiate_powers = List.of(SpellSchoolRegistry.BLOOD.id, SpellSchoolRegistry.UNHOLY.id, SpellSchools.FROST.id);
	
	public static List<ItemConfig.Attribute> getPowerForTier(int tier, Identifier power, Identifier secondary, float secondaryAmount) {
		return getPowerForTier(tier, power, secondary, secondaryAmount, false);
	}

	public static List<ItemConfig.Attribute> getPowerForTier(int tier, Identifier power, Identifier secondary, float secondaryAmount, boolean isSecondaryAdditive) {
		float main_power = 0.10f;

		if (tier > 1) {
			main_power = 0.20f;
		}

		if (isSecondaryAdditive) {
			return List.of(ItemConfig.Attribute.multiply(power, main_power), ItemConfig.Attribute.bonus(secondary, secondaryAmount));
		}

		return List.of(ItemConfig.Attribute.multiply(power, main_power), ItemConfig.Attribute.multiply(secondary, secondaryAmount));
	}

	// TIER 1 INITIATE - Initiate Set
	// +1 ALL POWERS
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final Armor.Set initiate_set =
		create(
				new Armor.CustomMaterial(
					"initiate_armor",
					10,
					9,
					InitiateArmor.equipSound,
					INITIATE_INGREDIENTS
				),
				ItemConfig.ArmorSet.with(
					new ItemConfig.ArmorSet.Piece(2)
							.addAll(ItemConfig.Attribute.bonuses(initiate_powers, 1)),
					new ItemConfig.ArmorSet.Piece(5)
							.addAll(ItemConfig.Attribute.bonuses(initiate_powers, 1)),
					new ItemConfig.ArmorSet.Piece(4)
							.addAll(ItemConfig.Attribute.bonuses(initiate_powers, 1)),
					new ItemConfig.ArmorSet.Piece(2)
							.addAll(ItemConfig.Attribute.bonuses(initiate_powers, 1))
				))
		.bundle(material -> new Armor.Set(DeathKnights.MODID,
				new InitiateArmor(material, ArmorItem.Type.HELMET, new Item.Settings()),
				new InitiateArmor(material, ArmorItem.Type.CHESTPLATE, new Item.Settings()),
				new InitiateArmor(material, ArmorItem.Type.LEGGINGS, new Item.Settings()),
				new InitiateArmor(material, ArmorItem.Type.BOOTS, new Item.Settings())
		))
		.put(entries)
		.armorSet();

	
	// TIER 2 FROST - Frozen Champion Set
	// +20% FROST POWER, +4% ATTACK SPEED

	@SuppressWarnings({ "unchecked", "rawtypes" })
    public static final Armor.Set frozen_champion =
		create(
				new Armor.CustomMaterial(
					"frozen_champion_armor",
					18,
					10,
					TieredArmor.equipSound,
					TIER_1_INGREDIENTS
				),
				ItemConfig.ArmorSet.with(
					new ItemConfig.ArmorSet.Piece(3)
							.addAll(getPowerForTier(2, SpellSchools.FROST.id, ResourceIdentifier.get("minecraft:generic.attack_speed"), 0.04f)),
					new ItemConfig.ArmorSet.Piece(6)
							.addAll(getPowerForTier(2, SpellSchools.FROST.id, ResourceIdentifier.get("minecraft:generic.attack_speed"), 0.04f)),
					new ItemConfig.ArmorSet.Piece(5)
							.addAll(getPowerForTier(2, SpellSchools.FROST.id, ResourceIdentifier.get("minecraft:generic.attack_speed"), 0.04f)),
					new ItemConfig.ArmorSet.Piece(3)
							.addAll(getPowerForTier(2, SpellSchools.FROST.id, ResourceIdentifier.get("minecraft:generic.attack_speed"), 0.04f))
				))
		.bundle(material -> new Armor.Set(DeathKnights.MODID,
				new TieredArmor("frozen_champion", material, ArmorItem.Type.HELMET, new Item.Settings()),
				new TieredArmor("frozen_champion", material, ArmorItem.Type.CHESTPLATE, new Item.Settings()),
				new TieredArmor("frozen_champion", material, ArmorItem.Type.LEGGINGS, new Item.Settings()),
				new TieredArmor("frozen_champion", material, ArmorItem.Type.BOOTS, new Item.Settings())
		))
		.put(entries)
		.armorSet();
	
	// ------------------------------

	// TIER 2 BLOOD - Crimson Guard Set
	// +20% BLOOD POWER, +2 MAX HEALTH

	@SuppressWarnings({ "unchecked", "rawtypes" })
    public static final Armor.Set crimson_guard =
		create(
				new Armor.CustomMaterial(
					"crimson_guard_armor",
					18,
					10,
					TieredArmor.equipSound,
					TIER_1_INGREDIENTS
				),
				ItemConfig.ArmorSet.with(
					new ItemConfig.ArmorSet.Piece(3)
							.addAll(getPowerForTier(2, SpellSchoolRegistry.BLOOD.id, ResourceIdentifier.get("minecraft:generic.max_health"), 2f, true)),
					new ItemConfig.ArmorSet.Piece(6)
							.addAll(getPowerForTier(2, SpellSchoolRegistry.BLOOD.id, ResourceIdentifier.get("minecraft:generic.max_health"), 2f, true)),
					new ItemConfig.ArmorSet.Piece(5)
							.addAll(getPowerForTier(2, SpellSchoolRegistry.BLOOD.id, ResourceIdentifier.get("minecraft:generic.max_health"), 2f, true)),
					new ItemConfig.ArmorSet.Piece(3)
							.addAll(getPowerForTier(2, SpellSchoolRegistry.BLOOD.id, ResourceIdentifier.get("minecraft:generic.max_health"), 2f, true))
				))
		.bundle(material -> new Armor.Set(DeathKnights.MODID,
				new TieredArmor("crimson_guard", material, ArmorItem.Type.HELMET, new Item.Settings()),
				new TieredArmor("crimson_guard", material, ArmorItem.Type.CHESTPLATE, new Item.Settings()),
				new TieredArmor("crimson_guard", material, ArmorItem.Type.LEGGINGS, new Item.Settings()),
				new TieredArmor("crimson_guard", material, ArmorItem.Type.BOOTS, new Item.Settings())
		))
		.put(entries)
		.armorSet();

	// TIER 2 UNHOLY - Plaguebringer Set
	// +20% UNHOLY POWER, +4% ATTACK DAMAGE

	@SuppressWarnings({ "unchecked", "rawtypes" })
    public static final Armor.Set plaguebringer =
		create(
				new Armor.CustomMaterial(
					"plaguebringer_armor",
					18,
					10,
					TieredArmor.equipSound,
					TIER_1_INGREDIENTS
				),
				ItemConfig.ArmorSet.with(
					new ItemConfig.ArmorSet.Piece(3)
							.addAll(getPowerForTier(2, SpellSchoolRegistry.UNHOLY.id, ResourceIdentifier.get("minecraft:generic.attack_damage"), 0.04f)),
					new ItemConfig.ArmorSet.Piece(6)
							.addAll(getPowerForTier(2, SpellSchoolRegistry.UNHOLY.id, ResourceIdentifier.get("minecraft:generic.attack_damage"), 0.04f)),
					new ItemConfig.ArmorSet.Piece(5)
							.addAll(getPowerForTier(2, SpellSchoolRegistry.UNHOLY.id, ResourceIdentifier.get("minecraft:generic.attack_damage"), 0.04f)),
					new ItemConfig.ArmorSet.Piece(3)
							.addAll(getPowerForTier(2, SpellSchoolRegistry.UNHOLY.id, ResourceIdentifier.get("minecraft:generic.attack_damage"), 0.04f))
				))
		.bundle(material -> new Armor.Set(DeathKnights.MODID,
				new TieredArmor("plaguebringer", material, ArmorItem.Type.HELMET, new Item.Settings()),
				new TieredArmor("plaguebringer", material, ArmorItem.Type.CHESTPLATE, new Item.Settings()),
				new TieredArmor("plaguebringer", material, ArmorItem.Type.LEGGINGS, new Item.Settings()),
				new TieredArmor("plaguebringer", material, ArmorItem.Type.BOOTS, new Item.Settings())
		))
		.put(entries)
		.armorSet();

	private static Armor.Entry create(Armor.CustomMaterial material, ItemConfig.ArmorSet defaults) {
		return new Armor.Entry(material, null, defaults);
	}

	public static void register(Map<String, ItemConfig.ArmorSet> configs) {
		Armor.register(configs, entries, ItemGroupRegistry.MAIN_TAB_GROUP);
	}
}
