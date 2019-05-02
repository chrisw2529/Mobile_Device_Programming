package com.jblearning.encryptionv0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
  public static final String MA = "MainActivity";
  private AESEncryption aes;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    aes = new AESEncryption( );

    // testing encryption and decryption
    String original = "Encryption is fun";
    Log.w( MA, "original: " + original );
    String encrypted =
      aes.crypt( Cipher.ENCRYPT_MODE, original, aes.getKey( ) );
    Log.w( MA, "encrypted: " + encrypted );
    String decrypted =
      aes.crypt( Cipher.DECRYPT_MODE, encrypted, aes.getKey( ) );
    Log.w( MA, "decrypted: " + decrypted );

    // testing key distribution
    byte [ ] keyBytes = aes.getKeyBytes( );
    // distribute keyBytes to a user
    String keyString = Arrays.toString( keyBytes );
    Log.w( MA, "original key: " + keyBytes + ": " + keyString );

    // having received keyBytes, reconstruct the key
    SecretKey reconstructedKey = new SecretKeySpec( keyBytes, "AES" );
    // check that the reconstructed key is the same as the original key
    byte [ ] bytesFromReconstructedKey = reconstructedKey.getEncoded( );
    String stringFromReconstructedKey =
      Arrays.toString( bytesFromReconstructedKey );
    Log.w( MA, "reconstructed key: "
         + bytesFromReconstructedKey + ": " + stringFromReconstructedKey );
  }
}