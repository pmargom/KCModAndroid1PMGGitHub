package com.ligartolabs.molapizza.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.LinkedList;

public class Dish implements Serializable {
    private double mId;
    private String mName;
    private double mPrice;
    private String mPhoto;
    private LinkedList<String> mAllergens;
    private int mQuantity;

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = quantity;
    }

    public Dish(double id, String name, double price, String photo, LinkedList<String> allergens, int quantity) {
        mId = id;
        mName = name;
        mPrice = price;
        mPhoto = photo;
        mAllergens = allergens;
        mQuantity = quantity;

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

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    public LinkedList<String> getAllergens() {
        return mAllergens;
    }

    public void setmAllergens(LinkedList<String> mAllergens) {
        this.mAllergens = mAllergens;
    }
}
