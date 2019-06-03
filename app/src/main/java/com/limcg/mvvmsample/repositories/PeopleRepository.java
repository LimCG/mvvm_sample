package com.limcg.mvvmsample.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.limcg.mvvmsample.models.People;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton People Repository
 */
public class PeopleRepository {

    // instance
    private static PeopleRepository instance;

    // People data set
    private ArrayList<People> dataSet = new ArrayList<>();

    // People container
    private MutableLiveData<List<People>> peopleContainer = new MutableLiveData<>();

    // update status
    private MutableLiveData<Boolean> updateStatus = new MutableLiveData<>();

    /**
     * Singleton instance
     */
    public static PeopleRepository getInstance(){
        if(instance == null) {
            instance = new PeopleRepository();
        }
        return instance;
    }

    /**
     * Set list update status
     * @param status
     */
    public void setStatus(boolean status)
    {
        updateStatus.setValue(status);
    }

    /**
     * Get list update status
     * @return mutable live data
     */
    public MutableLiveData<Boolean> getStatus()
    {
        return updateStatus;
    }

    /**
     * Get all peoples
     * @return mutable live data
     */
    public MutableLiveData<List<People>> getRepoAllPeoples()
    {
        Log.e("TAG", "Network called get data from REST API");

        setDataSet();

        peopleContainer.setValue(dataSet);

        return peopleContainer;
    }

    /**
     * Add single user
     * @param profileName
     */
    public void addRepoUser(String profileName)
    {
        People people = new People("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg", profileName);

        dataSet.add(people);

        peopleContainer.setValue(dataSet);
    }

    /**
     * Propagate data
     */
    private void setDataSet()
    {
        People people1 = new People("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg",
                "Henry");
        dataSet.add(people1);

        People people2 = new People("https://i.redd.it/tpsnoz5bzo501.jpg", "Alex");
        dataSet.add(people2);

        People people3 = new People("https://i.redd.it/j6myfqglup501.jpg", "Jason");
        dataSet.add(people3);

        People people4 = new People("https://i.redd.it/k98uzl68eh501.jpg", "John");
        dataSet.add(people4);

        People people5 = new People("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg", "Ben");
        dataSet.add(people5);

        People people6 = new People("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg", "Ben");
        dataSet.add(people6);

        People people7 = new People("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg", "Ben");
        dataSet.add(people7);

        People people8 = new People("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg", "Ben");
        dataSet.add(people8);

        People people9 = new People("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg", "Ben");
        dataSet.add(people9);

        People people10 = new People("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg", "Ben");
        dataSet.add(people10);
    }
}
