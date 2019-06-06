package com.example.festivalapp.databaseCreateYourLineUp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.festivalapp.models.CreateModel;

import java.util.List;

@Dao
public interface CreateDao {
    @Insert
    void insert(CreateModel createModel);

    @Delete
    void delete(CreateModel createModel);

    @Update
    void update(CreateModel createModel);

    @Query("DELETE FROM createlineup_table")
    void deleteAllLineUps();

    @Query("SELECT * from createlineup_table")
    LiveData<List<CreateModel>> getAllArtist();
}
