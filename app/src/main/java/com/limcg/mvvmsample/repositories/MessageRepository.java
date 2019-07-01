package com.limcg.mvvmsample.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MessageRepository {

    public MutableLiveData<List<String>>  getMessages()
    {
        Log.e("TAG", "getMessages:  message repo" );
        MutableLiveData<List<String>> liveData = new MutableLiveData<>();

        List<String> messageList = new ArrayList<>();
        messageList.add("Message 1 Message 1 Message 1 Message 1 Message 1 Message 1 Message 1 Message 1");
        messageList.add("Message 2 Message 2");

        liveData.setValue(messageList);

        return liveData;

    }

}
