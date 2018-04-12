package com.epicodus.animalbuddy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.animalbuddy.R;
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

    public ArrayList<Shelter> shelters = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);
        ButterKnife.bind(this);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, shelter);
//        mListView.setAdapter(adapter);


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");


        getShelters(location);
    }

    private void getShelters(String location) {
        final ShelterService shelterService = new ShelterService();
        shelterService.findShelters(location, new Callback() {

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
                        String[] shelterNames = new String[shelters.size()];
                        for (int i = 0; i < shelterNames.length; i++) {
                            shelterNames[i] = shelters.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(ShelterActivity.this,
                                android.R.layout.simple_list_item_1, shelterNames);

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
