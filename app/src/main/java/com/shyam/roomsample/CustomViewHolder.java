package com.shyam.roomsample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    private View item;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        item = itemView;
    }

    public void bindData(CustomData data) {
        TextView id = item.findViewById(R.id.id);
        TextView text = item.findViewById(R.id.text);
        id.setText(data.getId());
        text.setText(data.getText());
    }
}
