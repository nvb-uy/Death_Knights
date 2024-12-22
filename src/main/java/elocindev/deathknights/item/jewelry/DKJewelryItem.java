package elocindev.deathknights.item.jewelry;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

//? if (1.20.1) {
/*import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;
import net.minecraft.client.item.TooltipContext;
*///?} else {
import net.minecraft.util.Identifier;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
//?}

public class DKJewelryItem extends TrinketItem {
    //? if (1.20.1) {
    /*private List<Modifier> configurableModifiers = List.of();
    *///?} else {
    private AttributeModifiersComponent customAttributes = AttributeModifiersComponent.builder().build();
    //?}

    private final String lore;

    public DKJewelryItem(Settings settings, String lore) {
        super(settings.maxCount(1));
        this.lore = lore;
    }

    //? if (1.20.1) {
    /*@Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (lore != null && !lore.isEmpty()) {
            tooltip.add(Text.translatable(lore).formatted(Formatting.ITALIC, Formatting.GOLD));
        }
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        for (var modifier : this.configurableModifiers) {
            modifiers.put(modifier.attribute,
                    new EntityAttributeModifier(uuid, modifier.name, modifier.value, modifier.operation));
        }
        return modifiers;
    }

    public record Modifier(EntityAttribute attribute, String name, float value, EntityAttributeModifier.Operation operation) { }

    public void setConfigurableModifiers(List<Modifier> configurableModifiers) {
        this.configurableModifiers = configurableModifiers;
    }
    *///?} else {

    @Override
    public void appendTooltip(ItemStack itemStack, net.minecraft.item.Item.TooltipContext tooltipContext, List<Text> tooltip, TooltipType tooltipType) {
        super.appendTooltip(itemStack, tooltipContext, tooltip, tooltipType);

        if (lore != null && !lore.isEmpty()) {
            tooltip.add(Text.translatable(lore).formatted(Formatting.ITALIC, Formatting.GOLD));
        }
    }

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        // Why yarn why
        for (var entry : this.customAttributes.comp_2393()) {
            modifiers.put(entry.comp_2395(),
                    new EntityAttributeModifier(slotIdentifier, entry.comp_2396().value(), entry.comp_2396().comp_2450()));
        }
        return modifiers;
    }

    public void setConfigurableModifiers(AttributeModifiersComponent component) {
        this.customAttributes = component;
    }
    //?}
}