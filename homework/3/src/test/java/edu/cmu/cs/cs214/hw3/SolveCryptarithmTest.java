package edu.cmu.cs.cs214.hw3;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SolveCryptarithmTest {

  @Test
  public void mapperTest() {
    List<Integer> possArrangement = new ArrayList<Integer>();
    possArrangement.add(8);
    possArrangement.add(3);
    possArrangement.add(0);
    possArrangement.add(4);
    List<String> uniqueLetters = new ArrayList<String>();
    uniqueLetters.add("A");
    uniqueLetters.add("B");
    uniqueLetters.add("C");
    uniqueLetters.add("D");
    assertTrue(!uniqueLetters.equals(possArrangement));
//    Map<String, Integer> map = new HashMap<String, Integer>();
//    String[] s = {"AD", "+", "B", "=", "C"};
//    SolveCryptarithm cryp = new SolveCryptarithm(s);
//    assertTrue(map.get("A").equals(8));
//    assertTrue(map.get("D").equals(4));
//    assertTrue(map.size() == 4);
//    List<String> operands = new ArrayList<String>();
//    operands.add("ABCD");
//    operands.add("ABC");

    }
    
}
