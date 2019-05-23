package com.projects.test.tailordget;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class FragmentAllProfiles extends Fragment implements ProfileListAdapter.ItemClickListener {

    //    private static final String TAG = "MainActivity";
    private static final String TAG = MainActivity.class.getSimpleName();
    // Member variables for the adapter and RecyclerView
    private RecyclerView recyclerViewProfile;
    private ProfileListAdapter mAdapter;
    private FloatingActionButton newProfileActBtn;
    // Create AppDatabase member variable for the Database
    private AppDatabase mDb;
    Date date = new Date();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.all_profiles_fragment, container, false);

        newProfileActBtn = (FloatingActionButton) view.findViewById(R.id.newProfileFloatingActBtn);
        newProfileActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newProfileView = new Intent(view.getContext(), CreateProfileActivity.class);
                startActivity(newProfileView);
            }
        });

        // Set the RecyclerView to its corresponding view
        recyclerViewProfile = view.findViewById(R.id.recyclerViewProfile);
        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        recyclerViewProfile.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new ProfileListAdapter(view.getContext(), this);
        recyclerViewProfile.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(view.getContext(), VERTICAL);
        recyclerViewProfile.addItemDecoration(decoration);

        // COMPLETED (2) Initialize member variable for the data base
        mDb = AppDatabase.getInstance(view.getContext());

        return view;
    }

    public void addMock() {
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Sandrine EKE", "F", date));
        profileList.add(new Profile("Paul MBOA", "M", date));
        profileList.add(new Profile("Isaac Quartier", "M", date));
        profileList.add(new Profile("Esther NOUMBI", "F", date));
        for(Profile p : profileList) {
            mDb.profileDao().insertProfile(p);
        }
    }

    @Override
    public void onItemClickListener(int itemPosition) {
        Log.d(TAG, "------------------------------- onItemClickListener: " + itemPosition + " clicked.");
    }

    @Override
    public void onResume() {
        super.onResume();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Profile> profileList = mDb.profileDao().loadAllProfiles();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setProfiles(profileList);
                    }
                });
            }
        });
    }
}
