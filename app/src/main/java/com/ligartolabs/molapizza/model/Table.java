package com.ligartolabs.molapizza.model;

import com.ligartolabs.molapizza.global.Constants;

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

    private double calculateBill() {
        if (mDishes == null || mDishes.size() == 0) return 0;

        double accumulateValue = 0;
        for (Dish dish: mDishes) {
            accumulateValue += dish.getPrice() * dish.getQuantity();
        }
        return accumulateValue;
    }

    public Table(int id, LinkedList<Dish> dishes, boolean billStatus) {
        this.mId = id;
        this.mDishes = dishes;
        this.mBillStatus = billStatus;
        this.mBill = calculateBill();
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
        return String.format("Mesa nº - %d: Paid: %s -> Bill: %s", mId, mBillStatus ? "YES" : "NO ", Constants.formatMoney(mBill));
    }
}
