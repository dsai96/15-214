package edu.cmu.cs.cs214.hw2.expression;

import java.util.Objects;
/**
 * 
 * @author dsai96  Sai Dhulipalla
 * Creates numbers as an expression
 *
 */
public class NumberExpression implements Expression {

	private double operand;

	/**
	 * 
	 * @param operand is the double that creates the number expression
	 * Number expression is the creater for numbers
	 */
	public NumberExpression(double operand) {
		this.operand = Objects.requireNonNull(operand, "Operator shouldn't be null");
	}
	
	/**
	 * evaluates the number expression to a number
	 */
	@Override
	public double eval() {
		return operand;
	}
	
	@Override
	public String toString() {
		return String.format("(%s)", operand);
	}

}
