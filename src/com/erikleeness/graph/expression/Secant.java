package com.erikleeness.graph.expression;

public class Secant implements Term
{
	private Term term;
	
	public Secant(Term term)
	{
		this.term = term;
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return 1 / Math.cos( term.evaluate(xValue) );
	}

	@Override
	public Term derive()
	{
		// dterm * sec term * tan term
		return new Product( term.derive(), new Product( new Secant(term), new Tangent(term)));
	}
	
	@Override
	public String toString()
	{
		return "sec(" + term.toString() + ")";
	}

}
