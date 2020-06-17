package io.github.SebastianDanielFrenz.WorldEconomyPlugin.util;

public class QueueElement<T> {

	public QueueElement(QueueElement<T> next, T value) {
		this.setNext(next);
		this.setValue(value);
	}

	public QueueElement<T> getNext() {
		return next;
	}

	public void setNext(QueueElement<T> next) {
		this.next = next;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	private QueueElement<T> next;
	private T value;

}
