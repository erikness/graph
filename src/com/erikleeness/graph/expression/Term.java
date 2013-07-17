package com.erikleeness.graph.expression;

public interface Term
{
	public double evaluate(double xValue);
	public Term derive();
}
