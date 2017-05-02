package com.deepshooter.retrofitdemo.customs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.deepshooter.retrofitdemo.R;


public class SnackBar {

    public static final int LENGTH_SHORT = Snackbar.LENGTH_SHORT;

    public static Snackbar makeText(Context context, int resId, int duration) {
        Activity activity = (Activity) context;
        View layout;
        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), context.getResources().getText(resId), duration);
        layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        android.widget.TextView text = (android.widget.TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        text.setTextColor(context.getResources().getColor(R.color.white));
        Typeface font = null;
        if (readLocale(context).equals("pa")) {
            font = Typeface.createFromAsset(context.getAssets(), "AllerBold.ttf");
        } else {
            font = Typeface.createFromAsset(context.getAssets(), "AllerBold.ttf");
        }
        if (text != null) {
            text.setMaxLines(3);
        }
        text.setTypeface(font);
        return snackbar;

    }

    public static Snackbar makeText(Context context, String message, int duration) {
        Activity activity = (Activity) context;
        View layout;
        Snackbar snackbar = Snackbar
                .make(activity.findViewById(android.R.id.content), message, duration);
        layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        android.widget.TextView text = (android.widget.TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        text.setTextColor(context.getResources().getColor(R.color.white));
        Typeface font = null;
        if (text != null) {
            text.setMaxLines(3);
        }
        if (readLocale(context).equals("pa")) {
            font = Typeface.createFromAsset(context.getAssets(), "AllerBold.ttf");
        } else {
            font = Typeface.createFromAsset(context.getAssets(), "AllerBold.ttf");
        }
        text.setTypeface(font);
        return snackbar;

    }

    public static Snackbar makeText(Context context, View view, String message, int duration) {
        View layout;
        Snackbar snackbar = Snackbar
                .make(view, message, duration);
        layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        android.widget.TextView text = (android.widget.TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        text.setTextColor(context.getResources().getColor(R.color.white));
        Typeface font = null;
        if (readLocale(context).equals("pa")) {
            font = Typeface.createFromAsset(context.getAssets(), "AllerBold.ttf");
        } else {
            font = Typeface.createFromAsset(context.getAssets(), "AllerBold.ttf");
        }
        if (text != null) {
            text.setMaxLines(3);
        }
        text.setTypeface(font);
        return snackbar;

    }

    public static Snackbar makeText(Context context, View view, int resId, int duration) {
        View layout;
        Snackbar snackbar = Snackbar
                .make(view, context.getResources().getText(resId), duration);
        layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        android.widget.TextView text = (android.widget.TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        text.setTextColor(context.getResources().getColor(R.color.white));
        Typeface font = null;
        if (readLocale(context).equals("pa")) {
            font = Typeface.createFromAsset(context.getAssets(), "AllerBold.ttf");
        } else {
            font = Typeface.createFromAsset(context.getAssets(), "AllerBold.ttf");
        }
        if (text != null) {
            text.setMaxLines(3);
        }
        text.setTypeface(font);
        return snackbar;

    }

    private static String readLocale(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences("Locale", mContext.MODE_PRIVATE);
        return sp.getString("Language", "en");
    }
}
