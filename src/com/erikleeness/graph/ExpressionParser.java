package com.erikleeness.graph;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class ExpressionParser
{
	public String formalFromInformal(String informalExpression)
	{
		StringBuilder workingExpression = new StringBuilder(informalExpression);
		// sin(x*2^x) => Sine(Product(Variable(), Exponent(Constant(2), Variable())))
		//
		// Replace all parens with brackets
		// translate sin[x*2^x]
		// > translate x*2^x
		// > no parens! Do order of ops on this shit
		// > Exponent: get the arguments: 2 and x
		// > > translate: 2
		// > > = Constant(2)
		// > > translate x
		// > > = Variable()
		// > working: x*Exponent(Constant(2), Variable())
		// > Multiply: get the arguments: x and Exponent(Constant(2), Variable())
		// > > translate: x
		// > > = Variable()
		// > > translate: Exponent(Constant(2), Variable())
		// > > I see parens, so it must have already been translated 
		// > > = Exponent(Constant(2), Variable())   (already translated)
		// > working: Multiply(Variable(), Exponent(Constant(2), Variable()))
		// no brackets, no symbols - we good!
		replaceAll(workingExpression, "sin", "Sine");
		replaceAll(workingExpression, "tan", "Tangent");
		replaceAll(workingExpression, "cos", "Cosine");
		replaceAll(workingExpression, "sec", "Secant");
		
		replaceAll(workingExpression, "(", "[");
		replaceAll(workingExpression, ")", "]");
		
		boolean bracketsInExpression = workingExpression.indexOf("[") != -1;
		if (bracketsInExpression) {
			translateInnerExpressions(workingExpression);
		} else {
			// Order of operations!
			// We've already covered the brackets...
			// For every exponent sign, go in each direction and stop at the next lower-level
			// operator. Evaluate everything until there
			
		}
		
	}
	
	private void translateInnerExpressions(StringBuilder workingExpression)
	{
		// Solve the stuff within the brackets first
		// For all the outer brackets, translate the inner part (even if the inner has brackets itself)
		// Walk the string, find a bracket, put the new chars in a new string
		TreeMap<Vector2D, StringBuilder> innerParts = new TreeMap<Vector2D, StringBuilder>();
		int bracketDepth = 0;
		StringBuilder currentPart = null;
		int currentPartStartIndex = 0;
		for (int index = 0; index < workingExpression.length(); index++) {
			char currentChar = workingExpression.charAt(index);
			if (bracketDepth > 0) {
				if (currentChar == '[') {
					bracketDepth++;
				} else if (currentChar == ']') {
					bracketDepth--;
					if (bracketDepth == 0) {
						Vector2D position = new Vector2D(currentPartStartIndex, index-1);
						innerParts.put(position, currentPart);
						continue;
					}
				}
				currentPart.append(currentChar);
			} else {
				if (currentChar == '[') {
					bracketDepth = 1;
					currentPart = new StringBuilder();
					currentPartStartIndex = index+1;
				}
			}
		}
		
		for (Map.Entry<Vector2D, StringBuilder> part : innerParts.entrySet()) {
			int start = (int) part.getKey().x;
			int end = (int) part.getKey().y;
			
			// There are, so far, no functions whose informal form take multiple arguments.
			// Therefore, there should be no commas in an informal expression.
			String translation = formalFromInformal(part.getValue().toString());
			
			// Now if this bracket block is preceded by a function name (and not an operator),
			// we should deal with that now, as it is a unary function and this is its only argument.
			
			if (start-1 >= 0 && !isBinaryOperator(workingExpression.charAt(start-1))) {
				// There's a function name preceding this part, and we can evaluate it with the part as
				// an argument. We can do that by backtracing until we hit the end of the word or
				// we hit a binary operator.
				StringBuilder functionName = new StringBuilder();
				int index = start - 1;
				char currentChar;
				while (index >= 0 && !isBinaryOperator(
								currentChar = workingExpression.charAt(index))) {
					functionName.insert(0, currentChar);
					index--;
				}
				
				String formalFunctionString = formalFunctionString(functionName.toString(), translation);
				workingExpression.replace(index+1, end, formalFunctionString);
			} else {
				workingExpression.replace(start, end, translation);
			}

			// PROBLEM!!!!!@@@ 
			// Replace()ing on workingExpression will change where subsequent expressions are.
		}
	}
	
	/**
	 * Modifies the variable "input".
	 * 
	 * @param input
	 */
	private void replaceAll(StringBuilder input, String regex, String replacement)
	{
		String newString = Pattern.compile(regex).matcher(input).replaceAll(replacement);
		input.replace(0, input.length(), newString);
	}
	
	private boolean isBinaryOperator(char candidate)
	{
		return candidate == '*' || candidate == '/' || candidate == '+' || candidate == '-';
	}
	
	private String formalFunctionString(String informalFunctionName, String argument)
	{
		switch (informalFunctionName) {
		case "sin":
			return "Sine";
		case "cos":
			return "Cosine";
		case "tan":
			return "Tangent";
		case "sec":
			return "Secant";
		default:
			if (informalFunctionName.matches("log<.+>")) {
				
			} else if ("log".equals(informalFunctionName)) {
				return;
			}
		}
	}
}
