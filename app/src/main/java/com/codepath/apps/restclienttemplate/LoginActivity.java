package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.SampleModel;
import com.codepath.apps.restclienttemplate.models.SampleModelDao;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.oauth.OAuthLoginActionBarActivity;

import org.parceler.Parcels;

import okhttp3.Headers;

public class LoginActivity extends OAuthLoginActionBarActivity<TwitterClient> {

    SampleModelDao sampleModelDao;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SampleModel sampleModel = new SampleModel();
        sampleModel.setName("CodePath");

        sampleModelDao = ((TwitterApplication) getApplicationContext()).getMyDatabase().sampleModelDao();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                sampleModelDao.insertModel(sampleModel);
            }
        });

        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("he", "wowo");
                loginToRest(view);
            }
        });
    }


    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    // OAuth authenticated successfully, launch primary authenticated activity
    // i.e Display application "homepage"
    @Override
    public void onLoginSuccess() {
        Log.i("Login: ", "login");
        Intent i = new Intent(this, TimelineActivity.class);
        startActivity(i);
    }

    // OAuth authentication flow failed, handle the error
    // i.e Display an error dialog or toast
    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
    }

    // Click handler method for the button used to start OAuth flow
    // Uses the client to initiate OAuth authorization
    // This should be tied to a button used to login
    public void loginToRest(View view) {
        getClient().connect();
    }
}
