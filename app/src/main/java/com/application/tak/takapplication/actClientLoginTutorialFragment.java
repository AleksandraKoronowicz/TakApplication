package com.application.tak.takapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.nio.channels.FileLock;

/**
 * Created by Aleksandra on 04.05.2017.
 */
public class actClientLoginTutorialFragment extends Fragment {

    private FloatingActionButton fab;
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_client_login_tutorial, container, false);

        fab = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButton2);

    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //startActivity(new Intent(view.getContext(), actStudentMainAllTaskListActivity.class));
            Intent i = new Intent(view.getContext(), actClientTask.class);
            startActivity(i);
            getActivity().finish();

        }
    });
        return rootView;
    }

}