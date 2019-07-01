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
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.limcg.mvvmsample.adapters.MyRecyclerViewAdapter;
import com.limcg.mvvmsample.models.People;
import com.limcg.mvvmsample.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private ViewFlipper viewFlipper;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {

        Log.e(TAG, "MainActivity onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        viewFlipper = findViewById(R.id.view_flipper);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();
        if (viewFlipper != null) {

            viewFlipper.setInAnimation(this, R.anim.in_from_left);
            viewFlipper.setOutAnimation(this, R.anim.out_to_right);
        }



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

        mainActivityViewModel.getMessages().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {

                if(strings == null)
                {
                    return;
                }

                if (viewFlipper != null) {
                    for (String text : strings) {
                        TextView textView = new TextView(MainActivity.this);
                        LinearLayout.LayoutParams layoutParams =
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(30, 30, 30, 30);
                        textView.setTextColor(getResources().getColor(android.R.color.white));
                        layoutParams.weight = 1.0f;
                        layoutParams.gravity = Gravity.CENTER;

                        //textView.setGravity(Gravity.CENTER);
                        textView.setLayoutParams(layoutParams);
                        textView.setMaxLines(1);
//                        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//                        textView.setMarqueeRepeatLimit(-1);
                        textView.setSelected(true);
                        textView.setSingleLine(true);
                        textView.setText(text);
                        viewFlipper.addView(textView);
                    }
                }

            }
        });

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
            viewFlipper.stopFlipping();

            //startActivity(new Intent(MainActivity.this, AddPeopleActivity.class));
        }

    }
}
