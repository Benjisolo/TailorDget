package com.projects.tailordget.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projects.tailordget.datas.Profile;
import com.projects.test.tailordget.R;

import java.util.List;

public class SimpleProfileAdapter extends RecyclerView.Adapter<SimpleProfileAdapter.ProfileViewHolder> {
    private static List<Profile> profileList;
    private Context mContext;

    public SimpleProfileAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.view_profile_list_simple, viewGroup, false);
        return new ProfileViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder profileViewHolder, int i) {
        Profile profile = profileList.get(i);
        String name = profile.getName();

        profileViewHolder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        if (profileList == null) {
            return 0;
        }
        return profileList.size();
    }

    private Profile getItem(int index) {
        return profileList.get(index);
    }

    public void setProfiles(List<Profile> profileList) {
        this.profileList = profileList;
        notifyDataSetChanged();
    }

    public static List<Profile> getProfileList() {
        return profileList;
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.profileNameTextView2);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
