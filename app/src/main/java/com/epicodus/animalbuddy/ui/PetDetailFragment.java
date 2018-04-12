package com.epicodus.animalbuddy.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.animalbuddy.R;
import com.epicodus.animalbuddy.models.Pet;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetDetailFragment extends Fragment {
    @BindView(R.id.petImageView) ImageView mImageLabel;
    @BindView(R.id.petNameTextView) TextView mNameLabel;
    @BindView(R.id.ageTextView) TextView mAgeLabel;


    private Pet mPet;

    public static PetDetailFragment newInstance(Pet pet) {
        PetDetailFragment petDetailFragment = new PetDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("pet", Parcels.wrap(pet));
        petDetailFragment.setArguments(args);
        return petDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPet = Parcels.unwrap(getArguments().getParcelable("pet"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mPet.getImageUrl()).into(mImageLabel);
        mNameLabel.setText(mPet.getName());
        mAgeLabel.setText(mPet.getAge());

        return view;
    }
}
