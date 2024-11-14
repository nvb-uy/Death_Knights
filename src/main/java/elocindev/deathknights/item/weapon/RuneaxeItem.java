package elocindev.deathknights.item.weapon;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import elocindev.deathknights.api.DKAttributeAPI;
import elocindev.deathknights.api.types.RunebladeSize;
import elocindev.deathknights.api.types.RunebladeType;
import elocindev.deathknights.registry.ItemRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;

public class RuneaxeItem extends AxeItem {
    private Multimap<EntityAttribute, EntityAttributeModifier> mainHandAttributes;
    private Multimap<EntityAttribute, EntityAttributeModifier> offHandAttributes;

    private RunebladeType type;
    private RunebladeSize size;
    private float spellPowerAmount;
    private boolean isAddition;

    public RuneaxeItem(RunebladeType type, RunebladeSize size, ToolMaterial material, int attackDamage, float attackSpeed, float attributeAmount, boolean isAddition) {
        this(type, size, material, attackDamage, attackSpeed, attributeAmount, isAddition, false);
    }

    public RuneaxeItem(RunebladeType type, RunebladeSize size, ToolMaterial material, int attackDamage, float attackSpeed, float attributeAmount, boolean isAddition, boolean isRubyorAeternium) {
        super(
            new ToolMaterial() {
                @Override
                public int getDurability() {
                    return material.getDurability();
                }

                @Override
                public float getMiningSpeedMultiplier() {
                    return material.getMiningSpeedMultiplier();
                }

                @Override
                public float getAttackDamage() {
                    if (isRubyorAeternium) {
                        return material.getAttackDamage() - 0.5f;
                    }

                    return material.getAttackDamage();
                }

                @Override
                public int getEnchantability() {
                    return material.getEnchantability();
                }

                @Override
                public int getMiningLevel() {
                    return material.getMiningLevel();
                }

                @Override
                public Ingredient getRepairIngredient() {
                    return Ingredient.ofItems(new ItemConvertible[]{ItemRegistry.RUNECARVED_STONE});
                }

            },
        attackDamage, attackSpeed, new FabricItemSettings());

        this.type = type;
        this.size = size;
        this.spellPowerAmount = attributeAmount;
        this.isAddition = isAddition;

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> mh_modifiers = ImmutableMultimap.builder();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> oh_modifiers = ImmutableMultimap.builder();

        this.buildMainHandAttributes(mh_modifiers);
        this.buildOffHandAttributes(oh_modifiers);

        this.mainHandAttributes = mh_modifiers.build();  
        this.offHandAttributes = oh_modifiers.build();
    }

    public RunebladeType getType() {
        return this.type;
    }

    public boolean isTwoHanded() {
        return this.size == RunebladeSize.TWO_HANDED;
    }

    public boolean isOneHanded() {
        return this.size == RunebladeSize.ONE_HANDED;
    }
    
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        switch (slot) {
            case MAINHAND:
                return this.mainHandAttributes;
            case OFFHAND:
                return this.offHandAttributes;
            default:
                return super.getAttributeModifiers(slot);
        }
    }

    private void buildMainHandAttributes(ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> modifiers) {
        modifiers.putAll(super.getAttributeModifiers(EquipmentSlot.MAINHAND));
        EntityAttributeModifier.Operation operator = this.isAddition ? EntityAttributeModifier.Operation.ADDITION : EntityAttributeModifier.Operation.MULTIPLY_BASE;

        DKAttributeAPI.buildMagicAttributes(modifiers, this.type, UUID.fromString("0484aa27-cc29-47ce-9ad9-ff7d88eb84e3"), operator, this.spellPowerAmount);
    }

    private void buildOffHandAttributes(ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> modifiers) {
        if (this.size != RunebladeSize.ONE_HANDED) return;
        EntityAttributeModifier.Operation operator = this.isAddition ? EntityAttributeModifier.Operation.ADDITION : EntityAttributeModifier.Operation.MULTIPLY_BASE;
        
        DKAttributeAPI.buildMagicAttributes(modifiers, this.type, UUID.fromString("a8082dfc-871c-4a75-80e3-4cc6ec2ffbb0"), operator, this.spellPowerAmount);
    }    
}
