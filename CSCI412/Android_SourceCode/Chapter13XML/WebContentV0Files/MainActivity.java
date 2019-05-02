package com.jblearning.webcontentv0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance( );
      SAXParser saxParser = factory.newSAXParser( );
      SAXHandler handler = new SAXHandler( );   
      InputStream is = getResources( ).openRawResource( R.raw.test );    
      saxParser.parse( is, handler );
    } 
    catch ( Exception e ) {
      Log.w( "MainActivity", "e = " + e.toString( ) );
    } 
  }
}
