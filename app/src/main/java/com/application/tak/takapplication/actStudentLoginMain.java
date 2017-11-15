package com.application.tak.takapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import org.w3c.dom.ProcessingInstruction;

/**
 * Created by Aleksandra on 04.05.2017.
 */
public class actStudentLoginMain  extends Fragment
{
    public ImageView go;
    public String name,email;
    private static final int RC_SIGN_IN = 9001;
    private static final int RC_SHARE_DIALOG = 8031;
    private static String KEY_IS_RESOLVING = "is_resolving";
    private static String KEY_SHOULD_RESOLVE = "should_resolving";
    private static String URL= "http://plis.google.com/u/0/+DelaroyStudios/post";
    private static final String LABEL_READ_MORE = "READ_MORE";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_student_login_main, container, false);


        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
       // GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
   //*
        /*             .requestEmail()
                .build();


        //
        // GoogleSignInAccount
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        this.go = (ImageView) rootView.findViewById(R.id.go);
        this.gored = (SignInButton) rootView.findViewById(R.id.gored);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        }
*/

        return rootView;
    }




}