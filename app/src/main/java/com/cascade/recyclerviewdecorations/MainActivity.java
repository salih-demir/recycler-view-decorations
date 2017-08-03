package com.cascade.recyclerviewdecorations;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cascade.recyclerviewdecorations.adapter.Car;
import com.cascade.recyclerviewdecorations.adapter.CarAdapter;
import com.cascade.recyclerviewdecorations.decoration.DividerDecoration;
import com.cascade.recyclerviewdecorations.decoration.MarginDecoration;
import com.cascade.recyclerviewdecorations.decoration.ShadowDecoration;
import com.cascade.recyclerviewdecorations.decoration.SpaceDecoration;
import com.cascade.recyclerviewdecorations.util.DimenUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int SPACE_SIZE_IN_PX = DimenUtil.convertDpToPx(16);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeShadowDecorations();
        initializeSpaceDecorations();
        initializeMarginDecorations();
        initializeDividerDecorations();
    }

    private void initializeRecyclerView(@IdRes int recyclerViewId, RecyclerView.ItemDecoration itemDecoration, int layoutOrientation) {
        RecyclerView recyclerView = findViewById(recyclerViewId);
        recyclerView.addItemDecoration(itemDecoration);
        initializeRecyclerViewData(recyclerView, layoutOrientation);
    }

    private void initializeRecyclerViewData(RecyclerView recyclerView, int layoutOrientation) {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Renault", "Megane"));
        carList.add(new Car("Renault", "Symbol"));
        carList.add(new Car("Audi", "A3"));
        carList.add(new Car("Nissan", "Micra"));
        carList.add(new Car("Toyota", "Corolla"));

        CarAdapter carAdapter = new CarAdapter(carList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, layoutOrientation, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(carAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void initializeShadowDecorations() {
        ColorDrawable colorDrawable = new ColorDrawable(Color.GREEN);

        ShadowDecoration shadowDecorationVertical = new ShadowDecoration(this, SpaceDecoration.VERTICAL);
        ShadowDecoration shadowDecorationHorizontal = new ShadowDecoration(this, SpaceDecoration.HORIZONTAL);
        ShadowDecoration shadowDecorationWithDrawable = new ShadowDecoration(this, SpaceDecoration.VERTICAL, colorDrawable, colorDrawable);

        initializeRecyclerView(R.id.rv_shadow_decoration_vertical, shadowDecorationVertical, LinearLayoutManager.VERTICAL);
        initializeRecyclerView(R.id.rv_shadow_decoration_horizontal, shadowDecorationHorizontal, LinearLayoutManager.HORIZONTAL);
        initializeRecyclerView(R.id.rv_shadow_decoration_with_drawable, shadowDecorationWithDrawable, LinearLayoutManager.VERTICAL);
    }

    private void initializeSpaceDecorations() {
        SpaceDecoration spaceDecorationVertical = new SpaceDecoration(this, SpaceDecoration.VERTICAL, SPACE_SIZE_IN_PX);
        SpaceDecoration spaceDecorationHorizontal = new SpaceDecoration(this, SpaceDecoration.HORIZONTAL, SPACE_SIZE_IN_PX);

        initializeRecyclerView(R.id.rv_space_decoration_vertical, spaceDecorationVertical, LinearLayoutManager.VERTICAL);
        initializeRecyclerView(R.id.rv_space_decoration_horizontal, spaceDecorationHorizontal, LinearLayoutManager.HORIZONTAL);
    }

    private void initializeMarginDecorations() {
        Rect marginBounds = new Rect(SPACE_SIZE_IN_PX, SPACE_SIZE_IN_PX, SPACE_SIZE_IN_PX, SPACE_SIZE_IN_PX);
        Rect topBottomMarginBounds = new Rect(0, SPACE_SIZE_IN_PX, 0, SPACE_SIZE_IN_PX);
        Rect sideMarginBounds = new Rect(SPACE_SIZE_IN_PX, 0, SPACE_SIZE_IN_PX, 0);

        MarginDecoration marginDecoration = new MarginDecoration(this, MarginDecoration.VERTICAL, marginBounds);
        MarginDecoration marginDecorationTopBottom = new MarginDecoration(this, MarginDecoration.VERTICAL, topBottomMarginBounds);
        MarginDecoration marginDecorationSide = new MarginDecoration(this, MarginDecoration.VERTICAL, sideMarginBounds);

        initializeRecyclerView(R.id.rv_margin_decoration, marginDecoration, LinearLayoutManager.VERTICAL);
        initializeRecyclerView(R.id.rv_margin_decoration_top_bottom, marginDecorationTopBottom, LinearLayoutManager.VERTICAL);
        initializeRecyclerView(R.id.rv_margin_decoration_side, marginDecorationSide, LinearLayoutManager.VERTICAL);
    }

    private void initializeDividerDecorations() {
        Rect spaceBounds = new Rect(SPACE_SIZE_IN_PX, SPACE_SIZE_IN_PX, SPACE_SIZE_IN_PX, SPACE_SIZE_IN_PX);
        Rect topBottomSpaceBounds = new Rect(0, SPACE_SIZE_IN_PX, 0, SPACE_SIZE_IN_PX);
        Rect sideSpaceBounds = new Rect(SPACE_SIZE_IN_PX, 0, SPACE_SIZE_IN_PX, 0);

        DividerDecoration dividerItemDecoration = new DividerDecoration(this, DividerItemDecoration.VERTICAL);
        DividerDecoration dividerItemDecorationWithSpace = new DividerDecoration(this, DividerItemDecoration.VERTICAL, spaceBounds);
        DividerDecoration dividerItemDecorationWithTopBottomSpace = new DividerDecoration(this, DividerItemDecoration.VERTICAL, topBottomSpaceBounds);
        DividerDecoration dividerItemDecorationWithSideSpace = new DividerDecoration(this, DividerItemDecoration.VERTICAL, sideSpaceBounds);
        DividerDecoration dividerItemDecorationHorizontal = new DividerDecoration(this, DividerItemDecoration.HORIZONTAL, sideSpaceBounds);
        DividerDecoration dividerItemDecorationDrawable = new DividerDecoration(this, DividerItemDecoration.VERTICAL, sideSpaceBounds);
        dividerItemDecorationDrawable.setDrawable(new ColorDrawable(Color.BLUE));

        initializeRecyclerView(R.id.rv_divider_decoration, dividerItemDecoration, LinearLayoutManager.VERTICAL);
        initializeRecyclerView(R.id.rv_divider_decoration_with_space, dividerItemDecorationWithSpace, LinearLayoutManager.VERTICAL);
        initializeRecyclerView(R.id.rv_divider_decoration_with_top_bottom_space, dividerItemDecorationWithTopBottomSpace, LinearLayoutManager.VERTICAL);
        initializeRecyclerView(R.id.rv_divider_decoration_with_top_side_space, dividerItemDecorationWithSideSpace, LinearLayoutManager.VERTICAL);
        initializeRecyclerView(R.id.rv_divider_decoration_with_horizontal_view, dividerItemDecorationHorizontal, LinearLayoutManager.HORIZONTAL);
        initializeRecyclerView(R.id.rv_divider_decoration_with_drawable, dividerItemDecorationDrawable, LinearLayoutManager.VERTICAL);
    }
}