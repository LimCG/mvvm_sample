package com.limcg.mvvmsample.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.limcg.mvvmsample.models.People;
import com.limcg.mvvmsample.repositories.PeopleRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    private MutableLiveData<List<People>> mPeopleContainer;

    public MainActivityViewModel()
    {
        Log.e(TAG, "init MainActivityViewModel");

        mPeopleContainer = new PeopleRepository().getRepoAllPeoples();
    }

    public LiveData<List<People>> getAllPeoples() {

        return mPeopleContainer;
    }
}
