package com.erikleeness.graph;

import java.awt.Color;

public abstract class MathFunction
{
	
	private Color color;
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public abstract double calculate(double x);
}
