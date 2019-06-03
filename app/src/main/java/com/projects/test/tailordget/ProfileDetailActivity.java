package com.projects.test.tailordget;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ProfileDetailActivity extends AppCompatActivity {
    Toolbar profileViewToobar;
    CollapsingToolbarLayout profileViewCollapsingTB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        String profileName = getIntent().getExtras().getString("profileName");

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
        profileViewCollapsingTB.setTitle(profileName);
    }
}
