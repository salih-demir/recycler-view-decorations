package com.cascade.recyclerviewdecorations.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cascade.recyclerviewdecorations.R;

import java.util.List;

/**
 * Created by Salih Demir on 1.08.2017.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCarBrand;
        TextView textViewCarModel;

        ViewHolder(View itemView) {
            super(itemView);

            textViewCarBrand = itemView.findViewById(R.id.tv_car_brand);
            textViewCarModel = itemView.findViewById(R.id.tv_car_model);
        }
    }

    private List<Car> carList;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.textViewCarBrand.setText(car.getBrand());
        holder.textViewCarModel.setText(car.getModel());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}