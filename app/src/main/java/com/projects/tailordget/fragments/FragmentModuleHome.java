package com.projects.tailordget.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects.tailordget.datas.AppDatabase;
import com.projects.tailordget.datas.Order;
import com.projects.tailordget.utilities.AppExecutors;
import com.projects.test.tailordget.R;

import java.util.List;

public class FragmentModuleHome extends Fragment {
    private static final String TAG = FragmentModuleHome.class.getSimpleName();
    private Toolbar toolbar;
    private LinearLayout homeLinearLayout;
    private LayoutInflater inflater;
    private String name = "";
    private AppDatabase mDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_module_home, container, false);

        mDB = AppDatabase.getInstance(getContext());
        toolbar = (Toolbar) view.findViewById(R.id.homeToolbar);
//        getSupportActionBar().setElevation(0);
        homeLinearLayout = (LinearLayout) view.findViewById(R.id.homeLinearLayout);
        this.inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return view;
    }

    private void populateOrdersView(List<Order> orderList) {
        Log.d(TAG, "---------------- ITEMS "+orderList.size());
        homeLinearLayout.removeViews(1, homeLinearLayout.getChildCount()-1);
        for(Order o : orderList) {
            addCardView(o);
        }
    }

    public void retrieveOrders() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Order> orderList = mDB.orderDao().loadAllOrders();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        populateOrdersView(orderList);
                    }
                });
            }
        });
    }

    public void addCardView(Order order) {
        View v = this.inflater.inflate(R.layout.view_order, null);
        ((TextView) v.findViewById(R.id.orderTitleTextView)).setText(order.getTitle());
        ((TextView) v.findViewById(R.id.orderTypeTextView)).setText(getOrderType(order));
        ((TextView) v.findViewById(R.id.profileOrderingTextView)).setText(getProfileName(order));
        ((TextView) v.findViewById(R.id.orderDateTextView)).setText(order.getDateUpdate().toString());
//        v.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        homeLinearLayout.addView(v);
    }

    private String getProfileName(final Order order) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                name = mDB.profileDao().loadProfileById(order.getProfile()).getName();
            }
        });
        return name;
    }

    private String getOrderType(final Order order) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                name = mDB.typeDao().loadTypeById(order.getType()).getName();
            }
        });
        return name;
    }

    private String getOrderStatus(final Order order) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                name = mDB.typeDao().loadTypeById(order.getType()).getName();
            }
        });
        return name;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        Log.d(TAG, "onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Log.d(TAG, "onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
//        Log.d(TAG, "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.d(TAG, "onResume()");
        homeLinearLayout.setBackgroundColor(Color.LTGRAY);
        retrieveOrders();
    }

    @Override
    public void onPause() {
        super.onPause();
//        Log.d(TAG, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
//        Log.d(TAG, "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Log.d(TAG, "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        Log.d(TAG, "onDetach()");
    }
}
