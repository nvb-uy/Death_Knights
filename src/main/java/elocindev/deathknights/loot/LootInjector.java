package elocindev.deathknights.loot;

import elocindev.deathknights.config.Configs;
import elocindev.deathknights.config.entries.loot.JewelryLootConfig;
import elocindev.deathknights.registry.ItemRegistry;
import elocindev.necronomicon.api.ResourceIdentifier;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootInjector {
    public static JewelryLootConfig CONFIG = Configs.Loot.JEWELRY;
    
    public static void init() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            injectLoot(ItemRegistry.FROST_DK_NECKLACE.item(), CONFIG.frost_necklace, id, tableBuilder);
            injectLoot(ItemRegistry.FROST_DK_RING.item(), CONFIG.frost_ring, id, tableBuilder);
            injectLoot(ItemRegistry.UNHOLY_DK_NECKLACE.item(), CONFIG.unholy_necklace, id, tableBuilder);
            injectLoot(ItemRegistry.UNHOLY_DK_RING.item(), CONFIG.unholy_ring, id, tableBuilder);
            injectLoot(ItemRegistry.BLOOD_DK_NECKLACE.item(), CONFIG.blood_necklace, id, tableBuilder);
            injectLoot(ItemRegistry.BLOOD_DK_RING.item(), CONFIG.blood_ring, id, tableBuilder);
        });
    }

    private static void injectLoot(Item item, JewelryLootConfig.LootHolder lootHolder, Identifier id, LootTable.Builder tableBuilder) {
        LootPool pool = LootPool.builder()
                .with(ItemEntry.builder(item).build())
                .rolls(BinomialLootNumberProvider.create(lootHolder.rolls, lootHolder.chance))
                .build();
        
        for (String lootTable : lootHolder.loot_tables) {
            if (id.equals(ResourceIdentifier.get(lootTable))) {
                tableBuilder.pool(pool);
            }
        }
    }
}