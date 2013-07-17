package com.erikleeness.graph.expression;

public class Cosine implements Term
{
	private Term term;
	
	public Cosine(Term term)
	{
		this.term = term;
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return Math.cos( term.evaluate(xValue) );
	}

	@Override
	public Term derive()
	{
		// dterm * -sin(term)
		return new Product(term.derive(), new Product(new Constant(-1), new Sine(term)));
	}

	@Override
	public String toString()
	{
		return "cos(" + term.toString() + ")";
	}

}