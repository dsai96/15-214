package edu.cmu.cs.cs214.hw2.expression;

/**
 * An expression that represents a variable. Its value may be set as well as read.
 * Unlike other expressions in this assignment, variables are mutable. Using a variable
 * within a containing expression makes the expression a function of the current value of 
 * the variable.
 *
 * <p>Variables have immutable string names, which are used in their string representations. If an 
 * expression contains multiple distinct variables as subexpressions, it's important that they 
 * have different names, or the string representation of the containing expression will be 
 * misleading.
 */
public class Variable implements Expression {
    /** The name of this variable */
	private final String name;
	
	/** The current value of this variable */
	private double value;

	/**
    * Constructs a variable with the specified name, whose initial value is zero.
    * @param name is the variable name
   */
    public Variable(String name) {
    	this.name = name;
    	this.value = 0.0;
    }

    @Override
    public double eval() {
    	return value; 
    }

    @Override
    public String toString() { 
        return String.format("(%s = %s)", name, value);
    }
 
    /**
     * Sets the value of this variable.
     *
     * @param value the new value of this variable
     */
    public void store(double value) {
    	this.value = value;
    }

    /**
     * @return the name of this variable
     */
    
    public String name() { 
        return name;
    }
}

