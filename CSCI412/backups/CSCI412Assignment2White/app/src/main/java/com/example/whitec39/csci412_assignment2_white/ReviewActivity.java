package com.example.whitec39.csci412_assignment2_white;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import static com.example.whitec39.csci412_assignment2_white.R.layout.review_page;

public class ReviewActivity extends AppCompatActivity{
    Bundle bundle;
    private RestDataHolder rdh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(review_page);
        bundle = getIntent().getExtras();
        rdh = RestDataHolder.getInstance();
        ImageView revResImage = (ImageView) findViewById(R.id.revImage);
        final EditText ratingInput = (EditText) findViewById(R.id.ratingInput);
        ratingInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                float rating = Float.parseFloat(ratingInput.getText().toString());
                if(rating >= 1 && rating <= 5){
                    switch (bundle.getInt("extraID")){
                        case 1:
                            rdh.getRestData(1).setRating(rating);
                            break;
                        case 2:
                            rdh.getRestData(2).setRating(rating);
                            break;
                        case 3:
                            rdh.getRestData(3).setRating(rating);
                            break;
                    }
                    MainActivity.ratingBar.setRating(rating);
                }


            }
        });

        final EditText reviewInput = (EditText) findViewById(R.id.reviewEditText);
        ratingInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String revText = reviewInput.getText().toString();
                System.out.println("revText: " + revText);
                switch (bundle.getInt("extraID")){
                    case 1:
                        rdh.getRestData(1).setReviewText(revText);
                        break;
                    case 2:
                        rdh.getRestData(2).setReviewText(revText);
                        break;
                    case 3:
                        rdh.getRestData(3).setReviewText(revText);
                        break;
                }
                MainActivity.reviewText.setText(revText);
            }
        });


        switch (bundle.getInt("extraID")){
            case 1:
                revResImage.setImageResource(R.drawable.wfp);
                break;
            case 2:
                revResImage.setImageResource(R.drawable.applebees);
                break;
            case 3:
                revResImage.setImageResource(R.drawable.olive_garden);
                break;
        }


    }


    public void goBack(View v){
        this.finish();
        overridePendingTransition(R.anim.fade_animation_out,R.anim.fade_animation);

    }
}
