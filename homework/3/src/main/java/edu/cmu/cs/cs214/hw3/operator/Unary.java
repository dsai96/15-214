package edu.cmu.cs.cs214.hw3.operator;

/**
 * 
 * @author dsai96
 * unary has absValue and negation enums
 */
public enum Unary implements UnaryOperator {

	ABSVALUE {

		@Override
		public double apply(double arg) {
			return Math.abs(arg);
		}
		
		@Override
		public String toString() {
			return "||";
		}
	}, 
	
	NEGATION {

		@Override
		public double apply(double arg) {
			return arg * -1;
		}
		
		@Override
		public String toString() {
			return "~"; 
		}
	};
	
}
