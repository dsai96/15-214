package edu.cmu.cs.cs214.main;

import java.io.IOException;
import java.io.InputStream;

public interface Mapper<K, V> {
  
/**
 * 
 * @param input is an inputstream that can represent an entire file
 * @param e is the emitter to push the words onto a new file handled by the Emitter class
 */
  void apply(InputStream input, Emitter e);
  
}


