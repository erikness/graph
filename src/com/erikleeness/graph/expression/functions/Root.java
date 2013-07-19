package com.erikleeness.graph.expression.functions;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

public class Root implements Term
{
	// Proper words are degree and radicand
	private Constant degree;
	private Term term;
	
	public Root(Constant degree, Term term)
	{
		this.degree = degree;
		this.term = term;
	}
	
	public static Root of(List<?> params)
	{
		if (params.size() != 2) throw new IllegalArgumentException("Must have exactly two elements in param list");
		if ( !(params.get(0) instanceof Term) ) throw new IllegalArgumentException("Parameter 1 must be a Constant");
		if ( !(params.get(1) instanceof Term) ) throw new IllegalArgumentException("Parameter 2 must be a Term");
		return new Root( (Constant) (params.get(0)), (Term) (params.get(1)) );
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return Math.pow( term.evaluate(xValue), 1 / degree.evaluate(xValue) );
	}

	public Term derive()
	{
		// dterm * (1/degree) * term ^ ((1/degree)-1)
		Term reciprocal = new Quotient(new Constant(1), degree);
		return new Product( term.derive(), new Product( 
				reciprocal, 
				new Exponent( term, new Difference( reciprocal, new Constant(1) ))));
	}
	
	@Override
	public String toString()
	{
		String result;
		byte[] resultByteArray;
		
		try {
			result = new String("<" + degree.toString() + ">√(" + term.toString() + ")");
			resultByteArray = result.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// What the fuck doesn't support UTF-8?
			result = "sqrt<" + degree.toString() + ">(" + term.toString() + ")";
			resultByteArray = result.getBytes();
		}
		
		return new String(resultByteArray, Charset.forName("UTF-8"));
	}
}
