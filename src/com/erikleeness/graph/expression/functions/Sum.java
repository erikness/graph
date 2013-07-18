package com.erikleeness.graph.expression.functions;

import java.util.List;

public class Sum implements Term
{
	// Proper words for both are addends
	private Term left;
	private Term right;
	
	public Sum(Term left, Term right)
	{
		this.left = left;
		this.right = right;
	}

	public static Sum of(List<?> params)
	{
		if (params.size() != 2) throw new IllegalArgumentException("Must have exactly two elements in param list");
		if ( !(params.get(0) instanceof Term) ) throw new IllegalArgumentException("Parameter 1 must be a Term");
		if ( !(params.get(1) instanceof Term) ) throw new IllegalArgumentException("Parameter 2 must be a Term");
		return new Sum( (Term) (params.get(0)), (Term) (params.get(1)) );
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return left.evaluate(xValue) + right.evaluate(xValue);
	}

	@Override
	public Term derive()
	{
		return new Sum(left.derive(), right.derive());
	}

	@Override
	public String toString()
	{
		return left.toString() + "+" + right.toString();
	}
}
