package com.erikleeness.graph.sort.algorithms;

import com.erikleeness.graph.sort.SortAlgorithm;

public class MergeSortAlgorithm extends SortAlgorithm
{
	private int[] trackingArray;
	
	public MergeSortAlgorithm(int delayMS)
	{
		super(delayMS);
	}

	public void sort(int[] data)
	{
		// Mergesort in-place is supposed to be slower than
		// one that uses extra memory, so we'll choose the 
		// latter.
		trackingArray = data;
		mergesort(data, 0, data.length - 1);
		
	}
	
	private int[] mergesort(int[] data, int startIndex, int endIndex)
	{
		/* startIndex and endIndex (inclusive) refer to what elements in the 
		 * original array are being accessed by this particular recursive 
		 * attempt.
		 * 
		 * They are primarily used to keep track of what is being modified within
		 * any given call to mergesort(), so we can track it externally.
		 */
		if (data.length == 1) {
			return data;
		} else {
			int[] unsortedLeft = new int[data.length/2];
			int[] unsortedRight = new int[data.length - data.length/2];
			System.arraycopy(data, 0, unsortedLeft, 0, data.length/2);
			System.arraycopy(data, data.length/2, unsortedRight, 0, data.length - data.length/2);
			
			int[] left = mergesort(unsortedLeft,
					startIndex, startIndex + data.length/2 - 1);
			int[] right = mergesort(unsortedRight,
					startIndex + data.length/2, endIndex);
			
			int[] result = new int[data.length];
			int leftPointer = 0;
			int rightPointer = 0;
			int resultPointer = 0;
			
			while (resultPointer < result.length && leftPointer < left.length && rightPointer < right.length) {
				if (left[leftPointer] <= right[rightPointer]) {
					result[resultPointer] = left[leftPointer];
					leftPointer++;
				} else if (left[leftPointer] > right[rightPointer]) {
					result[resultPointer] = right[rightPointer];
					rightPointer++;
				}

				notifyObservers(result[resultPointer], startIndex + resultPointer);
				resultPointer++;
			}
			
			// Place any remaining items from the non-empty half
			while (leftPointer < left.length) {
				result[resultPointer] = left[leftPointer];
				leftPointer++;
				notifyObservers(result[resultPointer], startIndex + resultPointer);
				resultPointer++;
			}
			
			while (rightPointer < right.length) {
				result[resultPointer] = right[rightPointer];
				rightPointer++;
				notifyObservers(result[resultPointer], startIndex + resultPointer);
				resultPointer++;
			}
			
			return result;
			
		}
	}
	
	public void notifyObservers(int alteredData, int alteredDataIndex)
	{
		trackingArray[alteredDataIndex] = alteredData;
		notifyObservers();
	}
	
}
