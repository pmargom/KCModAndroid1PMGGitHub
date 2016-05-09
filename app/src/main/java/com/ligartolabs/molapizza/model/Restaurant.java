package com.ligartolabs.molapizza.model;

import java.util.LinkedList;

public class Restaurant {

    private LinkedList<Table> mTables;
    private LinkedList<Dish> mDishes;

    public Restaurant() {
        this.mTables = new LinkedList<>();
        this.mDishes = new LinkedList<>();
    }

    public static Restaurant instance;

    public static Restaurant getInstance() {

        if (instance == null) {
            instance = new Restaurant();
        }

        return instance;
    }

    public void addNewTable(Table table) {

        mTables.add(table);

    }

    public LinkedList<Table> getTables() {
        return mTables;
    }

    public void setTables(LinkedList<Table> Tables) {
        this.mTables = Tables;
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(LinkedList<Dish> Dishes) {
        this.mDishes = mDishes;
    }

    public Table getTable(int position) {

        return mTables.get(position);

    }

}
