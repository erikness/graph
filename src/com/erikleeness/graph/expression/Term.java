package com.erikleeness.graph.expression;

public interface Term
{
	public abstract double evaluate(double xValue);
	public abstract Term derive();
}
