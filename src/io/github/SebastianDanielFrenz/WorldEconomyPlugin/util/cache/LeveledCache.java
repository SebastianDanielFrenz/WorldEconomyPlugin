package io.github.SebastianDanielFrenz.WorldEconomyPlugin.util.cache;

public abstract class LeveledCache<T> implements Cache<T> {

	public LeveledCache(LevelableCache<T>[] caches) {
		this.caches = caches;
	}

	private LevelableCache<T>[] caches;
	
	@Override
	public T get(CacheComparator<T> comparator) {
		T out;
		for (LevelableCache<T> cache:caches) {
			out = cache.getLeveled(comparator);
			if ()
		}
	}

}
