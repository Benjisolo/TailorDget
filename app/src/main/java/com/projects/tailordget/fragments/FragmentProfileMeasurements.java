package com.projects.tailordget.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects.tailordget.activities.ProfileDetailActivity;
import com.projects.tailordget.utilities.AppExecutors;
import com.projects.test.tailordget.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentProfileMeasurements extends Fragment {

    private LinearLayout linearLeftWrapper, linearRightWrapper;
    private List<String> measurementValuesList;
    private List<String> measurementLabelList;
    private LayoutInflater measurementInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile_measurements, container, false);

        linearLeftWrapper = view.findViewById(R.id.linearLeft);
        linearRightWrapper = view.findViewById(R.id.linearRight);

        measurementInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        measurementLabelList = new ArrayList<>();
        measurementValuesList = new ArrayList<>();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        populateMeasurementLabelList();
        populateMeasurementValuesList();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<measurementValuesList.size(); i++) {
                            LinearLayout wrapper;

                            if(i%2==0) wrapper = linearLeftWrapper;
                            else wrapper = linearRightWrapper;

                            addCardView(wrapper, measurementLabelList.get(i), measurementValuesList.get(i));
                        }
                    }
                });
            }
        });
    }

    public void addCardView(LinearLayout wrapper, String label, String value) {
        View v = measurementInflater.inflate(R.layout.measurement_card_view, null);
        ((TextView) v.findViewById(R.id.measurementLabel)).setText(label);
        ((TextView) v.findViewById(R.id.measurementValue)).setText(value);
        wrapper.addView(v);
    }

    public void populateMeasurementValuesList() {
        String neck = String.valueOf(ProfileDetailActivity.mProfile.getNeck());
        String chest = String.valueOf(ProfileDetailActivity.mProfile.getChest());
        String waist = String.valueOf(ProfileDetailActivity.mProfile.getWaist());
        String hip = String.valueOf(ProfileDetailActivity.mProfile.getHip());
        String seat = String.valueOf(ProfileDetailActivity.mProfile.getSeat());
        String shirtLength = String.valueOf(ProfileDetailActivity.mProfile.getShirtLength());
        String halfShoulder = String.valueOf(ProfileDetailActivity.mProfile.getHalfShoulder());
        String shoulderWidth = String.valueOf(ProfileDetailActivity.mProfile.getShoulderWidth());
        String armLength = String.valueOf(ProfileDetailActivity.mProfile.getArmLength());
        String biceps = String.valueOf(ProfileDetailActivity.mProfile.getBiceps());
        String wrist = String.valueOf(ProfileDetailActivity.mProfile.getWrist());
        String inseam = String.valueOf(ProfileDetailActivity.mProfile.getInseam());
        String coatSleeveLength = String.valueOf(ProfileDetailActivity.mProfile.getCoatSleeveLength());
        String jacketLength = String.valueOf(ProfileDetailActivity.mProfile.getJacketLength());
        String thigh = String.valueOf(ProfileDetailActivity.mProfile.getThigh());
        String aboveKnee = String.valueOf(ProfileDetailActivity.mProfile.getAboveKnee());
        String belowKnee = String.valueOf(ProfileDetailActivity.mProfile.getBelowKnee());
        String calf = String.valueOf(ProfileDetailActivity.mProfile.getCalf());
        String ankle = String.valueOf(ProfileDetailActivity.mProfile.getAnkle());
        String crotchToKnee = String.valueOf(ProfileDetailActivity.mProfile.getCrotchToKnee());
        String kneeToCalf = String.valueOf(ProfileDetailActivity.mProfile.getKneeToCalf());
        String calfToAnkle = String.valueOf(ProfileDetailActivity.mProfile.getCalfToAnkle());
        String waistToAnkle = String.valueOf(ProfileDetailActivity.mProfile.getWaistToAnkle());
        measurementValuesList.add(neck);
        measurementValuesList.add(chest);
        measurementValuesList.add(waist);
        measurementValuesList.add(hip);
        measurementValuesList.add(seat);
        measurementValuesList.add(shirtLength);
        measurementValuesList.add(halfShoulder);
        measurementValuesList.add(shoulderWidth);
        measurementValuesList.add(armLength);
        measurementValuesList.add(biceps);
        measurementValuesList.add(wrist);
        measurementValuesList.add(inseam);
        measurementValuesList.add(coatSleeveLength);
        measurementValuesList.add(jacketLength);
        measurementValuesList.add(thigh);
        measurementValuesList.add(aboveKnee);
        measurementValuesList.add(belowKnee);
        measurementValuesList.add(calf);
        measurementValuesList.add(ankle);
        measurementValuesList.add(crotchToKnee);
        measurementValuesList.add(kneeToCalf);
        measurementValuesList.add(calfToAnkle);
        measurementValuesList.add(waistToAnkle);
    }

    public void populateMeasurementLabelList() {
        measurementLabelList.add(getString(R.string.label_neck));
        measurementLabelList.add(getString(R.string.label_chest));
        measurementLabelList.add(getString(R.string.label_waist));
        measurementLabelList.add(getString(R.string.label_hip));
        measurementLabelList.add(getString(R.string.label_seat));
        measurementLabelList.add(getString(R.string.label_shirt_length));
        measurementLabelList.add(getString(R.string.label_half_shoulder));
        measurementLabelList.add(getString(R.string.label_shoulder_width));
        measurementLabelList.add(getString(R.string.label_arm_length));
        measurementLabelList.add(getString(R.string.label_biceps));
        measurementLabelList.add(getString(R.string.label_wrist));
        measurementLabelList.add(getString(R.string.label_inseam));
        measurementLabelList.add(getString(R.string.label_coat_sleeve_length));
        measurementLabelList.add(getString(R.string.label_jacket_length));
        measurementLabelList.add(getString(R.string.label_thigh));
        measurementLabelList.add(getString(R.string.label_above_knee));
        measurementLabelList.add(getString(R.string.label_below_knee));
        measurementLabelList.add(getString(R.string.label_calf));
        measurementLabelList.add(getString(R.string.label_ankle));
        measurementLabelList.add(getString(R.string.label_crotch_to_knee));
        measurementLabelList.add(getString(R.string.label_knee_to_calf));
        measurementLabelList.add(getString(R.string.label_calf_to_ankle));
        measurementLabelList.add(getString(R.string.label_waist_to_ankle));
    }
}
