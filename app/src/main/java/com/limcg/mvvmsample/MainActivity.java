package com.limcg.mvvmsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import com.limcg.mvvmsample.di.components.DaggerMainActivityComponent;
import com.limcg.mvvmsample.di.components.MainActivityComponent;
import com.limcg.mvvmsample.di.modules.MainActivityModule;
import com.limcg.mvvmsample.di.qualifiers.ActivityContext;
import com.limcg.mvvmsample.models.People;
import com.limcg.mvvmsample.viewmodels.MainActivityViewModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private MainActivityComponent mainActivityComponent;

    @Inject
    Context mContext;

    private MainActivityComponent getActivityComponent()
    {
        if (mainActivityComponent == null)
        {
            mainActivityComponent = DaggerMainActivityComponent.builder()
                    .mainActivityModule(new MainActivityModule(this))
                    .appComponent(((MyApplication) getApplication()).getmAppComponent())
                    .build();
        }

        return mainActivityComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.e(TAG, "MainActivity onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

//        // observe on people list changed
//        mainActivityViewModel.getAllPeoples().observe(this, new Observer<List<People>>() {
//            @Override
//            public void onChanged(@Nullable List<People> peopleList) {
//
//                if(peopleList == null)
//                {
//                    return;
//                }
//
//                Toast.makeText(MainActivity.this, "Total item(s): " + peopleList.size(), Toast.LENGTH_SHORT).show();
//
//                myRecyclerViewAdapter.notifyDataSetChanged();
//
//            }
//        });

//        // observe on list update status
//        mainActivityViewModel.getUpdateStatus().observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(@Nullable Boolean aBoolean) {
//
//                if(aBoolean != null && aBoolean)
//                {
//                    recyclerView.smoothScrollToPosition(mainActivityViewModel.getAllPeoples().getValue().size() - 1);
//                }
//            }
//        });

       // initRecyclerView();

        Button button = findViewById(R.id.btn_add_more);
        button.setOnClickListener(this);

        PackageInfo pInfo = null;
        try {
            pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            String version = pInfo.versionName;

            Toast.makeText(mContext, version, Toast.LENGTH_LONG).show();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
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
