package com.jblearning.showamapv6;

import java.util.ArrayList;

public class MatchingUtility {
  public static final float MIN_CONFIDENCE = 0.25f;
  
  public static String firstMatchWithMinConfidence
                ( ArrayList<String> words, float [ ] confidLevels ) {
    if( words == null || confidLevels == null )
      return null;
    int numberOfWords = words.size( );
    for( int i = 0; i < numberOfWords && i < confidLevels.length; i++ ) {
      if( confidLevels[i] < MIN_CONFIDENCE )
        break;
      String word = words.get( i );
      for( Directions dir : Directions.values( ) ) {
        if( word.equalsIgnoreCase( dir.toString( ) ) )
          return word;
      }
    }
    return null; 
  }
}