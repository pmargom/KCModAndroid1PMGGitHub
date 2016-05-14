package com.ligartolabs.molapizza.global;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Utils {

    public Utils() {
    }

    public String formatMoney(double amount) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return String.format("€%s", formatter.format(amount));
    }

    public Drawable buildDrawableForName(String imageName, Context context) {

        Resources resources = context.getApplicationContext().getResources();
        String packageName = context.getApplicationContext().getPackageName();

        String uri = "@drawable/" + imageName.replace(".png", "").replace(".jpg", "");
        int imageResource = resources.getIdentifier(uri, null, packageName);
        Drawable resPhoto = resources.getDrawable(imageResource);
        return resPhoto;
    }
}
