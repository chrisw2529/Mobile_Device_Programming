package com.example.whitec39.csci412_assignment2_white;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public int radID = 0;
    public static RatingBar ratingBar;
    public static TextView reviewText;
    private RestDataHolder rdh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
//        SharedPreferences.Editor editor = pref.edit();
        rdh = RestDataHolder.getInstance();
        ratingBar = (RatingBar) findViewById(R.id.ratingStars);
    }



    public void onModifyButtonClicked(View view){
        Intent myIntent = new Intent(MainActivity.this, ReviewActivity.class);
        myIntent.putExtra("extraID", radID);
        if(radID == 1 || radID == 2 ||radID == 3){
            startActivity(myIntent);
            overridePendingTransition(R.anim.diag_trans,R.anim.diag_trans_out);
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        ImageView restImage = (ImageView) findViewById(R.id.restImage);

        reviewText = (TextView) findViewById(R.id.fullReviewText);

        ratingBar.setStepSize((float) .1);



        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.WFP:
                if (checked)
                    radID = 1;
                    restImage.setImageResource(R.drawable.wfp);
                    restImage.setVisibility(View.VISIBLE);
                    ratingBar.setRating(rdh.getRestData(1).getRating());
                    ratingBar.setVisibility(View.VISIBLE);
                    reviewText.setText(rdh.getRestData(1).getRevText());
                    reviewText.setVisibility(View.VISIBLE);

                    break;
            case R.id.AB:
                if (checked)
                    radID = 2;
                    restImage.setImageResource(R.drawable.applebees);
                    restImage.setVisibility(View.VISIBLE);
                    ratingBar.setRating(rdh.getRestData(2).getRating());
                    ratingBar.setVisibility(View.VISIBLE);
                    reviewText.setText(rdh.getRestData(2).getRevText());
                    reviewText.setVisibility(View.VISIBLE);

                break;
            case R.id.OG:
                if (checked)
                    radID = 3;
                    restImage.setImageResource(R.drawable.olive_garden);
                    restImage.setVisibility(View.VISIBLE);
                    ratingBar.setRating(rdh.getRestData(3).getRating());
                    ratingBar.setVisibility(View.VISIBLE);
                    reviewText.setText(rdh.getRestData(3).getRevText());
                    reviewText.setVisibility(View.VISIBLE);

                break;
        }
    }
}

