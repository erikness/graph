package com.erikleeness.graph.sort;

import java.util.HashSet;
import java.util.Set;

public abstract class SortAlgorithm
{
	private Set<AlgorithmObserver> observers;
	private int delayMS;
	
	public SortAlgorithm(int delayMS)
	{
		this.delayMS = delayMS;
		observers = new HashSet<AlgorithmObserver>();
	}
	
	public abstract void sort(int[] data);
	
	public void notifyObservers()
	{
		if (delayMS != 0) {
			/* Why not zero? If that is the delay, the client logically wants us not to delay.
			 * Even calling Thread.sleep() will, however, produce a non-negligible delay even
			 * if its parameter is 0. */
			try {
				Thread.sleep(delayMS);
			} catch (InterruptedException e) {
				// If there's a threading error here, it is probably not cause for stopping execution.
				e.printStackTrace();
			}
		}
		
		for (AlgorithmObserver obs : observers) {
			obs.update();
		}
	}
	
	public void registerObserver(AlgorithmObserver o)
	{
		observers.add(o);
	}
	
	public void removeObserver(AlgorithmObserver o)
	{
		observers.remove(o);
	}
}
