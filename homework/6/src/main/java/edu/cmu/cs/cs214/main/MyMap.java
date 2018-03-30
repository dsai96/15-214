package edu.cmu.cs.cs214.main;

import java.io.InputStream;
import java.util.Scanner;

/**
 * 
 * @author dsai96
 * My Map function that takes in the String of the word and the count of the word in the document
 */
public class MyMap implements Mapper<String, Integer> {

 
  @Override
  public void apply(InputStream input, Emitter emitter) {
    try (Scanner scanner = new Scanner(input)) { 
      scanner.useDelimiter("\\W+");   
      while (scanner.hasNext()) {
        emitter.emit(scanner.next(), "1");
      }
    }
   catch (Exception e) {
     e.printStackTrace(System.err);
   }
  }
}


