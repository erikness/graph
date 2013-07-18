package com.erikleeness.graph.expression;

import java.util.List;

public class Constant implements Term
{
	private double value;
	
	public Constant(double value)
	{
		this.value = value;
	}
	
	public static Constant of(List<Object> params)
	{
		if (params.size() != 1) throw new IllegalArgumentException("Must have exactly one element in param list");
		if ( !(params.get(0) instanceof Double) ) throw new IllegalArgumentException("Parameter must be a Double");
		return new Constant( ((Double) (params.get(0))).doubleValue());
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
