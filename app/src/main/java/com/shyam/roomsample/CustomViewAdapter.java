package com.shyam.roomsample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    List<CustomData> customDataList;

    CustomViewAdapter(List<CustomData> list) {
        customDataList = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        CustomData data = customDataList.get(i);
        customViewHolder.bindData(data);
    }

    @Override
    public int getItemCount() {
        return customDataList.size();
    }

    public void addItem(CustomData data) {
        customDataList.add(data);
        notifyDataSetChanged();
    }
}
