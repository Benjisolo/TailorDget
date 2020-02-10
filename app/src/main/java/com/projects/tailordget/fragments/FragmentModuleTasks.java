package com.projects.tailordget.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.projects.test.tailordget.R;

public class FragmentModuleTasks extends Fragment {
    private Toolbar toolbar;
    private LinearLayout tasksLinearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_module_tasks, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.tasksToolbar);
//        getSupportActionBar().setElevation(0);

        tasksLinearLayout = (LinearLayout) getActivity().findViewById(R.id.tasksLinearLayout);

        return view;
    }
}
