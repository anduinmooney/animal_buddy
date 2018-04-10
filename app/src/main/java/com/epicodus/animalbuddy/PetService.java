package com.epicodus.animalbuddy;



import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Guest on 4/10/18.
 */

public class PetService {

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

}


