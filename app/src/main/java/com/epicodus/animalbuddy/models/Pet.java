package com.epicodus.animalbuddy.models;

/**
 * Created by Guest on 4/10/18.
 */

public class Pet {
    private String name;
    private String age;
    private String imageUrl;
    
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
