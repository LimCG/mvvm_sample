package com.limcg.mvvmsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.limcg.mvvmsample.viewmodels.AddPeopleActivityViewModel;

public class AddPeopleActivity extends AppCompatActivity implements View.OnClickListener {

    // String profile name
    private String profile_name;

    // View model
    private AddPeopleActivityViewModel addPeopleActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);

        addPeopleActivityViewModel = ViewModelProviders.of(this).get(AddPeopleActivityViewModel.class);

        Button btn_add = findViewById(R.id.button_add);
        btn_add.setOnClickListener(this);

        EditText edt_profile_name = findViewById(R.id.edt_profile_name);
        edt_profile_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                profile_name = editable.toString();

            }
        });
    }


    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.button_add)
        {
            // Add a single user
            addPeopleActivityViewModel.addUser(profile_name).observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {

                    if(aBoolean != null && aBoolean)
                    {
                        Toast.makeText(AddPeopleActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();

                        finish();
                    }
                    else
                    {
                        Toast.makeText(AddPeopleActivity.this, "Field is required", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }
}
