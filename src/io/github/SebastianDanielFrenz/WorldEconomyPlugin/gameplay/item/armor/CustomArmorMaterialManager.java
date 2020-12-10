package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.armor;

import java.util.Set;
import java.util.TreeSet;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ArmorItemDetail;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.material.CustomArmorMaterial;

public class CustomArmorMaterialManager {

	private static Set<CustomArmorMaterial> custom_armor_materials = new TreeSet<CustomArmorMaterial>();

	public static void init() {
		CustomArmorMaterial material;

		for (CustomItem customItem : CustomItemRegistry.getContents()) {
			if (customItem.hasDetail(ArmorItemDetail.class)) {
				material = customItem.getDetail(ArmorItemDetail.class).getCustomArmorMaterial();
				if (custom_armor_materials.add(material)) {
					WorldEconomyPlugin.plugin.getLogger().info("Found custom armor material " + material.toString());
				}
			}
		}
	}

}
