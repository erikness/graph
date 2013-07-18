package com.erikleeness.graph.expression.functions;

import java.util.List;

import com.erikleeness.graph.expression.OrderOfOperations;

public class Product implements Term
{
	// Proper word are multiplicand and multiplier
	private Term left;
	private Term right;
	
	public Product(Term left, Term right)
	{
		this.left = left;
		this.right = right;
	}

	public static Product of(List<Object> params)
	{
		if (params.size() != 2) throw new IllegalArgumentException("Must have exactly two elements in param list");
		if ( !(params.get(0) instanceof Term) ) throw new IllegalArgumentException("Parameter 1 must be a Term");
		if ( !(params.get(1) instanceof Term) ) throw new IllegalArgumentException("Parameter 2 must be a Term");
		return new Product( (Term) (params.get(0)), (Term) (params.get(1)) );
	}
	
	@Override
	public double evaluate(double xValue)
	{
		return left.evaluate(xValue) * right.evaluate(xValue);
	}

	@Override
	public Term derive()
	{
		// left * dright + dleft * right
		return new Sum( new Product(left, right.derive()), new Product(left.derive(), right));
	}

	@Override
	public String toString()
	{
		String leftString;
		if (OrderOfOperations.comparePrecedence(this, left) > 0) {
			leftString = "(" + left.toString() + ")";
		} else {
			leftString = left.toString();
		}
		
		String rightString;
		if (OrderOfOperations.comparePrecedence(this, left) > 0) {
			rightString = "(" + right.toString() + ")";
		} else {
			rightString = right.toString();
		}
		
		return leftString + "*" + rightString;
	}
}
