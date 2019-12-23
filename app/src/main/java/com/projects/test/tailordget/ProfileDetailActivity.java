package com.projects.test.tailordget;

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

public class ProfileDetailActivity extends AppCompatActivity {

    public static Profile mProfile;

    private Toolbar profileViewToolbar;
    private CollapsingToolbarLayout profileViewCollapsingTB;
    private FloatingActionButton editFab;
    private FrameLayout frameLayout;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.mennuMensuration:
                    replaceFragment(new FragmentProfileMeasurements());
                    return true;
                case R.id.menuCommands:
                    replaceFragment(new FragmentProfileCommands());
                    return true;
                case R.id.menuOversee:
                    replaceFragment(new FragmentProfileOversee());
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

        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditActivity();
            }
        });

        replaceFragment(new FragmentProfileMeasurements());

        BottomNavigationView bottomNavigationView = findViewById(R.id.profileDetailBottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
        finish();
    }
}
