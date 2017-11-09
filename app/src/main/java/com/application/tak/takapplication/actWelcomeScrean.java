package com.application.tak.takapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.application.tak.takapplication.data_model.User;
import com.application.tak.takapplication.local_db.LocalDB;

import java.util.List;

public class actWelcomeScrean extends AppCompatActivity {

    public EditText welcome;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_welcome_screan);


        ///////////////////////////////////////Jezeli juz jest Zalogowany i dał wyloguj to otworz od razu
        /////////////////////////////spr local db czy sa dane -> tak
        ////                                                    - spr, ktróry user ma aktywną sesję i
        ///                                                     - weź role i otwórz act..Task
        ////////////////////////////
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }


        LocalDB localDB = new LocalDB(this.getApplicationContext());

        final List<User> users = localDB.GetAllUsers();

        User u = getLoggedUser(users);

        if(u != null)
        {
            if(u.get_RoleId() == 1)
            {
// open StuswntTask
               // Intent i = new Intent(ctx,actStudentTask.class);
           //     startActivity(i);
            }
            else if(u.get_RoleId() == 2)
            {
             //   Intent i = new Intent(ctx,actClientTask.class);
             //   startActivity(i);
               // open clientTask
            }
        }
        else
        {
//            Intent i = new Intent(ctx,actFirstUsage.class);
  //          startActivity(i);
            // open normal Login :)
        }
    }

    private boolean checkUsersRights(List<User> users, Integer roleId)
    {
        // Integer role_id = 0;
        Boolean canLogin = true;
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
