package com.projects.test.tailordget;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private TabLayout profilesTabLayout;
    private ViewPager profilesViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profilesTabLayout = (TabLayout) findViewById(R.id.profilesTabLayout);
        profilesViewPager = (ViewPager) findViewById(R.id.profilesViewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentAllProfiles(), getString(R.string.all_profile_title_pane));
        adapter.AddFragment(new FragmentFavoritesProfiles(), getString(R.string.favorite_profile_title_pane));

        profilesViewPager.setAdapter(adapter);
        profilesTabLayout.setupWithViewPager(profilesViewPager);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.homeItem) {
                } else if (menuItem.getItemId() == R.id.profilesItem) {
                } else if (menuItem.getItemId() == R.id.taskItem) {
                } else if (menuItem.getItemId() == R.id.settingsItem) {
                }

                return true;
            }
        });
    }

//    public void toastMessage(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
}
