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

        populateMensurationLabelList();
        populateMensurationValuesList();

        for(int i=0; i<mensurationValuesList.size(); i++) {
            if(i%2==0)
                addCardView(linearLeftWrapper, mensurationLabelList.get(i), mensurationValuesList.get(i));
            else
                addCardView(linearRightWrapper, mensurationLabelList.get(i), mensurationValuesList.get(i));
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void addCardView(LinearLayout wrapper, String label, String value) {
        View v = mensurationInflater.inflate(R.layout.mensuration_card_view, null);
        ((TextView) v.findViewById(R.id.mensurationLabel)).setText(label);
        ((TextView) v.findViewById(R.id.mensurationValue)).setText(value);
        wrapper.addView(v);
    }

    public void populateMensurationValuesList() {
        String neck = String.valueOf(FragmentAllProfiles.displayedProfile.getNeck());
        String chest = String.valueOf(FragmentAllProfiles.displayedProfile.getChest());
        String waist = String.valueOf(FragmentAllProfiles.displayedProfile.getWaist());
        String hip = String.valueOf(FragmentAllProfiles.displayedProfile.getHip());
        String seat = String.valueOf(FragmentAllProfiles.displayedProfile.getSeat());
        String shirtLength = String.valueOf(FragmentAllProfiles.displayedProfile.getShirtLength());
        String halfShoulder = String.valueOf(FragmentAllProfiles.displayedProfile.getHalfShoulder());
        String shoulderWidth = String.valueOf(FragmentAllProfiles.displayedProfile.getShoulderWidth());
        String armLength = String.valueOf(FragmentAllProfiles.displayedProfile.getArmLength());
        String biceps = String.valueOf(FragmentAllProfiles.displayedProfile.getBiceps());
        String wrist = String.valueOf(FragmentAllProfiles.displayedProfile.getWrist());
        String inseam = String.valueOf(FragmentAllProfiles.displayedProfile.getInseam());
        String coatSleeveLength = String.valueOf(FragmentAllProfiles.displayedProfile.getCoatSleeveLength());
        String jacketLength = String.valueOf(FragmentAllProfiles.displayedProfile.getJacketLength());
        String thigh = String.valueOf(FragmentAllProfiles.displayedProfile.getThigh());
        String aboveKnee = String.valueOf(FragmentAllProfiles.displayedProfile.getAboveKnee());
        String belowKnee = String.valueOf(FragmentAllProfiles.displayedProfile.getBelowKnee());
        String calf = String.valueOf(FragmentAllProfiles.displayedProfile.getCalf());
        String ankle = String.valueOf(FragmentAllProfiles.displayedProfile.getAnkle());
        String crotchToKnee = String.valueOf(FragmentAllProfiles.displayedProfile.getCrotchToKnee());
        String kneeToCalf = String.valueOf(FragmentAllProfiles.displayedProfile.getKneeToCalf());
        String calfToAnkle = String.valueOf(FragmentAllProfiles.displayedProfile.getCalfToAnkle());
        String waistToAnkle = String.valueOf(FragmentAllProfiles.displayedProfile.getWaistToAnkle());
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
