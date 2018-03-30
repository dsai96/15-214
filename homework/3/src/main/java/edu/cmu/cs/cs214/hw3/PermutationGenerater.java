package edu.cmu.cs.cs214.hw3;

import java.util.Arrays;
import java.util.Iterator;

public class PermutationGenerater implements Iterator<int[]>, Iterable<int[]> {

  private int[] numList;
  private int numOfPermutations; 
  private int currIndex = 0;
  

  PermutationGenerater(int[] list) {
    Arrays.sort(list);
    this.numList = list;
    numOfPermutations = Factorial.getFactorial(numList.length);
  }
  
  @Override
  public boolean hasNext() {
    return (currIndex < numOfPermutations);
  }

  //code for this algorithm found online and made minor adjustments
  @Override
  public int[] next() {
    if (currIndex == 0) {
      currIndex++;
      return numList;
    }
    int i1 = numList.length - 1;
    while (i1 > 0 && numList[i1 - 1] >= numList[i1]) {
      i1--;
    }
    if (i1 <= 0) {
      return null;
    }
    int i2 = numList.length - 1;
    while (numList[i2] <= numList[i1 - 1]) {
      i2--;
    }
    int temp = numList[i1 - 1];
    numList[i1 - 1] = numList[i2];
    numList[i2] = temp;
    
    i2 = numList.length - 1;
    while (i1 < i2) {
      temp = numList[i1];
      numList[i1] = numList[i2];
      numList[i2] = temp;
      i1++; 
      i2--;
    }
    currIndex++;
    return numList;
  }

  @Override
  public Iterator<int[]> iterator() {
    return this;
  }

}
