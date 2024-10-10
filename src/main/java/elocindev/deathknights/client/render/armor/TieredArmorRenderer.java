package elocindev.deathknights.client.render.armor;

import elocindev.deathknights.item.armor.TieredArmor;
import mod.azure.azurelibarmor.renderer.GeoArmorRenderer;

public class TieredArmorRenderer extends GeoArmorRenderer<TieredArmor> {
    public TieredArmorRenderer(String name) {
        super(new TieredArmorModel(name));
    }
}
