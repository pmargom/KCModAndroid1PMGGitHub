package com.ligartolabs.molapizza.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.LinkedList;

public class Dish implements Serializable {
    private double mId;
    private String mName;
    private double mPrice;
    private Drawable mPhoto;
    private LinkedList<String> mAllergens;


    public Dish(double id, String name, double price, Drawable photo, LinkedList<String> allergens) {
        mId = id;
        mName = name;
        mPrice = price;
        mPhoto = photo;
        mAllergens = allergens;
    }

    public double getId() {
        return mId;
    }

    public void setId(double id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public Drawable getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Drawable photo) {
        mPhoto = photo;
    }

    public LinkedList<String> getAllergens() {
        return mAllergens;
    }

    public void setmAllergens(LinkedList<String> mAllergens) {
        this.mAllergens = mAllergens;
    }
}
