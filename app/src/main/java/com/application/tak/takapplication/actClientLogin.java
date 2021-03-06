package com.application.tak.takapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.TextView;

public class actClientLogin extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    private Context ctx;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public void gotoTutorial(View v){
        Intent tutorialPage = new Intent (this, actClientTask.class);
        startActivity(tutorialPage);
    }
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    TextInputLayout txtCustomerPostcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_client_login);
        ctx = this;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
   //     txtCustomerPostcode = (TextInputLayout) findViewById(R.id.txtNewCustomerPostcode);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

     /*   String adj = "to jest tekst";
        try{
        String encrypted = Config.encrypt(adj);
        String decrypted = Config.decrypt(encrypted);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final CreateClient newClient = new CreateClient(ctx);
                User u = new User();
                u.set_Username("testowyzaplikacji");
                u.set_FName("ania");
                u.set_LName("zet");
                u.set_PhoneNo("12345");
                Adress a = new Adress();
                a.set_City("gorolowice");
                a.set_Road("goralska");
                a.set_RoadNo("10/15");
                a.set_PostCode("ww");

                newClient.setDBRequestFinishedListener(new OnDBRequestFinished() {
                    @Override
                    public void onDBRequestFinished() {
                        Config.LoggedInClient = newClient.newClient;
                        User addedUser = (User) Config.LoggedInClient;
                        if(addedUser != null)
                        {
                        addedUser.set_IsLoggedIn(1);
                        localDb.AddUser(addedUser);
                        startActivity(new Intent(ctx, actClientTask.class));
                        finish();
                        }
                        else
                        {
                            DB_result retData = newClient.dbResult;
                            if(retData != null)
                            {
                                Toast t = Toast.makeText(ctx,retData.getMSG(),Toast.LENGTH_LONG);
                                t.show();
                            }
                            else
                            {
                                Toast t = Toast.makeText(ctx,"Nastąpił nieoczekiwany błąd. Spróbuj później.",Toast.LENGTH_LONG);
                                t.show();
                            }
                        }
                    }});

                newClient.InsertClient(u,a);
                //    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //     .setAction("Action", null).show();

              startActivity(new Intent(view.getContext(), actClientTask.class));

               // startActivity(new Intent(view.getContext(), actClientTaskNotSelectedListActivity.class));
                //   Intent i = new Intent( null, actClientTask.class);
             //  startActivity(i);


            }
        });
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.act_clinet_login_details, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            return rootView;

            //  textView.setOnClickListener(new View.OnClickListener() {
            //  public void onClick(View v) {
            ////       Intent i = new Intent(this.getActivity(), actStudentMainAllTaskListActivity.class);
            //    startActivity(i);
            //    }
            // Do something in response to button click
            //   });
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    actClinetLoginMainFragment clientMain = new actClinetLoginMainFragment();
                    return clientMain;
                case 1:
                    actClientLoginRegulationFragment clientCode = new actClientLoginRegulationFragment();

                    return clientCode;
                case 2:
                    actClientLoginTutorialFragment clientTut = new actClientLoginTutorialFragment();

                    return clientTut;
                default:
                    return  null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:

                    return "Dane";
                case 1:

                    return "Regulamin";
                case 2:

                    return "Tutorial";
            }
            return null;
        }
    }
}
