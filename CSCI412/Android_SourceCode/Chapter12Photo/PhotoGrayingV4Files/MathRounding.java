package com.jblearning.photograyingv4;

public class MathRounding {
  public static float keepTwoDigits( float f ) {
    int n = ( int ) ( 100 * f );  
    return ( ( float ) n ) / 100; 
  }
}
