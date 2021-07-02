package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {
    public String body;
    public String createdAt;
    public User user;
    public boolean hasMedia;
    public ArrayList<String> embeddedImages;
    public String firstEmbeddedImage;

    // empty constructor needed by the Parceler library
    public Tweet(){}

    public static Tweet fromJson (JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("full_text");
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

        try {
            JSONObject entities = jsonObject.getJSONObject("entities");
            if (entities.has("media")) {
                JSONArray media = entities.getJSONArray("media");
                tweet.hasMedia = true;
                tweet.embeddedImages = new ArrayList<>();
                for (int i=0; i < media.length(); i++) {
                    tweet.embeddedImages.add(media.getJSONObject(i).getString("media_url_https"));
                }
                tweet.firstEmbeddedImage = tweet.embeddedImages.get(0);
            } else {
                tweet.hasMedia = false;
                tweet.embeddedImages = new ArrayList<>();
            }
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
