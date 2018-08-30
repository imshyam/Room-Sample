package com.shyam.roomsample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = CustomData.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CustomDataDao customDataDao();
}
