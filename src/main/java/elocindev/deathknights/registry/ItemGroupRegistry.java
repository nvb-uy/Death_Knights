package elocindev.deathknights.registry;

import elocindev.deathknights.DeathKnights;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.runes.api.RuneItems;
import net.runes.api.RuneItems.RuneType;

public class ItemGroupRegistry {
    public static final ItemGroup MAIN_TAB = FabricItemGroup.builder()
		.icon(() -> new ItemStack(ArmorRegistry.initiate_set.head))
        .displayName(Text.translatable("itemGroup.death_knights.main"))
		.build();

    public static RegistryKey<ItemGroup> MAIN_TAB_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(DeathKnights.MODID, "main"));

    public static void register() {
            Registry.register(Registries.ITEM_GROUP, new Identifier(DeathKnights.MODID, "main"), MAIN_TAB);
        
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
                content.addAfter(RuneItems.get(RuneType.SOUL), new ItemStack(ItemRegistry.BLOOD_RUNE));
                content.addAfter(ItemRegistry.BLOOD_RUNE, new ItemStack(ItemRegistry.UNHOLY_RUNE));
            });

            ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(DeathKnights.MODID, "main"))).register(content -> {
                content.add(new ItemStack(ItemRegistry.BLOOD_SPELL_BOOK));
                content.add(new ItemStack(ItemRegistry.UNHOLY_SPELL_BOOK));
                content.add(new ItemStack(ItemRegistry.FROST_SPELL_BOOK));

                content.add(new ItemStack(ItemRegistry.BLOOD_RUNE));
                content.add(new ItemStack(ItemRegistry.UNHOLY_RUNE));
                content.add(new ItemStack(RuneItems.get(RuneType.FROST)));

                content.add(new ItemStack(ItemRegistry.RUNECARVED_STONE));

                content.add(new ItemStack(ItemRegistry.IRON_RUNEBLADE));
                content.add(new ItemStack(ItemRegistry.DIAMOND_RUNEBLADE));
                content.add(new ItemStack(ItemRegistry.NETHERITE_RUNEBLADE));
                if (DeathKnights.BETTERNETHER_ENABLED) { content.add(new ItemStack(ItemRegistry.RUBY_RUNEBLADE)); }
                if (DeathKnights.BETTEREND_ENABLED) { content.add(new ItemStack(ItemRegistry.AETERNIUM_RUNEBLADE)); }

                content.add(new ItemStack(ItemRegistry.GREAT_IRON_RUNEBLADE));
                content.add(new ItemStack(ItemRegistry.GREAT_DIAMOND_RUNEBLADE));
                content.add(new ItemStack(ItemRegistry.GREAT_NETHERITE_RUNEBLADE));
                if (DeathKnights.BETTERNETHER_ENABLED) { content.add(new ItemStack(ItemRegistry.GREAT_RUBY_RUNEBLADE)); }
                if (DeathKnights.BETTEREND_ENABLED) { content.add(new ItemStack(ItemRegistry.GREAT_AETERNIUM_RUNEBLADE)); }

                content.add(new ItemStack(ItemRegistry.GREAT_IRON_RUNEAXE));
                content.add(new ItemStack(ItemRegistry.GREAT_DIAMOND_RUNEAXE));
                content.add(new ItemStack(ItemRegistry.GREAT_NETHERITE_RUNEAXE));
                if (DeathKnights.BETTERNETHER_ENABLED) { content.add(new ItemStack(ItemRegistry.GREAT_RUBY_RUNEAXE)); }
                if (DeathKnights.BETTEREND_ENABLED) { content.add(new ItemStack(ItemRegistry.GREAT_AETERNIUM_RUNEAXE)); }

                content.add(new ItemStack(ItemRegistry.FROST_DK_RING.item()));
                content.add(new ItemStack(ItemRegistry.FROST_DK_NECKLACE.item()));
                content.add(new ItemStack(ItemRegistry.UNHOLY_DK_RING.item()));
                content.add(new ItemStack(ItemRegistry.UNHOLY_DK_NECKLACE.item()));
                content.add(new ItemStack(ItemRegistry.BLOOD_DK_RING.item()));
                content.add(new ItemStack(ItemRegistry.BLOOD_DK_NECKLACE.item()));
            });
    }
}
