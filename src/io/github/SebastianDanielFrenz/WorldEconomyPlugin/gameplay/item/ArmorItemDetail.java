package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.material.CustomArmorMaterial;

public class ArmorItemDetail extends ItemDetail {

	public ArmorItemDetail(CustomArmorMaterial armorMaterial) {
		this.armorMaterial = armorMaterial;
	}

	private CustomArmorMaterial armorMaterial;

	public double processEvent(CustomDamageType damage_type, double damage) {
		return armorMaterial.getResistance(damage_type);
	}

	public CustomArmorMaterial getCustomArmorMaterial() {
		return armorMaterial;
	}

}
