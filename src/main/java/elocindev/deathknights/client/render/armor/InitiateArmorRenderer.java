package elocindev.deathknights.client.render.armor;
import elocindev.deathknights.item.armor.InitiateArmor;

//? if 1.20.1 {
import mod.azure.azurelibarmor.renderer.GeoArmorRenderer;
//?} else {
/*import mod.azure.azurelibarmor.common.api.client.renderer.GeoArmorRenderer;
*///?}

public class InitiateArmorRenderer extends GeoArmorRenderer<InitiateArmor> {
    public InitiateArmorRenderer() {
        super(new InitiateArmorModel());
    }
}
