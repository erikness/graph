package com.erikleeness.graph;

import java.util.ArrayList;

public class GraphBuilder
{
	/* This is used to encapsulate construction of a Graph.
	 * The build() method also handles some sensible default
	 * arguments.
	 * 
	 * The object is considered "incomplete" until the build()
	 * function is called. That is the only function that will
	 * return an object of type Graph.
	 */
	
	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	private int width;
	private int height;
	private int inputHeight;
	private double tmin;
	private double tmax;
	private double tstep;
	private ArrayList<MathFunction> functions;
	private ArrayList<MathParametric> parametrics;
	private ArrayList<DataSet> dataSets;
	private boolean hasInputPanel;
	
	private boolean xRangeBuilt;
	private boolean yRangeBuilt;
	private boolean tRangeBuilt;
	private boolean tStepBuilt;
	private boolean windowSizeBuilt;
	private boolean inputHeightBuilt;
	
	public GraphBuilder()
	{
		functions = new ArrayList<MathFunction>();
		parametrics = new ArrayList<MathParametric>();
		dataSets = new ArrayList<DataSet>();
		
		hasInputPanel = false;
	}
	public GraphBuilder xRange(double xmin, double xmax)
	{
		this.xmin = xmin;
		this.xmax = xmax;
		xRangeBuilt = true;
		return this;
	}
	
	public GraphBuilder yRange(double ymin, double ymax)
	{
		this.ymin = ymin;
		this.ymax = ymax;
		yRangeBuilt = true;
		return this;
	}
	
	public GraphBuilder tRange(double tmin, double tmax)
	{
		this.tmin = tmin;
		this.tmax = tmax;
		tRangeBuilt = true;
		return this;
	}
	
	public GraphBuilder tStep(double tstep)
	{
		this.tstep = tstep;
		tStepBuilt = true;
		return this;
	}
	
	public GraphBuilder windowSize(int width, int height)
	{
		this.width = width;
		this.height = height;
		windowSizeBuilt = true;
		return this;
	}
	
	public GraphBuilder inputHeight(int inputHeight)
	{
		this.inputHeight = inputHeight;
		return this;
	}
	
	public GraphBuilder addFunction(MathFunction f)
	{
		functions.add(f);
		return this;
	}
	
	public GraphBuilder addParametric(MathParametric p)
	{
		parametrics.add(p);
		return this;
	}
	
	public GraphBuilder addDataSet(DataSet d)
	{
		dataSets.add(d);
		return this;
	}
	
	public GraphBuilder enableInputPanel()
	{
		hasInputPanel = true;
		return this;
	}
	
	public Graph build()
	{
		// Set some defaults
		if (!xRangeBuilt) {
			xmin = -10;
			xmax = 10;
		}
		if (!yRangeBuilt) {
			ymin = -10;
			ymax = 100;
		}
		if (!tRangeBuilt) {
			tmin = 0;
			tmax = Math.PI;
		}
		if (!tStepBuilt) {
			tstep = 0.1;
		}
		if (!windowSizeBuilt) {
			width = 500;
			height = 500;
		}
		if (!inputHeightBuilt) {
			inputHeight = 100;
		}
			
		Graph graph = new Graph(xmin, xmax, ymin, ymax, tmin, tmax, tstep, width, height, inputHeight, hasInputPanel);
		
		// Post-construction changes
		
		for (MathFunction f : functions) {
			graph.addFunction(f);
		}
		
		for (MathParametric p : parametrics) {
			graph.addParametric(p);
		}
		
		for (DataSet d : dataSets) {
			graph.addDataSet(d);
		}
		
		return graph;
	}
}
