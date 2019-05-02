package com.jblearning.photograyingv1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  private static final int PHOTO_REQUEST = 1;
  private BitmapGrayer grayer;
  private Bitmap bitmap;
  private ImageView imageView;

  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );
    imageView = ( ImageView ) findViewById( R.id.picture );

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
                                   int resultCode, Intent data ) {
    super.onActivityResult( requestCode, resultCode, data );
    if( requestCode == PHOTO_REQUEST && resultCode == RESULT_OK ) {
      Bundle extras = data.getExtras( );
      bitmap = ( Bitmap ) extras.get( "data" );
      grayer = new BitmapGrayer( bitmap, .34f, .33f, .33f );
      bitmap = grayer.grayScale( );
      imageView.setImageBitmap( bitmap );
    }
  }
}