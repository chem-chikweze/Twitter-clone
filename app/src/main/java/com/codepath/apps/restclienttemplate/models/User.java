package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {
    public String name;
    public String screenName;
    public String profileImageUrl;

    // empty constructor needed by the Parceler library
    public User(){}

    public static User fromJson(JSONObject jsonObject) {
        User user = new User();
        try {
            user.name = jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            user.screenName = jsonObject.getString("screen_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            user.profileImageUrl = jsonObject.getString("profile_image_url_https");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
