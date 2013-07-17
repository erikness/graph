package com.erikleeness.graph.expression;

public class Sine implements Term
{
	private Term term;
	
	public Sine(Term term)
	{
		this.term = term;
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return Math.sin( term.evaluate(xValue) );
	}

	@Override
	public Term derive()
	{
		// dterm * cos(term)
		return new Product( term.derive(), new Cosine(term) );
	}
	
	@Override
	public String toString()
	{
		return "sin(" + term.toString() + ")";
	}

}