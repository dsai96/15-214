package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.cmu.cs.cs214.hw2.operator.Binary;

//CHECKSTYLE:OFF
public class BinaryExpressionTestTest {

  @Test
  public void testAdd() {
    Expression operand1 = new NumberExpression(3);
    Expression operand2 = new NumberExpression(10);
    Expression e = new BinaryExpression(Binary.ADD, operand1, operand2);
    assertEquals(e.eval(), 13.0, Math.pow(10, -4));

  }
  
  @Test
  public void testSubtract() {
    Expression operand1 = new NumberExpression(3);
    Expression operand2 = new NumberExpression(10);
    Expression e = new BinaryExpression(Binary.SUBTRACT, operand1, operand2);
    assertEquals(e.eval(), -7.0, Math.pow(10, -4));
  }

  @Test
  public void testMultiply() {
    Expression operand1 = new NumberExpression(3);
    Expression operand2 = new NumberExpression(10);
    Expression e = new BinaryExpression(Binary.MULTIPLY, operand1, operand2);
    assertEquals(e.eval(), 30.0, Math.pow(10, -4));
  }

 @Test
	public void testDivide() {
		Expression operand1 = new NumberExpression(30);
		Expression operand2 = new NumberExpression(10);
		Expression e = new BinaryExpression(Binary.DIVIDE, operand1, operand2);
	    assertEquals(e.eval(), 3.0, Math.pow(10, -4));
		assertTrue(e.toString().equals("((30.0) / (10.0))"));

	}
	

	@Test
	public void testExponent() {
		Expression operand1 = new NumberExpression(3);
		Expression operand2 = new NumberExpression(4);
		Expression e = new BinaryExpression(Binary.EXP, operand1, operand2);
	    assertEquals(e.eval(), 81.0, Math.pow(10, -4));
		assertTrue(e.toString().equals("((3.0) ^ (4.0))"));
	}
	
	@Test
	public void testToString() {
		Expression operand1 = new NumberExpression(3);
		Expression operand2 = new NumberExpression(10);
		Expression e = new BinaryExpression(Binary.ADD, operand1, operand2);
		assertTrue(e.toString().equals("((3.0) + (10.0))"));
	}

}
