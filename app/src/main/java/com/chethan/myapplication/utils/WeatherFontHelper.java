package com.chethan.myapplication.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;


public class WeatherFontHelper {

    private static Typeface typeface = null;

    public static void setWeatherTypeface(Context context, TextView textview) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/weather.ttf");
        }
        textview.setTypeface(typeface);
    }
}
