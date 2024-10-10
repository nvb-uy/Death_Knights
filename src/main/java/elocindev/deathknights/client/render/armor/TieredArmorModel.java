package elocindev.deathknights.client.render.armor;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.item.armor.TieredArmor;
import mod.azure.azurelibarmor.model.GeoModel;
import net.minecraft.util.Identifier;

public class TieredArmorModel extends GeoModel<TieredArmor> {
    String name;

    public TieredArmorModel(String name) {
        super();
        this.name = name;
    }

    @Override
    public Identifier getModelResource(TieredArmor object) {
        return new Identifier(DeathKnights.MODID, "geo/"+this.name+"_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(TieredArmor armor) {
        return new Identifier(DeathKnights.MODID, "textures/armor/"+this.name+".png");
    }

    @Override
    public Identifier getAnimationResource(TieredArmor animatable) {
        return null;
    }
}