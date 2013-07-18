package com.erikleeness.graph.expression.functions;

import java.util.List;

public class Exponent implements Term
{
	private Term base;
	private Term power;
	
	public Exponent(Term base, Term power)
	{
		this.base = base;
		this.power = power;
	}

	public static Exponent of(List<Object> params)
	{
		if (params.size() != 2) throw new IllegalArgumentException("Must have exactly two elements in param list");
		if ( !(params.get(0) instanceof Term) ) throw new IllegalArgumentException("Parameter 1 must be a Term");
		if ( !(params.get(1) instanceof Term) ) throw new IllegalArgumentException("Parameter 2 must be a Term");
		return new Exponent( (Term) (params.get(0)), (Term) (params.get(1)) );
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return Math.pow( base.evaluate(xValue), power.evaluate(xValue) );
	}

	@Override
	public Term derive()
	{
		/* The general form of this rule is (given base=f, power=g):
		 * 
		 * f^g * (g*f'/f + g'*ln f )
		 * 
		 * Note that this general form takes into account:
		 * 
		 * * Any implicit differentiation
		 * * Power rule (x^n => n*x^(n-1))
		 * * Exponent rule (n^x => n^x * ln n)
		 * * 0 when both base and power are constants
		 * * Chain rule (as expected)
		 * 
		 * One may choose to use any of these simpler rules in special cases if
		 * the general form is too slow, but that is currently not the case.
		 */
		
		return new Product( 
						new Exponent(base, power),
						new Sum(
							new Product(power, new Quotient(base.derive(), base)),
							new Product(power.derive(), new Logarithm(new Constant(Math.E), base))
								)
						   );
	}
	
	@Override
	public String toString()
	{
		// Put parens around the term if the outermost operation is of lower precedence
		// than the exponent operator.
		String baseString;
		if (isLowerPrecedence(base)) {
			baseString = "(" + base.toString() + ")";
		} else {
			baseString = base.toString();
		}
		
		String powerString;
		if (isLowerPrecedence(power)) {
			powerString = "(" + power.toString() + ")";
		} else {
			powerString = power.toString();
		}
		
		return baseString + "^" + powerString;
	}
	
	private boolean isLowerPrecedence(Term term)
	{
		return term instanceof Sum || term instanceof Difference || term instanceof Quotient || 
				term instanceof Product;
	}

}
