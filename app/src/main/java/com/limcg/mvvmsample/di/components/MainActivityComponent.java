package com.limcg.mvvmsample.di.components;

import android.app.Activity;
import android.content.Context;

import com.limcg.mvvmsample.MainActivity;
import com.limcg.mvvmsample.di.modules.AppModule;
import com.limcg.mvvmsample.di.modules.MainActivityModule;
import com.limcg.mvvmsample.di.qualifiers.ActivityContext;
import com.limcg.mvvmsample.di.qualifiers.ScopeActivity;
import com.limcg.mvvmsample.repositories.PeopleRepository;

import javax.inject.Named;
import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

@ScopeActivity
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity activity);

    @ActivityContext
    Context getActivityContext();
}
