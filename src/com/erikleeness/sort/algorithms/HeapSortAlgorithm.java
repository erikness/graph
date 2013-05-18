package com.erikleeness.sort.algorithms;

import com.erikleeness.sort.SortAlgorithm;

/**
 * Heap sort, where most of the work requiring tuning and analysis 
 * is done in the Heap class.
 * 
 * @author Erik
 *
 */
public class HeapSortAlgorithm extends SortAlgorithm
{
	public HeapSortAlgorithm(int delayMS)
	{
		super(delayMS);
	}
	
	public void sort(int[] data)
	{
		heapify(data);
		
		int heapEnd = data.length - 1;
		// One by one, swap the top of the heap with the last element and 
		// reheap from that last element, the "new top"
		while (heapEnd > 0) {
			
			int oldTop = data[0];
			int newTop = data[heapEnd];
			data[0] = newTop;
			data[heapEnd] = oldTop;
			heapEnd--;
			notifyObservers();
			
			siftDown(0, heapEnd, data);
			
		}
		
	}
	
	/** 
	 * Turns the array into one that satisfies the (max) heap property.
	 * TODO: Implement a linear algoritm as this is naive and O(n log n)
	 * 
	 * @param data
	 * A reference to the data that sort() is supposed to be working on.
	 */
	public void heapify2(int[] data)
	{
		int[] heap = new int[data.length];
		int firstOpenIndex = 0;


		for (int dataElementIndex = 0; dataElementIndex < data.length; dataElementIndex++) {
			
			int element = data[dataElementIndex];
			siftUp(element, firstOpenIndex, heap);
			
		}
	}
	
	public void heapify(int[] data)
	{
		int start = (data.length - 1) / 2;
		
		while (start >= 0) {
			siftDown(start, data.length - 1, data);
			start--;
		}
	}
	
	/**
	 * Given an element, the a partially constructed heap, and the
	 * end of that partially constructed heap, we add an element at
	 * the end and swap upwards if necessary.
	 * 
	 * @param element
	 * Element to add to the heap
	 * @param firstOpenIndex
	 * First open index of the heap, a logical "end + 1"
	 * @param heap
	 * Reference to the heap array we're building
	 */
	private void siftUp(int element, int firstOpenIndex, int[] heap)
	{
		heap[firstOpenIndex] = element;
		int heapElementIndex = firstOpenIndex;
		firstOpenIndex++;
		notifyObservers();
		
		int parentIndex = (heapElementIndex + (heapElementIndex % 2) - 2) / 2;
		int parent = heap[parentIndex];
		
		
		
		while (element > parent && parentIndex >= 0) {
			parent = heap[parentIndex];
			heap[heapElementIndex] = parent;
			heap[parentIndex] = element;
			heapElementIndex = parentIndex;
			
			// New parent (as the old one is now a child)
			parentIndex = (heapElementIndex + (heapElementIndex % 2) - 2) / 2;
			notifyObservers();
		}
	}
	
	private void siftDown(int elementIndex, int heapEnd, int[] heap)
	{
		int element = heap[elementIndex];
		
		int leftChildIndex = 2*elementIndex + 1;
		int rightChildIndex = leftChildIndex + 1;
		
		while (leftChildIndex <= heapEnd) {
			int toSwitch = elementIndex;
			
			if (heap[leftChildIndex] > heap[toSwitch]) {
				toSwitch = leftChildIndex;
			}
			
			if (rightChildIndex <= heapEnd && heap[toSwitch] < heap[rightChildIndex]) {
				toSwitch = rightChildIndex;
			}
			
			if (heap[toSwitch] != element) {
				heap[elementIndex] = heap[toSwitch];
				heap[toSwitch] = element;
				notifyObservers();
				
				elementIndex = toSwitch;
				leftChildIndex = 2*elementIndex + 1;
				rightChildIndex = leftChildIndex + 1;
			} else {
				return;
			}
			
		}
	}
}
