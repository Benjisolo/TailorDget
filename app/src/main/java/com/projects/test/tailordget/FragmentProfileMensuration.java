package com.projects.test.tailordget;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentProfileMensuration extends Fragment {

    private LinearLayout linearLeftWrapper, linearRightWrapper;
    private List<String> mensurationValuesList;
    private List<String> mensurationLabelList;
    private LayoutInflater mensurationInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile_mensuration, container, false);

        linearLeftWrapper = view.findViewById(R.id.linearLeft);
        linearRightWrapper = view.findViewById(R.id.linearRight);

        mensurationInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mensurationLabelList = new ArrayList<>();
        mensurationValuesList = new ArrayList<>();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        populateMensurationLabelList();
        populateMensurationValuesList();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<mensurationValuesList.size(); i++) {
                            LinearLayout wrapper;

                            if(i%2==0) wrapper = linearLeftWrapper;
                            else wrapper = linearRightWrapper;

                            addCardView(wrapper, mensurationLabelList.get(i), mensurationValuesList.get(i));
                        }
                    }
                });
            }
        });
    }

    public void addCardView(LinearLayout wrapper, String label, String value) {
        View v = mensurationInflater.inflate(R.layout.mensuration_card_view, null);
        ((TextView) v.findViewById(R.id.mensurationLabel)).setText(label);
        ((TextView) v.findViewById(R.id.mensurationValue)).setText(value);
        wrapper.addView(v);
    }

    public void populateMensurationValuesList() {
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
        mensurationValuesList.add(neck);
        mensurationValuesList.add(chest);
        mensurationValuesList.add(waist);
        mensurationValuesList.add(hip);
        mensurationValuesList.add(seat);
        mensurationValuesList.add(shirtLength);
        mensurationValuesList.add(halfShoulder);
        mensurationValuesList.add(shoulderWidth);
        mensurationValuesList.add(armLength);
        mensurationValuesList.add(biceps);
        mensurationValuesList.add(wrist);
        mensurationValuesList.add(inseam);
        mensurationValuesList.add(coatSleeveLength);
        mensurationValuesList.add(jacketLength);
        mensurationValuesList.add(thigh);
        mensurationValuesList.add(aboveKnee);
        mensurationValuesList.add(belowKnee);
        mensurationValuesList.add(calf);
        mensurationValuesList.add(ankle);
        mensurationValuesList.add(crotchToKnee);
        mensurationValuesList.add(kneeToCalf);
        mensurationValuesList.add(calfToAnkle);
        mensurationValuesList.add(waistToAnkle);
    }

    public void populateMensurationLabelList() {
        mensurationLabelList.add(getString(R.string.label_neck));
        mensurationLabelList.add(getString(R.string.label_chest));
        mensurationLabelList.add(getString(R.string.label_waist));
        mensurationLabelList.add(getString(R.string.label_hip));
        mensurationLabelList.add(getString(R.string.label_seat));
        mensurationLabelList.add(getString(R.string.label_shirt_length));
        mensurationLabelList.add(getString(R.string.label_half_shoulder));
        mensurationLabelList.add(getString(R.string.label_shoulder_width));
        mensurationLabelList.add(getString(R.string.label_arm_length));
        mensurationLabelList.add(getString(R.string.label_biceps));
        mensurationLabelList.add(getString(R.string.label_wrist));
        mensurationLabelList.add(getString(R.string.label_inseam));
        mensurationLabelList.add(getString(R.string.label_coat_sleeve_length));
        mensurationLabelList.add(getString(R.string.label_jacket_length));
        mensurationLabelList.add(getString(R.string.label_thigh));
        mensurationLabelList.add(getString(R.string.label_above_knee));
        mensurationLabelList.add(getString(R.string.label_below_knee));
        mensurationLabelList.add(getString(R.string.label_calf));
        mensurationLabelList.add(getString(R.string.label_ankle));
        mensurationLabelList.add(getString(R.string.label_crotch_to_knee));
        mensurationLabelList.add(getString(R.string.label_knee_to_calf));
        mensurationLabelList.add(getString(R.string.label_calf_to_ankle));
        mensurationLabelList.add(getString(R.string.label_waist_to_ankle));
    }
}
