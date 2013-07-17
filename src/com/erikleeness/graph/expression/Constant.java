package com.erikleeness.graph.expression;

public class Constant implements Term
{
	private double value;
	
	public Constant(double value)
	{
		this.value = value;
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return this.value;
	}
	
	@Override
	public Term derive()
	{
		return new Constant(0);
	}
	
	@Override
	public String toString()
	{
		return Double.toString(value);
	}

}
