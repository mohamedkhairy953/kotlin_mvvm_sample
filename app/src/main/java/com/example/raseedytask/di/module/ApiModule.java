package com.example.raseedytask.di.module;

import com.example.raseedytask.adslist.model.remote.AdsServices;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import javax.inject.Singleton;

@Module
public class ApiModule {

   @Provides
    @Singleton
    public AdsServices providesAdsService(Retrofit retrofit) {
        return retrofit.create(AdsServices.class);
    }

}
