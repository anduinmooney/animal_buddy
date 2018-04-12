package com.epicodus.animalbuddy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.animalbuddy.R;
import com.epicodus.animalbuddy.adapters.ShelterListAdapter;
import com.epicodus.animalbuddy.models.Shelter;
import com.epicodus.animalbuddy.services.ShelterService;

import java.io.IOException;
import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ShelterActivity extends AppCompatActivity {

    public static final String TAG = ShelterActivity.class.getSimpleName();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private ShelterListAdapter mAdapter;

    public ArrayList<Shelter> shelters = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getShelters(location);
    }

    private void getShelters(String location) {
        final ShelterService shelterService = new ShelterService();
        ShelterService.findShelters(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                shelters = shelterService.processResults(response);

                ShelterActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new ShelterListAdapter(getApplicationContext(), shelters);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(ShelterActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        for (Shelter shelter : shelters) {
                            Log.d(TAG, "Name: " + shelter.getName());
                            Log.d(TAG, "Phone: " + shelter.getPhone());
                            Log.d(TAG, "Email: " + shelter.getEmail());
                            Log.d(TAG, "State: " + shelter.getState());
                            Log.d(TAG, "City: " + shelter.getCity());
                            Log.d(TAG, "Zip: " + shelter.getZip());
                        }

                    }
                });
            }
        });
    }
}
