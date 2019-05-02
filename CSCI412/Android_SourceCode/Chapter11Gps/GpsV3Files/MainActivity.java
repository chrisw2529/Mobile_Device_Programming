package com.jblearning.gpsv3;

import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks,
                   GoogleApiClient.OnConnectionFailedListener,
                   LocationListener {
  public static final String MA = "MainActivity";
  private final static int REQUEST_CODE = 100;
  private GoogleApiClient gac;
  private TravelManager manager;
  private EditText addressET;
  private TextView distanceTV;
  private TextView timeLeftTV;
  private String destinationAddress = "";

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );
    manager = new TravelManager( );
    addressET = ( EditText ) findViewById( R.id.destination_et );
    distanceTV = ( TextView ) findViewById( R.id.distance_tv );
    timeLeftTV = ( TextView ) findViewById( R.id.time_left_tv );

    gac = new GoogleApiClient.Builder( this )
            .addConnectionCallbacks( this )
            .addOnConnectionFailedListener( this )
            .addApi( LocationServices.API ).build( );
  }

  public void onLocationChanged( Location location ) {
    float accuracy = location.getAccuracy( );
    long nanos = location.getElapsedRealtimeNanos( );
    Log.w( "MainActivity", "accuracy " + accuracy + ", nanos = " + nanos );
    updateTrip( null );
  }

  public void updateTrip( View v ) {
    String address = addressET.getText( ).toString( );
    boolean goodGeoCoding = true;
    if( ! address.equals( destinationAddress ) ) {
      destinationAddress = address;
      Geocoder coder = new Geocoder( this );
      try {
        // geocode destination
        List<Address> addresses
                         = coder.getFromLocationName( destinationAddress, 5 );
        if( addresses != null ) {
          double latitude = addresses.get( 0 ).getLatitude( );
          double longitude = addresses.get( 0 ).getLongitude( );
          Location destinationLocation = new Location( "destination" );
          destinationLocation.setLatitude( latitude );
          destinationLocation.setLongitude( longitude );
          manager.setDestination( destinationLocation );
        }
      } catch ( IOException ioe ) {
        goodGeoCoding = false;
      }
    }

    FusedLocationProviderApi flpa = LocationServices.FusedLocationApi;
    Location current = flpa.getLastLocation( gac );
    if( current != null && goodGeoCoding ) {
      distanceTV.setText( manager.milesToDestination( current ) );
      timeLeftTV.setText( manager.timeToDestination( current ) );
    }
  }

  protected void onPause( ) {
    super.onPause( );
    FusedLocationProviderApi flpa = LocationServices.FusedLocationApi;
    flpa.removeLocationUpdates( gac, this);
  }

  public void onConnected( Bundle hint ) {
    FusedLocationProviderApi flpa = LocationServices.FusedLocationApi;
    LocationRequest request = new LocationRequest( );
    request.setInterval( 30000 );
    // request.setPriority( LocationRequest.PRIORITY_HIGH_ACCURACY );
    // request.setSmallestDisplacement( 100 );
    if( gac.isConnected( ) ) // in case connection was just lost !!
      flpa.requestLocationUpdates( gac, request, this );
    else
      gac.connect( );
  }

  public void onConnectionSuspended( int cause ) {
  }

  public void onConnectionFailed( ConnectionResult result ) {
    // test result here
    if( result.hasResolution( ) ) { // a resolution can be started
      try {
        result.startResolutionForResult( this, REQUEST_CODE );
      } catch( IntentSender.SendIntentException sie ) {
        // Intent has been cancelled or cannot execute, exit app
        Toast.makeText( this, "Google Play services problem, exiting",
                Toast.LENGTH_LONG ).show( );
        finish( );
      }
    }
  }

  public void onActivityResult( int requestCode,
                                int resultCode, Intent data ) {
    if( requestCode == REQUEST_CODE && resultCode == RESULT_OK ) {
      // problem solved, try to connect again
      gac.connect( );
    }
  }

  protected void onStart( ) {
    super.onStart( );
    if( gac != null )
      gac.connect( );
  }
}