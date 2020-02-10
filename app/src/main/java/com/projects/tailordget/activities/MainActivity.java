package com.projects.tailordget.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.projects.tailordget.datas.AppDatabase;
import com.projects.tailordget.datas.Order;
import com.projects.tailordget.fragments.FragmentModuleHome;
import com.projects.tailordget.fragments.FragmentModuleProfile;
import com.projects.tailordget.fragments.FragmentModuleSettings;
import com.projects.tailordget.fragments.FragmentModuleTasks;
import com.projects.tailordget.utilities.AppExecutors;
import com.projects.test.tailordget.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String ON_CREATE = "onCreate";
    private String ON_START = "onStart";
    private String ON_RESUME = "onResume";
    private String ON_PAUSE = "onPause";
    private String ON_STOP = "onStop";
    private String ON_DESTROY = "onDestroy";
    private String ON_RESTART = "onRestart";
    private AppDatabase mDB;
    private static final String TAG = MainActivity.class.getSimpleName();
    private FrameLayout frameLayout;
    enum FrameMode {
        HOME,
        PROFILES,
        TASKS,
        SETTINGS
    }
    private FrameMode frameMode;
    private BottomNavigationView bottomNavigationView;

    public static List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = AppDatabase.getInstance(getApplicationContext());
        frameLayout = (FrameLayout) findViewById(R.id.mainActivityFrameLayout);

        final Fragment homeFragment = new FragmentModuleHome();
        final Fragment profileFragment = new FragmentModuleProfile();
        final Fragment taskFragment = new FragmentModuleTasks();
        final Fragment settingFragment = new FragmentModuleSettings();

                bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.homeItem) {
                    replaceFragment(homeFragment);
                } else if (menuItem.getItemId() == R.id.profilesItem) {
                    replaceFragment(profileFragment);
                } else if (menuItem.getItemId() == R.id.taskItem) {
                    replaceFragment(taskFragment);
                } else if (menuItem.getItemId() == R.id.settingsItem) {
                    replaceFragment(settingFragment);
                }

                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.homeItem);

        orderList = new ArrayList<>();
        Log.d(TAG, "============> "+ON_CREATE);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainActivityFrameLayout, fragment).commit();
    }

    public void retrieveOrders() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                orderList = mDB.orderDao().loadAllOrders();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "============> "+ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "============> "+ON_RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "============> "+ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "============> "+ON_STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "============> "+ON_DESTROY);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "============> "+ON_RESTART);
    }

}
