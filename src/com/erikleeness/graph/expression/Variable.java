package com.erikleeness.graph.expression;

public class Variable implements Term
{
	private String alias;
	
	public Variable(String alias)
	{
		this.alias = alias;
	}
	
	public Variable()
	{
		this("x");
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return xValue;
	}
	
	@Override
	public Term derive()
	{
		return new Constant(1);
	}
	
	@Override
	public String toString()
	{
		return alias;
	}
}
