package com.erikleeness.graph.expression;

public class Tangent implements Term
{
	private Term term;
	
	public Tangent(Term term)
	{
		this.term = term;
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