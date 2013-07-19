package com.erikleeness.graph.expression;

import com.erikleeness.graph.expression.functions.Constant;
import com.erikleeness.graph.expression.functions.Cosine;
import com.erikleeness.graph.expression.functions.Exponent;
import com.erikleeness.graph.expression.functions.Product;
import com.erikleeness.graph.expression.functions.Root;
import com.erikleeness.graph.expression.functions.Tangent;
import com.erikleeness.graph.expression.functions.Term;
import com.erikleeness.graph.expression.functions.Variable;

public class Tester
{
	public static void main(String[] args)
	{
		Term exponentPart = new Exponent(new Constant(Math.E), 
				new Cosine(new Product(new Constant(5), new Root(new Constant(2), new Variable()))));
		Term t1 = new Product( new Constant(2), new Product( new Tangent(new Variable()), exponentPart));
		
		System.out.println(t1);
		System.out.println(t1.derive());
	}
}
