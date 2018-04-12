package com.epicodus.animalbuddy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.animalbuddy.R;
import com.epicodus.animalbuddy.models.Shelter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Guest on 4/12/18.
 */

public class ShelterListAdapter extends RecyclerView.Adapter<ShelterListAdapter.ShelterViewHolder> {

    private ArrayList<Shelter> mShelters = new ArrayList<>();
    private Context mContext;

    public ShelterListAdapter(Context context, ArrayList<Shelter> shelters) {
        mContext = context;
        mShelters = shelters;
    }

    @Override
    public ShelterListAdapter.ShelterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shelter_list_item, parent, false);
        ShelterViewHolder viewHolder = new ShelterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShelterListAdapter.ShelterViewHolder holder, int position) {
        holder.bindShelter(mShelters.get(position));
    }

    @Override
    public int getItemCount() {
        return mShelters.size();
    }

    public class ShelterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nameTextView) TextView mNameTextView;
        @BindView(R.id.phoneTextView) TextView mPhoneTextView;
        @BindView(R.id.emailTextView) TextView mEmailTextView;
        @BindView(R.id.cityTextView) TextView mCityTextView;

        private Context mContext;

        public ShelterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindShelter(Shelter shelter) {
            mNameTextView.setText(shelter.getName());
            mPhoneTextView.setText(shelter.getPhone());
            mEmailTextView.setText(shelter.getEmail());
            mCityTextView.setText(shelter.getCity());

        }
    }

}
