package com.example.raseedytask.di.module;

import android.app.Application;
import androidx.room.Room;
import com.example.raseedytask.AppDatabase;
import com.example.raseedytask.adslist.model.dao.AdsDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Mohamed Khaled on Thu, 09/Aug/2018 at 1:01 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
@Module
public class DaoModule {

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(Application app) {
        return Room.databaseBuilder(app,
                AppDatabase.class, "raseedy_local.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public AdsDao provideAdsDao(AppDatabase appDatabase) {
        return appDatabase.adsDao();
    }


}
