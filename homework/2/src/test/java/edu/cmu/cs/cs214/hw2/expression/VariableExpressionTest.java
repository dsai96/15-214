package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.cmu.cs.cs214.hw2.operator.Binary;
/**
 * this tests for the variable expressions using the binary and unary operators
 * @author dsai96
 *
 */
public class VariableExpressionTest {
	//CHECKSTYLE:OFF

	@Test
	public void test() {
		Variable x = new Variable("x");
		x.store(3.0);
		Expression operand2 = new NumberExpression(10);
		Expression e = new BinaryExpression(Binary.ADD, x, operand2);
		assertEquals(e.eval(), 13.0, Math.pow(10, -4));
	
	}
	
	@Test
	public void testToString() {
		Variable x = new Variable("x");
		x.store(3.0);
		assertTrue(x.toString().equals("(x = 3.0)"));
	}

	@Test
	public void testGetter() {
		Variable x = new Variable("x");
		x.store(3.0);
		assertTrue(x.name().equals("x"));
	}
}
