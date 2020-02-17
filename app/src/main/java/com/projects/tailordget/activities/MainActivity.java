package com.projects.tailordget.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.projects.tailordget.datas.Order;
import com.projects.tailordget.fragments.FragmentModuleHome;
import com.projects.tailordget.fragments.FragmentModuleProfile;
import com.projects.tailordget.fragments.FragmentModuleSettings;
import com.projects.tailordget.fragments.FragmentModuleTasks;
import com.projects.test.tailordget.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String ON_CREATE = "onCreate";
    private String ON_START = "onStart";
    private String ON_RESUME = "onResume";
    private String ON_PAUSE = "onPause";
    private String ON_STOP = "onStop";
    private String ON_DESTROY = "onDestroy";
    private String ON_RESTART = "onRestart";
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
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainActivityFrameLayout, fragment).addToBackStack(null).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}