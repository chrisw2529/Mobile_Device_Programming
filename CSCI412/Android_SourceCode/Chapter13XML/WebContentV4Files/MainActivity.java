package com.jblearning.webcontentv4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  private final String URL
      = "http://blogs.jblearning.com/computer-science/feed/";
  private ListView listView;
  private ArrayList<Item> listItems;

  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );
    listView = ( ListView ) findViewById( R.id.list_view );
    ParseTask task = new ParseTask( this );
    task.execute( URL );
  }

  public void displayList( ArrayList<Item> items ) {
    listItems = items;
    if( items != null ) {
      // Build ArrayList of titles to display
      ArrayList<String> titles = new ArrayList<String>( );
      for( Item item : items )
        titles.add( item.getTitle( ) );

      ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
          android.R.layout.simple_list_item_1, titles );
      listView.setAdapter( adapter );
      ListItemHandler lih = new ListItemHandler( );
      listView.setOnItemClickListener( lih );
    } else
      Toast.makeText( this, "Sorry - No data found",
          Toast.LENGTH_LONG ).show( );
  }

  private class ListItemHandler
      implements AdapterView.OnItemClickListener {
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id ) {
      Item selectedItem = listItems.get( position );
      Uri uri = Uri.parse( selectedItem.getLink( ) );
      Intent browserIntent = new Intent( Intent.ACTION_VIEW, uri );
      startActivity( browserIntent );
    }
  }
}
