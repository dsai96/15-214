package edu.cmu.cs.cs214.hw3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import edu.cmu.cs.cs214.hw3.expression.BinaryExpression;
import edu.cmu.cs.cs214.hw3.expression.Expression;
import edu.cmu.cs.cs214.hw3.expression.NumberExpression;
import edu.cmu.cs.cs214.hw3.expression.Variable;
import edu.cmu.cs.cs214.hw3.operator.Binary;
import edu.cmu.cs.cs214.hw3.operator.BinaryOperator;

public class Cryptarithm {

  private Map<String, Variable> letterVars = new LinkedHashMap<String, Variable>();
  private List<String> rightExp = new ArrayList<String>();
  private List<String> firstDigits = new ArrayList<String>();
  private List<String> leftExp = new ArrayList<String>();
  private HashSet<String> uniqueLetters = new HashSet<String>();

  /**
   * 
   * @param stringInput
   *          Cryptarithm takes a string array of words s with operators.
   */
  public Cryptarithm(String[] stringInput) {
    for (int itemIndex = 0; itemIndex < stringInput.length; itemIndex++) {
      String item = stringInput[itemIndex];
      if (!(item.equals("-") || item.equals("*") || item.equals("+") || item.equals("="))) {
        for (int i = 0; i < item.length(); i++) {
          if (!Character.isAlphabetic(item.charAt(i))) {
            throw new IllegalArgumentException("Not an alphabetic character");
          } else {
            String tempC = Character.toString(item.charAt(i));
            uniqueLetters.add(tempC);
            letterVars.put(tempC, new Variable(tempC));
          }
        }
      }
    }
    boolean foundEquals = false;
    for (int itemIndex = 0; itemIndex < stringInput.length; itemIndex++) {
      String item = stringInput[itemIndex];
      if (item.equals("=")) {
        foundEquals = !foundEquals;
      } else if (!foundEquals) {
        leftExp.add(item);
      } else {
        rightExp.add(item);
      }
    }
    if (!foundEquals) {
      throw new IllegalArgumentException("Too many or no equal sign");
    }
    if (uniqueLetters.size() > 10) {
      throw new IllegalArgumentException("Too many unique letters");
    }
  }

  public Map<String, Variable> getletterVars() {
    return letterVars;
  }

  public List<String> getrightExp() {
    return rightExp;
  }

  public List<String> getleftExp() {
    return leftExp;
  }

  public List<String> getfirstDigits() {
    return firstDigits;
  }

  /**
   * 
   * @return number of unique Letters.
   */

  public Integer getNumOfUniqueLetters() {
    return uniqueLetters.size();
  }

  /**
   * 
   * @param inputStrings
   *          is the left or right arraylist that was split at initialization.
   * @param letterVars
   *          is the map that is made at initialization.
   * @return a binary expression composing the operators and operands in e.
   */

  public Expression createExpression(List<String> inputStrings, Map<String, Variable> letterVars) {
    Expression result = parseWord(inputStrings.get(0), letterVars);
    BinaryOperator lastOperator = null;
    for (int i = 1; i < inputStrings.size(); i++) {
      switch (inputStrings.get(i)) {
        case "+":
          lastOperator = Binary.ADD;
          break;
        case "-":
          lastOperator = Binary.SUBTRACT;
          break;
        case "*":
          lastOperator = Binary.MULTIPLY;
          break;
        default:
          Expression e2 = parseWord(inputStrings.get(i), letterVars);
          result = new BinaryExpression(lastOperator, result, e2);
      }
    }
    return result;
  }

  private Expression parseWord(String word, Map<String, Variable> letterVars) {
    firstDigits.add(word.charAt(0) + "");
    Expression convertedWord = letterVars.get(word.charAt(0) + "");
    Expression ten = new NumberExpression(10);
    for (int i = 1; i < word.length(); i++) {
      Expression e1 = new BinaryExpression(Binary.MULTIPLY, convertedWord, ten);
      Expression e2 = letterVars.get(word.charAt(i) + "");
      convertedWord = new BinaryExpression(Binary.ADD, e1, e2);
    }
    return convertedWord;
  }

}
