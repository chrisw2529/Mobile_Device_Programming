package com.example.whitec39.white_assignment3;

/**
 * Created by whitec39 on 11/1/18.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import java.util.ArrayList;

public class DeleteFriend extends AppCompatActivity {
    private DatabaseManager dbm;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbm = new DatabaseManager( this );
        updateView( );
    }

    // Build a View dynamically with all the candies
    public void updateView( ) {
        ArrayList<Friend> friends = dbm.selectAll( );
        RelativeLayout layout = new RelativeLayout( this );
        ScrollView scrollView = new ScrollView( this );
        RadioGroup group = new RadioGroup( this );
        for ( Friend friend : friends ) {
            RadioButton rb = new RadioButton( this );
            rb.setId( friend.getId( ) );
            String text = friend.getFirstName()+ " " + friend.getLastName();
            rb.setText(text);
            group.addView( rb );
        }
        // set up event handling
        RadioButtonHandler rbh = new RadioButtonHandler( );
        group.setOnCheckedChangeListener(rbh);

        // create a back button
        Button backButton = new Button( this );
        backButton.setText( R.string.button_back );

        backButton.setOnClickListener( new View.OnClickListener( ) {
            public void onClick(View v) {
                DeleteFriend.this.finish();
                overridePendingTransition(R.anim.fade_animation_out,R.anim.fade_animation);

            }
        });

        scrollView.addView(group);
        layout.addView( scrollView );

        // add back button at bottom
        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT );
        params.addRule( RelativeLayout.ALIGN_PARENT_BOTTOM );
        params.addRule( RelativeLayout.CENTER_HORIZONTAL );
        params.setMargins( 0, 0, 0, 50 );
        layout.addView( backButton, params );

        setContentView( layout );
    }

    private class RadioButtonHandler
            implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged( RadioGroup group, int checkedId ) {
            // delete friend from database
            dbm.deleteById( checkedId );
            Toast.makeText( DeleteFriend.this, "Friend deleted",
                    Toast.LENGTH_SHORT ).show( );

            // update screen
            updateView( );
        }
    }
}
