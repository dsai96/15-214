package edu.cmu.cs.cs214.hw2.termcalc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumberExpression;
/**
 * 
 * @author dsai96
 *this tests for the expressionMaker implementation with all 7 methods and toString
 */
public class ExpressionMakeTest {
	//CHECKSTYLE:OFF

	@Test
	public void testSum() {
		ExpressionMake em = new ExpressionMake();
		Expression operator1 = em.numberExpression(5);
		Expression operator2 = em.numberExpression(8);
	    Expression e = em.sumExpression(operator1, operator2);
	    assertEquals(e.eval(), 13.0, Math.pow(10, -4));
	}
	
	@Test
	public void testSubtract() {
		ExpressionMake em = new ExpressionMake();
		Expression operator1 = em.numberExpression(5);
		Expression operator2 = em.numberExpression(8);
	    Expression e = em.differenceExpression(operator1, operator2);
	    assertEquals(e.eval(), -3.0, Math.pow(10, -4));
	}
	

	@Test
	public void testMultiply() {
		ExpressionMake em = new ExpressionMake();
		Expression operator1 = em.numberExpression(5);
		Expression operator2 = em.numberExpression(8);
	    Expression e = em.productExpression(operator1, operator2);
	    assertEquals(e.eval(), 40.0, Math.pow(10, -4));
	}
	
	@Test
	public void testDivide() {
		ExpressionMake em = new ExpressionMake();
		Expression operator1 = em.numberExpression(32);
		Expression operator2 = em.numberExpression(8);
	    Expression e = em.divisionExpression(operator1, operator2);
	    assertEquals(e.eval(), 4.0, Math.pow(10, -4));
	}
	
	@Test
	public void testExp() {
		ExpressionMake em = new ExpressionMake();
		Expression operator1 = em.numberExpression(1);
		Expression operator2 = em.numberExpression(8);
	    Expression e = em.exponentiationExpression(operator1, operator2);
	    assertEquals(e.eval(), 1.0, Math.pow(10, -4));
	}
	

	@Test
	public void testNegation() {
		ExpressionMake em = new ExpressionMake();
		Expression operand1 = new NumberExpression(2);
		Expression e = em.negationExpression(operand1);
		assertEquals(e.eval(), -2.0, Math.pow(10, -4));	
	}
	
	@Test
	public void testAbsValue() {
		ExpressionMake em = new ExpressionMake();
		Expression operand1 = new NumberExpression(-2);
		Expression e = em.absoluteValueExpression(operand1);
		assertEquals(e.eval(), 2.0,Math.pow(10,-4));
	}
	
	@Test
	public void testNumberExpression() {
		ExpressionMake em = new ExpressionMake();
		Expression e = em.numberExpression(3.0);
		assertEquals(e.eval(), 3.0, Math.pow(10,-4));

	}
	
	@Test
	public void testToString() {
		ExpressionMake em = new ExpressionMake();
		Expression operator1 = em.numberExpression(5);
		Expression operator2 = em.numberExpression(8);
	    Expression e = em.sumExpression(operator1, operator2);
	    assertEquals(e.toString(), "((5.0) + (8.0))");
	}

}
