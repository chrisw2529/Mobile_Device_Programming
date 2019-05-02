package com.jblearning.stopwatchv2;

public class ClockUtility {
  /*
   * This method computes and returns the equivalent number of milliseconds
   *   for its parameter, a String that represents a clock
   * @param  clock, a String, expected to look like mm:ss or hh:mm:ss
   * @return a long, the equivalent number of milliseconds to clock
   */
  public static long milliseconds( String clock  ){
	  long ms = 0;
    String [] clockArray = clock.split( ":" );
		    
    // compute milliseconds
    try {
	    if( clockArray.length == 3 ) {
		    ms = Integer.parseInt( clockArray[0] ) * 60 * 60 * 1000
           + Integer.parseInt( clockArray[1] ) * 60 * 1000
           + Integer.parseInt( clockArray[2] ) * 1000;
      } else if ( clockArray.length == 2 ) {
        ms = Integer.parseInt( clockArray[0] ) * 60 * 1000
           + Integer.parseInt( clockArray[1] ) * 1000;
      }
    } catch( NumberFormatException nfe ) {
      // should never get here if clock has proper format	
    }
    return ms;
  }
}
