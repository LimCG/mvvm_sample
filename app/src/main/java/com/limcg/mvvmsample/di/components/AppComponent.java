package com.limcg.mvvmsample.di.components;

import android.content.Context;

import com.limcg.mvvmsample.MyApplication;
import com.limcg.mvvmsample.di.modules.AppModule;
import com.limcg.mvvmsample.di.qualifiers.ActivityContext;
import com.limcg.mvvmsample.di.qualifiers.ApplicationContext;
import com.limcg.mvvmsample.di.qualifiers.BaseContext;
import com.limcg.mvvmsample.repositories.PeopleRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MyApplication myApplication);

    @BaseContext
    Context getApplicationBaseContext();

    @ApplicationContext
    Context getApplicationContext();
}
