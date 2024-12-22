package elocindev.deathknights.compat;

import elocindev.deathknights.registry.ItemRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import elocindev.necronomicon.api.ResourceIdentifier;

public class JewelryCompat {
    public static void registerInjection() {
        ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(RegistryKeys.ITEM_GROUP, ResourceIdentifier.get("jewelry", "generic"))).register(content -> {
                content.add(new ItemStack(ItemRegistry.FROST_DK_RING.item()));
                content.add(new ItemStack(ItemRegistry.FROST_DK_NECKLACE.item()));
                content.add(new ItemStack(ItemRegistry.UNHOLY_DK_RING.item()));
                content.add(new ItemStack(ItemRegistry.UNHOLY_DK_NECKLACE.item()));
                //content.add(new ItemStack(ItemRegistry.BLOOD_DK_RING.item()));
        });
    }
}
