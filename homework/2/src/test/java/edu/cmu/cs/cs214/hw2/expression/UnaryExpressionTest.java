package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.cmu.cs.cs214.hw2.operator.Unary;

public class UnaryExpressionTest {
	//CHECKSTYLE:OFF

	@Test
	public void test() {
		Expression operand1 = new NumberExpression(-3);
		Expression e = new UnaryExpression(Unary.ABSVALUE, operand1);
		assertEquals(e.eval(), 3.0, Math.pow(10, -5));
	}
	
	@Test
	public void test1() {
		Expression operand1 = new NumberExpression(2);
		Expression e = new UnaryExpression(Unary.ABSVALUE, operand1);
		assertEquals(e.eval(), 2.0, Math.pow(10, -5));
	}
	
	@Test
	public void test2() {
		Expression operand1 = new NumberExpression(-3);
		Expression e = new UnaryExpression(Unary.NEGATION, operand1);
		assertEquals(e.eval(), 3.0, Math.pow(10, -5));
	}
	
	@Test
	public void test3() {
		Expression operand1 = new NumberExpression(2);
		Expression e = new UnaryExpression(Unary.NEGATION, operand1);
		assertEquals(e.eval(), -2.0, Math.pow(10, -5));
	}
	
//	@Test(expected = NullPointerException.class)
//	public void test4() {
//		Expression e = new UnaryExpression(Unary.NEGATION, null);
//		
//	}
	
	@Test
	public void testToStringOperator() {
		Expression operand1 = new NumberExpression(2);
		Expression e = new UnaryExpression(Unary.ABSVALUE, operand1);
		assertTrue(e.toString().equals("(||(2.0))"));
	}
	
	@Test
	public void testToString() {
		Expression operand1 = new NumberExpression(2);
		Expression e = new UnaryExpression(Unary.NEGATION, operand1);
		assertTrue(e.toString().equals("(~(2.0))"));
	}
	
	
	
}
