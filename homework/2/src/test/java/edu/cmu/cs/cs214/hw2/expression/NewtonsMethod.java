package edu.cmu.cs.cs214.hw2.expression;

import org.junit.Test;

import edu.cmu.cs.cs214.hw2.operator.Binary;

public class NewtonsMethod {
	//CHECKSTYLE:OFF

	@Test
	public void test() {
		// x*x - 2
		double approxZero = 10.0;
		double tolerance = 0.0; 
		Expression operand1 = new NumberExpression(9);
		Variable var = new Variable("x");
		Expression e = new BinaryExpression(Binary.MULTIPLY, var, var);
		Expression e2 = new BinaryExpression(Binary.SUBTRACT, e, operand1);
		System.out.println(NewtonMethod.zero(e2, var, approxZero, tolerance));
		}	

}
