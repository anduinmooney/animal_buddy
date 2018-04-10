package com.epicodus.animalbuddy;

/**
 * Created by Guest on 4/10/18.
 */

public class Constants {
    public static final String PET_TOKEN = BuildConfig.PET_TOKEN;
    public static final String PET_BASE_URL = ("http://api.petfinder.com/pet.find?key=" + PET_TOKEN + "&animal=cat&location=97203&output=basic&format=json&callback=?");
    public static final String PET_LOCATION_QUERY_PARAMETER = "location";
}
