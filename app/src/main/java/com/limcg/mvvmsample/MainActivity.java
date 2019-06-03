package com.limcg.mvvmsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.limcg.mvvmsample.adapters.MyRecyclerViewAdapter;
import com.limcg.mvvmsample.models.People;
import com.limcg.mvvmsample.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.e(TAG, "MainActivity onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);

        setSupportActionBar(toolbar);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getAllPeoples().observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged(@Nullable List<People> peopleList) {

               myRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();

    }

    private void initRecyclerView()
    {
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, mainActivityViewModel.getAllPeoples().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }
}
