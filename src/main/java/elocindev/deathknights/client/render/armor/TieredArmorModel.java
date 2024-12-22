package elocindev.deathknights.client.render.armor;

import elocindev.deathknights.DeathKnights;
import elocindev.deathknights.item.armor.TieredArmor;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import elocindev.necronomicon.api.ResourceIdentifier;

//? if 1.20.1 {
import mod.azure.azurelibarmor.model.GeoModel;
//?} else {
/*import mod.azure.azurelibarmor.common.api.client.model.GeoModel;
*///?}

public class TieredArmorModel extends GeoModel<TieredArmor> {
    String name;

    public TieredArmorModel(String name) {
        super();
        this.name = name;
    }

    @Override
    public Identifier getModelResource(TieredArmor object) {
        String id = Registries.ITEM.getId(object).getPath();

        return ResourceIdentifier.get(DeathKnights.MODID, "geo/"+id+".geo.json");
    }

    @Override
    public Identifier getTextureResource(TieredArmor armor) {
        String id = Registries.ITEM.getId(armor).getPath();
        id = id.substring(0, id.length() - 6);

        return ResourceIdentifier.get(DeathKnights.MODID, "textures/armor/"+this.name+".png");
    }

    @Override
    public Identifier getAnimationResource(TieredArmor animatable) {
        return null;
    }
}