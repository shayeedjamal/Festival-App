package com.example.festivalapp;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.festivalapp.databaseCreateYourLineUp.CreateDao;
import com.example.festivalapp.databaseCreateYourLineUp.CreateLineUpDatabase;
import com.example.festivalapp.models.CreateModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CreateYourLineUpRepository {
    private CreateLineUpDatabase createDatabase;
    private CreateDao mcreateDao;
    private LiveData<List<CreateModel>> mLineUpList;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public CreateYourLineUpRepository(Context context) {
        createDatabase = CreateLineUpDatabase.getDatabase(context);
        mcreateDao = createDatabase.createDao();
        mLineUpList=mcreateDao.getAllArtist();
    }
    public LiveData<List<CreateModel>> getAllLineUps() {
        return mLineUpList;
    }

    public void insert(final CreateModel createModel) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mcreateDao.insert(createModel);
            }
        });
    }



    public void update(final CreateModel createModel) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mcreateDao.update(createModel);
            }
        });
    }

    public void deleteAllLineUps() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mcreateDao.deleteAllLineUps();

                getAllLineUps();
            }
        });
    }

    public void delete(final CreateModel createModel) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mcreateDao.delete(createModel);
            }
        });
    }

}
