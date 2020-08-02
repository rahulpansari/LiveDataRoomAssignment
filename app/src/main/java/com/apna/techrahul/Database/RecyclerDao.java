package com.apna.techrahul.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface RecyclerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RecyclerEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RecyclerEntity> entitylist);

    @Update
    void update(RecyclerEntity entity);

    @Delete
    void delete(RecyclerEntity entity);

    @Query("SELECT * FROM recycler_entity")
   public LiveData<List<RecyclerEntity>> getEntities();
}
