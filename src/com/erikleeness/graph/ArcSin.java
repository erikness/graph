package com.erikleeness.graph;

import java.awt.Color;

public class ArcSin extends MathParametric
{
	public ArcSin()
	{
		this.setColor(Color.green);
	}
	
	public Vector2D calculate(double t)
	{
		return new Vector2D(x(t), y(t));
	}
	
	private double x(double t)
	{
		return Math.sin(t);
	}
	
	private double y(double t)
	{
		return t;
	}

	
}
