package elocindev.deathknights.client.render.armor;

import elocindev.deathknights.item.armor.TieredArmor;

//? if 1.20.1 {
import mod.azure.azurelibarmor.renderer.GeoArmorRenderer;
//?} else {
/*import mod.azure.azurelibarmor.common.api.client.renderer.GeoArmorRenderer;
*///?}

public class TieredArmorRenderer extends GeoArmorRenderer<TieredArmor> {
    public TieredArmorRenderer(String name) {
        super(new TieredArmorModel(name));
    }
}
