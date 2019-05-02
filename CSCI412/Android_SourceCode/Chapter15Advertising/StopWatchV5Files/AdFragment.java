package com.jblearning.stopwatchv5;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AdFragment extends Fragment {
  private AdView adView;

  @Override
  public View onCreateView( LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState ) {
    return inflater.inflate( R.layout.fragment_ad, container, false );
  }
  
  @Override
  public void onActivityCreated( Bundle bundle ) {
    super.onActivityCreated( bundle  );
    // retrieve the AdView
    adView = ( AdView ) getView( ).findViewById( R.id.ad_view );
    // build the ad request
    AdRequest.Builder adRequestBuilder = new AdRequest.Builder( );
    // Define target data for the ad request (optional)
    adRequestBuilder.setGender( AdRequest.GENDER_UNKNOWN );
    adRequestBuilder.addKeyword( "fitness" );
    adRequestBuilder.addKeyword ( "workout" );
    // request test (not live) ads for emulator
    adRequestBuilder.addTestDevice( AdRequest.DEVICE_ID_EMULATOR );
    AdRequest adRequest = adRequestBuilder.build( );
    // load the ad
    adView.loadAd( adRequest );
  }

  public void onPause( ) {
    if( adView != null )
      adView.pause( );
    super.onPause( );
  }

  public void onResume( ) {
    super.onResume( );
    if( adView != null )
      adView.resume( );
  }

  public void onDestroy( ) {
    if( adView != null )
      adView.destroy( );
    super.onDestroy( );
  }
}
