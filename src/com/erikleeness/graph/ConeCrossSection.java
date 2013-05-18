package com.erikleeness.graph;

import java.awt.Color;

public class ConeCrossSection extends MathParametric
{
	private double radius;
	
	public ConeCrossSection(double radius, Color color)
	{
		this.setColor(color);
		this.radius = radius;
	}
	
	public Vector2D calculate(double t)
	{
		return new Vector2D(x(t), y(t));
	}

	private double x(double t)
	{
		return radius*Math.sin(t);
	}
	
	private double y(double t)
	{
		return radius*Math.cos(t);
	}

}
