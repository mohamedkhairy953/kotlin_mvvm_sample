package com.example.raseedytask.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {
    final private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return application;
    }


}
