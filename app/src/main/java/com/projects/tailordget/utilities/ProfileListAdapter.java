package com.projects.tailordget.utilities;

import android.content.Context;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projects.tailordget.interfaces.ActionModeAdapterCallback;
import com.projects.tailordget.interfaces.ActionModeViewCallbacks;
import com.projects.tailordget.interfaces.ListProfileActionModeViewCallbacks;
import com.projects.tailordget.datas.Profile;
import com.projects.test.tailordget.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ProfileViewHolder>
        implements ActionModeAdapterCallback, ActionModeViewCallbacks, ListProfileActionModeViewCallbacks {

    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";

    // Member variable to handle item clicks
    private ItemClickListener mItemClickListener;
    private ItemLongClickListener mItemLongClickListener;
    private static List<Profile> mProfileList;
    private ArrayList<Integer> selectedList;
    private SparseBooleanArray selectedItemsIds;
    private Context mContext;
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    private ActionMode mActionMode;

    public ProfileListAdapter(Context context, ItemClickListener clickListener, ItemLongClickListener longClickListener) {
        mContext = context;
        mItemClickListener = clickListener;
        mItemLongClickListener = longClickListener;
        // For item selection
        selectedItemsIds = new SparseBooleanArray();
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

        holder.itemView.setActivated(selectedItemsIds.get(position));
    }

    @Override
    public int getItemCount() {
        if (mProfileList == null) {
            return 0;
        }
        return mProfileList.size();
    }

    private Profile getItem(int index) {
        return mProfileList.get(index);
    }

    private int getSelectedItemCount() {
        return selectedList.size();
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setProfiles(List<Profile> profileList) {
        mProfileList = profileList;
        notifyDataSetChanged();
    }

    public static List<Profile> getProfileList() {
        return mProfileList;
    }

    //<------------------------
    @Override
    public void toggleSelection(int position) {
        if (selectedItemsIds.get(position)) {
            selectedItemsIds.delete(position);
        } else {
            selectedItemsIds.put(position, true);
        }
        notifyItemChanged(position);
    }

    @Override
    public void clearSelections() {
        selectedItemsIds.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getSelectedCount() {
        return selectedItemsIds.size();
    }

    @Override
    public List<Profile> getSelectedItems() {
        final List<Profile> selectedItemList = new LinkedList<>();
        for (int i = 0; i < selectedItemsIds.size(); i++) {
            selectedItemList.add(mProfileList.get(selectedItemsIds.keyAt(i)));
        }
        return selectedItemList;
    }

    @Override
    public void onListItemSelect(int position) {

    }

    @Override
    public void onDestroyActionMode() {

    }

    @Override
    public void onDeleteActionClicked() {

    }

    @Override
    public void onFavoriteActionClicked() {

    }
//--------------------------->

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    public interface ItemLongClickListener {
        void onItemLongClickListener(int itemId);
    }

    // Inner class for creating ViewHolders
    class ProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

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
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClickListener(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            mItemLongClickListener.onItemLongClickListener(getAdapterPosition());
            return true;
        }
    }
}
