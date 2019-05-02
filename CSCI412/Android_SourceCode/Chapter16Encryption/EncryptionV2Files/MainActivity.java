package com.jblearning.encryptionv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import javax.crypto.Cipher;

public class MainActivity extends AppCompatActivity {
  public static final String MA = "MainActivity";
  private AESEncryption aes;
  private RSAEncryption rsa;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    aes = new AESEncryption( );
    rsa = new RSAEncryption( );
    setContentView( R.layout.activity_main );

    // encrypt with public key, decrypt with private key
    String original1 = "Hello";
    Log.w( MA, "original1: " + original1 );
    String encrypted1 =
        rsa.crypt( Cipher.ENCRYPT_MODE, original1, rsa.getPublicKey( ) );
    Log.w( MA, "encrypted1: " + encrypted1 );
    String decrypted1 =
        rsa.crypt( Cipher.DECRYPT_MODE, encrypted1, rsa.getPrivateKey( ) );
    Log.w( MA, "decrypted1: " + decrypted1 );

    // encrypt with private key, decrypt with public key
    String original2 = "Hello";
    Log.w( MA, "original2: " + original2 );
    String encrypted2 =
        rsa.crypt( Cipher.ENCRYPT_MODE, original2, rsa.getPrivateKey( ) );
    Log.w( MA, "encrypted2: " + encrypted2 );
    String decrypted2 =
        rsa.crypt( Cipher.DECRYPT_MODE, encrypted2, rsa.getPublicKey( ) );
    Log.w( MA, "decrypted2: " + decrypted2 );
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