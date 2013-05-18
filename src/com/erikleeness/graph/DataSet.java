package com.erikleeness.graph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class DataSet implements Iterable<Vector2D>
{
	private ArrayList<Vector2D> points;
	private Color color;
	
	public DataSet()
	{
		points = new ArrayList<Vector2D>();
		color = Color.black;
	}
	
	public void add(Vector2D point)
	{
		points.add(point);
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public Iterator<Vector2D> iterator()
	{
		return points.iterator();
	}
}
