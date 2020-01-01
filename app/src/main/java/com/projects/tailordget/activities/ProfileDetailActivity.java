package com.projects.tailordget.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.projects.tailordget.fragments.FragmentProfileOrder;
import com.projects.tailordget.fragments.FragmentProfileMeasurements;
import com.projects.tailordget.fragments.FragmentProfileOversee;
import com.projects.tailordget.datas.Profile;
import com.projects.test.tailordget.R;

public class ProfileDetailActivity extends AppCompatActivity {

    public static Profile mProfile;

    enum FragmentMode {
        MEASUREMENT,
        ORDERS,
        OVERSEE
    }
    private FragmentMode fragmentMode;

    private Toolbar profileViewToolbar;
    private CollapsingToolbarLayout profileViewCollapsingTB;
    private FloatingActionButton editFab;
    private FrameLayout frameLayout;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.mennuMensuration: {
                        replaceFragment(new FragmentProfileMeasurements());
                        editFab.setImageResource(R.drawable.ic_edit);
                        fragmentMode = FragmentMode.MEASUREMENT;
                    }
                    return true;
                case R.id.menuOrders: {
                        replaceFragment(new FragmentProfileOrder());
                        editFab.setImageResource(R.drawable.ic_add);
                        fragmentMode = FragmentMode.ORDERS;
                    }
                    return true;
                case R.id.menuOversee: {
                        replaceFragment(new FragmentProfileOversee());
                        fragmentMode = FragmentMode.OVERSEE;
                    }
                    return true;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        initViews();

        profileViewToolbar.inflateMenu(R.menu.profile_detail_menu);
        profileViewToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profileViewCollapsingTB.setTitle(mProfile.getName());

        fragmentMode = FragmentMode.MEASUREMENT;

        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentMode == FragmentMode.MEASUREMENT) {
                    openEditActivity();
                }
                if(fragmentMode == FragmentMode.ORDERS) {
                    openNewOrderActivity();
                }
            }
        });

        replaceFragment(new FragmentProfileMeasurements());

        BottomNavigationView bottomNavigationView = findViewById(R.id.profileDetailBottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void openNewOrderActivity() {
        Intent intent = new Intent(getApplicationContext(), NewOrder.class);
        startActivity(intent);
    }

    private void initViews() {
        profileViewToolbar = (Toolbar) findViewById(R.id.profileViewToolbar);
        profileViewCollapsingTB = (CollapsingToolbarLayout) findViewById(R.id.profileViewCollapsingTB);
        editFab = (FloatingActionButton) findViewById(R.id.editFab);
        frameLayout = (FrameLayout) findViewById(R.id.profileDetailFrameLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.profileDetailFrameLayout, fragment).commit();
    }

    public void openEditActivity() {
        Intent gotoEditActivity = new Intent(getApplicationContext(), CreateProfileActivity.class);
        gotoEditActivity.putExtra(CreateProfileActivity.EDIT_MODE, true);
        startActivity(gotoEditActivity);
//        finish();
    }
}
