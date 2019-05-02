package com.jblearning.hangmanv0lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
  public static String MA = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.w(MA, "Inside MainActivity:onCreate");
    setContentView(R.layout.activity_main);
  }

  public void onStart( ) {
    super.onStart();
    Log.w(MA, "Inside MainActivity:onStart");
  }

  public void onRestart( ) {
    super.onRestart();
    Log.w(MA, "Inside MainActivity:onRestart");
  }

  public void onResume( ) {
    super.onResume();
    Log.w( MA, "Inside MainActivity:onResume" );
  }

  public void onPause( ) {
    super.onPause();
    Log.w( MA, "Inside MainActivity:onPause" );
  }

  public void onStop( ) {
    super.onStop();
    Log.w( MA, "Inside MainActivity:onStop" );
  }

  public void onDestroy( ) {
    super.onDestroy();
    Log.w( MA, "Inside MainActivity:onDestroy" );
  }

}
