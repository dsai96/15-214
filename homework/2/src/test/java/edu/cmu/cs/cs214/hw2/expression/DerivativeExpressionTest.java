package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.cmu.cs.cs214.hw2.operator.Binary;

/**
 * 
 * @author dsai96
 * creates derivative expressions and checks eval 
 */
public class DerivativeExpressionTest { 
	//CHECKSTYLE:OFF

	@Test
	public void test() {
		//f(x) = x^2 - 1
		//derivative = 2x, which with x = 2, is 4.0
		Expression operand1 = new NumberExpression(1);
		Variable var = new Variable("x");
		var.store(2.0);
		Expression varSquared = new BinaryExpression(Binary.MULTIPLY, var, var);
		Expression subtracted = new BinaryExpression(Binary.SUBTRACT, varSquared, operand1);
		Expression derivativeFn = new DerivativeExpression(subtracted, var);
		assertEquals(derivativeFn.eval(), 4.00, Math.pow(10, -4));
	}
	
	@Test
	public void testToString() {
		//f(x) = x^2 - 1
		Expression operand1 = new NumberExpression(1);
		Variable var = new Variable("x");
		Expression varSquared = new BinaryExpression(Binary.MULTIPLY, var, var);
		Expression subtracted = new BinaryExpression(Binary.SUBTRACT, varSquared, operand1);
		Expression derivativeFn = new DerivativeExpression(subtracted, var);
		assertTrue(derivativeFn.toString().equals("(((x = 0.0) * (x = 0.0)) - (1.0)) , x"));
	}
}
