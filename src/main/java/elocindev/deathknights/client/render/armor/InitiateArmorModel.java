package elocindev.deathknights.client.render.armor;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.item.armor.InitiateArmor;
import net.minecraft.util.Identifier;

//? if 1.20.1 {
import mod.azure.azurelibarmor.model.GeoModel;
//?} else {
/*import mod.azure.azurelibarmor.common.api.client.model.GeoModel;
*///?}

public class InitiateArmorModel extends GeoModel<InitiateArmor> {
    @Override
    public Identifier getModelResource(InitiateArmor object) {
        return new Identifier(DeathKnights.MODID, "geo/initiate_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(InitiateArmor armor) {
        return new Identifier(DeathKnights.MODID, "textures/armor/initiate.png");
    }

    @Override
    public Identifier getAnimationResource(InitiateArmor animatable) {
        return null;
    }
}