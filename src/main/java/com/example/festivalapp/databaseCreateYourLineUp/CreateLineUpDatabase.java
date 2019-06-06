package com.example.festivalapp.databaseCreateYourLineUp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.festivalapp.models.CreateModel;

@Database(entities = {CreateModel.class}, version = 1, exportSchema = false)
public abstract class CreateLineUpDatabase extends RoomDatabase {
    public abstract CreateDao createDao();

    private static String NAME_DATABASE="createlineup_database";

    private static volatile CreateLineUpDatabase INSTANCE;

   public static CreateLineUpDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CreateLineUpDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CreateLineUpDatabase.class, NAME_DATABASE)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
