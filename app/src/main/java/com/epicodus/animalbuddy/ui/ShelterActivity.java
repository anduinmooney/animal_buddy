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
import com.epicodus.animalbuddy.services.ShelterService;

import java.io.IOException;


import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ShelterActivity extends AppCompatActivity {

    public static final String TAG = ShelterActivity.class.getSimpleName();



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
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
