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

    public static final String EDIT_MODE = "editMode";

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

        initViews();

        Bundle extraData = getIntent().getExtras();
        final boolean editMode = (boolean)extraData.get(EDIT_MODE);
        if(editMode) {
            createProfileToolbar.setTitle("Edit Profile");
            populateViews();
        }

        createProfileToolbar.inflateMenu(R.menu.create_profile_toolbar_menu);
        createProfileToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem == createProfileToolbar.getMenu().getItem(0)) {
                    final Profile newProfile = new Profile();
                    newProfile.setName(nameEditText.getText().toString());
                    newProfile.setSexe(sexSpinner.getSelectedItem().toString().substring(0, 1));
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

                    if(editMode) {
                        updateProfile(newProfile);
                        Toast.makeText(getApplicationContext(), "Profile updated successfully!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        saveProfile(newProfile);
                        Toast.makeText(getApplicationContext(), "Profile saved successfully!", Toast.LENGTH_LONG).show();
                    }
                    finish();
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

    public void saveProfile(final Profile profile) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final Date currentDate = new Date();
                profile.setDateRecord(currentDate);
                mDB.profileDao().insertProfile(profile);
            }
        });
    }

    public void updateProfile(final Profile profile) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDB.profileDao().updateProfile(profile);
            }
        });
    }

    public void initViews() {
        createProfileToolbar = (Toolbar) findViewById(R.id.createProfileToolbar);
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
    }

    private void populateViews() {
        Profile p = ProfileDetailActivity.mProfile;

        nameEditText.setText(p.getName());
        phoneEditText.setText(p.getPhone());
        if(p.getSexe().equals("F")) sexSpinner.setSelection(0);
        else sexSpinner.setSelection(1);
        neckEditText.setText(String.valueOf(p.getNeck()));
        chestEditText.setText(String.valueOf(p.getChest()));
        waistEditText.setText(String.valueOf(p.getWaist()));
        hipEditText.setText(String.valueOf(p.getHip()));
        seatEditText.setText(String.valueOf(p.getSeat()));
        shirtLengthEditText.setText(String.valueOf(p.getShirtLength()));
        halfShoulderEditText.setText(String.valueOf(p.getHalfShoulder()));
        shoulderWidthEditText.setText(String.valueOf(p.getShoulderWidth()));
        armLengthEditText.setText(String.valueOf(p.getArmLength()));
        bicepsEditText.setText(String.valueOf(p.getBiceps()));
        wristEditText.setText(String.valueOf(p.getWrist()));
        inseamEditText.setText(String.valueOf(p.getInseam()));
        coatSleeveLengthEditText.setText(String.valueOf(p.getCoatSleeveLength()));
        jacketLengthEditText.setText(String.valueOf(p.getJacketLength()));
        thighEditText.setText(String.valueOf(p.getThigh()));
        aboveKneeEditText.setText(String.valueOf(p.getAboveKnee()));
        belowKneeEditText.setText(String.valueOf(p.getBelowKnee()));
        calfEditText.setText(String.valueOf(p.getCalf()));
        ankleEditText.setText(String.valueOf(p.getAnkle()));
        crotchToKneeEditText.setText(String.valueOf(p.getCrotchToKnee()));
        kneeToCalfEditText.setText(String.valueOf(p.getKneeToCalf()));
        calfToAnkleEditText.setText(String.valueOf(p.getCalfToAnkle()));
        waistToAnkleEditText.setText(String.valueOf(p.getWaistToAnkle()));
    }
}
