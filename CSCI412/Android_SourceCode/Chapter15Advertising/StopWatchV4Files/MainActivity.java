package com.jblearning.stopwatchv4;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
  private Chronometer chrono;
  private boolean started = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    chrono = (Chronometer) findViewById( R.id.stop_watch );
  }

  public void startStop( View view ) {
    Button startStopButton = (Button) findViewById( R.id.start_stop );
    if( started ) {
      chrono.stop( );
      started = false;
      startStopButton.setText( "START" );
      startStopButton.setBackgroundResource( R.drawable.start_button );
    } else {
      resetChrono( );
      chrono.start( );
      started = true;
      startStopButton.setText( "STOP" );
      startStopButton.setBackgroundResource( R.drawable.stop_button );
    }
  }

  public void reset( View view ) {
    if( !started )
      chrono.setBase( SystemClock.elapsedRealtime( ) );
  }

  public void resetChrono( ) {
    String chronoText = chrono.getText( ).toString();
    long idleMilliseconds = ClockUtility.milliseconds( chronoText );
    chrono.setBase( SystemClock.elapsedRealtime( ) - idleMilliseconds );
  }
}
