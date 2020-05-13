package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_12_R1.BiomeBase;
import net.minecraft.server.v1_12_R1.BiomeBase.BiomeMeta;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityTypes;
import net.minecraft.server.v1_12_R1.MinecraftKey;

public final class NMSUtil {

	public static void registerEntity(final String name, final int id, final Class<? extends Entity> nmsClass,
			final Class<? extends Entity> customClass) {
		try {
			final List<Map<?, ?>> dataMaps = new ArrayList<Map<?, ?>>();
			// System.out.println("MAP: " + Map.class.getSimpleName());
			for (Field f : EntityTypes.class.getDeclaredFields()) {
				// System.out.println(f.getName() + ", type: " +
				// f.getType().getSimpleName());
				if (f.getType().getSimpleName().equals(Map.class.getSimpleName())) {
					// System.out.println(f.getType().getSimpleName());
					f.setAccessible(true);
					dataMaps.add((Map<?, ?>) f.get(null));
				}
			}
			EntityTypes.b.a(id, new MinecraftKey(name), customClass);
			for (Field f : BiomeBase.class.getDeclaredFields()) {
				if (f.getType().getSimpleName().equals(BiomeBase.class.getSimpleName())) {
					if (f.get(null) != null) {
						for (Field list : BiomeBase.class.getDeclaredFields()) {
							if (list.getType().getSimpleName().equals(List.class.getSimpleName())) {
								list.setAccessible(true);
								@SuppressWarnings("unchecked")
								List<BiomeMeta> metaList = (List<BiomeMeta>) list.get(f.get(null));

								for (BiomeMeta meta : metaList) {
									Field clazz = BiomeMeta.class.getDeclaredFields()[0];
									if (clazz.get(meta).equals(nmsClass))
										clazz.set(meta, customClass);
								}
							}
						}
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
