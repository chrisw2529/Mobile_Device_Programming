package com.jblearning.webcontentv3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  private final String URL
      = "http://blogs.jblearning.com/computer-science/feed/";
  private ListView listView;

  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );
    listView = ( ListView ) findViewById( R.id.list_view );
    ParseTask task = new ParseTask( this );
    task.execute( URL );
  }

  public void displayList( ArrayList<Item> items ) {
    if( items != null ) {
      // Build ArrayList of titles to display
      ArrayList<String> titles = new ArrayList<String>( );
      for( Item item : items )
        titles.add( item.getTitle( ) );

      ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
          android.R.layout.simple_list_item_1, titles );
      listView.setAdapter( adapter );
    } else
      Toast.makeText( this, "Sorry - No data found",
          Toast.LENGTH_LONG ).show( );
  }
}
