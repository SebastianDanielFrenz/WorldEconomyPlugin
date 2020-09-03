package io.github.SebastianDanielFrenz.WorldEconomyPlugin.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OrderedList<T> implements List<T> {

	private Comparator<T> comparator;

	private ArrayList<T> sublist = new ArrayList<T>();

	public OrderedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public boolean add(T e) {
		int r;
		for (int i = 0; i < sublist.size(); i++) {
			r = comparator.compare(e, sublist.get(i));

			if (r != -1) {
				sublist.add(i, e);
				return true;
			}
		}
		sublist.add(e);
		return true;
	}

	@Override
	public void add(int index, T element) {
		add(element);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for (T e : c) {
			add(e);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return addAll(c);
	}

	@Override
	public void clear() {
		sublist = new ArrayList<T>();
	}

	@Override
	public boolean contains(Object o) {
		return sublist.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return sublist.containsAll(c);
	}

	@Override
	public T get(int index) {
		return sublist.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return sublist.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return sublist.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return sublist.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return sublist.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return sublist.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return sublist.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return sublist.remove(o);
	}

	@Override
	public T remove(int index) {
		return sublist.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return sublist.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return sublist.retainAll(c);
	}

	@Override
	public T set(int index, T element) {
		return sublist.set(index, element);
	}

	@Override
	public int size() {
		return sublist.size();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return sublist.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return sublist.toArray();
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		return sublist.toArray(a);
	}

}
