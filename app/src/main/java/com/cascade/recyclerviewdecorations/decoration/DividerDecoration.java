package com.cascade.recyclerviewdecorations.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.cascade.recyclerviewdecorations.util.DimenUtil;

/**
 * Created by Salih Demir on 21.06.2017.
 */

public class DividerDecoration extends RecyclerView.ItemDecoration {
    private static final int DEFAULT_DIVIDER_SIZE_IN_DP = 1;
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private Drawable dividerDrawable;
    private int orientation;

    private final Rect currentBounds;
    private final Rect marginBounds;

    public DividerDecoration(Context context, int orientation) {
        this(context, orientation, null);
    }

    public DividerDecoration(Context context, int orientation, Rect marginBounds) {
        if (marginBounds == null)
            marginBounds = new Rect();

        this.currentBounds = new Rect();
        this.marginBounds = marginBounds;

        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        dividerDrawable = typedArray.getDrawable(0);
        typedArray.recycle();

        setOrientation(orientation);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null || dividerDrawable == null)
            return;

        if (orientation == VERTICAL)
            drawVertical(c, parent);
        else
            drawHorizontal(c, parent);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (dividerDrawable == null)
            return;

        Rect targetBounds = new Rect();
        targetBounds.top += marginBounds.top;
        targetBounds.bottom += marginBounds.bottom;
        targetBounds.left += marginBounds.left;
        targetBounds.right += marginBounds.right;

        if (dividerDrawable instanceof ColorDrawable) {
            if (orientation == VERTICAL)
                targetBounds.bottom += getDividerSize();
            else
                targetBounds.right += getDividerSize();
        } else {
            if (orientation == VERTICAL)
                targetBounds.bottom += dividerDrawable.getIntrinsicHeight();
            else
                targetBounds.right += dividerDrawable.getIntrinsicWidth();
        }

        outRect.set(targetBounds);
    }

    private int getDividerSize() {
        return DimenUtil.convertDpToPx(DEFAULT_DIVIDER_SIZE_IN_DP);
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int left;
        int right;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        left += marginBounds.left;
        right -= marginBounds.right;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, currentBounds);

            final int bottom = currentBounds.bottom + Math.round(child.getTranslationY());
            int top;
            if (dividerDrawable instanceof ColorDrawable)
                top = bottom - getDividerSize();
            else
                top = bottom - dividerDrawable.getIntrinsicHeight();

            dividerDrawable.setBounds(left, top, right, bottom);
            dividerDrawable.draw(canvas);
        }
        canvas.restore();
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int top;
        int bottom;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        top += marginBounds.top;
        bottom -= marginBounds.bottom;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, currentBounds);

            final int right = currentBounds.right + Math.round(child.getTranslationX());
            int left;
            if (dividerDrawable instanceof ColorDrawable)
                left = right - getDividerSize();
            else
                left = right - dividerDrawable.getIntrinsicWidth();

            dividerDrawable.setBounds(left, top, right, bottom);
            dividerDrawable.draw(canvas);
        }
        canvas.restore();
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.orientation = orientation;
    }

    public void setDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        dividerDrawable = drawable;
    }
}