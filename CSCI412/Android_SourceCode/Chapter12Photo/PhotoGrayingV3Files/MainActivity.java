package com.jblearning.photograyingv3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  private static final int PHOTO_REQUEST = 1;
  private BitmapGrayer grayer;
  private Bitmap bitmap;
  private ImageView imageView;
  private SeekBar redBar, greenBar, blueBar;
  private TextView redTV, greenTV, blueTV;

  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );
    imageView = ( ImageView ) findViewById( R.id.picture );

    redBar = ( SeekBar ) findViewById( R.id.red_bar );
    greenBar = ( SeekBar ) findViewById( R.id.green_bar );
    blueBar = ( SeekBar ) findViewById( R.id.blue_bar );

    redTV = ( TextView ) findViewById( R.id.red_tv );
    greenTV = ( TextView ) findViewById( R.id.green_tv );
    blueTV = ( TextView ) findViewById( R.id.blue_tv );

    GrayChangeHandler gch = new GrayChangeHandler( );
    redBar.setOnSeekBarChangeListener( gch );
    greenBar.setOnSeekBarChangeListener( gch );
    blueBar.setOnSeekBarChangeListener( gch );

    PackageManager manager = this.getPackageManager( );
    if( manager.hasSystemFeature( PackageManager.FEATURE_CAMERA ) ) {
      Intent takePictureIntent
        = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
      startActivityForResult( takePictureIntent, PHOTO_REQUEST );
    } else {
      Toast.makeText( this,
        "Sorry - Your device does not have a camera",
        Toast.LENGTH_LONG ).show( );
    }
  }

  protected void onActivityResult( int requestCode,
                                   int resultCode, Intent data )  {
    super.onActivityResult( requestCode, resultCode, data );
    if( requestCode == PHOTO_REQUEST && resultCode == RESULT_OK ) {
      Bundle extras = data.getExtras( );
      bitmap = ( Bitmap ) extras.get( "data" );
      grayer = new BitmapGrayer( bitmap, 0.0f, 0.0f, 0.0f );
      imageView.setImageBitmap( bitmap );
    }
  }

  private class GrayChangeHandler
          implements SeekBar.OnSeekBarChangeListener {
    public void onProgressChanged( SeekBar seekBar,
                                   int progress, boolean fromUser ) {
      if( fromUser ) {
        if( seekBar == redBar ) {
          grayer.setRedCoeff( progress / 100.0f );
          redBar.setProgress( (int) ( 100 * grayer.getRedCoeff( ) ) );
          redTV.setText( ""
              + MathRounding.keepTwoDigits( grayer.getRedCoeff( ) ) );
        } else if( seekBar == greenBar ) {
          grayer.setGreenCoeff( progress / 100.0f );
          greenBar.setProgress( (int) ( 100 * grayer.getGreenCoeff( ) ) );
          greenTV.setText( ""
              + MathRounding.keepTwoDigits( grayer.getGreenCoeff( ) ) );
        } else if( seekBar == blueBar ) {
          grayer.setBlueCoeff( progress / 100.0f );
          blueBar.setProgress( (int) ( 100 * grayer.getBlueCoeff( ) ) );
          blueTV.setText( ""
              + MathRounding.keepTwoDigits( grayer.getBlueCoeff( ) ) );
        }
        bitmap = grayer.grayScale( );
        imageView.setImageBitmap( bitmap );
      }
    }

    public void onStartTrackingTouch( SeekBar seekBar ) {
    }

    public void onStopTrackingTouch( SeekBar seekBar ) {
    }
  }
}