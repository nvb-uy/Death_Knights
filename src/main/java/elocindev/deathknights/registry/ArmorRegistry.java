package elocindev.deathknights.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Supplier;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.item.armor.InitiateArmor;
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
    public static final ArrayList<Armor.Entry> entries = new ArrayList<>();
    public static final List<Identifier> initiate_powers = List.of(SpellSchoolRegistry.BLOOD.id, SpellSchoolRegistry.UNHOLY.id, SpellSchools.FROST.id);

    @SuppressWarnings("unchecked")
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

        private static Armor.Entry create(Armor.CustomMaterial material, ItemConfig.ArmorSet defaults) {
            return new Armor.Entry(material, null, defaults);
        }

	public static void register(Map<String, ItemConfig.ArmorSet> configs) {
			Armor.register(configs, entries, ItemGroupRegistry.MAIN_TAB_GROUP);
	}
}
