package io.github.SebastianDanielFrenz.WorldEconomyPlugin.util.cache;

public interface Cache<T> {

	/**
	 * Gets a value.
	 * 
	 * @param comparator
	 * @return a value from the cache or null if not in cache.
	 */
	public T get(CacheComparator<T> comparator, Object obj);

	/**
	 * This should be used after a cache miss to tell the cache to include the
	 * object.
	 * 
	 * @param obj
	 */
	public void learn(T obj);

	/**
	 * Should be called when a new value is inserted into the data base.
	 * 
	 * @param obj
	 */
	public void created(T obj);

	/**
	 * Should be called when a value is removed from the data base. If not
	 * called upon removal, the cache will keep returning ghost values.
	 * 
	 * @param obj
	 * @return
	 */
	public boolean deleted(T obj);

	public boolean isFull();

}
