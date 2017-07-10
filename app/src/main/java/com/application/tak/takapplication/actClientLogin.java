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
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import com.application.tak.takapplication.data_access.Config;
import com.application.tak.takapplication.data_access.CreateClient;
import com.application.tak.takapplication.data_access.DB_result;
import com.application.tak.takapplication.data_access.Helper;
import com.application.tak.takapplication.data_model.Adress;
import com.application.tak.takapplication.data_model.User;
import com.application.tak.takapplication.interfaces.OnDBRequestFinished;
import com.application.tak.takapplication.local_db.LocalDB;

import java.util.List;

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
        Intent tutorialPage = new Intent (this, actStudentMainAllTaskListActivity.class);
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

        final LocalDB localDb = new LocalDB(this.getApplicationContext());
        List<User> users = localDb.GetAllUsera();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        txtCustomerPostcode = (TextInputLayout) findViewById(R.id.txtNewCustomerPostcode);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        String adj = "to jest tekst";
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
                        startActivity(new Intent(ctx, actClientTaskNotSelectedListActivity.class));
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

               // startActivity(new Intent(view.getContext(), actClientTaskNotSelectedListActivity.class));
                //   Intent i = new Intent( null, actClientTask.class);
             //  startActivity(i);


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            View rootView = inflater.inflate(R.layout.act_clinet_login_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
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
                    fab.show();
                    return "Dane";
                case 1:
                    fab.hide();
                    return "Regulamin";
                case 2:
                    fab.show();
                    return "Tutorial";
            }
            return null;
        }
    }
}
