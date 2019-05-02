package com.jblearning.temperaturewidgetv5;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class TemperatureWidgetConfigure extends Activity {
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState ); 
    setResult( RESULT_CANCELED );
    setContentView( R.layout.widget_config ); 
  }
  
  public void configure( View view ) {
    // Get user input
    EditText cityText = ( EditText ) findViewById( R.id.city_input );
    String cityInput = cityText.getText( ).toString( );	
    
    // Update city variable of TemperatureProvider
    TemperatureProvider.city = cityInput;
    
    // Get widget id
    Intent intent = getIntent( );
    Bundle extras = intent.getExtras( );
    int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    if ( extras != null )
      appWidgetId =
          extras.getInt( AppWidgetManager.EXTRA_APPWIDGET_ID,
                         AppWidgetManager.INVALID_APPWIDGET_ID );
    
    if( appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID ) {
      // Update the widget
      Context context = TemperatureWidgetConfigure.this;
      AppWidgetManager appWidgetManager =
          AppWidgetManager.getInstance( context );
      RemoteViews views = new RemoteViews( context.getPackageName( ), 
                                           R.layout.widget_layout );
      appWidgetManager.updateAppWidget( appWidgetId, views );
    
      // Create the return intent
      Intent resultIntent = new Intent( );
      
      resultIntent.putExtra( AppWidgetManager.EXTRA_APPWIDGET_ID,
                             appWidgetId );
      setResult( RESULT_OK, resultIntent );
    }
    
    // exit this activity
    finish( );
  }
}
