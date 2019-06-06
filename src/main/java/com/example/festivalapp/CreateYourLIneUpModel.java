package com.example.festivalapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.festivalapp.models.CreateModel;

import java.util.List;

public class CreateYourLIneUpModel extends  AndroidViewModel {
    private CreateYourLineUpRepository mRepository;
    private LiveData<List<CreateModel>> mLineUpList;

    public CreateYourLIneUpModel(@NonNull Application application) {
        super(application);
        mRepository = new CreateYourLineUpRepository(application.getApplicationContext());
        mLineUpList = mRepository.getAllLineUps();
    }


    public LiveData<List<CreateModel>> getLineUp() {
        return mLineUpList;

    }

    public void insert(CreateModel createModel) {
        mRepository.insert(createModel);
    }

    public void update(CreateModel createModel) {
        mRepository.update(createModel);
    }

    public void deleteAllLineUps() {
        mRepository.deleteAllLineUps();
    }

    public void delete(CreateModel createModel) {
        mRepository.delete(createModel);
    }


}
