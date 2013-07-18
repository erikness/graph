package com.erikleeness.graph.expression.functions;

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
