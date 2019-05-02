package com.jblearning.asynctasktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    Log.w( "MainActivity", "Instantiating TestTask object" );
    TestTask task = new TestTask( this );
    Log.w( "MainActivity", "Starting TestTask" );
    task.execute( 1 );
    Log.w( "MainActivity", "Started TestTask" );
  }

  public void updateView( String s ) {
    Log.w( "MainActivity", "Inside updateView" );
    TextView tv = (TextView) findViewById( R.id.tv );
    tv.setText( s );
  }
}
