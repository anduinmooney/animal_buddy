package com.epicodus.animalbuddy.services;



import android.support.v7.app.AppCompatActivity;

import com.epicodus.animalbuddy.Constants;
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

    public static void findPets(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PET_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.PET_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .header("Authorization", Constants.PET_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Pet> processResults(Response response) {
        ArrayList<Pet> pets = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject petFinderJSON = new JSONObject(jsonData);
            JSONArray dataJSON = petFinderJSON.getJSONObject("petfinder").getJSONObject("pets").getJSONArray("pet");
            for (int i = 0; i < dataJSON.length(); i++) {
                JSONObject petJSON = dataJSON.getJSONObject(i);
                String name = petJSON.getJSONObject("name").getString("$t");
//                String age = petJSON.getJSONObject("pets").getJSONArray("pet").getJSONObject(0).getJSONObject("age").getString("$t");
//                String imageUrl = petJSON.getJSONObject("pets").getJSONArray("pet").getJSONObject(0).getJSONObject("media").getJSONObject("photos").getJSONArray("photo").getJSONObject(0).getString("$t");

                Pet pet = new Pet(name);
                pets.add(pet);
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return pets;
    }

}


