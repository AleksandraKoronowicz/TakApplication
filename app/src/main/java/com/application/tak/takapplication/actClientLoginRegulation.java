package com.application.tak.takapplication;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.application.tak.takapplication.data_access.Config;
import com.application.tak.takapplication.services.LoginStatus;

public class actClientLoginRegulation extends AppCompatActivity {

    Context _ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_client_login_regulation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        _ctx = this.getApplicationContext();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                LoginStatus loginStatus = new LoginStatus(_ctx);
                loginStatus.NewClient(Config.LoggedInClient,Config.LoggedInClient.get_Adress());
            }
        });
    }
}

