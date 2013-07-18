package com.erikleeness.graph.expression;

import java.util.List;

public class Cosine implements Term
{
	private Term term;
	
	public Cosine(Term term)
	{
		this.term = term;
	}
	
	public static Cosine of(List<Object> params)
	{
		if (params.size() != 1) throw new IllegalArgumentException("Must have exactly one element in param list");
		if ( !(params.get(0) instanceof Term) ) throw new IllegalArgumentException("Parameter must be a Term");
		return new Cosine( (Term) (params.get(0)));
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