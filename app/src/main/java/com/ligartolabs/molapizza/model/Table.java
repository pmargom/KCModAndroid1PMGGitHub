package com.ligartolabs.molapizza.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Table implements Serializable {
    private int mId;
    private LinkedList<Dish> mDishes;
    private boolean mBillStatus;
    private double mBill;

    public double getBill() {
        return mBill;
    }

    public void setBill(double bill) {
        this.mBill = bill;
    }

    public Table(int id, LinkedList<Dish> dishes, boolean billStatus, double bill) {
        this.mId = id;
        this.mDishes = dishes;
        this.mBillStatus = billStatus;
        this.mBill = bill;
    }

    public boolean getBuildStatus() {
        return mBillStatus;
    }

    public void setBuildStatus(boolean buildStatus) {
        this.mBillStatus = buildStatus;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }

    @Override
    public String toString() {
        return String.format("Mesa nº - %d: pagado: %s", mId, mBillStatus ? "SI" : "NO");
    }
}
