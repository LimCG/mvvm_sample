package com.limcg.mvvmsample.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.limcg.mvvmsample.repositories.PeopleRepository;

import javax.inject.Inject;


public class AddPeopleActivityViewModel extends ViewModel {

    @Inject
    PeopleRepository peopleRepository;

    /**
     * Adding single user
     * @param profileName
     * @return status
     */
    public LiveData<Boolean> addUser(String profileName)
    {
        // Return status false if profile name is empty
        if(profileName == null || profileName.isEmpty())
        {
            peopleRepository.setStatus(false);

            return peopleRepository.getStatus();
        }

        // Add people to people repository
        peopleRepository.addRepoUser(profileName);

        // set update status for recycler view navigate to bottom
        peopleRepository.setStatus(true);

        // set return status. Added successfully
        return peopleRepository.getStatus();
    }
}
