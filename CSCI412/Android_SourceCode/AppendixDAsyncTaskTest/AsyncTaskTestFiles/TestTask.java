package com.jblearning.asynctasktest;

import android.os.AsyncTask;
import android.util.Log;

public class TestTask extends AsyncTask<Integer, Void, String>  {
  private MainActivity activity;

  public TestTask( MainActivity fromActivity ) {
    Log.w( "MainActivity", "Inside TestTask constructor" );
    activity = fromActivity;
  }

  protected String doInBackground( Integer... numbers ) {
    Log.w( "MainActivity", "Inside doInBackground" );
    return "Changed using AsyncTask";
  }

  protected void onPostExecute( String message ) {
    Log.w( "MainActivity", "Inside onPostExecute" );
    activity.updateView( message );
  }
}
