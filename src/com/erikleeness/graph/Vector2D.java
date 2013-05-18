package com.erikleeness.graph;

public class Vector2D
{
	public Vector2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	public double x;
	public double y;
	
	public boolean inBounds(int xmin, int ymin, int xmax, int ymax)
	{
		return x >= xmin && x <= xmax && y >= ymin && y <= ymax;
	}
	
	public boolean isFinite()
	{
		return !(Double.isInfinite(x) || Double.isInfinite(y) || Double.isNaN(x) || Double.isNaN(y));
	}
	
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
}
