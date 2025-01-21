package elocindev.deathknights.mixin.entity.damage;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import elocindev.deathknights.config.Configs;
import elocindev.eternal_attributes.registry.SpellSchoolRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.spell_power.api.SpellDamageSource;

//? if 1.20.1 {
@Mixin(value = LivingEntity.class, priority = 1)
public abstract class DecayingDamageMixin {
    
    // Hacky solution meanwhile Spell Engine gets fixed
    @ModifyVariable(
        method = "damage",
        at = @At("HEAD"),
        argsOnly = true
    )
    private float death_knights$decayingDamageMult(float amount, DamageSource source) {
        if (source.getAttacker() == null || !(source.getAttacker() instanceof PlayerEntity player)) return amount;

        float newAmount = amount;

        int decayingCount = 0;

        for (int i = 0; i < player.getInventory().armor.size(); i++) {
            var list = player.getInventory().armor.get(i).getEnchantments();

            for (var enchant : list) {
                String enchantStr = enchant.toString();
                if (enchantStr.contains("id:\"death_knights:decaying\"")) {
                    int levelIndex = enchantStr.indexOf("lvl:") + 4;
                    int levelEndIndex = enchantStr.indexOf("s", levelIndex);
                    int level = Integer.parseInt(enchantStr.substring(levelIndex, levelEndIndex));

                    decayingCount += level;
                }
            }
        }

        if (source.getType().equals(SpellDamageSource.player(SpellSchoolRegistry.BLOOD, player).getType()) || source.getType().equals(SpellDamageSource.player(SpellSchoolRegistry.UNHOLY, player).getType())) {
            newAmount *= 1 + (Configs.Enchantments.DECAYING.bonus_per_level * decayingCount);
        }

        return newAmount;
    }
}
//?}