package com.jblearning.dynamicfontsizing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    // retrieve TextView
    TextView tv = (TextView) findViewById( R.id.tv );
    // set width and text of TextView
    tv.setWidth( 600 );
    tv.setText( "PROGRAMMING" );
    // set optimal font size
    DynamicSizing.setFontSizeToFitInView( tv );
  }
}
