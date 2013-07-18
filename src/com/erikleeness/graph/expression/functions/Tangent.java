package com.erikleeness.graph.expression.functions;

import java.util.List;


public class Tangent implements Term
{
	private Term term;
	
	public Tangent(Term term)
	{
		this.term = term;
	}
	
	public static Tangent of(List<?> params)
	{
		if (params.size() != 1) throw new IllegalArgumentException("Must have exactly one element in param list");
		if ( !(params.get(0) instanceof Term) ) throw new IllegalArgumentException("Parameter must be a Term");
		return new Tangent( (Term) (params.get(0)) );
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return Math.tan( term.evaluate(xValue) );
	}
	
	@Override
	public Term derive()
	{
		// dterm * sec^2(term)
		return new Product( term.derive(), new Exponent( new Secant(term), new Constant(2)));
	}
	
	@Override
	public String toString()
	{
		return "tan(" + term.toString() + ")";
	}

}