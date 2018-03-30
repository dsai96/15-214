package edu.cmu.cs.cs214.hw2.expression;

import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;

/**
 * 
 * @author  dsai96
 * Creates a BinaryExpression with 2 operands and operator 
 *
 */
public class BinaryExpression implements Expression {

  private BinaryOperator operator;
  private Expression operand1;
  private Expression operand2;

/**
 * 
 * @param operator BinaryOperator that operands are being done on 
 * @param operand1 expression that op is being done
 * @param operand2 expression that op is being done
 */
	public BinaryExpression(BinaryOperator operator, Expression operand1, Expression operand2) {
		this.operator = operator;
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	
	@Override
	public double eval() {
		return operator.apply(operand1.eval(), operand2.eval());
	}

	@Override
	public String toString() {
		return String.format("(%s %s %s)", operand1, operator, operand2);
	}

	
}
