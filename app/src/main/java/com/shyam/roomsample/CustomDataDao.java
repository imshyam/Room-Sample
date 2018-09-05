package com.shyam.roomsample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CustomDataDao {

    @Query("SELECT * FROM custom_data_table")
    List<CustomData> getAll();

    @Insert
    void insertData(CustomData data);

}
