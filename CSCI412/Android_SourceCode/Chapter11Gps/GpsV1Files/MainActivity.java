package com.jblearning.gpsv1;

import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks,
                   GoogleApiClient.OnConnectionFailedListener {
  public static final String MA = "MainActivity";
  private final static int REQUEST_CODE = 100;
  private GoogleApiClient gac;
  private Location location;
  private TextView locationTV;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );
    locationTV = ( TextView ) findViewById( R.id.location_tv );

    gac = new GoogleApiClient.Builder( this )
            .addConnectionCallbacks( this )
            .addOnConnectionFailedListener( this )
            .addApi( LocationServices.API ).build( );
  }

  public void displayLocation( ) {
    FusedLocationProviderApi flpa = LocationServices.FusedLocationApi;
    location = flpa.getLastLocation( gac );
    if( location != null ) {
      double latitude = location.getLatitude( );
      double longitude = location.getLongitude( );
      locationTV.setText( latitude + ", " + longitude );
      Log.w( MA, "latitude = " + latitude + "; longitude = " + longitude );
    } else
      locationTV.setText( "Error locating the device" );
  }

  public void onConnected( Bundle hint ) {
    Log.w( MA, "connected" );
    displayLocation( );
  }

  public void onConnectionSuspended( int cause ) {
    Log.w( MA, "connection suspended" );
    // Our GoogleApiClient will automatically try to restore the connection
  }

  public void onConnectionFailed( ConnectionResult result ) {
    // test result here
    Log.w( MA, "connection failed" );
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