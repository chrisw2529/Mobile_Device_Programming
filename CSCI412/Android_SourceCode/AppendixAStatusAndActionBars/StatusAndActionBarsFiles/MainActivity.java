package com.jblearning.statusandactionbars;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {
  public static int STATUS_BAR_HEIGHT = 24; // in dp
  public static int ACTION_BAR_HEIGHT = 56; // in dp

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    Resources res = getResources( );
    DisplayMetrics metrics = res.getDisplayMetrics( );
    float pixelDensity = metrics.density;
    Log.w( "MainActivity", "pixel density = " + pixelDensity );

    TypedValue tv = new TypedValue( );
    int actionBarHeight = ( int ) ( pixelDensity * ACTION_BAR_HEIGHT );
    if( getTheme( ).resolveAttribute( android.R.attr.actionBarSize,
        tv, true ) ) {
      actionBarHeight = TypedValue.complexToDimensionPixelSize( tv.data,
          res.getDisplayMetrics( ) );
      Log.w( "MainActivity", "retrieved action bar height = "
          + actionBarHeight );
    }

    int statusBarHeight = ( int ) ( pixelDensity * STATUS_BAR_HEIGHT );
    int resourceId =
        res.getIdentifier( "status_bar_height", "dimen", "android" );
    Log.w( "MainActivity", "resource id for action bar height = "
        + resourceId );
    if( resourceId != 0 ) { // found resource for status bar height
      statusBarHeight = res.getDimensionPixelSize( resourceId );
      Log.w( "MainActivity", "retrieved status bar height = "
          + statusBarHeight );
    }
  }
}
