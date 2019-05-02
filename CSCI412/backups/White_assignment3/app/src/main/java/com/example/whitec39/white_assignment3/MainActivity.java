package com.example.whitec39.white_assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbm = new DatabaseManager(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        //This Need to be updated when you go back to the main screen
        //try making an update button to test


    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("in resume");
        ArrayList<Friend> friends = new ArrayList<Friend>();
        friends = dbm.selectAll();
        ArrayList<String> friendsString = new ArrayList<String>();
        for(Friend friend: friends){
            friendsString.add(friend.getEmail());
        }
        System.out.println(friendsString.toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, friendsString);
        final AutoCompleteTextView autoTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTV);

        autoTextView.setAdapter(adapter);

    }

    public void searchFriend(View view){
        AutoCompleteTextView autoTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTV);
        System.out.println("email searching: " + autoTextView.getText().toString());
        Friend displayFriend = dbm.selectByEmail(autoTextView.getText().toString());
        TextView displayName = (TextView) findViewById(R.id.friendNameTV);

        if(displayFriend != null){
            String name = displayFriend.getFirstName() + " " + displayFriend.getLastName();
            displayName.setText(name);
        }
        else{
            displayName.setText("error: email not found.");
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_add:
                System.out.println("action add!!!");
                Log.w("MainActivity", "Add selected");
                Intent addIntent = new Intent(this, AddFriend.class);
                this.startActivity(addIntent);
                overridePendingTransition(R.anim.diag_trans,R.anim.diag_trans_out);

                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "Delete selected");
                Intent deleteIntent = new Intent(this, DeleteFriend.class);
                this.startActivity(deleteIntent);
                overridePendingTransition(R.anim.diag_trans,R.anim.diag_trans_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
