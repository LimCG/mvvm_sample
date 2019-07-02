package com.limcg.mvvmsample;

import android.app.Application;

import com.limcg.mvvmsample.di.components.AppComponent;
import com.limcg.mvvmsample.di.components.DaggerAppComponent;
import com.limcg.mvvmsample.di.modules.AppModule;

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder().
                appModule(new AppModule(this)).build();
    }

    public AppComponent getmAppComponent()
    {
        return mAppComponent;
    }
}
