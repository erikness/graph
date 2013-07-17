package com.erikleeness.graph.expression;

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
