package com.erikleeness.graph.expression;

import java.util.List;

public class Difference implements Term
{
	// Proper words are minuend and subtrahend ... I shit you not
	private Term left;
	private Term right;
	
	public Difference(Term left, Term right)
	{
		this.left = left;
		this.right = right;
	}
	
	public static Difference of(List<Object> params)
	{
		if (params.size() != 2) throw new IllegalArgumentException("Must have exactly two elements in param list");
		if ( !(params.get(0) instanceof Term) ) throw new IllegalArgumentException("Parameter 1 must be a Term");
		if ( !(params.get(1) instanceof Term) ) throw new IllegalArgumentException("Parameter 2 must be a Term");
		return new Difference( (Term) (params.get(0)), (Term) (params.get(1)) );
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return left.evaluate(xValue) - right.evaluate(xValue);
	}

	@Override
	public Term derive()
	{
		return new Difference( left.derive(), right.derive() );
	}
	
	@Override
	public String toString()
	{
		return left.toString() + " - " + right.toString();
	}

}
