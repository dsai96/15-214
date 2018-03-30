package edu.cmu.cs.cs214.hw2.expression;
/**
 * 
 * @author dsai96 this class returns zeros using Newtons method
 *
 */
public class NewtonMethod {
	
  /**
   * 
   * @param fn function to take derative of
   * @param x with respect to
   * @param approxZero starting value, that has to be higher than the actual value
   * @param tolerance the zero
   * @return the zero
   */
	 public static double zero(Expression fn, Variable x, double approxZero, double tolerance) {
		 x.store(approxZero);
		 if (fn.eval() <= tolerance) return approxZero;
		 DerivativeExpression d = new DerivativeExpression(fn, x);
		 approxZero =  x.eval() - fn.eval()/d.eval();
		 x.store(approxZero); 
		 return zero(fn, x, approxZero, tolerance);
	 }
	

}
