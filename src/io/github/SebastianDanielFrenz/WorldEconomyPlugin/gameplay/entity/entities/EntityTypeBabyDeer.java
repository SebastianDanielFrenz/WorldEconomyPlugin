package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.entities;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.CustomEntityType;
import net.minecraft.server.v1_12_R1.Entity;

public class EntityTypeBabyDeer implements CustomEntityType {

	@Override
	public String getEntityID() {
		return "baby_deer";
	}

	@Override
	public String getCustomEntityName() {
		return Age.EARLY_STONE_AGE.color + "Baby Deer";
	}

	@Override
	public Class<? extends Entity> getCustomEntityClass() {
		return EntityBabyDeer.class;
	}

}
