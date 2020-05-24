package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity;

import org.bukkit.entity.LivingEntity;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchableObject;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.StatisticalObject;
import net.minecraft.server.v1_12_R1.Entity;

public interface CustomEntityType extends StatisticalObject, ResearchableObject {

	public String getEntityID();

	@Override
	default String getStatisticID() {
		return "entity_" + getEntityID();
	}

	public String getCustomEntityName();

	public default LivingEntity getVanilla() {
		return (LivingEntity) this;
	}

	public Class<? extends Entity> getCustomEntityClass();

}