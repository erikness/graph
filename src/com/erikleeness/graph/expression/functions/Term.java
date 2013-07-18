package com.erikleeness.graph.expression.functions;

public interface Term
{
	public abstract double evaluate(double xValue);
	public abstract Term derive();
}
