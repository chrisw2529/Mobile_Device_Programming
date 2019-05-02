package com.jblearning.webcontentv0;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import android.util.Log;

public class SAXHandler extends DefaultHandler {
  public SAXHandler( ) {
  }

  public void startElement( String uri, String localName,
                            String startElement, Attributes attributes ) 
                            throws SAXException {
    Log.w( "MainActivity", "Inside startElement, startElement = " 
               + startElement );
  }

  public void endElement( String uri, String localName,
                          String endElement ) throws SAXException {
    Log.w( "MainActivity", "Inside endElement, endElement = " 
               + endElement );
  }

  public void characters( char ch [], int start,
                          int length ) throws SAXException {
    String text = new String( ch, start, length );
    Log.w( "MainActivity", "Inside characters, text = " + text );
  }
}

