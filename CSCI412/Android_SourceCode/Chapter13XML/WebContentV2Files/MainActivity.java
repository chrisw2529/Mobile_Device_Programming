package com.jblearning.webcontentv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  private final String URL
      = "http://blogs.jblearning.com/computer-science/feed/";
  // private final String URL
      // = "https://captechu.edu/rss.xml";

  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );
    ParseTask task = new ParseTask( this );
    task.execute( URL );
  }

  public void displayList( ArrayList<Item> items ) {
    if( items != null ) {
      for( Item item : items )
        Log.w( "MainActivity", item.toString( ) );
    }
  }
}
