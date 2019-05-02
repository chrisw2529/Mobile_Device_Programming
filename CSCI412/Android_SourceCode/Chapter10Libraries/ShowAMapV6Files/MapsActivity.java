package com.jblearning.showamapv6;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity
                          implements OnMapReadyCallback {
  public static final double DELTA = 0.01;

  private GoogleMap mMap;
  private String city;
  private SpeechRecognizer recognizer;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_maps );

    // retrieve city name from original intent
    Intent originalIntent = getIntent( );
    city = originalIntent.getStringExtra( Cities.CITY_KEY );
    if( city == null )
      city = Cities.DEFAULT_CITY;

    // Obtain the SupportMapFragment and get notified
    // when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment)
        getSupportFragmentManager( ).findFragmentById( R.id.map );
    mapFragment.getMapAsync( this );
  }

  @Override
  public void onMapReady( GoogleMap googleMap ) {
    mMap = googleMap;

    // default latitude and longitude for city before geocoding city
    double latitude = Cities.DEFAULT_LATITUDE;
    double longitude = Cities.DEFAULT_LONGITUDE;

    // retrieve attraction for city
    Cities cities = MainActivity.cities;
    String attraction = cities.getAttraction( city );

    // retrieve latitude and longitude of city/attraction
    Geocoder coder = new Geocoder( this );
    try {
      // geocode city
      String address = attraction + ", " + city;
      List<Address> addresses = coder.getFromLocationName( address, 5 );
      if( addresses != null ) {
        latitude = addresses.get( 0 ).getLatitude( );
        longitude = addresses.get( 0 ).getLongitude( );
      } else // geocoding failed; use default values
        city = Cities.DEFAULT_CITY;
    } catch( IOException ioe ) {
      // geocoding failed; use default city, latitude and longitude
      city = Cities.DEFAULT_CITY;
    }

    // update the map
    LatLng cityLocation = new LatLng( latitude, longitude );

    CameraUpdate update
        = CameraUpdateFactory.newLatLngZoom( cityLocation, 15.5f );
    mMap.moveCamera( update );

    MarkerOptions options = new MarkerOptions( );
    options.position( cityLocation );
    options.title( attraction );
    options.snippet( Cities.MESSAGE );
    mMap.addMarker( options );

    CircleOptions circleOptions = new CircleOptions( )
        .center( cityLocation ).radius( 500 )
        .strokeWidth( 10.0f ).strokeColor( 0xFFFF0000 );
    mMap.addCircle( circleOptions );

    // start listening to speech
    listen( );
  }

  public void updateMap(LatLng location, float zoomLevel ) {
    CameraUpdate center = CameraUpdateFactory.newLatLng( location );
    CameraUpdate zoom = CameraUpdateFactory.zoomTo( zoomLevel );
    mMap.moveCamera( center );
    mMap.animateCamera( zoom );
  }

  public void listen( ) {
    if( recognizer != null )
      recognizer.destroy( );
    recognizer = SpeechRecognizer.createSpeechRecognizer( this );
    SpeechListener listener = new SpeechListener( );
    recognizer.setRecognitionListener( listener );
    recognizer.startListening( new Intent(
        RecognizerIntent.ACTION_RECOGNIZE_SPEECH ) );
  }

  private class SpeechListener implements RecognitionListener {
    public void onBeginningOfSpeech( ){
    }

    public void onEndOfSpeech( ) {
    }

    public void onReadyForSpeech( Bundle params ){
    }

    public void onError( int error ) {
      listen( );
    }

    public void onResults( Bundle results ) {
      ArrayList<String> words =
          results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
      float [ ] scores =
          results.getFloatArray( SpeechRecognizer.CONFIDENCE_SCORES );

      String match =
          MatchingUtility.firstMatchWithMinConfidence( words, scores );
      LatLng pos = mMap.getCameraPosition( ).target;
      float zoomLevel = mMap.getCameraPosition( ).zoom;

      if( match != null ) {
        if( match.equalsIgnoreCase( Directions.NORTH.toString( ) ) )
          pos = new LatLng( pos.latitude + DELTA, pos.longitude );
        else if( match.equalsIgnoreCase( Directions.SOUTH.toString( ) ) )
          pos = new LatLng( pos.latitude - DELTA, pos.longitude );
        else if( match.equalsIgnoreCase( Directions.WEST.toString( ) ) )
          pos = new LatLng( pos.latitude, pos.longitude - DELTA );
        else if( match.equalsIgnoreCase( Directions.EAST.toString( ) ) )
          pos = new LatLng( pos.latitude , pos.longitude + DELTA );

        updateMap( pos, zoomLevel );
      }
      listen( );
    }

    public void onPartialResults( Bundle partialResults ) {
    }

    public void onBufferReceived( byte [ ] buffer) {
    }

    public void onEvent( int eventType, Bundle params ) {
    }

    public void onRmsChanged( float rmsdB ) {
    }
  }
}



