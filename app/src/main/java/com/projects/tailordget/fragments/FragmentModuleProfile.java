package com.projects.tailordget.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.tailordget.adapters.ViewPagerAdapter;
import com.projects.test.tailordget.R;

public class FragmentModuleProfile extends Fragment {
    private static final String TAG = FragmentModuleHome.class.getSimpleName();
    private Toolbar toolbar;
    private TabLayout profilesTabLayout;
    private ViewPager profilesViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_module_profile, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.profilesToolbar);
//        getSupportActionBar().setElevation(0);

        profilesTabLayout = (TabLayout) view.findViewById(R.id.profilesTabLayout);
        profilesViewPager = (ViewPager) view.findViewById(R.id.profilesViewPager);

        final Fragment allProfilesFragment = new FragmentAllProfiles();
        final Fragment favoriteProfiles = new FragmentFavoritesProfiles();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.AddFragment(allProfilesFragment, getString(R.string.all_profile_title_pane));
        adapter.AddFragment(favoriteProfiles, getString(R.string.favorite_profile_title_pane));

        profilesViewPager.setAdapter(adapter);
        profilesTabLayout.setupWithViewPager(profilesViewPager);

        Log.d(TAG, "============> ON_CREATE_VIEW");
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "============> ON_ATTACH");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "============> ON_CREATE");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "============> ON_ACTIVITY_CREATED");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "============> ON_START");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "============> ON_RESUME");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "============> ON_PAUSE");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "============> ON_STOP");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "============> ON_DESTROY_VIEW");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "============> ON_DESTROY");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "============> ON_DETACH");
    }
}
