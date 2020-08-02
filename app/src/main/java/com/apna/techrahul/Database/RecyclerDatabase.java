package com.apna.techrahul.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = RecyclerEntity.class,version = 1,exportSchema = false)
public abstract class RecyclerDatabase extends RoomDatabase {
    public static RecyclerDatabase instance;

    public abstract RecyclerDao getRecyclerDao();

    public static synchronized  RecyclerDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),RecyclerDatabase.class,"recycler-database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
