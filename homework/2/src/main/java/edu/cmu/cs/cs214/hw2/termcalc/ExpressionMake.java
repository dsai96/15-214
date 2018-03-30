package edu.cmu.cs.cs214.hw2.termcalc;

import edu.cmu.cs.cs214.hw2.expression.BinaryExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumberExpression;
import edu.cmu.cs.cs214.hw2.expression.UnaryExpression;
import edu.cmu.cs.cs214.hw2.operator.Binary;
import edu.cmu.cs.cs214.hw2.operator.Unary;
/**
 * ExpressionMake implements expressionMaker
 * @author dsai96
 *
 */
public class ExpressionMake implements ExpressionMaker {

	@Override
	public Expression sumExpression(Expression addend1, Expression addend2) {
		return new BinaryExpression(Binary.ADD, addend1, addend2);
	}

	@Override
	public Expression differenceExpression(Expression op1, Expression op2) {
		return new BinaryExpression(Binary.SUBTRACT, op1, op2);
	}

	@Override
	public Expression productExpression(Expression factor1, Expression factor2) {
		return new BinaryExpression(Binary.MULTIPLY, factor1, factor2);
	}

	@Override
	public Expression divisionExpression(Expression dividend, Expression divisor) {
		
		return new BinaryExpression(Binary.DIVIDE, dividend, divisor);
	}

	@Override
	public Expression exponentiationExpression(Expression base, Expression exponent) {
		return new BinaryExpression(Binary.EXP, base, exponent);
	}

	@Override
	public Expression negationExpression(Expression operand) {
		return new UnaryExpression(Unary.NEGATION, operand);
	}

	@Override
	public Expression absoluteValueExpression(Expression value) {
		return new UnaryExpression(Unary.ABSVALUE, value);
	}

	@Override
	public Expression numberExpression(double value) {
		return new NumberExpression(value);
	}

}
