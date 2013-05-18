package com.erikleeness.sort.algorithms;

import com.erikleeness.sort.SortAlgorithm;

public class QuickSortAlgorithm extends SortAlgorithm
{	
			
	public QuickSortAlgorithm(int delayMS)
	{
		super(delayMS);
	}
	
	/**
	 * The implementation of quicksort, to be recursive, must have
	 * some more parameters. Luckily, we know what those have to be
	 * for the first call - 0 and data.length, the start and end.
	 */
	public void sort(int[] data)
	{
		quicksort(data, 0, data.length);
	}
	/**
	 * Acts on the (reference to the) array passed.
	 * startIndex is inclusive, endIndex is exclusive
	 */
	public void quicksort(int[] data, int startIndex, int endIndex)
	{
		if (endIndex - startIndex <= 1) {
		
			// already sorted
			
		} else if (endIndex - startIndex == 2) {
		
			if (data[startIndex] > data[startIndex + 1]) {
				// Wrong order
				int temp = data[startIndex];
				data[startIndex] = data[startIndex + 1];
				data[startIndex + 1] = temp;
			}
			
			notifyObservers();
			
			
		} else { // sample is 3 or more
		
			int pivotIndex = endIndex - 1; // as endIndex is exclusive
			int pivot = data[pivotIndex];
			
			// Find an index from the start that is greater than pivot
			// and another index from the end that is less than pivot
			int leftSwitchIndex = startIndex;
			int leftSwitch = data[leftSwitchIndex];
			int rightSwitchIndex = endIndex - 1;
			int rightSwitch = data[rightSwitchIndex];

			while (leftSwitchIndex < rightSwitchIndex)
			{
				while (leftSwitch < pivot) {
					leftSwitchIndex++;
					leftSwitch = data[leftSwitchIndex];
				}
				
				// Find an index from the end that is less than pivot
				while (rightSwitch > pivot) {
					rightSwitchIndex--;
					rightSwitch = data[rightSwitchIndex];
				}
				
				if (leftSwitchIndex <= rightSwitchIndex) {
					// The indexes haven't crossed
					boolean delay = true;
					if (leftSwitchIndex == rightSwitchIndex) delay = false;
					data[rightSwitchIndex] = leftSwitch;
					data[leftSwitchIndex] = rightSwitch;
					leftSwitchIndex++;
					rightSwitchIndex--;
					leftSwitch = data[leftSwitchIndex];
					rightSwitch = data[rightSwitchIndex];
					
					// This ensures there is no delay when we swap something with itself
					if (delay) notifyObservers();
				}
			}
			
			quicksort(data, startIndex, rightSwitchIndex + 1);
			quicksort(data, leftSwitchIndex, endIndex);
			
		}
		
	}
}