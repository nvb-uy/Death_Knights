package elocindev.deathknights.client.render.armor;
import elocindev.deathknights.item.armor.InitiateArmor;
import mod.azure.azurelibarmor.renderer.GeoArmorRenderer;


public class InitiateArmorRenderer extends GeoArmorRenderer<InitiateArmor> {
    public InitiateArmorRenderer() {
        super(new InitiateArmorModel());
    }
}
