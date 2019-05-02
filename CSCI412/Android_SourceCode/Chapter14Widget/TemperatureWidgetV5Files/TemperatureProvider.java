package com.jblearning.temperaturewidgetv5;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.util.Date;

public class TemperatureProvider extends AppWidgetProvider {
  public static final char DEGREE = '\u00B0';
  public static final String STARTING_URL
      = "http://api.openweathermap.org/data/2.5/weather?q=";
  public static final String KEY_NAME = "appid";

  public static String city = "New York, NY";
  private String key = "YOUR_KEY_GOES_HERE";

  @Override
  public void onUpdate ( Context context,
    AppWidgetManager appWidgetManager, int [ ] appWidgetIds ) {
    super.onUpdate( context, appWidgetManager, appWidgetIds );

    // execute task to retrieve current temperature for city
    TemperatureTask task = new TemperatureTask( this, context,
                      appWidgetManager, appWidgetIds );
    task.execute( STARTING_URL, city, KEY_NAME, key );
  }

  public void updateWidget( int temp, Context context,
                            AppWidgetManager appWidgetManager, int [ ] appWidgetIds ) {
    Date dateToday = new Date( );
    String today = DateFormat.getDateTimeInstance( ).format( dateToday );
    String displayString = today + "\n" + city + "\n";
    displayString += new String( temp + "" + DEGREE + "F" );

    RemoteViews remoteViews = new RemoteViews( context.getPackageName( ),
        R.layout.widget_layout );

    remoteViews.setTextViewText( R.id.display, displayString );
    for( int widgetId : appWidgetIds ) {
      // Set up event handling when the user clicks on the widget
      Intent intent = new Intent( context, TemperatureProvider.class );
      intent.setAction( AppWidgetManager.ACTION_APPWIDGET_UPDATE );
      intent.putExtra( AppWidgetManager.EXTRA_APPWIDGET_IDS,
          appWidgetIds );

      PendingIntent pendingIntent = PendingIntent.getBroadcast( context,
          0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
      remoteViews.setOnClickPendingIntent( R.id.display, pendingIntent );

      appWidgetManager.updateAppWidget( widgetId, remoteViews );
    }
  }

  public void onReceive( Context context, Intent intent ) {
    super.onReceive( context, intent );
    AppWidgetManager appWidgetManager =
        AppWidgetManager.getInstance( context );
    int appWidgetId =
        intent.getIntExtra( AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID );

    if( appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID ) {
      int [ ] appWidgetIds = { appWidgetId };
      onUpdate( context, appWidgetManager,  appWidgetIds );
    }
  }
} 