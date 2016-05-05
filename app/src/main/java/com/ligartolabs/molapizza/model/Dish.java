package com.ligartolabs.molapizza.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Dish implements Serializable {
    private float mId;
    private String mName;
    private float mPrice;
    private int mPhoto;
    private LinkedList<Allergen> mAllergens;


    public Dish(float id, String name, float price, int photo, LinkedList<Allergen> allergens) {
        mId = id;
        mName = name;
        mPrice = price;
        mPhoto = photo;
        mAllergens = allergens;
    }

    public float getId() {
        return mId;
    }

    public void setId(float id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public int getPhoto() {
        return mPhoto;
    }

    public void setPhoto(int photo) {
        mPhoto = photo;
    }

    public LinkedList<Allergen> getmAllergens() {
        return mAllergens;
    }

    public void setmAllergens(LinkedList<Allergen> mAllergens) {
        this.mAllergens = mAllergens;
    }
}
