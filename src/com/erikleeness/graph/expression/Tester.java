package com.erikleeness.graph.expression;

public class Tester
{
	public static void main(String[] args)
	{
		Term exponentPart = new Exponent(new Constant(Math.E), 
				new Cosine(new Product(new Constant(5), new SquareRoot(new Variable()))));
		Term t1 = new Product( new Constant(2), new Product( new Tangent(new Variable()), exponentPart));
		
		System.out.println(t1);
		System.out.println(t1.derive());
	}
}
