package com.limcg.mvvmsample.di.modules;

import android.content.Context;

import com.limcg.mvvmsample.di.qualifiers.ActivityContext;
import com.limcg.mvvmsample.repositories.PeopleRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private Context mContext;

    public MainActivityModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @ActivityContext
    Context provideContext()
    {
        return mContext;
    }

}
