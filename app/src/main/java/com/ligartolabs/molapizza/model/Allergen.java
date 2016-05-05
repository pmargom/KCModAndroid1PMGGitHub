package com.ligartolabs.molapizza.model;

import java.io.Serializable;

/**
 * Created by pmargom on 17/4/16.
 */
public class Allergen implements Serializable {

    private String mName;
    private String mImage;
    private String mIcon;

    public Allergen(String name, String image, String icon) {
        this.mName = name;
        this.mImage = image;
        this.mIcon = icon;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmIcon() {
        return mIcon;
    }

    public void setmIcon(String mIcon) {
        this.mIcon = mIcon;
    }
}
