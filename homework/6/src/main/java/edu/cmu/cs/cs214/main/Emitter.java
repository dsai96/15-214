package edu.cmu.cs.cs214.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 
 * @author dsai96
 * An emitter is used to write to a file
 */
public class Emitter {
  
  private String filename;
  
  public Emitter(String filename) {
    this.filename = filename;
  }
  
  /**
   * 
   * @param key the word sent from the map function
   * @param value the count of the word in the document
   */
  public void emit(String key, String value) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
      //create a class to hold key value pair
      bw.write(key);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}
