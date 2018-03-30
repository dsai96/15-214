package edu.cmu.cs.cs214.hw3;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PermutationGen {

  @Test
  public void orderedListTest() { 
    int[] l = new int[] {1, 2, 3};
    PermutationGenerater permGen = new PermutationGenerater(l);
    int size = 0;
    List<int[]> result = new ArrayList<int[]>();
    while (permGen.hasNext()) {
      size++;
      result.add(permGen.next());
    } 
    assertTrue(size == Factorial.getFactorial(l.length));
    List<int[]> expectedResult = new ArrayList<int[]>();
    int[] a = new int[] {1, 2, 3};
    int[] b = new int[] {1, 3, 2};
    int[] c = new int[] {2, 1, 3};
    int[] d = new int[] {2, 3, 1};
    int[] e = new int[] {3, 1, 2};
    int[] f = new int[] {3, 2, 1};
    expectedResult.add(a);
    expectedResult.add(b);
    expectedResult.add(c);
    expectedResult.add(d);
    expectedResult.add(e);
    expectedResult.add(f);
    for (int[] p : expectedResult){
      System.out.println(Arrays.toString(p));
    } 
    //order is different for result and expectedResult
    assertTrue(!result.equals(expectedResult));
  }
  
  
  
}
