package com.epicodus.animalbuddy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.animalbuddy.R;
import com.epicodus.animalbuddy.models.Pet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;



public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.PetViewHolder> {
    private ArrayList<Pet> mPets = new ArrayList<>();
    private Context mContext;

    public PetListAdapter(Context context, ArrayList<Pet> pets) {
        mContext = context;
        mPets = pets;
    }

    @Override
    public PetListAdapter.PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item, parent, false);
        PetViewHolder viewHolder = new PetViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PetListAdapter.PetViewHolder holder, int position) {
        holder.bindPet(mPets.get(position));
    }

    @Override
    public int getItemCount() {
        return mPets.size();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.petImageView) ImageView mPetImageView;
        @BindView(R.id.petNameTextView) TextView mNameTextView;
        @BindView(R.id.petAgeTextView) TextView mAgeTextView;


        private Context mContext;

        public PetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindPet(Pet pet) {
            mNameTextView.setText(pet.getName());
            mAgeTextView.setText(pet.getAge());
        }
    }
}
