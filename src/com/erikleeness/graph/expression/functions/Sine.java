package com.erikleeness.graph.expression.functions;

import java.util.List;


public class Sine implements Term
{
	private Term term;
	
	public Sine(Term term)
	{
		this.term = term;
	}
	
	public static Sine of(List<?> params)
	{
		if (params.size() != 1) throw new IllegalArgumentException("Must have exactly one element in param list");
		if ( !(params.get(0) instanceof Term) ) throw new IllegalArgumentException("Parameter must be a Term");
		return new Sine( (Term) (params.get(0)) );
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