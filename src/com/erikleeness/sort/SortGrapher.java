package com.erikleeness.sort;

import com.erikleeness.graph.DataSet;
import com.erikleeness.graph.Graph;
import com.erikleeness.graph.GraphBuilder;
import com.erikleeness.graph.Vector2D;
import com.erikleeness.sort.algorithms.*;

public class SortGrapher implements AlgorithmObserver
{
	private Graph graph;
	private DataSet dataSet;
	private int[] data;
	private SortAlgorithm algorithm;
	
	public SortGrapher(SortAlgorithm algorithm, int[] data)
	{
		
		this.data = data;
		this.algorithm = algorithm;
		dataSet = new DataSet();
		
		graph = new GraphBuilder()
			.xRange(0, 100)
			.yRange(0, 100)
			.windowSize(500,500)
			.build();
		
		
	}
	
	public void view()
	{
		graph.view();
		update();
		algorithm.registerObserver(this);
		algorithm.sort(data);
	}
	
	public static void main(String args[])
	{
		int[] numbers = new int[]{74, 82, 24, 15, 31, 21, 45, 1, 9, 30, 51, 69, 57, 41, 14, 8, 61, 65, 26, 88, 32, 98, 93, 22, 3, 
				33, 66, 34, 2, 40, 20, 87, 19, 38, 92, 10, 95, 59, 23, 81, 83, 17, 53, 91, 86, 72, 29, 84, 64, 76, 5, 96, 4, 90, 
				79, 6, 42, 13, 12, 43, 62, 52, 27, 67, 100, 47, 7, 80, 60, 70, 55, 99, 58, 44, 78, 46, 89, 71, 54, 75, 11, 48, 
				36, 68, 56, 73, 63, 39, 37, 97, 85, 16, 25, 35, 50, 77, 94, 18, 28, 49};
		
		SortGrapher sortGrapher = new SortGrapher(new QuickSortAlgorithm(50), numbers);
		sortGrapher.view();
	}
	
	public static void printArray(int[] arr)
	{
		for (int i : arr) {
			System.out.print(i);
			System.out.print(" ");
		}
		
		System.out.println();
	}

	/**
	 * Called when an algorithm this instance is observing has new information
	 * (i.e. some order of the data has been changed and we need to show it).
	 */
	public void update()
	{
		graph.removeDataSet(dataSet);
		dataSet = new DataSet(); // Throw the old one away
		
		for (int elementIndex = 0; elementIndex < data.length; elementIndex++) {
			Vector2D point = new Vector2D(elementIndex, data[elementIndex]);
			dataSet.add(point);
		}
		
		graph.addDataSet(dataSet);
		graph.redraw();
	}
}
