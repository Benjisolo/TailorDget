package com.projects.test.tailordget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavoritesProfiles extends Fragment {

    private AppDatabase mDb;
    private static List<Profile> favoriteProfileList;
    private LinearLayout linearLeftWrapper, linearRightWrapper;
    private LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.favorites_profiles_fragment, container, false);

        mDb = AppDatabase.getInstance(view.getContext());
        favoriteProfileList = new ArrayList<>();

        linearLeftWrapper = (LinearLayout) view.findViewById(R.id.favoriteLinearLeft);
        linearRightWrapper = (LinearLayout) view.findViewById(R.id.favoriteLinearRight);

        return view;
    }

    public static List<Profile> getFavoriteProfileList() {
        return favoriteProfileList;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        linearLeftWrapper.removeAllViews();
                        linearRightWrapper.removeAllViews();
                        if(favoriteProfileList.size() > 0) {
                            for(int i=0; i<favoriteProfileList.size(); i++) {
                                LinearLayout wrapper;

                                if(i%2==0) wrapper = linearLeftWrapper;
                                else wrapper = linearRightWrapper;
                                addFavoriteView(wrapper, favoriteProfileList.get(i).getName(), favoriteProfileList.get(i).getPhone());
                            }
                        }
                    }
                });
            }
        });
    }

    public void addFavoriteView(final LinearLayout wrapper, final String name, String phone) {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(R.layout.favorite_list_layout, null);
        Toolbar tooblar = (Toolbar) v.findViewById(R.id.toolbar);
        tooblar.inflateMenu(R.menu.favorite_menu);
        tooblar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.removeFavoriteItem: {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                Profile profile = new Profile();
                                for (Profile p : favoriteProfileList) {
                                    if(p.getName().equals(name))
                                        profile = p;
                                }
                                profile.setFavorite(false);
                                mDb.profileDao().updateProfile(profile);

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                });
                            }
                        });
                        wrapper.removeView(v);
                    }
                }
                return true;
            }
        });
        ((TextView) v.findViewById(R.id.favoriteFirstLetterTextView)).setText(name.substring(0, 1).toUpperCase());
        ((TextView) v.findViewById(R.id.favoriteNameTextView)).setText(name);
        ((TextView) v.findViewById(R.id.favoritePhoneTextView)).setText(phone);
        wrapper.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
