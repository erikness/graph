package com.erikleeness.graph.expression.functions;

import java.util.List;


public class Secant implements Term
{
	private Term term;
	
	public Secant(Term term)
	{
		this.term = term;
	}
	
	public static Secant of(List<?> params)
	{
		if (params.size() != 1) throw new IllegalArgumentException("Must have exactly one element in param list");
		if ( !(params.get(0) instanceof Term) ) throw new IllegalArgumentException("Parameter must be a Term");
		return new Secant( (Term) (params.get(0)) );
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
