package com.example.raseedytask;

import android.app.Application;
import android.content.Context;
import com.example.raseedytask.di.AppComponent;
import com.example.raseedytask.di.DaggerAppComponent;
import com.example.raseedytask.di.module.*;

public class App extends Application {
    private static AppComponent appComponent;
    private static App instance;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static Context get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .netModule(new NetModule(BuildConfig.BASE_URL))
                .daoModule(new DaoModule())
                .repoModule(new RepoModule())
                .build();
        appComponent.inject(this);

    }

}
