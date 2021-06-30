package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
    public String body;
    public String createdAt;
    public User user;

    public static Tweet fromJson (JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            tweet.createdAt = jsonObject.getString("created_at");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweet;
    }
    
    public static List<Tweet> fromJsonArray (JSONArray jsonArray){
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                tweets.add(fromJson(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return tweets;
    }
}
