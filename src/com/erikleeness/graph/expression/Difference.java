package com.erikleeness.graph.expression;

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
