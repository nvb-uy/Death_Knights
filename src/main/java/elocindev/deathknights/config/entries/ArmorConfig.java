package elocindev.deathknights.config.entries;

import elocindev.deathknights.registry.ArmorRegistry;
import net.spell_engine.api.item.ItemConfig;

public class ArmorConfig {
    public final static ItemConfig INSTANCE;
    
    static {
        INSTANCE = new ItemConfig();

        for (var armorSet : ArmorRegistry.entries) {
            INSTANCE.armor_sets.put(armorSet.name(), armorSet.defaults());
        }
    }
}
