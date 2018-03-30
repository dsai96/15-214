package edu.cmu.cs.cs214.hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cmu.cs.cs214.hw3.expression.Variable;


public class SolveCryptarithm {
    
  private List<ArrayList<Integer>> allsubsets;
  private static int numOfDigits = 10;
  private Cryptarithm cryp;
  private List<Map<String, Variable>> answer = new ArrayList<Map<String, Variable>>();

  /**
   * 
   * @param args is the string[] or words and operators taken to parse.
   */
  public SolveCryptarithm(String[] args) {
    cryp = new Cryptarithm(args);
    List<ArrayList<Integer>> allsubsets = generateSubsets(cryp.getNumOfUniqueLetters());
    this.allsubsets = allsubsets;
  }
  
  /**
   * 
   * @param numOfUniqueLetters is number of unique letters from parsed strings.
   * @return is a list all subsets that could be made where 10 choose numOfuniqueLetters.
   */
  public List<ArrayList<Integer>> generateSubsets(Integer numOfUniqueLetters) {
    List<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
    for (int bitVec = 0; bitVec < 1 << numOfDigits; bitVec++) {
      if (Integer.bitCount(bitVec) == numOfUniqueLetters) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        // bitString represents the string of indexes where 1 is and is length
        // 10
        String bitString = Integer.toBinaryString(bitVec);
        if (bitString.length() != numOfDigits) {
          bitString = String.format("%010d", Integer.parseInt(bitString));
        }
        // indexes returns array of integers mapped to those indexes
        for (int i = 0; i < bitString.length(); i++) {
          if ((bitString.charAt(i)) == "1".charAt(0)) {
            indexes.add(i);
          }
        }
        allsubsets.add(indexes);
      }
    }
    return allsubsets;
  }
  
  /**
   * returns the final list of possible solutions.
   */
  public List<HashMap<String, Integer>> solve() {
    List<HashMap<String, Integer>> result = new ArrayList<HashMap<String, Integer>>();
    for (ArrayList<Integer> subset : allsubsets) {
      int[] subsetArray = new int[subset.size()];
      // need to convert from arraylist to int[]
      for (int i = 0; i < subset.size(); i++) {
        subsetArray[i] = subset.get(i);
      }
      PermutationGenerater permGen = new PermutationGenerater(subsetArray);
      for (int[] array : permGen) {
        Map<String, Variable> letterVars = cryp.getletterVars();
        int index = 0;
        for (Variable variable : letterVars.values()) {
          variable.store(array[index]);
          index++;
          Double left = cryp.createExpression(cryp.getleftExp(), letterVars).eval();
          Double right = cryp.createExpression(cryp.getrightExp(), letterVars).eval();
          if (left.equals(right)) {
            System.out.println(left);
            System.out.println(right);
            answer.add(letterVars);
          }
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    new SolveCryptarithm(args);
  }

}
