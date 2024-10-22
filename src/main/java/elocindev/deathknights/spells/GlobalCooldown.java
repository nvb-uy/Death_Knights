package elocindev.deathknights.spells;

import net.minecraft.entity.player.PlayerEntity;
import net.spell_engine.api.event.CombatEvents;
import java.util.List;

public class GlobalCooldown {
    public static final List<String> gcd_spells = List.of(
        "death_knights:death_strike",
        "death_knights:obliterate",
        "death_knights:festering_strike",
        "death_knights:lesser_death_strike",
        "death_knights:lesser_obliterate",
        "death_knights:lesser_festering_strike",
        "death_knights:marrowrend",
        "death_knights:frost_strike"
    );

    public static void register() {
        CombatEvents.SPELL_CAST.register(
            args -> {
                PlayerEntity caster = args.caster();
                if (caster == null) return;

                if (gcd_spells.contains(args.spell().id().toString())) {
                    caster.resetLastAttackedTicks();
                }
            }
        );
    }
}
