package com.erikleeness.graph.sort.algorithms;

import com.erikleeness.graph.sort.SortAlgorithm;

public class InsertionSortAlgorithm extends SortAlgorithm
{
	public InsertionSortAlgorithm(int delayMS)
	{
		super(delayMS);
	}

	public void sort(int[] data)
	{
		for (int elementIndex = 1; elementIndex < data.length; elementIndex++) {

			int element = data[elementIndex];
			int swapIndex = elementIndex; 
			
			while ((swapIndex > 0) && (data[swapIndex - 1] > element)) {
				System.out.println("A");
				data[swapIndex] = data[swapIndex - 1];
				swapIndex--;
				notifyObservers();
			}
			
			data[swapIndex] = element;
			notifyObservers();
		}
	}
	
	
}
