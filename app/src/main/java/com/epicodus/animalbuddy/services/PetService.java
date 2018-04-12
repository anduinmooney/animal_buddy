package com.epicodus.animalbuddy.services;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.epicodus.animalbuddy.Constants;
import com.epicodus.animalbuddy.R;
import com.epicodus.animalbuddy.models.Pet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class PetService extends AppCompatActivity {
    public static final String TAG = PetService.class.getSimpleName();
    public static void findPets(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PET_URL).newBuilder();
        urlBuilder.addQueryParameter("key", Constants.PET_KEY);
        urlBuilder.addQueryParameter("animal", "dog");
        urlBuilder.addQueryParameter(Constants.PET_LOCATION ,location);
        urlBuilder.addQueryParameter("output", "full");
        urlBuilder.addQueryParameter("format", "json");
        String url = urlBuilder.build().toString();
        Log.v(TAG, url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
    }

    public ArrayList<Pet> processResults (Response response) {
        ArrayList<Pet> petList = new ArrayList<>();

        try {
            String jsonData = response.body().string();

                JSONObject petJSON = new JSONObject(jsonData);
                JSONArray petListJSON = petJSON.getJSONObject("petfinder").getJSONObject("pets").getJSONArray("pet");
                for (int i = 0; i < petListJSON.length(); i++) {
                    JSONObject petObjectJSON = petListJSON.getJSONObject(i);
                    String name = petObjectJSON.getJSONObject("name").getString("$t");
                    String age = petObjectJSON.getJSONObject("age").getString("$t");
                    String imageUrl = petObjectJSON.getJSONObject("media").getJSONObject("photos").getJSONArray("photo").getJSONObject(2).getString("$t");

                    Pet pet = new Pet(name, age, imageUrl);
                    petList.add(pet);
                }
        }

        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return petList;
    }

}


