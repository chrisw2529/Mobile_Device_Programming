package com.jblearning.webcontentv3;

import android.os.AsyncTask;
import android.widget.Toast;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParseTask extends AsyncTask<String, Void, ArrayList<Item>> {
  private MainActivity activity;
	
  public ParseTask( MainActivity fromActivity ) {
    activity = fromActivity; 
  }

  protected ArrayList<Item> doInBackground( String... urls ) {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance( );
      SAXParser saxParser = factory.newSAXParser( );
      SAXHandler handler = new SAXHandler( );
      saxParser.parse( urls[0], handler );
      return handler.getItems( ); 
    } catch( Exception e )  {
      Toast.makeText( activity, "Sorry - There was a problem parsing",
          Toast.LENGTH_LONG ).show( );
      return null;
    }    
  }

  protected void onPostExecute ( ArrayList<Item> returnedItems ) {
    activity.displayList( returnedItems );
  }
}