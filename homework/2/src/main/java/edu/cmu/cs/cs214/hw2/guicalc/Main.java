package edu.cmu.cs.cs214.hw2.guicalc;
import edu.cmu.cs.cs214.hw2.operator.Binary;
import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;
import edu.cmu.cs.cs214.hw2.operator.Unary;
import edu.cmu.cs.cs214.hw2.operator.UnaryOperator;

import java.util.HashSet;
import java.util.Set;

/**
 * Main program that runs the GUI Calculator
 * Creates sets for unary & binary operators to pass GUIcalc
 */
public class Main {
  /**
   * 
   * @param args takes in the unary and binary operators
   * 
   */
    public static void main(String[] args) {
        Set<UnaryOperator> unaryOperators = new HashSet<>();
        for (UnaryOperator o : Unary.values()) {
        	unaryOperators.add(o);
        }

        Set<BinaryOperator> binaryOperators = new HashSet<>();
        for (BinaryOperator o : Binary.values()) {
        	binaryOperators.add(o);
        }
        
        new GuiCalculator(unaryOperators, binaryOperators);
    }
}
    
