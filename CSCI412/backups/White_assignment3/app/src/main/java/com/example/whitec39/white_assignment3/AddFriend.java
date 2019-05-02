package com.example.whitec39.white_assignment3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by whitec39 on 10/31/18.
 */

public class AddFriend extends AppCompatActivity{
    private DatabaseManager dbm;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbm = new DatabaseManager(this);
        setContentView(R.layout.add_friend_layout);
    }

    public void addFriend(View v) {
// Retrieve name and price
        EditText firstNameEditText = ( EditText) findViewById( R.id.input_first_name );
        EditText lastNameEditText = ( EditText) findViewById( R.id.input_last_name );
        EditText emailEditText = ( EditText) findViewById( R.id.input_email );

        String firstName = firstNameEditText.getText( ).toString( );
        String lastName = lastNameEditText.getText( ).toString( );
        String email = emailEditText.getText( ).toString( );


        // insert new friend in database
        try {
            Friend friend = new Friend( 0, firstName, lastName, email);
            dbm.insert( friend );
            Toast.makeText( this, "Friend added", Toast.LENGTH_SHORT ).show( );
        } catch( NumberFormatException nfe ) {
            Toast.makeText( this, "Friend error", Toast.LENGTH_LONG ).show( );
        }


        // clear data
        firstNameEditText.setText( "" );
        lastNameEditText.setText( "" );
        emailEditText.setText( "" );

    }

    public void goBack(View v) {
        this.finish();
        overridePendingTransition(R.anim.fade_animation_out,R.anim.fade_animation);

    }

}
