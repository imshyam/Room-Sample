package com.shyam.roomsample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "custom_data_table")
class CustomData {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "text")
    private String text;

    CustomData(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
