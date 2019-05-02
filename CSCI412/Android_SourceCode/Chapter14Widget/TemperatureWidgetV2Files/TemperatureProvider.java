package com.jblearning.temperaturewidgetv2;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
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
    for ( int widgetId : appWidgetIds )
      appWidgetManager.updateAppWidget( widgetId, remoteViews );
  }
} 