package com.cascade.recyclerviewdecorations.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by Salih Demir on 30.06.2017.
 */

public class DimenUtil {
    public static int convertDpToPx(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) (dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}