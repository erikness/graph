package com.erikleeness.graph.sort.algorithms;

import com.erikleeness.graph.sort.SortAlgorithm;

public class BubbleSortAlgorithm extends SortAlgorithm
{
	public BubbleSortAlgorithm(int delayMS)
	{
		super(delayMS);
	}
	
	public void sort(int[] data)
	{
		bubblesort(data);
	}
	
	private void bubblesort(int[] data)
	{
		for (int end = data.length - 1; end >= 1; end--) {
			for (int i = 0; i < end; i++) {
				if (data[i] > data[i+1]) {
					int temp = data[i];
					data[i] = data[i+1];
					data[i+1] = temp;
				}
				
				notifyObservers();
			}
		}
	}
	
}
