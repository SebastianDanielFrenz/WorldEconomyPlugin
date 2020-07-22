package io.github.SebastianDanielFrenz.WorldEconomyPlugin.util.cache;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.NotImplementedException;

public class FixedCache<T> implements LevelableCache<T> {

	/**
	 * This cache searching using ==, not using .equals.
	 * 
	 * @param size
	 */
	public FixedCache(int size) {
		stored = new Object[size];
	}

	private Object[] stored;
	private int current_size = 0;

	@SuppressWarnings("unchecked")
	@Override
	public T get(CacheComparator<T> comparator, Object obj) {
		for (int i = 0; i < current_size; i++) {
			if (comparator.isEqual(obj, (T) stored[i])) {
				if (i != 0) {
					Object tmp = stored[i - 1];
					stored[i - 1] = stored[i];
					stored[i] = tmp;
				}
				return (T) stored[i];
			}
		}
		return null;
	}

	@Override
	public void learn(T obj) {
		if (stored.length == current_size) {
			stored[stored.length - 1] = obj;
		} else {
			stored[current_size] = obj;
			current_size++;
		}
	}

	@Override
	public void created(T obj) {
	}

	@Override
	public boolean deleted(T obj) {
		if (current_size == 0) {
			return false;
		}
		for (int i = 0; i < current_size; i++) {
			if (stored[i] == obj) {
				stored[i] = stored[current_size - 1];
				stored[current_size - 1] = null;
				current_size--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isFull() {
		return current_size == stored.length;
	}

	@Override
	public T getLeveled(CacheComparator<T> comparator) {
		throw new NotImplementedException();
	}

}
