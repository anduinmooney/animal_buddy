package com.epicodus.animalbuddy.services;

import com.epicodus.animalbuddy.Constants;
import com.epicodus.animalbuddy.models.Shelter;

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


public class ShelterService {

    public static void findShelters(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.SHELTER_URL).newBuilder();
        urlBuilder.addQueryParameter("key", Constants.PET_KEY);
        urlBuilder.addQueryParameter(Constants.PET_LOCATION ,location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .header("Authorization", Constants.PET_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Shelter> processResults (Response response) {
        ArrayList<Shelter> shelterList = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            JSONObject shelterJSON = new JSONObject(jsonData);
            JSONArray shelterListJSON = shelterJSON.getJSONObject("petfinder").getJSONObject("shelters").getJSONArray("shelter");
            for (int i = 0; i < shelterListJSON.length(); i++) {
                JSONObject shelterObjectJSON = shelterListJSON.getJSONObject(i);
                String name = shelterObjectJSON.getJSONObject("name").getString("$t");
                String phone = shelterObjectJSON.getJSONObject("phone").getString("$t");
                String state = shelterObjectJSON.getJSONObject("state").getString("$t");
                String email = shelterObjectJSON.getJSONObject("email").getString("$t");
                String city = shelterObjectJSON.getJSONObject("city").getString("$t");
                String zip = shelterObjectJSON.getJSONObject("zip").getString("$t");

                Shelter shelter = new Shelter(name, phone, state, email, city, zip);
                shelterList.add(shelter);
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return shelterList;
    }

}
