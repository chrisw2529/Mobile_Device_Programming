package com.jblearning.temperaturewidgetv0;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class TemperatureProvider extends AppWidgetProvider {
  @Override
  public void onUpdate ( Context context,
    AppWidgetManager appWidgetManager, int [ ] appWidgetIds ) {
    super.onUpdate( context, appWidgetManager, appWidgetIds );
    RemoteViews remoteViews = new RemoteViews(
        context.getPackageName( ), R.layout.widget_layout );
    for ( int widgetId : appWidgetIds )
      appWidgetManager.updateAppWidget( widgetId, remoteViews );
  }
} 