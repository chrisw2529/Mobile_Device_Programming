package com.jblearning.encryptionv1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import javax.crypto.Cipher;

public class MainActivity extends AppCompatActivity {
  public static final String MA = "MainActivity";
  private AESEncryption aes;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    aes = new AESEncryption( );
    setContentView( R.layout.activity_main );
  }

  public void encryptAndDecryptAES( View v ) {
    EditText et = ( EditText ) findViewById( R.id.string_original );
    String original = et.getText( ).toString( );
    String encrypted =
        aes.crypt( Cipher.ENCRYPT_MODE, original, aes.getKey( ) );
    TextView tvEncrypted =
        ( TextView ) findViewById( R.id.string_encrypted );
    tvEncrypted.setText( encrypted );
    TextView tvDecrypted =
        ( TextView ) findViewById( R.id.string_decrypted );
    String decrypted =
        aes.crypt( Cipher.DECRYPT_MODE, encrypted, aes.getKey( ) );
    tvDecrypted.setText( decrypted );
  }
}