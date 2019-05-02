package com.jblearning.temperaturewidgetv3;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.util.Date;

public class TemperatureProvider extends AppWidgetProvider {
  @Override
  public void onUpdate ( Context context,
    AppWidgetManager appWidgetManager, int [ ] appWidgetIds ) {
    super.onUpdate( context, appWidgetManager, appWidgetIds );
    Date dateToday = new Date( );
    String today = DateFormat.getDateTimeInstance( ).format( dateToday );
    Resources resources = context.getResources( );
    String displayString
        = today + "\n" + resources.getString( R.string.city_and_temp );

    RemoteViews remoteViews = new RemoteViews(
        context.getPackageName( ), R.layout.widget_layout );
    remoteViews.setTextViewText( R.id.display, displayString );
    for ( int widgetId : appWidgetIds ) {
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
} 