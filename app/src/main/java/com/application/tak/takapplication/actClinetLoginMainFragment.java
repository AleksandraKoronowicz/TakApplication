package com.application.tak.takapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.tak.takapplication.data_access.CheckUsernameExists;
import com.application.tak.takapplication.data_access.Config;
import com.application.tak.takapplication.data_access.CreateClient;
import com.application.tak.takapplication.data_model.Adress;
import com.application.tak.takapplication.data_model.User;
import com.application.tak.takapplication.interfaces.OnDBRequestFinished;
import com.application.tak.takapplication.local_db.LocalDB;
import com.application.tak.takapplication.services.LoginStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aleksandra on 04.05.2017.
 */
public class actClinetLoginMainFragment extends Fragment {

    AutoCompleteTextView tvPassword;
    AutoCompleteTextView tvPhoneNo;
    AutoCompleteTextView tvUsername;
    AutoCompleteTextView tvCity;
    AutoCompleteTextView tvHouseNo;
    AutoCompleteTextView tvLastName;
    AutoCompleteTextView tvFirstName;
    AutoCompleteTextView tvPostCode;
    AutoCompleteTextView tvStreet;
    ImageView imgView;
    Context _ctx;
    boolean userExists = true;
    boolean fnameOk = false;
    boolean lnameOk =false;
    boolean passwordOk = false;
    boolean cityOk = false;
    boolean postcodeOk = false;
    boolean streetOk = false;
    boolean housenoOk = true;
    boolean phonenoOk = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_clinet_login_main, container, false);

        _ctx = this.getContext();
        tvPassword = (AutoCompleteTextView) rootView.findViewById(R.id.regClientPassword);
        tvPhoneNo = (AutoCompleteTextView) rootView.findViewById(R.id.regClientPhoneNo);
        tvUsername = (AutoCompleteTextView) rootView.findViewById(R.id.regClientUsername);
        tvCity = (AutoCompleteTextView) rootView.findViewById(R.id.regClientCity);
        tvHouseNo = (AutoCompleteTextView) rootView.findViewById(R.id.regClientHouseNo);
        tvLastName = (AutoCompleteTextView) rootView.findViewById(R.id.regClientLName);
        tvFirstName = (AutoCompleteTextView) rootView.findViewById(R.id.regClientName);
        tvPostCode = (AutoCompleteTextView) rootView.findViewById(R.id.regClientPostCode);
        tvStreet = (AutoCompleteTextView) rootView.findViewById(R.id.regClientStreet);
        imgView = (ImageView) rootView.findViewById(R.id.imageView2);

        tvPhoneNo.setText("+48");

        if(tvFirstName != null)
        {
            tvFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus) {
                        if(!tvLastName.getText().toString().isEmpty())
                        {
                        String username = tvFirstName.getText().toString().toLowerCase()+"."+tvLastName.getText().toString().toLowerCase();
                        tvUsername.setText(username);
                        }
                        if(CheckIsDataCorrect(tvFirstName.getText().toString()))
                        {
                            fnameOk = true;
                        }
                    }
                }
            });
        }

        if(tvLastName != null)
        {
            tvLastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus) {
                        if(!tvFirstName.getText().toString().isEmpty())
                        {
                            String username = tvFirstName.getText().toString().toLowerCase()+"."+tvLastName.getText().toString().toLowerCase();
                            tvUsername.setText(username);
                        }
                        if(CheckIsDataCorrect(tvLastName.getText().toString()))
                        {
                            lnameOk = true;
                        }
                    }
                }
            });
        }

        tvPhoneNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {

                    phonenoOk = CheckPhoneNoCorrect(tvPhoneNo.getText().toString());
                }
            }
        });

        tvPostCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String postcode =tvPostCode.getText().toString().trim();
                    postcodeOk = CheckPostCodeCorrect(postcode);
                    if(postcodeOk && !postcode.contains("-"))
                    {
                        postcode = postcode.substring(0,2) +"-"+postcode.substring(2,5);
                        tvPostCode.setText(postcode);
                    }
                }
            }
        });

        tvCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    cityOk = CheckIsDataCorrect(tvCity.getText().toString());
                }
            }
        });

        tvStreet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    streetOk = CheckIsDataCorrect(tvStreet.getText().toString());
                }
            }
        });

        tvPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    passwordOk = CheckIsDataCorrect(tvPassword.getText().toString());
                }
            }
        });



        if(tvUsername != null)
        {
            tvUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus) {
                        if(!tvUsername.getText().toString().isEmpty())
                        {
                        CheckUsernameExists checker = new CheckUsernameExists(tvUsername.getText().toString().trim(),_ctx);
                        checker.setDBRequestFinishedListener(new OnDBRequestFinished() {
                            @Override
                            public void onDBRequestFinished() {
                                if(checker.dbResult != null)
                                {
                                    String msg = checker.dbResult.getMSG();
                                    if(msg.contentEquals("Sukces"))
                                    {
                                        if(checker.dbResult.getRESULT() > 0)
                                        {
                                            userExists = true;
                                            Toast.makeText(_ctx,"Użytkownik o takim nicku już istnieje!",Toast.LENGTH_LONG).show();
                                            tvUsername.setText(tvUsername.getText().toString()+"1");
                                        }
                                        else if(CheckIsDataCorrect(tvUsername.getText().toString())){
                                            userExists = false;
                                        }

                                    }
                                    else{
                                        Toast.makeText(_ctx,checker.dbResult.getMSG(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
                    }
                    }
                }
            });
        }

        if(imgView != null)
        {
            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvPhoneNo.clearFocus();
                    if(userExists == false && passwordOk && lnameOk && fnameOk && cityOk && postcodeOk && housenoOk && phonenoOk && streetOk)
                    {
                        LoginStatus lgStatus = new LoginStatus(_ctx);
                        User u = new User();
                        u.set_IsActive(1);
                        u.set_FName(tvFirstName.getText().toString());
                        u.set_LName(tvLastName.getText().toString());
                        u.set_Password(tvPassword.getText().toString());
                        u.set_PhoneNo(tvPhoneNo.getText().toString());
                        u.set_RoleId(2);
                        u.set_Username(tvUsername.getText().toString());
                        Adress a = new Adress();
                        a.set_City(tvCity.getText().toString());
                        a.set_PostCode(tvPostCode.getText().toString());
                        a.set_Road(tvStreet.getText().toString());
                        a.set_RoadNo(tvHouseNo.getText().toString());

                        lgStatus.NewClient(u,a);
                        Intent i = new Intent(_ctx,actClientTask.class);
                        startActivity(i);


                    }
                }
            });
        }

        return rootView;
    }

    boolean CheckPhoneNoCorrect(String text)
    {
        /*if(text.trim().startsWith("+48"))
        {
            text = text.replace("+48","").trim();
        }
        Integer i = 0;
        try{
            i = Integer.parseInt(text);
        }
        catch (Exception ex)
        {
            Toast.makeText(_ctx,"Telefon powinien składać się z cyfr.",Toast.LENGTH_LONG).show();
        }

        if(text.length() == 9 && i != 0)
        {
            return true;
        }*/

        if(Pattern.matches("^\\+(48)?\\d{9}$", text))
        {
            return true;
        }

        Toast.makeText(_ctx,"Nieprawidłowe dane!",Toast.LENGTH_LONG).show();
        return false;
    }

    boolean CheckIsDataCorrect(String str)
    {
        if(str!= null && str.length() >= 3)
            return true;
        Toast.makeText(_ctx,"Nieprawidłowe dane!",Toast.LENGTH_LONG).show();
        return false;
    }

    boolean CheckPostCodeCorrect(String text)
    {
        if(Pattern.matches("^\\d{2}\\-?\\d{3}$", text))
        {
            return true;
        }
        Toast.makeText(_ctx,"Nieprawidłowe dane!",Toast.LENGTH_LONG).show();
        return false;
    }
}
