package com.limcg.mvvmsample.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.limcg.mvvmsample.models.People;

import java.util.ArrayList;
import java.util.List;

public class PeopleRepository {

    private ArrayList<People> dataSet = new ArrayList<>();

    public MutableLiveData<List<People>> getRepoAllPeoples()
    {
        Log.e("TAG", "Network called get data from REST API");

        setDataSet();

        MutableLiveData<List<People>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(dataSet);

        return mutableLiveData;
    }

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
