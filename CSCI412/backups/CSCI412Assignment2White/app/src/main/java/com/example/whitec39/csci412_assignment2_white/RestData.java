package com.example.whitec39.csci412_assignment2_white;

/**
 * Created by whitec39 on 10/18/18.
 */

public class RestData {
    private float rating;
    private String reviewText;

    public RestData(float rat, String rev){
        rating = rat;
        reviewText = rev;
    }

    public float getRating(){
        return rating;
    }
    public String getRevText(){
        return reviewText;
    }
    public void setRating(float r){
        rating = r;
    }
    public void setReviewText(String t){
        reviewText = t;
    }

}
