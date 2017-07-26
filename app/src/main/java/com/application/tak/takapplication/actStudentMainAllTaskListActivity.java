package com.application.tak.takapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.tak.takapplication.dummy.DummyContent2;

import com.application.tak.takapplication.data_model.Category;
import com.application.tak.takapplication.dummy.DummyContent2_old;

import java.util.List;


/*
 * An activity representing a list of act_Student_Main_All_Task. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link actStudentMainAllTaskDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
*/
public class actStudentMainAllTaskListActivity extends AppCompatActivity {}

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_student_main_task_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = findViewById(R.id.actstudentmainalltask_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.actstudentmainalltask_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent2_old.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {


        private final List<DummyContent2.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent2_old.DummyItem> items) {

            private final List<Category> mValues;

        public SimpleItemRecyclerViewAdapter(List < Category > items) {

                mValues = items;
            }

            @Override
            public ViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_act_student_main_task_list_content, parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder ( final ViewHolder holder, int position){
                holder.mItem = mValues.get(position);
                holder.mIdView.setText(mValues.get(position).get_CategoryName());
                holder.mContentView.setText(mValues.get(position).get_CategoryName());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTwoPane) {
                            Bundle arguments = new Bundle();
                            arguments.putInt(actStudentMainAllTaskDetailFragment.ARG_ITEM_ID, holder.mItem.get_Id());
                            actStudentMainAllTaskDetailFragment fragment = new actStudentMainAllTaskDetailFragment();
                            fragment.setArguments(arguments);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.actstudentmainalltask_detail_container, fragment)
                                    .commit();
                        } else {
                            Context context = v.getContext();
                            Intent intent = new Intent(context, actStudentMainAllTaskDetailActivity.class);
                            intent.putExtra(actStudentMainAllTaskDetailFragment.ARG_ITEM_ID, holder.mItem.get_Id());

                            context.startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public int getItemCount () {
                return mValues.size();
            }

            public class ViewHolder extends RecyclerView.ViewHolder {
                public final View mView;
                public final TextView mIdView;
                public final TextView mContentView;

                public DummyContent2_old.DummyItem mItem;

                public Category mItem;


                public ViewHolder(View view) {
                    super(view);
                    mView = view;
                    mIdView = (TextView) view.findViewById(R.id.id);
                    mContentView = (TextView) view.findViewById(R.id.content);


                }

                @Override
                public String toString() {
                    return super.toString() + " '" + mContentView.getText() + "'";
                }
            }
        }
    }
}

*/