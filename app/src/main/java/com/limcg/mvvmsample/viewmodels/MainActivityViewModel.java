package com.limcg.mvvmsample.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.limcg.mvvmsample.models.People;
import com.limcg.mvvmsample.repositories.PeopleRepository;

import java.util.List;

import javax.inject.Inject;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    // Container list
    private LiveData<List<People>> mPeopleContainer;

    @Inject
    PeopleRepository peopleRepository;

    /**
     * Get all people to propagate into list
     * @return List container
     */
    public LiveData<List<People>> getAllPeoples() {

        // Check if null get data from repository
        if(mPeopleContainer == null)
        {
            mPeopleContainer = peopleRepository.getRepoAllPeoples();
        }

        return mPeopleContainer;
    }

    /**
     * Get the update status
     * @return boolean
     */
    public LiveData<Boolean> getUpdateStatus() {

        return peopleRepository.getStatus();
    }

}
