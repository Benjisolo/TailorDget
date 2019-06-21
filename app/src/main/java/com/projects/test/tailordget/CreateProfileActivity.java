package com.projects.test.tailordget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

public class CreateProfileActivity extends AppCompatActivity {

    private AppDatabase mDB;
    private Toolbar createProfileToolbar;
    private EditText nameEditText, phoneEditText, neckEditText, chestEditText, waistEditText,
            hipEditText, seatEditText, shirtLengthEditText, halfShoulderEditText,
            shoulderWidthEditText, armLengthEditText, bicepsEditText, wristEditText,
            inseamEditText, coatSleeveLengthEditText, jacketLengthEditText, thighEditText,
            aboveKneeEditText, belowKneeEditText, calfEditText, ankleEditText, crotchToKneeEditText,
            kneeToCalfEditText, calfToAnkleEditText, waistToAnkleEditText;
    private Spinner sexSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        mDB = AppDatabase.getInstance(getApplicationContext());

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        sexSpinner = (Spinner) findViewById(R.id.sexSpinner);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        neckEditText = (EditText) findViewById(R.id.neckEditText);
        chestEditText = (EditText) findViewById(R.id.chestEditText);
        waistEditText = (EditText) findViewById(R.id.waistEditText);
        hipEditText = (EditText) findViewById(R.id.hipEditText);
        seatEditText = (EditText) findViewById(R.id.seatEditText);
        shirtLengthEditText = (EditText) findViewById(R.id.shirtLengthEditText);
        halfShoulderEditText = (EditText) findViewById(R.id.halfShoulderEditText);
        shoulderWidthEditText = (EditText) findViewById(R.id.shoulderWidthEditText);
        armLengthEditText = (EditText) findViewById(R.id.armLengthEditText);
        bicepsEditText = (EditText) findViewById(R.id.bicepsEditText);
        wristEditText = (EditText) findViewById(R.id.wristEditText);
        inseamEditText = (EditText) findViewById(R.id.inseamEditText);
        coatSleeveLengthEditText = (EditText) findViewById(R.id.coatSleeveLengthEditText);
        jacketLengthEditText = (EditText) findViewById(R.id.jacketLengthEditText);
        thighEditText = (EditText) findViewById(R.id.thighEditText);
        aboveKneeEditText = (EditText) findViewById(R.id.aboveKneeEditText);
        belowKneeEditText = (EditText) findViewById(R.id.belowKneeEditText);
        calfEditText = (EditText) findViewById(R.id.calfEditText);
        ankleEditText = (EditText) findViewById(R.id.ankleEditText);
        crotchToKneeEditText = (EditText) findViewById(R.id.crotchToKneeEditText);
        kneeToCalfEditText = (EditText) findViewById(R.id.kneeToCalfEditText);
        calfToAnkleEditText = (EditText) findViewById(R.id.calfToAnkleEditText);
        waistToAnkleEditText = (EditText) findViewById(R.id.waistToAnkleEditText);

        createProfileToolbar = (Toolbar) findViewById(R.id.createProfileToolbar);
        createProfileToolbar.inflateMenu(R.menu.create_profile_toolbar_menu);
        createProfileToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(getApplicationContext(), "Profile saved successfully!", Toast.LENGTH_LONG).show();
                if(menuItem == createProfileToolbar.getMenu().getItem(0)) {
                    Date currentDate = new Date();
                    String name = nameEditText.getText().toString();
                    String sexe = sexSpinner.getSelectedItem().toString();

                    final Profile newProfile = new Profile(name, sexe.substring(0, 1), currentDate);
                    newProfile.setPhone(phoneEditText.getText().toString());
                    newProfile.setNeck(checkMensurationEntry(neckEditText.getText().toString()));
                    newProfile.setChest(checkMensurationEntry(chestEditText.getText().toString()));
                    newProfile.setWaist(checkMensurationEntry(waistEditText.getText().toString()));
                    newProfile.setHip(checkMensurationEntry(hipEditText.getText().toString()));
                    newProfile.setSeat(checkMensurationEntry(seatEditText.getText().toString()));
                    newProfile.setShirtLength(checkMensurationEntry(shirtLengthEditText.getText().toString()));
                    newProfile.setHalfShoulder(checkMensurationEntry(halfShoulderEditText.getText().toString()));
                    newProfile.setShoulderWidth(checkMensurationEntry(shoulderWidthEditText.getText().toString()));
                    newProfile.setArmLength(checkMensurationEntry(armLengthEditText.getText().toString()));
                    newProfile.setBiceps(checkMensurationEntry(bicepsEditText.getText().toString()));
                    newProfile.setWrist(checkMensurationEntry(wristEditText.getText().toString()));
                    newProfile.setInseam(checkMensurationEntry(inseamEditText.getText().toString()));
                    newProfile.setCoatSleeveLength(checkMensurationEntry(coatSleeveLengthEditText.getText().toString()));
                    newProfile.setJacketLength(checkMensurationEntry(jacketLengthEditText.getText().toString()));
                    newProfile.setThigh(checkMensurationEntry(thighEditText.getText().toString()));
                    newProfile.setAboveKnee(checkMensurationEntry(aboveKneeEditText.getText().toString()));
                    newProfile.setBelowKnee(checkMensurationEntry(belowKneeEditText.getText().toString()));
                    newProfile.setCalf(checkMensurationEntry(calfEditText.getText().toString()));
                    newProfile.setAnkle(checkMensurationEntry(ankleEditText.getText().toString()));
                    newProfile.setCrotchToKnee(checkMensurationEntry(crotchToKneeEditText.getText().toString()));
                    newProfile.setKneeToCalf(checkMensurationEntry(neckEditText.getText().toString()));
                    newProfile.setCalfToAnkle(checkMensurationEntry(calfToAnkleEditText.getText().toString()));
                    newProfile.setWaistToAnkle(checkMensurationEntry(waistToAnkleEditText.getText().toString()));

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDB.profileDao().insertProfile(newProfile);
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
//        setSupportActionBar(createProfileToolbar);
    }

    private float checkMensurationEntry(String entry) {
        return (entry.equals(""))? 0 : Float.parseFloat(entry);
    }
}
