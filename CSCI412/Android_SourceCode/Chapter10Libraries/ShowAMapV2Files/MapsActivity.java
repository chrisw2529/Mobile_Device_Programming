package com.jblearning.showamapv2;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

  private GoogleMap mMap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;

    LatLng whiteHouse = new LatLng( 38.8977, -77.0366 );
    CameraUpdate update
        = CameraUpdateFactory.newLatLngZoom( whiteHouse, 15.5f );
    mMap.moveCamera( update );

    MarkerOptions options = new MarkerOptions( );
    options.position( whiteHouse );
    options.title( "Hello" );
    options.snippet( "How is the food?" );
    mMap.addMarker( options );

    CircleOptions circleOptions = new CircleOptions( )
        .center( whiteHouse ).radius( 500 )
        .strokeWidth( 10.0f ).strokeColor( 0xFFFF0000 );
    mMap.addCircle( circleOptions );
  }
}
