package com.application.tak.takapplication;

import android.content.Context;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.application.tak.takapplication.data_model.User;
import com.application.tak.takapplication.local_db.LocalDB;

import java.util.List;
import java.util.stream.Stream;

public class actFirstUsage extends AppCompatActivity {


    Button btnClient;
    Button btnStudent;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_first_usage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ctx = this.getApplicationContext();
        btnClient = (Button)findViewById(R.id.btnClient);
        btnStudent = (Button) findViewById(R.id.btnStudent);

        LocalDB localDB = new LocalDB(this.getApplicationContext());

        final List<User> users = localDB.GetAllUsera();

        User u = getLoggedUser(users);

        if(u != null)
        {
            if(u.get_RoleId() == 1)
            {

            }
            else if(u.get_RoleId() == 2)
            {
                Intent i = new Intent(ctx, actClientTaskNotSelectedListActivity.class);
                i.putExtra("user_id",u.get_Id());
                startActivity(i);
            }

        }

        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(checkUsersRights(users,2))
                {
                    if(users.size() > 0)
                    {
                        //okno logowania i rejestracji

                        Intent i = new Intent(ctx,actClientLogin.class);
                        startActivity(i);

                    }
                    else
                    {
                        //konto uzytkownika z innym role_id,
                        Toast t =Toast.makeText(ctx,"Na urządzeniu istnieją już użytkownicy z innymi uprawnieniami. Nie można utworzyć nowego konta typu KLIENT",Toast.LENGTH_LONG);
                        t.show();
                    }

                }
            }});

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(checkUsersRights(users,1))
                {
                    if(users.size() > 0)
                    {
                        //okno logowania i rejestracji

                        Intent i = new Intent(ctx,actStudentLogin.class);
                        startActivity(i);

                }
                else
                {
                    //konto uzytkownika z innym role_id,
                    Toast t =Toast.makeText(ctx,"Na urządzeniu istnieją już użytkownicy z innymi uprawnieniami. Nie można utworzyć nowego konta typu STUDENT",Toast.LENGTH_LONG);
                    t.show();
                }

            }}});

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private boolean checkUsersRights(List<User> users, Integer roleId)
    {
        Integer role_id = 0;
        for (User u:users)
        {
            if(u.get_RoleId() == roleId)
            {
                return true;
            }
            else{
                return false;
            }

        }
        return true;
    }

    private User getLoggedUser(List<User> users)
    {
        User loggedInUser = null;
        Integer i = 0;

        if(users == null)
            return null;

        for (User u:users)
        {
            if(u.get_IsLoggedIn() != null && u.get_IsLoggedIn() == 1)
            {
                loggedInUser = u;
                return loggedInUser;
            }
        }
        return loggedInUser;
    }

}
