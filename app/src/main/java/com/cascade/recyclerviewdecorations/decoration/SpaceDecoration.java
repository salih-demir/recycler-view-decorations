package com.cascade.recyclerviewdecorations.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Salih Demir on 25.07.2017.
 */

public class SpaceDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;
    private static final float DEFAULT_SIZE_IN_DP = 15;

    private int spaceSizeInPx;
    private int orientation;

    public SpaceDecoration(Context context, int orientation) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        spaceSizeInPx = (int) (DEFAULT_SIZE_IN_DP * metrics.density);
        setOrientation(orientation);
    }

    public SpaceDecoration(Context context, int orientation, int spaceSizeInPx) {
        this.spaceSizeInPx = spaceSizeInPx;
        setOrientation(orientation);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (orientation == VERTICAL)
            outRect.set(0, 0, 0, spaceSizeInPx);
        else
            outRect.set(0, 0, spaceSizeInPx, 0);
    }

    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL)
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL.");
        this.orientation = orientation;
    }
}