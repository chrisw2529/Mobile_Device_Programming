package com.jblearning.mortgagev1lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
  public static final String MA = "MainActivity";

  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    Log.w( MA, "Inside MainActivity:onCreate\n" );
    setContentView( R.layout.activity_main );
  }

  public void modifyData( View v ) {
    Intent myIntent = new Intent( this, DataActivity.class );
    this.startActivity( myIntent );
  }

  protected void onStart( ) {
    super.onStart( );
    Log.w( MA, "Inside MainActivity:onStart\n" );
  }

  protected void onRestart( ) {
    super.onRestart( );
    Log.w( MA, "Inside MainActivity:onReStart\n" );
  }

  protected void onResume( ) {
    super.onResume( );
    Log.w( MA, "Inside MainActivity:onResume\n" );
  }

  protected void onPause( ) {
    super.onPause( );
    Log.w( MA, "Inside MainActivity:onPause\n" );
  }

  protected void onStop( ) {
    super.onStop( );
    Log.w( MA, "Inside MainActivity:onStop\n" );
  }

  protected void onDestroy( ) {
    super.onDestroy( );
    Log.w( MA, "Inside MainActivity:onDestroy\n" );
  }
}
