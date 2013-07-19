package com.erikleeness.graph;

public class ExpressionParser
{
	public void formalFromInformal(String informalExpression)
	{
		String workingExpression = informalExpression;
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
		workingExpression = workingExpression.replaceAll("sin", "Sine");
		workingExpression = workingExpression.replaceAll("tan", "Tangent");
		workingExpression = workingExpression.replaceAll("cos", "Cosine");
		workingExpression = workingExpression.replaceAll("sec", "Secant");
		
	}
}
