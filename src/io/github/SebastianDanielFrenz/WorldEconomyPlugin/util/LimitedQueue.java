package io.github.SebastianDanielFrenz.WorldEconomyPlugin.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.NotImplementedException;

public class LimitedQueue<T> implements Queue<T> {

	public LimitedQueue(int size) {

	}

	private QueueElement<T> first;
	private QueueElement<T> last;

	private int size;
	private int populated_size;

	public void changeSize(int size) {
		while (populated_size > size) {
			remove();
		}

		this.size = size;
	}

	// queue stuff

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for (T e : c) {
			add(e);
		}
		return true;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		populated_size = 0;
	}

	@Override
	public boolean contains(Object o) {
		for (QueueElement<T> e = first; e != null; e = e.getNext()) {
			if (o.equals(e.getValue())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private QueueElement<T> e = first;

			@Override
			public boolean hasNext() {
				return e != null;
			}

			@Override
			public T next() {
				T out = e.getValue();
				e = e.getNext();
				return out;
			}
		};
	}

	@Override
	public boolean remove(Object o) {
		if (first == null) {
			return false;
		}
		if (o.equals(first)) {
			first = first.getNext();
			size--;
			if (first == null) {
				last = null;
			}
			populated_size--;
			return true;
		}
		for (QueueElement<T> e = first; e.getNext() != null; e = e.getNext()) {
			if (o.equals(e.getNext().getValue())) {
				e.setNext(e.getNext().getNext());
				if (e.getNext() == last) {
					last = e;
					size--;
				}
				populated_size--;
				return true;
			}
		}
		if (o.equals(last.getValue())) {
			if (size == 1) {
				first = null;
				last = null;
				size = 0;
				populated_size--;
				return true;
			}
			for (QueueElement<T> e = first; e != null; e = e.getNext()) {
				if (e.getNext() == last) {
					last = e;
					size--;
					populated_size--;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;
		for (Object o : c) {
			if (remove(o)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new NotImplementedException();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] out = new Object[size];
		QueueElement<T> e = first;
		for (int i = 0; i < size; i++) {
			out[i] = e.getValue();
			e = e.getNext();
		}
		return out;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T2> T2[] toArray(T2[] a) {
		T2[] out;
		if (a.length < size) {
			out = (T2[]) Array.newInstance(a.getClass(), size);
		} else {
			out = a;
		}

		QueueElement<T> e = first;
		for (int i = 0; i < size; i++) {
			out[i] = (T2) e.getValue();
			e = e.getNext();
		}

		if (size < out.length) {
			out[size] = null;
		}

		return out;
	}

	@Override
	public boolean add(T e) {
		if (populated_size >= size) {
			remove();
		}
		last.setNext(new QueueElement<T>(null, e));
		last = last.getNext();
		populated_size++;
		return true;
	}

	@Override
	public T element() {
		if (first == null) {
			throw new NoSuchElementException("The queue is empty!");
		} else {
			return first.getValue();
		}
	}

	@Override
	public boolean offer(T e) {
		add(e);
		return true;
	}

	@Override
	public T peek() {
		return first == null ? null : first.getValue();
	}

	@Override
	public T poll() {
		if (first == null) {
			return null;
		}
		T out = first.getValue();
		first = first.getNext();
		populated_size--;
		return out;
	}

	@Override
	public T remove() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		T out = first.getValue();
		first = first.getNext();
		populated_size--;
		return out;
	}

	public boolean isFull() {
		return populated_size >= size;
	}

}
