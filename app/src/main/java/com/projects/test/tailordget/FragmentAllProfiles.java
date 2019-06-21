package com.projects.test.tailordget;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class FragmentAllProfiles extends Fragment
        implements ProfileListAdapter.ItemClickListener, ActionModeViewCallbacks,
        ListProfileActionModeViewCallbacks, ProfileListAdapter.ItemLongClickListener {

    //    private static final String TAG = "MainActivity";
    private static final String TAG = MainActivity.class.getSimpleName();
    // Member variables for the adapter and RecyclerView
    private RecyclerView recyclerViewProfile;
    private ProfileListAdapter mAdapter;
    private FloatingActionButton newProfileActBtn;
    private ActionMode mActionMode;
    // Create AppDatabase member variable for the Database
    private AppDatabase mDb;
    Date date = new Date();

    public static Profile displayedProfile;

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
        mAdapter = new ProfileListAdapter(view.getContext(), this, this);
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
        // select the clicked item if cab menu is activated
        if(mActionMode != null) {
            onListItemSelect(itemPosition);
        }
        else {
            Intent openDetailView = new Intent(getContext(), ProfileDetailActivity.class);
            displayedProfile = mAdapter.getProfileList().get(itemPosition);
            startActivity(openDetailView);
        }
    }

    @Override
    public void onItemLongClickListener(int itemId) {
        // Activates the cab menu and select the long cliked item
        onListItemSelect(itemId);
    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveProfiles();
    }

    public void retrieveProfiles() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Profile> profileList = mDb.profileDao().loadAllProfiles();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setProfiles(profileList);

                        FragmentFavoritesProfiles.getFavoriteProfileList().clear();
                        for(Profile p : profileList) {
                            if(p.isFavorite())
                                FragmentFavoritesProfiles.getFavoriteProfileList().add(p);
                        }
                    }
                });
            }
        });
    }

//    This method activates the cab menu
    @Override
    public void onListItemSelect(int position) {
        mAdapter.toggleSelection(position);
        final boolean hasCheckedItems = mAdapter.getSelectedCount() > 0;
        if (hasCheckedItems && mActionMode == null) {
            // there are some selected items, start the actionMode
            mActionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(new ListProfileToolbarActionModeCallback(this, mAdapter));
        } else if (!hasCheckedItems && mActionMode != null) {
            // there no selected items, finish the actionMode
            mActionMode.finish();
            mActionMode = null;
        }
        if (mActionMode != null) {
            //set action mode title on item selection
            mActionMode.setTitle(getString(R.string.cab_selected, mAdapter.getSelectedCount()));
        }
    }

    @Override
    public void onDestroyActionMode() {
        mActionMode = null;
    }

    @Override
    public void onDeleteActionClicked() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                for(Profile p : mAdapter.getSelectedItems())
                    mDb.profileDao().deleteProfile(p);
            }
        });
        retrieveProfiles();
        Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFavoriteActionClicked() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                for(Profile p : mAdapter.getSelectedItems()) {
                    p.setFavorite(true);
                    mDb.profileDao().updateProfile(p);
                }
            }
        });
        retrieveProfiles();
        Toast.makeText(getContext(), getString(R.string.added_to_favorite_message), Toast.LENGTH_SHORT).show();
    }
}
