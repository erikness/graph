package com.erikleeness.graph.expression;

public class Quotient implements Term
{
	// Proper words are dividend and divisor
	private Term high;
	private Term low;
	
	public Quotient(Term high, Term low)
	{
		this.high = high;
		this.low = low;
	}

	@Override
	public double evaluate(double xValue)
	{
		return high.evaluate(xValue) / low.evaluate(xValue);
	}

	@Override
	public Term derive()
	{
		// low * dhigh - high * dlow / low^2
		return new Quotient( new Difference(
											new Product(low, high.derive()),
											new Product(high, low.derive())
											),
							 new Exponent( low, new Constant(2)));
	}

	@Override
	public String toString()
	{
		String leftString;
		if (OrderOfOperations.comparePrecedence(this, high) > 0) {
			leftString = "(" + high.toString() + ")";
		} else {
			leftString = high.toString();
		}
		
		String rightString;
		if (OrderOfOperations.comparePrecedence(this, low) > 0) {
			rightString = "(" + low.toString() + ")";
		} else {
			rightString = low.toString();
		}
		
		return leftString + "/" + rightString;
	}
}
