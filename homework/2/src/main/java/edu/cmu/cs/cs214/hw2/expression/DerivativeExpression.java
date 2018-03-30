package edu.cmu.cs.cs214.hw2.expression;

/**
 * 
 * @author dsai96
 * This class is creating derivative expressions which evals the derivative of the fn
 * with respect to independentVar
 *
 */
public class DerivativeExpression implements Expression {

	private Variable independentVar;
	private final Expression fn;
	private final double deltaX = Math.pow(10, -9); 


	/**
	   * Creates an expression representing the derivative of the specified
	   * function with respect to the specified variable.
	   *
	   * @param fn the function whose derivative this expression represents
	   * @param independentVar the variable with respect to which we’re
	   *   differentiating
	   */
	  public DerivativeExpression(Expression fn, Variable independentVar) {
		  this.independentVar = independentVar;
		  this.fn = fn; 
		  }
	
	  /**
	   * evals the function to return the derivative 
	   */
	@Override
	public double eval() {
		double resultRight = fn.eval();
		double currX = independentVar.eval();
		independentVar.store(currX + deltaX);
		double resultLeft = fn.eval();
		independentVar.store(currX);
		return (resultLeft - resultRight)/deltaX;
	}

	/**
	 * @return the function and the name
	 */
	public String toString() {
		return fn.toString() + " , " + independentVar.name();
	}
}
