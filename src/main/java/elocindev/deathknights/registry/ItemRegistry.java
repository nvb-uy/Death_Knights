package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.api.types.RunebladeSize;
import elocindev.deathknights.api.types.RunebladeType;
import elocindev.deathknights.item.weapon.RunebladeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.spell_engine.api.item.trinket.SpellBookItem;
import net.spell_engine.api.item.trinket.SpellBooks;

public class ItemRegistry {
    
    public static final Item BLOOD_RUNE = reg(new Item(new FabricItemSettings()), "blood_stone");
    public static final Item UNHOLY_RUNE = reg(new Item(new FabricItemSettings()), "unholy_stone");

    public static final Item RUNECARVED_STONE = reg(new Item(new FabricItemSettings()), "runecarved_stone");

    public static final SpellBookItem BLOOD_SPELL_BOOK = SpellBooks.create(new Identifier(DeathKnights.MODID, "blood"));
    public static final SpellBookItem UNHOLY_SPELL_BOOK = SpellBooks.create(new Identifier(DeathKnights.MODID, "unholy"));
    public static final SpellBookItem FROST_SPELL_BOOK = SpellBooks.create(new Identifier(DeathKnights.MODID, "frost"));

    // Weapons
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
            3F, true,
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
            3F, true,
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
            5, -3.0F,
            1F, true
            ),
            "great_iron_runeblade"
        );
    
    public static final RunebladeItem GREAT_DIAMOND_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.TWO_HANDED,
            ToolMaterials.DIAMOND,
            7, -3.0F,
            2F, true
            ),
            "great_diamond_runeblade"
        );
    
    public static final RunebladeItem GREAT_NETHERITE_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.TWO_HANDED,
            ToolMaterials.NETHERITE,
            7, -3.0F,
            4F, true
            ),
            "great_netherite_runeblade"
        );

    public static final RunebladeItem GREAT_RUBY_RUNEBLADE = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.TWO_HANDED,
            ToolMaterials.NETHERITE,
            9, -3.0F,
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
            9, -3.0F,
            5F, true,
            true
            ),
            "great_aeternium_runeblade"
        );

    public static <T extends Item> T reg(T instance, String id) {
        return Registry.register(Registries.ITEM, new Identifier(DeathKnights.MODID, id), instance);
    }

    public static void register() {
        Registry.register(Registries.ITEM, new Identifier(DeathKnights.MODID, "blood_spell_book"), BLOOD_SPELL_BOOK.asItem());
        Registry.register(Registries.ITEM, new Identifier(DeathKnights.MODID, "unholy_spell_book"), UNHOLY_SPELL_BOOK.asItem());
        Registry.register(Registries.ITEM, new Identifier(DeathKnights.MODID, "frost_spell_book"), FROST_SPELL_BOOK.asItem());
    }
}
