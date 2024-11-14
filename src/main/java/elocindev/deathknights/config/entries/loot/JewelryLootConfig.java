package elocindev.deathknights.config.entries.loot;

import java.util.List;

import elocindev.deathknights.config.ConfigLoader;
import elocindev.necronomicon.config.NecConfig;

public class JewelryLootConfig {
    @NecConfig
    public static JewelryLootConfig INSTANCE;

    public static String getFile() {
        return ConfigLoader.getNestedFile("jewelry_v2.json5", "loot");
    }

    public class LootHolder {
        public int rolls;
        public float chance;
        public List<String> loot_tables;
        
        public LootHolder(int rolls, float chance, List<String> loot_tables) {
            this.rolls = rolls;
            this.chance = chance;
            this.loot_tables = loot_tables;
        }
    }

    public LootHolder frost_necklace = new LootHolder(1, 0.20F, List.of("minecraft:chests/ancient_city_ice_box"));
    public LootHolder frost_ring = new LootHolder(3, 0.15F, List.of("minecraft:chests/ancient_city_ice_box"));

    public LootHolder unholy_necklace = new LootHolder(1, 0.20F, List.of("minecraft:chests/woodland_mansion"));
    public LootHolder unholy_ring = new LootHolder(3, 0.10F, List.of("minecraft:chests/woodland_mansion"));

    public LootHolder blood_necklace = new LootHolder(1, 0.20F, List.of("minecraft:chests/bastion_treasure"));
    public LootHolder blood_ring = new LootHolder(3, 0.10F, List.of("minecraft:chests/bastion_treasure"));
}
