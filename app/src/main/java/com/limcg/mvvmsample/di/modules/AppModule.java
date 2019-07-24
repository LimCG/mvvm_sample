package com.limcg.mvvmsample.di.modules;

import android.app.Application;
import android.content.Context;

import com.limcg.mvvmsample.di.qualifiers.ApplicationContext;
import com.limcg.mvvmsample.di.qualifiers.BaseContext;
import com.limcg.mvvmsample.repositories.PeopleRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application app) {

        this.mApplication = app;
    }

    @Provides
    public Application provideApplication()
    {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    public Context provideApplicationContext()
    {
        return mApplication.getApplicationContext();
    }

    @Provides
    @BaseContext
    public Context provideBaseContext()
    {
        return mApplication.getBaseContext();
    }

    @Provides
    @Singleton
    PeopleRepository providePeopleRepository() {
        return new PeopleRepository(mApplication.getApplicationContext());
    }

}
