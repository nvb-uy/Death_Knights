package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.api.types.RunebladeSize;
import elocindev.deathknights.api.types.RunebladeType;
import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.JewelryConfig;
import elocindev.deathknights.item.jewelry.DKJewelryItem;
import elocindev.deathknights.item.weapon.RuneaxeItem;
import elocindev.deathknights.item.weapon.RunebladeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import net.spell_engine.api.item.AttributeResolver;
import net.spell_engine.api.item.trinket.SpellBookItem;
import net.spell_engine.api.item.trinket.SpellBooks;
import net.spell_power.api.SpellSchools;

public class ItemRegistry {
    // RUNES
    public static final Item BLOOD_RUNE = reg(new Item(new FabricItemSettings()), "blood_stone");
    public static final Item UNHOLY_RUNE = reg(new Item(new FabricItemSettings()), "unholy_stone");
    public static final Item RUNECARVED_STONE = reg(new Item(new FabricItemSettings()), "runecarved_stone");

    // SPELL BOOKS
    public static final SpellBookItem BLOOD_SPELL_BOOK = SpellBooks.create(new Identifier(DeathKnights.MODID, "blood"));
    public static final SpellBookItem UNHOLY_SPELL_BOOK = SpellBooks.create(new Identifier(DeathKnights.MODID, "unholy"));
    public static final SpellBookItem FROST_SPELL_BOOK = SpellBooks.create(new Identifier(DeathKnights.MODID, "frost"));

    // JEWELRY
    public record Jewelry(Identifier id, DKJewelryItem item, JewelryConfig.Item config, boolean fireproof) {}
    public static final ArrayList<Jewelry> JEWELRY_ITEMS = new ArrayList<>();
    public static final Map<String, Item> jewelryMap = new HashMap<>();

    public static Jewelry FROST_DK_RING = add(new Identifier(DeathKnights.MODID, "frost_dk_ring"), Rarity.RARE, true, new JewelryConfig.Item(
            List.of(
                    new JewelryConfig.AttributeModifier(SpellSchools.FROST.id, 0.08F, EntityAttributeModifier.Operation.MULTIPLY_BASE),
                    new JewelryConfig.AttributeModifier(new Identifier("generic.attack_damage") , 0.06F, EntityAttributeModifier.Operation.MULTIPLY_BASE)
            )
    ));

    public static Jewelry FROST_DK_NECKLACE = add(new Identifier(DeathKnights.MODID, "frost_dk_necklace"), Rarity.RARE, true, new JewelryConfig.Item(
            List.of(
                new JewelryConfig.AttributeModifier(SpellSchools.FROST.id, 0.08F, EntityAttributeModifier.Operation.MULTIPLY_BASE),
                new JewelryConfig.AttributeModifier(new Identifier("generic.attack_damage") , 0.06F, EntityAttributeModifier.Operation.MULTIPLY_BASE)
            )
    ));

    public static Jewelry UNHOLY_DK_RING = add(new Identifier(DeathKnights.MODID, "unholy_dk_ring"), Rarity.RARE, true, new JewelryConfig.Item(
            List.of(
                    new JewelryConfig.AttributeModifier(SpellSchoolRegistry.UNHOLY.id, 0.08F, EntityAttributeModifier.Operation.MULTIPLY_BASE),
                    new JewelryConfig.AttributeModifier(new Identifier("generic.attack_damage") , 0.06F, EntityAttributeModifier.Operation.MULTIPLY_BASE)
            )
    ));

    public static Jewelry UNHOLY_DK_NECKLACE = add(new Identifier(DeathKnights.MODID, "unholy_dk_necklace"), Rarity.RARE, true, new JewelryConfig.Item(
            List.of(
                new JewelryConfig.AttributeModifier(SpellSchoolRegistry.UNHOLY.id, 0.08F, EntityAttributeModifier.Operation.MULTIPLY_BASE),
                new JewelryConfig.AttributeModifier(new Identifier("generic.attack_damage") , 0.06F, EntityAttributeModifier.Operation.MULTIPLY_BASE)
            )
    ));

    public static Jewelry BLOOD_DK_RING = add(new Identifier(DeathKnights.MODID, "blood_dk_ring"), Rarity.RARE, true, new JewelryConfig.Item(
            List.of(
                    new JewelryConfig.AttributeModifier(SpellSchoolRegistry.BLOOD.id, 0.08F, EntityAttributeModifier.Operation.MULTIPLY_BASE),
                    new JewelryConfig.AttributeModifier(new Identifier("generic.max_health") , 0.06F, EntityAttributeModifier.Operation.MULTIPLY_BASE)
            )
    ));

    public static Jewelry BLOOD_DK_NECKLACE = add(new Identifier(DeathKnights.MODID, "blood_dk_necklace"), Rarity.RARE, true, new JewelryConfig.Item(
            List.of(
                new JewelryConfig.AttributeModifier(SpellSchoolRegistry.BLOOD.id, 0.08F, EntityAttributeModifier.Operation.MULTIPLY_BASE),
                new JewelryConfig.AttributeModifier(new Identifier("generic.max_health") , 0.06F, EntityAttributeModifier.Operation.MULTIPLY_BASE)
            )
    ));

    // WEAPONS
    public static final RunebladeItem IRON_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.ONE_HANDED,
            ToolMaterials.IRON,
            3, -2.4F,
            0.5F, true
            ),
            "initiate_runeblade"
        );

    public static final RunebladeItem DIAMOND_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.ONE_HANDED,
            ToolMaterials.DIAMOND,
            3, -2.4F,
            1F, true
            ),
            "diamond_runeblade"
        );

    public static final RunebladeItem NETHERITE_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.ONE_HANDED,
            ToolMaterials.NETHERITE,
            3, -2.4F,
            2F, true
            ),
            "netherite_runeblade"
        );

    public static final RunebladeItem RUBY_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.ONE_HANDED,
            ToolMaterials.NETHERITE,
            4, -2.4F,
            2.5F, true,
            true
            ),
            "ruby_runeblade"
        );
    
    public static final RunebladeItem AETERNIUM_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.ONE_HANDED,
            ToolMaterials.NETHERITE,
            4, -2.4F,
            2.5F, true,
            true
            ),
            "aeternium_runeblade"
        );

    // TWO HANDED RUNEBLADES

    public static final RunebladeItem GREAT_IRON_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.TWO_HANDED,
            ToolMaterials.IRON,
            6, -3.2F,
            1F, true
            ),
            "great_iron_runeblade"
        );
    
    public static final RunebladeItem GREAT_DIAMOND_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.TWO_HANDED,
            ToolMaterials.DIAMOND,
            7, -3.2F,
            2F, true
            ),
            "great_diamond_runeblade"
        );
    
    public static final RunebladeItem GREAT_NETHERITE_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.TWO_HANDED,
            ToolMaterials.NETHERITE,
            7, -3.2F,
            4F, true
            ),
            "great_netherite_runeblade"
        );

    public static final RunebladeItem GREAT_RUBY_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.TWO_HANDED,
            ToolMaterials.NETHERITE,
            9, -3.2F,
            5F, true,
            true
            ),
            "great_ruby_runeblade"
        );

    public static final RunebladeItem GREAT_AETERNIUM_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.TWO_HANDED,
            ToolMaterials.NETHERITE,
            9, -3.2F,
            5F, true,
            true
            ),
            "great_aeternium_runeblade"
        );

        public static final RuneaxeItem GREAT_IRON_RUNEAXE = reg(
            new RuneaxeItem(
                RunebladeType.ALL,
                RunebladeSize.TWO_HANDED,
                ToolMaterials.IRON,
                7, -3.4F,
                1F, true
                ),
                "great_iron_runeaxe"
            );
        
        public static final RuneaxeItem GREAT_DIAMOND_RUNEAXE = reg(
            new RuneaxeItem(
                RunebladeType.ALL,
                RunebladeSize.TWO_HANDED,
                ToolMaterials.DIAMOND,
                8, -3.4F,
                2F, true
                ),
                "great_diamond_runeaxe"
            );
        
        public static final RuneaxeItem GREAT_NETHERITE_RUNEAXE = reg(
            new RuneaxeItem(
                RunebladeType.ALL,
                RunebladeSize.TWO_HANDED,
                ToolMaterials.NETHERITE,
                8, -3.4F,
                4F, true
                ),
                "great_netherite_runeaxe"
            );
    
        public static final RuneaxeItem GREAT_RUBY_RUNEAXE = reg(
            new RuneaxeItem(
                RunebladeType.ALL,
                RunebladeSize.TWO_HANDED,
                ToolMaterials.NETHERITE,
                10, -3.4F,
                5F, true,
                true
                ),
                "great_ruby_runeaxe"
            );
    
        public static final RuneaxeItem GREAT_AETERNIUM_RUNEAXE = reg(
            new RuneaxeItem(
                RunebladeType.ALL,
                RunebladeSize.TWO_HANDED,
                ToolMaterials.NETHERITE,
                10, -3.4F,
                5F, true,
                true
                ),
                "great_aeternium_runeaxe"
            );

    public static <T extends Item> T reg(T instance, String id) {
        return Registry.register(Registries.ITEM, new Identifier(DeathKnights.MODID, id), instance);
    }

    public static void register() {
        Registry.register(Registries.ITEM, new Identifier(DeathKnights.MODID, "blood_spell_book"), BLOOD_SPELL_BOOK.asItem());
        Registry.register(Registries.ITEM, new Identifier(DeathKnights.MODID, "unholy_spell_book"), UNHOLY_SPELL_BOOK.asItem());
        Registry.register(Registries.ITEM, new Identifier(DeathKnights.MODID, "frost_spell_book"), FROST_SPELL_BOOK.asItem());

        register(Configs.Items.JEWELRY.value);
        Configs.Items.JEWELRY.save();
    }

    public static void register(JewelryConfig jewelry_configs) {
        for (var entry : JEWELRY_ITEMS) {
            JewelryConfig.Item itemConfig = jewelry_configs.items.get(entry.id.toString());
            if (itemConfig == null) {
                itemConfig = entry.config;
                jewelry_configs.items.put(entry.id.toString(), entry.config);
            }

            var modifiers = new ArrayList<DKJewelryItem.Modifier>();
            for (var modifier : itemConfig.attributes) {
                var attribute = AttributeResolver.get(new Identifier(modifier.id));

                if (attribute == null) {
                    System.err.println("Failed to resolve EntityAttribute with id: " + modifier.id);
                    continue;
                }

                modifiers.add(new DKJewelryItem.Modifier(
                        attribute,
                        "Death Knight Jewelry modifier",
                        modifier.value,
                        modifier.operation));
            }

            entry.item().setConfigurableModifiers(modifiers);
            
            Registry.register(Registries.ITEM, entry.id(), entry.item());
        }
    }

    public static Jewelry add(Identifier id, JewelryConfig.Item config) {
        return add(id, Rarity.COMMON, config, null, false);
    }

    public static Jewelry add(Identifier id, Rarity rarity, JewelryConfig.Item config) {
        return add(id, rarity, config, null, false);
    }

    public static Jewelry add(Identifier id, Rarity rarity, JewelryConfig.Item config, boolean fireproof) {
        return add(id, rarity, config, null, fireproof);
    }

    public static Jewelry add(Identifier id, Rarity rarity, boolean addLore, JewelryConfig.Item config) {
        return add(id, rarity, config, addLore ? ("item." + id.getNamespace() + "." + id.getPath() + ".lore") : null, false);
    }

    public static Jewelry add(Identifier id, Rarity rarity, JewelryConfig.Item config, String lore, boolean fireproof) {
        var settings = new FabricItemSettings().rarity(rarity);
        if (fireproof) {
            settings = settings.fireproof();
        }
        var Jewelry = new Jewelry(id, new DKJewelryItem(settings, lore), config, fireproof);
        JEWELRY_ITEMS.add(Jewelry);
        jewelryMap.put(id.toString(), Jewelry.item());
        return Jewelry;
    }
}
