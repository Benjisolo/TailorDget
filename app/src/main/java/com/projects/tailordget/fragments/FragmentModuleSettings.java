package com.projects.tailordget.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.projects.test.tailordget.R;

public class FragmentModuleSettings extends Fragment {
    private Toolbar toolbar;
    private LinearLayout settingsLinearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_module_settings, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.settingsToolbar);
//        getSupportActionBar().setElevation(0);

        settingsLinearLayout = (LinearLayout) getActivity().findViewById(R.id.settingLinearLayout);

        return view;
    }
}
