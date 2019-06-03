package com.limcg.mvvmsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.limcg.mvvmsample.adapters.MyRecyclerViewAdapter;
import com.limcg.mvvmsample.models.People;
import com.limcg.mvvmsample.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.e(TAG, "MainActivity onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        // observe on people list changed
        mainActivityViewModel.getAllPeoples().observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged(@Nullable List<People> peopleList) {

                if(peopleList == null)
                {
                    return;
                }

                Toast.makeText(MainActivity.this, "Total item(s): " + peopleList.size(), Toast.LENGTH_SHORT).show();

                myRecyclerViewAdapter.notifyDataSetChanged();

            }
        });

        // observe on list update status
        mainActivityViewModel.getUpdateStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {

                if(aBoolean != null && aBoolean)
                {
                    recyclerView.smoothScrollToPosition(mainActivityViewModel.getAllPeoples().getValue().size() - 1);
                }
            }
        });

        initRecyclerView();

        Button button = findViewById(R.id.btn_add_more);
        button.setOnClickListener(this);
    }

    private void initRecyclerView()
    {
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, mainActivityViewModel.getAllPeoples().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    @Override
    public void onClick(View view) {

        // Go to Add activity
        if(view.getId() == R.id.btn_add_more)
        {
            startActivity(new Intent(MainActivity.this, AddPeopleActivity.class));
        }

    }
}
