package com.projects.test.tailordget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Date;
import java.util.List;

public class TestActivity extends AppCompatActivity implements ProfileListAdapter.ItemClickListener {

    private AppDatabase mDB;
    private RecyclerView profileRecycleView;
    private List<Profile> profileList;
    private ProfileListAdapter profileListAdapter;
    private Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mDB = AppDatabase.getInstance(getApplicationContext());
        profileRecycleView = (RecyclerView) findViewById(R.id.profileRecycleView);
        profileListAdapter = new ProfileListAdapter(this, this);

        Profile p = new Profile("Duip", "M", date);
        mDB.profileDao().insertProfile(p);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        profileRecycleView.setLayoutManager(new LinearLayoutManager(this));
        profileRecycleView.setAdapter(profileListAdapter);
        profileRecycleView.addItemDecoration(itemDecorator);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete
            }
        }).attachToRecyclerView(profileRecycleView);

        profileList = mDB.profileDao().loadAllProfiles();
    }

    @Override
    public void onItemClickListener(int itemId) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        profileListAdapter.setProfiles(mDB.profileDao().loadAllProfiles());
    }
}
