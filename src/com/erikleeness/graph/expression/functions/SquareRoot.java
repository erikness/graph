package com.erikleeness.graph.expression.functions;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

public class SquareRoot implements Term
{
	private Term term;
	
	public SquareRoot(Term term)
	{
		this.term = term;
	}
	
	public static SquareRoot of(List<?> params)
	{
		if (params.size() != 1) throw new IllegalArgumentException("Must have exactly one element in param list");
		if ( !(params.get(0) instanceof Term) ) throw new IllegalArgumentException("Parameter must be a Term");
		return new SquareRoot( (Term) (params.get(0)) );
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
