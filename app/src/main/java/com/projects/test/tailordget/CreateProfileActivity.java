package com.projects.test.tailordget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

public class CreateProfileActivity extends AppCompatActivity {

    private AppDatabase mDB;
    private Toolbar createProfileToolbar;
    private EditText profileNameEditText;
    private Spinner profileSexeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        mDB = AppDatabase.getInstance(getApplicationContext());

        profileNameEditText = (EditText) findViewById(R.id.profileNameEditText);
        profileSexeSpinner = (Spinner) findViewById(R.id.profileSexeSpinner);

        createProfileToolbar = (Toolbar) findViewById(R.id.createProfileToolbar);
        createProfileToolbar.inflateMenu(R.menu.create_profile_toolbar_menu);
        createProfileToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem == createProfileToolbar.getMenu().getItem(0)) {
                    Date currentDate = new Date();
                    String name = profileNameEditText.getText().toString();
                    String sexe = profileSexeSpinner.getSelectedItem().toString();

                    final Profile newProfile = new Profile(name, sexe, currentDate);
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDB.profileDao().insertProfile(newProfile);
                            Toast.makeText(getApplicationContext(), "Profile saved successfully!", Toast.LENGTH_LONG);
                            finish();
                        }
                    });
                }
                return true;
            }

        });
        createProfileToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
