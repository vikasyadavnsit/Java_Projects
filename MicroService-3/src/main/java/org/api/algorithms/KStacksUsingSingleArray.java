package org.api.algorithms;

import java.util.Arrays;

public class KStacksUsingSingleArray {

	int n, k;
	int arr[];
	int top[];
	int next[];
	int free;

	public KStacksUsingSingleArray(int n, int k) {
		this.arr = new int[n];
		this.top = new int[k];
		this.next = new int[n];
		this.free = 0;

		Arrays.fill(this.top, -1);
		for (int i = 0; i < n; i++)
			this.next[i] = i + 1;
		this.next[n - 1] = -1;
	}

	boolean isFull() {
		return free == -1;
	}

	boolean isEmpty(int sn) {
		return this.top[sn] == -1;
	}

	void push(int sn, int x) {
		if (isFull()) {
			System.err.println("Stack Overflow");
			return;
		}

		int i = free;
		free = next[i];
		next[i] = top[sn];
		top[sn] = i;
		arr[i] = x;
	}

	int pop(int sn) {
		if (isEmpty(sn)) {
			System.err.println("Stack Underflow");
			return Integer.MIN_VALUE;
		}
		int i = top[sn];
		top[sn] = next[i];
		next[i] = free;
		free = i;
		return arr[i];
	}

	public static void main(String[] args) {
		KStacksUsingSingleArray obj = new KStacksUsingSingleArray(10, 3);
		obj.push(0, 1);
		obj.push(1, 2);
		obj.push(2, 3);
		obj.push(0, 4);
		obj.push(0, 5);

		System.out.println(obj.pop(1));
		System.out.println(obj.pop(0));
		System.out.println(obj.pop(2));
		System.out.println(obj.pop(1));
		System.out.println(obj.pop(0));
		System.out.println(obj.pop(0));

	}
}
