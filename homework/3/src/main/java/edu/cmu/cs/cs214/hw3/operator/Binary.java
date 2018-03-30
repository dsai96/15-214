package edu.cmu.cs.cs214.hw3.operator;
/**
 * 
 * @author dsai96
 * binary enums include add, subtract, multiply, exp and divide
 */
public enum Binary implements BinaryOperator{

	ADD {
		@Override
		public double apply(double arg1, double arg2) {
			return arg1 + arg2;
		}
		
		@Override
		public String toString() {
			return "+";
		}
	},
	
	SUBTRACT {
		@Override
		public double apply(double arg1, double arg2) {
			return arg1 - arg2;
		}
		
		@Override
		public String toString() {
			return "-";
		}
	},
	
	MULTIPLY {
		@Override
		public double apply(double arg1, double arg2) {
			return arg1 * arg2;
		}
		
		@Override
		public String toString() {
			return "*";
		}
	},

	DIVIDE {
		@Override
		public double apply(double arg1, double arg2) {
			return arg1 / arg2;
		}
		
		@Override
		public String toString() {
			return "/";
		}
	},
	
	EXP {
		@Override
		public double apply(double arg1, double arg2) {
			return Math.pow(arg1, arg2);
		}
		
		@Override
		public String toString() {
			return "^";
		}
	};
	
}
