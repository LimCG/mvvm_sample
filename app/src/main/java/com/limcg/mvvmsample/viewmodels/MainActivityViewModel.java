package com.limcg.mvvmsample.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.limcg.mvvmsample.models.People;
import com.limcg.mvvmsample.repositories.MessageRepository;
import com.limcg.mvvmsample.repositories.PeopleRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    // Container list
    private LiveData<List<People>> mPeopleContainer;

    // Message live data
    private LiveData<List<String>> liveDataMessage;

    // people repository
    private PeopleRepository peopleRepository = PeopleRepository.getInstance();

    public LiveData<List<String>> getMessages()
    {
        if(liveDataMessage == null)
        {
            liveDataMessage = new MessageRepository().getMessages();
        }

        return liveDataMessage;
    }

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
