package elocindev.deathknights.api.core;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SpellEffect extends StatusEffect {
    protected SpellEffect(StatusEffectCategory category, int color) { super(category, color); }
}