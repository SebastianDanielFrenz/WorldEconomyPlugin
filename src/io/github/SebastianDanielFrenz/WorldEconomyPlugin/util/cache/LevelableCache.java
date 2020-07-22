package io.github.SebastianDanielFrenz.WorldEconomyPlugin.util.cache;

public interface LevelableCache<T> extends Cache<T> {

	public T getLeveled(CacheComparator<T> comparator);

}
