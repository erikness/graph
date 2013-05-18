package com.erikleeness.graph;

import java.awt.Color;

public abstract class MathParametric
{
	private Color color;
	
	public Color getColor()
	{
		if (color != null) {
			return color;
		} else {
			return Color.black;
		}
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public abstract Vector2D calculate(double t);
}
