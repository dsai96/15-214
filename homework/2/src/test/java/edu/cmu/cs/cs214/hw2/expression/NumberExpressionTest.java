package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
/**
 * this creates tests for the number expression eval and toString
 * @author dsai96
 *
 */
public class NumberExpressionTest {
    //CHECKSTYLE:OFF

	@Test
	public void testEval() {
		Expression operand1 = new NumberExpression(3);
		assertEquals(operand1.eval(), 3.0, Math.pow(10, -4));
	}
	
	@Test
	public void testToString() {
		Expression operand1 = new NumberExpression(-3);
		assertTrue(operand1.toString().equals("(-3.0)"));
		
	}
	

}
