package edu.cmu.cs.cs214.hw3;


public class Factorial {

  /**
   * 
   * @param n is any int you want to find factorial of.
   * @return the factorial of n.
   */
  public static int getFactorial(int n) {
    int result = 1;
    for (int i = 1; i <= n; i++) {
      result *= i;
    }
    return result;
  }

}
