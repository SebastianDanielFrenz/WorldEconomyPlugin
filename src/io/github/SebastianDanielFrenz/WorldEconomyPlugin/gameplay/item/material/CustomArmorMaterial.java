package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.material;

import java.util.Map;
import java.util.TreeMap;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.api.WorldEconomyExtension;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.api.WECP.WECPArmorAttachment;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomDamageType;

public class CustomArmorMaterial {

	private Map<CustomDamageType, Double> resistances = new TreeMap<CustomDamageType, Double>();
	private Map<WorldEconomyExtension, WECPArmorAttachment> extension_attachments = new TreeMap<WorldEconomyExtension, WECPArmorAttachment>();

	private double base_durability;

	public double getResistance(CustomDamageType damage_type) {
		return resistances.get(damage_type);
	}

	public WECPArmorAttachment getAttachment(WorldEconomyExtension extension) {
		return extension_attachments.get(extension);
	}

}
