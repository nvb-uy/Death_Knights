package elocindev.deathknights.util;

import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;

public class AttributeUtil {
    public static Operation getAddition() {
        //? if 1.20.1 {
        return Operation.ADDITION;
        //?} else {
        /*return Operation.ADD_VALUE;
        *///?}
    }

    public static Operation getMultiplyBase() {
        //? if 1.20.1 {
        return Operation.MULTIPLY_BASE;
        //?} else {
        /*return Operation.ADD_MULTIPLIED_BASE;
        *///?}
    }

    public static Operation getMultiplyTotal() {
        //? if 1.20.1 {
        return Operation.MULTIPLY_TOTAL;
        //?} else {
        /*return Operation.ADD_MULTIPLIED_TOTAL;
        *///?}
    }
}
