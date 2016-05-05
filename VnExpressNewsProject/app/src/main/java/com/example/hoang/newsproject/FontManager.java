package com.example.hoang.newsproject;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by hoang on 3/21/2016.
 */
public class FontManager {
    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}
