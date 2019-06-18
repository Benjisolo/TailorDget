package com.projects.test.tailordget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class ProfileDetailActivity extends AppCompatActivity {
    private Toolbar profileViewToobar;
    private CollapsingToolbarLayout profileViewCollapsingTB;
    private FrameLayout frameLayout;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.mennuMensuration:
                    replaceFragment(new FragmentProfileMensuration());
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

        profileViewToobar = (Toolbar) findViewById(R.id.profileViewToolbar);
//        profileViewToobar.setTitle(profileName);
        profileViewToobar.inflateMenu(R.menu.profile_detail_menu);
        profileViewToobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profileViewCollapsingTB = (CollapsingToolbarLayout) findViewById(R.id.profileViewCollapsingTB);
        profileViewCollapsingTB.setTitle(FragmentAllProfiles.displayedProfile.getName());

        frameLayout = (FrameLayout) findViewById(R.id.profileDetailFrameLayout);
        replaceFragment(new FragmentProfileMensuration());

        BottomNavigationView bottomNavigationView = findViewById(R.id.profileDetailBottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.profileDetailFrameLayout, fragment).commit();
    }
}
