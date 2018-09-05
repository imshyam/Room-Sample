package com.shyam.roomsample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = CustomData.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CustomDataDao customDataDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "my-db").build();
        }
        return INSTANCE;
    }
}
