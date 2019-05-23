package com.projects.test.tailordget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ProfileViewHolder> {

    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";

    // Member variable to handle item clicks
    private ItemClickListener mItemClickListener;
    // Class variables for the List that holds task data and the Context
    private List<Profile> mProfileList;
    private Context mContext;
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public ProfileListAdapter(Context context) {
        mContext = context;
//        mItemClickListener = listener;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.profile_list_layout, parent, false);

        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        // Determine the values of the wanted data
        Profile profile = mProfileList.get(position);
        String name = profile.getName();
        String sexe = profile.getSexe();
        String dateRecord = dateFormat.format(profile.getDateRecord());

        //Set values
        holder.profileNameTextView.setText(name);
        holder.profileSexeTextView.setText(sexe);
        holder.dateRecordTextView.setText(dateRecord);

        // Programmatically set the text and color for the priority TextView
//        String priorityString = "" + priority; // converts int to String
//        holder.priorityView.setText(priorityString);
//
//        GradientDrawable priorityCircle = (GradientDrawable) holder.priorityView.getBackground();
//        // Get the appropriate background color based on the priority
//        int priorityColor = getPriorityColor(priority);
//        priorityCircle.setColor(priorityColor);
    }

    @Override
    public int getItemCount() {
        if (mProfileList == null) {
            return 0;
        }
        return mProfileList.size();
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setProfiles(List<Profile> profileList) {
        mProfileList = profileList;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    // Inner class for creating ViewHolders
    class ProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Class variables for the Profile description and priority TextViews
        TextView profileNameTextView;
        TextView profileSexeTextView;
        TextView dateRecordTextView;

        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public ProfileViewHolder(View itemView) {
            super(itemView);

            profileNameTextView = (TextView) itemView.findViewById(R.id.profileNameTextView);
            profileSexeTextView = (TextView) itemView.findViewById(R.id.profileSexe);
            dateRecordTextView = (TextView) itemView.findViewById(R.id.dateRecordProfileList);
//            itemView.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if(listener != null) {
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION) {
//                            listener.onItemClickListener(position);
//                        }
//                    }
                }
            });
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClickListener(getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }
}
