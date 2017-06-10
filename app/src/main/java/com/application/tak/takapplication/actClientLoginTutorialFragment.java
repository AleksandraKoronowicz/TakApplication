package com.application.tak.takapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Aleksandra on 04.05.2017.
 */
public class actClientLoginTutorialFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_client_login_tutorial, container, false);

        return rootView;
    }

}