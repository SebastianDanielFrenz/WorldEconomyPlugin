package io.github.SebastianDanielFrenz.WorldEconomyPlugin.util.cache;

public interface CacheComparator<T> {

	public boolean isEqual(Object obj, T cached);

}
