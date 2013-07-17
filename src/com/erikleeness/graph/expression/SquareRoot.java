package com.erikleeness.graph.expression;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class SquareRoot implements Term
{
	private Term term;
	
	public SquareRoot(Term term)
	{
		this.term = term;
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return Math.sqrt( term.evaluate(xValue) );
	}

	public Term derive()
	{
		// dterm / (2 * sqrt(term))
		return new Quotient( term.derive(),
				new Product( new Constant(2), new SquareRoot(term) ));
	}
	
	@Override
	public String toString()
	{
		String result;
		byte[] resultByteArray;
		
		try {
			result = new String("√(" + term.toString() + ")");
			resultByteArray = result.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// What the fuck doesn't support UTF-8?
			result = "sqrt(" + term.toString() + ")";
			resultByteArray = result.getBytes();
		}
		
		return new String(resultByteArray, Charset.forName("UTF-8"));
	}
}
