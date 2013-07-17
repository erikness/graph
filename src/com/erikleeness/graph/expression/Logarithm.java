package com.erikleeness.graph.expression;

public class Logarithm implements Term
{
	private Constant base;
	private Term term;
	
	public Logarithm(Constant base, Term term)
	{
		this.base = base;
		this.term = term;
	}

	@Override
	public double evaluate(double xValue)
	{
		return Math.log(term.evaluate(xValue)) / Math.log(base.evaluate(xValue));
	}

	@Override
	public Term derive()
	{
		// dterm / (term ln base)
		return new Quotient(term.derive(), 
							new Product(term, 
										new Logarithm( new Constant(Math.E), base )
										)
							);
	}

	@Override
	public String toString()
	{
		double baseNumber = base.evaluate(0);
		String logString;
		
		if (baseNumber == Math.E) {
			logString = "ln";
		} else if (baseNumber == 2.0) {
			logString = "lb";
		} else if (baseNumber == 10.0) {
			logString = "log";
		} else {
			logString = "log<" + baseNumber + ">";
		}
		
		
		return logString + "(" + term + ")";
	}
}
