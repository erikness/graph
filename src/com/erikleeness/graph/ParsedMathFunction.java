package com.erikleeness.graph;

import java.awt.Color;
import java.util.ArrayList;

import com.erikleeness.graph.expression.Term;

public class ParsedMathFunction extends MathFunction
{
	private String expression;
	private Term term;
	
	public ParsedMathFunction(String informalExpression, Color color)
	{
		this.expression = informalToFormal(informalExpression);
		this.setColor(color);
	}
	
	public ParsedMathFunction(String expression)
	{
		this(expression, Color.black);
	}
	
	public double calculate(String workingExpression, double x)
	{
		double result = 0;
		String[] parts = workingExpression.split("\\(", 2);
		workingExpression = parts[1].substring(0, parts[1].length() - 1);
		String function = parts[0];
		
		ArrayList<String> arguments = splitIntoMultipleArguments(workingExpression);
		if (arguments.get(0) == workingExpression) {
			
			
			if (workingExpression == "x") {
				result = x;	
			} else if (isConstant(workingExpression)) {
				result = Double.parseDouble(workingExpression);
			} else {
				result = calculate(workingExpression, x);
			}
			
			switch (function) {
				case "sin":
					result = Math.sin(result);
					break;
				case "cos":
					result = Math.cos(result);
					break;
				case "tan":
					result = Math.tan(result);
					break;
				case "asin":
					result = Math.asin(result);
					break;
				case "acos":
					result = Math.acos(result);
					break;
				case "atan":
					result = Math.atan(result);
					break;
				case "sec":
					result = 1/Math.cos(result);
					break;
				case "csc":
					result = 1/Math.sin(result);
					break;
				case "cot":
					result = 1/Math.tan(result);
					break;
				case "asec":
					result = Math.acos(1/result);
					break;
				case "acsc":
					result = Math.asin(1/result);
					break;
				case "acot":
					result = Math.atan(1/result);
					break;
				case "sinh":
					result = Math.sinh(result);
					break;
				case "cosh":
					result = Math.cosh(result);
					break;
				case "tanh":
					result = Math.tanh(result);
					break;
				
				case "ln":
					result = Math.log(result);
					break;
				case "log":
					result = Math.log10(result);
					break;
				case "inverse":
					result = 1/result;
					break;
			}
			
			
		} else { // Multiple arguments!
			// Recursively evaluate each of them
			double[] results = new double[arguments.size()];
			for (int i = 0; i < arguments.size(); i++) {
				results[i] = calculate(arguments.get(i), x);
			}
			
			switch(function) {
			case "add":
				result = results[0] + results[1];
				break;
			case "subtract":
				result = results[0] - results[1];
				break;
			case "multiply":
				result = results[0] + results[1];
				break;
			}
				
		}
		
		
		return result;
	}
	
	public double calculate(double x)
	{
		return calculate(this.expression, x);
	}
	
	private String informalToFormal(String input)
	{
		return input;
	}
	
	public double calculate2(double x)
	{
		Term term = termFromFormalExpression(this.expression);
		return term.evaluate(x);
	}
	
	public Term termFromFormalExpression(String formalExpression)
	{
		while (true) {
			// strip function name and parens
			// identify the class that corresponds to the function
			// get the function's arguments in a list with splitIntoMultipleArguments
			// pass those arguments to "new [class we just got]"
			// call evaluate(x) on that class
		}
	}
	
	private ArrayList<String> splitIntoMultipleArguments(String expression)
	{
		boolean insideParen = false;
		int parenCount = 0;
		int index;
		for (index = 0; index < expression.length(); index++) {
			if (expression.charAt(index) == '(') {
				parenCount++;
				insideParen = true;
			} else if (expression.charAt(index) == ')') {
				parenCount--;
			} else {
				if (insideParen && parenCount == 0) {
					break;
				}
			}
		}

		System.out.println(index);
		System.out.println(expression);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> result = new ArrayList<String>();
		if (index != expression.length()) {
			// There are at least 2 arguments
			result.add(expression.substring(0, index));
			System.out.println("@");
			result.addAll(splitIntoMultipleArguments(expression.substring(index)));
		} else {
			// One argument
			result.add(expression);
		}
		return result;
			
	}
	
	private boolean isConstant(String s)
	{
		return s.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+ ");
	}
}
