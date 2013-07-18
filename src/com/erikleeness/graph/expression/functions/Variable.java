package com.erikleeness.graph.expression.functions;

import java.util.List;

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
	
	public static Variable of(List<?> params)
	{
		if (params.size() == 0) return new Variable();
		if (params.size() > 1) throw new IllegalArgumentException("Must have either one or no elements in param list");
		if ( !(params.get(0) instanceof String) ) throw new IllegalArgumentException("Parameter must be a String");
		return new Variable( (String) (params.get(0)) );
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
