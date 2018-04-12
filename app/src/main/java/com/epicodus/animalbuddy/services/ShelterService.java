package com.epicodus.animalbuddy.services;

import com.epicodus.animalbuddy.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by Guest on 4/12/18.
 */

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

}
