package com.jblearning.temperaturewidgetv6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RemoteDataReader {
  private String urlString;
  
  public RemoteDataReader( String baseUrl, String cityString,
                           String keyName, String key ) {
    try {
      urlString = baseUrl
                + URLEncoder.encode( cityString, "UTF-8" );
      if( keyName != null && key != null )
        urlString += URLEncoder.encode( "&", "UTF-8" )
                   + keyName + "=" + key;
    } catch( UnsupportedEncodingException uee ) {
      urlString = "";
    }
  }
  
  public String getData( ) {
    try {
      // Establish the connection
      URL url = new URL( urlString ); 
      HttpURLConnection con = ( HttpURLConnection ) url.openConnection( );  
      con.connect( );  
      
      // Get the input stream and prepare to read
      InputStream is = con.getInputStream( );
      BufferedReader br =
          new BufferedReader( new InputStreamReader( is ) );
      
      // Read the data
      String dataRead = new String( );
      String line = br.readLine( );
      while ( line != null ) {
        dataRead += line;
        line = br.readLine( );
      }
            
      is.close( );
      con.disconnect( ); 
      return dataRead;
    } catch( Exception e ) {
      return "";
    }           
  }
}