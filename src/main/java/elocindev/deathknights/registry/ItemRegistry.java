package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.api.types.RunebladeSize;
import elocindev.deathknights.api.types.RunebladeType;
import elocindev.deathknights.item.weapon.RunebladeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
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
    public static final RunebladeItem RUNEBLADE_1H = reg(
        new RunebladeItem(
            RunebladeType.ALL,
            RunebladeSize.ONE_HANDED,
            2, -2.4F,
            1F, true
            ),
            "initiate_runeblade"
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
