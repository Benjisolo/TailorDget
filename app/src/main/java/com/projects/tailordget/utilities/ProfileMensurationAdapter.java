package com.projects.tailordget.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projects.test.tailordget.R;

public class ProfileMensurationAdapter extends RecyclerView.Adapter<ProfileMensurationAdapter.MensurationViewHolder> {

    private Context mContext;
    private Object[][] mensurationList;

    public ProfileMensurationAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MensurationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.profile_list_layout, viewGroup, false);

        return new MensurationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MensurationViewHolder mensurationViewHolder, int i) {
        String label = null;
        String dimension = null;

        mensurationViewHolder.label.setText(label);
        mensurationViewHolder.dimension.setText(dimension);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MensurationViewHolder extends RecyclerView.ViewHolder {
        TextView label, dimension, unit;

        public MensurationViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
