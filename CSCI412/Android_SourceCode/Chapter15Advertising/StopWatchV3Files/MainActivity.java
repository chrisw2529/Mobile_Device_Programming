package com.jblearning.stopwatchv3;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
  private Chronometer chrono;
  private boolean started = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    chrono = (Chronometer) findViewById( R.id.stop_watch );

    // create a banner ad
    AdView adView = new AdView( this );
    // Set ad size and ad unit ID
    adView.setAdSize( AdSize.SMART_BANNER );
    String adUnitId = "ca-app-pub-3940256099942544/6300978111";
    adView.setAdUnitId( adUnitId );

    // define an ad request
    AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
    // Define target data for the ad request (optional)
    adRequestBuilder.setGender( AdRequest.GENDER_UNKNOWN );
    adRequestBuilder.addKeyword( "fitness" );
    adRequestBuilder.addKeyword ( "workout" );
    // request test (not live) ads for emulator
    adRequestBuilder.addTestDevice( AdRequest.DEVICE_ID_EMULATOR );
    // build the AdRequest
    AdRequest adRequest = adRequestBuilder.build( );

    // add the AdView to the LinearLayout
    LinearLayout adLayout = (LinearLayout) findViewById( R.id.ad_view );
    adLayout.addView( adView );

    // load the ad into the AdView
    adView.loadAd( adRequest );
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
