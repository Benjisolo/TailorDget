package com.projects.tailordget.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.projects.tailordget.datas.AppDatabase;
import com.projects.tailordget.utilities.AppExecutors;
import com.projects.tailordget.datas.Profile;
import com.projects.test.tailordget.R;

import java.util.ArrayList;
import java.util.Date;

public class CreateProfileActivity extends AppCompatActivity {

    public static final String EDIT_MODE = "editMode";
    private static final String LOG_TAG = CreateProfileActivity.class.getSimpleName();

    private AppDatabase mDB;
    private Toolbar createProfileToolbar;
    private EditText nameEditText, phoneEditText, neckEditText, chestEditText, waistEditText,
            hipEditText, seatEditText, shirtLengthEditText, halfShoulderEditText,
            shoulderWidthEditText, armLengthEditText, bicepsEditText, wristEditText,
            inseamEditText, coatSleeveLengthEditText, jacketLengthEditText, thighEditText,
            aboveKneeEditText, belowKneeEditText, calfEditText, ankleEditText, crotchToKneeEditText,
            kneeToCalfEditText, calfToAnkleEditText, waistToAnkleEditText;
    private Spinner sexSpinner;

    private Profile targetProfile = ProfileDetailActivity.mProfile;

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
                    if(editMode) {
                        updateProfile();
                        Toast.makeText(getApplicationContext(), "Profile updated successfully!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        saveProfile();
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

    private void initProfileInfos(Profile newProfile) {
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
    }

    private float checkMensurationEntry(String entry) {
        return (entry.equals(""))? 0 : Float.parseFloat(entry);
    }

    public void saveProfile() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final Profile newProfile = new Profile();
                final Date currentDate = new Date();
                initProfileInfos(newProfile);
                newProfile.setDateRecord(currentDate);
                mDB.profileDao().insertProfile(newProfile);
            }
        });
    }

    public void updateProfile() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<Profile> list = (ArrayList) mDB.profileDao().loadAllProfiles();
                for (Profile p : list) {
                    if(p.getName().equals(targetProfile.getName()))
                        targetProfile = p;
                }
                initProfileInfos(targetProfile);
                mDB.profileDao().updateProfile(targetProfile);
                resetTargetProfile(targetProfile);
            }
        });
    }

    private void resetTargetProfile(Profile targetProfile) {
        Intent openDetailView = new Intent(getApplicationContext(), ProfileDetailActivity.class);
        ProfileDetailActivity.mProfile = targetProfile;
        startActivity(openDetailView);
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
        nameEditText.setText(targetProfile.getName());
        phoneEditText.setText(targetProfile.getPhone());
        if(targetProfile.getSexe().equals("F")) sexSpinner.setSelection(0);
        else sexSpinner.setSelection(1);
        neckEditText.setText(String.valueOf(targetProfile.getNeck()));
        chestEditText.setText(String.valueOf(targetProfile.getChest()));
        waistEditText.setText(String.valueOf(targetProfile.getWaist()));
        hipEditText.setText(String.valueOf(targetProfile.getHip()));
        seatEditText.setText(String.valueOf(targetProfile.getSeat()));
        shirtLengthEditText.setText(String.valueOf(targetProfile.getShirtLength()));
        halfShoulderEditText.setText(String.valueOf(targetProfile.getHalfShoulder()));
        shoulderWidthEditText.setText(String.valueOf(targetProfile.getShoulderWidth()));
        armLengthEditText.setText(String.valueOf(targetProfile.getArmLength()));
        bicepsEditText.setText(String.valueOf(targetProfile.getBiceps()));
        wristEditText.setText(String.valueOf(targetProfile.getWrist()));
        inseamEditText.setText(String.valueOf(targetProfile.getInseam()));
        coatSleeveLengthEditText.setText(String.valueOf(targetProfile.getCoatSleeveLength()));
        jacketLengthEditText.setText(String.valueOf(targetProfile.getJacketLength()));
        thighEditText.setText(String.valueOf(targetProfile.getThigh()));
        aboveKneeEditText.setText(String.valueOf(targetProfile.getAboveKnee()));
        belowKneeEditText.setText(String.valueOf(targetProfile.getBelowKnee()));
        calfEditText.setText(String.valueOf(targetProfile.getCalf()));
        ankleEditText.setText(String.valueOf(targetProfile.getAnkle()));
        crotchToKneeEditText.setText(String.valueOf(targetProfile.getCrotchToKnee()));
        kneeToCalfEditText.setText(String.valueOf(targetProfile.getKneeToCalf()));
        calfToAnkleEditText.setText(String.valueOf(targetProfile.getCalfToAnkle()));
        waistToAnkleEditText.setText(String.valueOf(targetProfile.getWaistToAnkle()));
    }
}
