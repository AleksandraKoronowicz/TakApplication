package com.application.tak.takapplication;

import android.content.Intent;
import android.opengl.Visibility;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;

/**
 * Created by Aleksandra on 04.05.2017.
 */
public class actStudentLoginDetails  extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.act_student_login_details, container, false);


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabGoToStudentTaSK);
        fab.setVisibility(View.INVISIBLE);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(view.getContext(), actStudentMainAllTaskListActivity.class));
                Intent i = new Intent(view.getContext(), actStudentTask.class);
                startActivity(i);
getActivity().finish();

            }
        });

        RadioButton rb = (RadioButton) rootView.findViewById(R.id.rbRegulaminAccept);
        rb.setChecked(false);
        rb.setOnClickListener(new View.OnClickListener()
                              {
                                  @Override
                                  public void onClick(View view) {

                                      RadioButton rb = (RadioButton) rootView.findViewById(R.id.rbRegulaminAccept);
                                      FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabGoToStudentTaSK);
                                      if (rb.isChecked())
                                      {
                                          fab.show();

                                      }
                                  }
                              });

        AutoCompleteTextView regulamin = (AutoCompleteTextView) rootView.findViewById(R.id.regulamin);

        regulamin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                    Intent i = new Intent(view.getContext(), actStudentRegulation.class);
                    startActivity(i);

            }
        });

        return rootView;
    }

}