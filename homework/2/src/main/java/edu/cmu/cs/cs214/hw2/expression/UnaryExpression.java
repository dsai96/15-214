package edu.cmu.cs.cs214.hw2.expression;

import java.util.Objects;

import edu.cmu.cs.cs214.hw2.operator.Unary;
/**
 * 
 * @author dsai96
 *  * Creates a BinaryExpression with an operand and operator 
 */
public class UnaryExpression implements Expression {

	private Unary operator;
	private Expression operand;

	/**
	 * 
	 * @param u is unary operator
	 * @param e is the expression to put the operator on
	 */
	public UnaryExpression(Unary u, Expression e) {
		this.operator = Objects.requireNonNull(u, "Operator shouldn't be null");
		this.operand = Objects.requireNonNull(e, "Operand shouldn't be null");
	} 
	
	@Override
	public double eval() {
		return operator.apply(operand.eval());
	}

	@Override
	public String toString() {
		return String.format("(%s%s)", operator, operand);
	}
	
}
