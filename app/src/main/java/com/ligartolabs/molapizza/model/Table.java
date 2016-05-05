package com.ligartolabs.molapizza.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Table implements Serializable {
    private int mId;
    private String mName;
    private LinkedList<Dish> mDishes;

    public Table(int id, String name) {
        mId = id;
        mName = name;
    }

    public Table(int id, String name, LinkedList<Dish> Dishes) {
        mId = id;
        mName = name;
        mDishes = Dishes;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }

    @Override
    public String toString() {
        return getName();
    }
}
