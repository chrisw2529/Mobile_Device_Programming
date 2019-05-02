package com.example.whitec39.csci412_assignment1_white;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.graphics.Color.*;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button nameBtn = (Button) findViewById(R.id.nameBtn);
        final TextView nameTextView = (TextView) findViewById( R.id.nameTxt);
        final Button bioBtn = (Button) findViewById(R.id.bioBtn);
        final TextView bioTextView = (TextView) findViewById( R.id.bioTxt);
        final Button picBtn = (Button) findViewById(R.id.picBtn);
        final ImageView imageView = (ImageView) findViewById( R.id.myPic);


        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setVisibility(INVISIBLE);
                bioTextView.setVisibility(INVISIBLE);
                nameTextView.setVisibility(VISIBLE);
                nameBtn.setBackgroundColor(Color.YELLOW);
                bioBtn.setBackgroundColor(Color.BLUE);
                picBtn.setBackgroundColor(Color.RED);



            }
        });
        bioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setVisibility(INVISIBLE);
                bioTextView.setVisibility(VISIBLE);
                nameTextView.setVisibility(INVISIBLE);
                nameBtn.setBackgroundColor(Color.GREEN);
                bioBtn.setBackgroundColor(Color.YELLOW);
                picBtn.setBackgroundColor(Color.RED);

            }
        });
        picBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setVisibility(VISIBLE);
                bioTextView.setVisibility(INVISIBLE);
                nameTextView.setVisibility(INVISIBLE);
                nameBtn.setBackgroundColor(Color.GREEN);
                bioBtn.setBackgroundColor(Color.BLUE);
                picBtn.setBackgroundColor(Color.YELLOW);



            }
        });

    }

}
