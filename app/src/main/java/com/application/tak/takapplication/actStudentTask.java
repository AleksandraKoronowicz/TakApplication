package com.application.tak.takapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.application.tak.takapplication.adapters.ViewPagerAdapter;
import com.application.tak.takapplication.student.task.actStudentAllTask;
import com.application.tak.takapplication.student.task.actStudentMyTask;
import com.application.tak.takapplication.student.task.actStudentTaskPlan;

public class actStudentTask extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_student_task);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new actStudentTaskPlan(),"Zaplanowane");
        viewPagerAdapter.addFragments(new actStudentAllTask(),"Wszystkie");
        viewPagerAdapter.addFragments(new actStudentMyTask(),"Moje zadania");



        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_client_overall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            return true;
        }
        if (id == R.id.action_problem)
        {
            SendMail();
        }

        return super.onOptionsItemSelected(item);
    }
    private void SendMail()
    {

    }
}
/*
    private RecyclerView recyclerView;


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private boolean mTwoPane;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
/*
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_client_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    actClientTaskNotSelectedListFragment client3 = new actClientTaskNotSelectedListFragment();
                    return client3;

                case 1:
                    actClientTaskAddFragment client2 = new actClientTaskAddFragment();
                    return client2;
                case 2:
                    actClientTaskAddFragment addtask = new actClientTaskAddFragment();
                    return addtask;
                default:
                    return null;
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
                    return "Oczekuj&#x105;ce";
                case 1:
                    return "W toku";
                case 2:
                    return "Dodaj nowe";
            }
            return null;
        }
    }}
*/
