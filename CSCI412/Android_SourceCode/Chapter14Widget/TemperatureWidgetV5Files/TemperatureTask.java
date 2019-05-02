package com.jblearning.temperaturewidgetv5;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.AsyncTask;

public class TemperatureTask extends AsyncTask<String, Void, String> {
  private TemperatureProvider provider;
  private Context context; 
  private AppWidgetManager appWidgetManager;
  private int [ ] appWidgetIds;
  
  public TemperatureTask( TemperatureProvider fromTemperatureProvider,
                          Context fromContext, 
                          AppWidgetManager fromAppWidgetManager, 
                          int [ ] fromAppWidgetIds) {
    provider = fromTemperatureProvider;
    context = fromContext;
    appWidgetManager = fromAppWidgetManager;
    appWidgetIds = fromAppWidgetIds;
  }

  protected String doInBackground( String... urlParts ) {
    String baseUrl = "", cityString = "", keyName = "", key = "";
    if( urlParts != null ) {
      baseUrl = urlParts[0];
      cityString = urlParts[1];
      keyName = urlParts[2];
      key = urlParts[3];
    }
    
    RemoteDataReader rdr =
      new RemoteDataReader( baseUrl, cityString, keyName, key );
    String json = rdr.getData( );
    return json;
  }

  protected void onPostExecute ( String returnedJSON ) {
    TemperatureParser parser = new TemperatureParser( returnedJSON );
    provider.updateWidget( parser.getTemperatureF( ), context, 
                           appWidgetManager, appWidgetIds );
  }
}
      
