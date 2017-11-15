package com.application.tak.takapplication;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tak.takapplication.adapters.RVAdapterTaskNotSelected;
import com.application.tak.takapplication.data_access.CheckPassword;
import com.application.tak.takapplication.data_access.Config;
import com.application.tak.takapplication.data_access.GetUserById;
import com.application.tak.takapplication.data_model.User;
import com.application.tak.takapplication.interfaces.OnDBRequestFinished;
import com.application.tak.takapplication.local_db.LocalDB;
import com.application.tak.takapplication.services.LoginStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class actFirstUsage extends AppCompatActivity {


    Button btnClient;
    Button btnStudent;
    Context ctx;

    private EditText email;
    private EditText password;
    private Button btnLogin;
    private Button btnRegister;
    private Switch switchLoginModel;
    private ImageView imgclient;
    private ImageView imgstudent;
    private RadioButton rbFace;
    private RadioButton rbGoogle;
    private TextView mode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_first_usage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ctx = this.getApplicationContext();

        LoginStatus lgStatus = new LoginStatus(ctx);
        /*LocalDB loc = new LocalDB(ctx);
        loc.DeleteAllUsers();
        User u = new User();
        u.set_Id(20);
        u.set_Username("test.test");
        u.set_IsLoggedIn(1);
        loc.AddUser(u,1);
        ArrayList<User> all = loc.GetAllUsers();*/

        User u = lgStatus.IsLoggedIn();

        if(u!= null)
        {   GetUserById getUsr;
            if(u.get_RoleId() == 2)
            {
                getUsr = new GetUserById("Client",u.get_Id(),ctx);
            }
            else if(u.get_RoleId() == 1)
            {
                getUsr = new GetUserById("Student",u.get_Id(),ctx);
            }
            else{
                getUsr = new GetUserById("",-1,ctx);
            }

            if(getUsr != null)
            {
                getUsr.setDBRequestFinishedListener(new OnDBRequestFinished() {
                    @Override
                    public void onDBRequestFinished() {
                        if(getUsr._client!= null)
                        {
                            Config.LoggedInClient = getUsr._client;
                            Intent i = new Intent(ctx,actClientTask.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });

            }
        }
        btnClient = (Button)findViewById(R.id.btnClient);
        btnStudent = (Button) findViewById(R.id.btnStudent);
        email = (EditText) findViewById(R.id.tbEmail) ;
        password = (EditText) findViewById(R.id.tbPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        switchLoginModel = (Switch) findViewById(R.id.switchLoginType);
        imgclient = (ImageView) findViewById(R.id.imgClientMode);
        imgclient.setVisibility(View.INVISIBLE);
        imgstudent = (ImageView) findViewById(R.id.imgStudentMode);
        mode = (TextView) findViewById(R.id.lbMode);

        rbGoogle = (RadioButton) findViewById(R.id.rbGoogle);
        rbFace = (RadioButton) findViewById(R.id.rbFacebook);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mode.getText() == "Klient")
                {
                    CheckClientAuthentication(email.getText().toString(),password.getText().toString());
                }
                else if (mode.getText() == "Uczeń")
                {
                            Intent i = new Intent(ctx,actStudentLogin.class);
                            startActivity(i);
}
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode.getText() == "Klient"){

                    Intent i = new Intent(ctx,actClientLogin.class);
                    startActivity(i);
                    //fisnish
                }
                else if (mode.getText() == "Uczeń")
                {
                    Intent i = new Intent(ctx,actStudentLogin.class);
                    startActivity(i);
                    //finish
                }

            }
        });

switchLoginModel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        Animation slideDown = AnimationUtils.loadAnimation(ctx, R.anim.anim_slide_out_right);

      if(!isChecked) // klient
      {
        //  imgstudent.setAnimation(slideDown);
          imgclient.setVisibility(View.VISIBLE);
          imgstudent.setVisibility(View.INVISIBLE);
          rbFace.setVisibility(View.INVISIBLE);
          rbGoogle.setVisibility(View.INVISIBLE);
          email.setVisibility(View.VISIBLE);
          password.setVisibility(View.VISIBLE);
          mode.setText("Klient");


      }
      else //uczen
      {
         //imgclient.setAnimation(slideDown);
          imgstudent.setVisibility(View.VISIBLE);
          imgclient.setVisibility(View.INVISIBLE);
          rbFace.setVisibility(View.VISIBLE);
          rbGoogle.setVisibility(View.VISIBLE);
          email.setVisibility(View.INVISIBLE);
          password.setVisibility(View.INVISIBLE);

          mode.setText("Uczeń");

      }
    }
});

rbFace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked)
        {
            rbGoogle.setChecked(false);
            rbGoogle.setAlpha(0.5f);
        }
        else
        {
            rbFace.setAlpha(1f);
        }
    }
});

        rbFace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    rbFace.setChecked(false);
                    rbFace.setAlpha(0.5f);
                }
                else
                {
                    rbGoogle.setAlpha(1f);
                }
            }
        });
    }



    private void CheckClientAuthentication(String username, String password)
    {
        User u = new User();
        u.set_Username(username);
        u.set_Password(password);
        CheckPassword passCheck= new CheckPassword(ctx);
        passCheck.showProgressDialog = false;
        passCheck.setDBRequestFinishedListener(new OnDBRequestFinished() {
            @Override
            public void onDBRequestFinished() {
                if(passCheck._client != null)
                {
                        Config.LoggedInClient = passCheck._client;
                        LocalDB localdb = new LocalDB(ctx);
                        localdb.LogOutAllUsers();
                        localdb.UpdateUser(Config.LoggedInClient.get_Id(),1);
                        Intent i = new Intent(ctx,actClientTask.class);
                        startActivity(i);
                        finish();

                }
                if(passCheck.dbResult != null)
                {
                    Toast.makeText(ctx,passCheck.dbResult.getMSG(), Toast.LENGTH_LONG).show();
                }
            }
        });
        passCheck.CheckPassword(u,2);

    }

}
