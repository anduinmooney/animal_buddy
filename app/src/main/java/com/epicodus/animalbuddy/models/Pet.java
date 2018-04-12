package com.epicodus.animalbuddy.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 4/10/18.
 */

@Parcel
public class Pet {
    private String name;
    private String age;
    private String imageUrl;

    public Pet() {}

    public Pet(String name, String age, String imageUrl) {
        this.name = name;
        this.age = age;
        this.imageUrl = imageUrl;

    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
