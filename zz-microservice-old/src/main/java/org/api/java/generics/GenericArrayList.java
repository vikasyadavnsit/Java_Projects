package org.api.java.generics;

import java.util.Arrays;

public class GenericArrayList<U> implements GenericList<U> {

	Integer loadFactor;
	Integer size;
	Integer index;
	private U arr[];

	@SuppressWarnings("unchecked")
	GenericArrayList(Integer... a) {
		switch (a.length) {
		case 2:
			if (a[1] < 1)
				this.loadFactor = a[1];
		case 1:
			if (a[0] < 1)
				this.size = a[0];
			break;
		default:
			this.loadFactor = 75;
			this.size = 16;
			break;
		}
		this.index = -1;
		this.arr = (U[]) new Object[this.size];
	}

	private void scale() {
		int clf = Math.round((this.index / this.size) * 10);
		if (clf > this.loadFactor) {
			this.size *= 2;
			isBounded(this.size);
			this.arr = Arrays.copyOf(this.arr, this.size);
		}
	}

	void isBounded(Integer i) {
		if (i < 0 || i >= this.size) {
			throw new IndexOutOfBoundsException("Index out of bound for : " + i);
		}
	}

	@Override
	public U get(Integer i) {
		isBounded(i);
		return this.arr[i];
	}

	@Override
	public Integer add(U obj) {
		scale();
		this.arr[++index] = obj;
		return index;
	}

	@Override
	public Boolean delete(int i) throws IllegalAccessException {
		isBounded(i);
		if (i > index) {
			throw new IllegalAccessException("No Such Element at index : " + i);
		}
		for (int j = 0, k = 0; j < this.size; j++) {
			if (i == j)
				continue;
			else
				this.arr[k++] = this.arr[j];
		}
		return true;
	}

	@Override
	public Boolean delete(U obj) {
		return null;
	}

	@Override
	public String toString() {
		return "GenericArrayList [arr=" + Arrays.toString(arr) + "]";
	}

}
