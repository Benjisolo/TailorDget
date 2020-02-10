package com.projects.tailordget.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.tailordget.adapters.ViewPagerAdapter;
import com.projects.test.tailordget.R;

public class FragmentModuleProfile extends Fragment {
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

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.AddFragment(new FragmentAllProfiles(), getString(R.string.all_profile_title_pane));
        adapter.AddFragment(new FragmentFavoritesProfiles(), getString(R.string.favorite_profile_title_pane));

        profilesViewPager.setAdapter(adapter);
        profilesTabLayout.setupWithViewPager(profilesViewPager);

        return view;
    }
}
