package com.erikleeness.graph.sort.algorithms;

import com.erikleeness.graph.sort.SortAlgorithm;

public class SelectionSortAlgorithm extends SortAlgorithm
{
	public SelectionSortAlgorithm(int delayMS)
	{
		super(delayMS);
	}
	
	public void sort(int[] data)
	{
		selectionsort(data);
	}
	
	public void selectionsort(int[] data)
	{
		for (int currentIndex = 0; currentIndex < data.length; currentIndex++) {
		
			int runningMin = data[currentIndex];
			int runningMinIndex = currentIndex;
			
			for (int candidateIndex = currentIndex; candidateIndex < data.length; candidateIndex++) {
				if (data[candidateIndex] < runningMin) {
					runningMinIndex = candidateIndex;
					runningMin = data[candidateIndex];
				}
			}
			
			data[runningMinIndex] = data[currentIndex];
			data[currentIndex] = runningMin;
			
			notifyObservers();
			
		}
	}
	
}
	