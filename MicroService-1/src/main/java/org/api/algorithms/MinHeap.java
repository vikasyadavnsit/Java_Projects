package org.api.algorithms;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class MinHeap {

	private int[] arr;
	private int capacity;
	private int size;

	MinHeap() {
		capacity = 16;
		size = 0;
		arr = new int[capacity];
	}

	public void ensureCapacity() {
		if (size == capacity) {
			arr = Arrays.copyOf(arr, capacity = 2 * capacity);
		}
	}

	public int getParent(int i) {
		if (i == 0)
			return 0;
		return (i - 1) / 2;
	}

	public int getLeftChild(int i) {
		return 2 * i + 1;
	}

	public int getRightChild(int i) {
		return 2 * i + 2;
	}

	public void swap(int a, int b) {
		arr[a] = arr[a] ^ arr[b];
		arr[b] = arr[a] ^ arr[b];
		arr[a] = arr[a] ^ arr[b];
	}

	public void heapify_down(int i) {
		int l = getLeftChild(i);
		int r = getRightChild(i);
		int smallest = i;
		if (l < size && arr[l] < arr[smallest])
			smallest = l;
		if (r < size && arr[r] < arr[smallest])
			smallest = r;
		if (smallest != i) {
			swap(i, smallest);
			heapify_down(smallest);
		}
	}

	public void heapify_up(int i) {
		int p = getParent(i);
		if (p >= 0 && arr[p] > arr[i]) {
			swap(p, i);
			heapify_up(p);
		}
	}

	public int poll() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		int root = arr[0];
		arr[0] = arr[--size];
		heapify_down(0);
		return root;
	}

	public int peek() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		return arr[0];
	}

	public void add(int data) {
		ensureCapacity();
		arr[size] = data;
		heapify_up(size++);
	}

	public int size() {
		return size;
	}

	public static void main(String[] args) {
		MinHeap heap = new MinHeap();
		IntStream.range(0, 30).forEach(x -> heap.add(30 - x));

		while (heap.size() > 0)
			System.out.println(heap.poll());

		// Priority Queue
		System.out.println("Starting with Priority Queue");
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		IntStream.range(0, 10).forEach(x -> queue.add(10 - x));
		while (queue.size() > 0)
			System.out.println(queue.poll());
	}

}
