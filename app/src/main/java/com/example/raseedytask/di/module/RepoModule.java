package com.example.raseedytask.di.module;

import com.example.raseedytask.adslist.model.AdsRepo;
import com.example.raseedytask.adslist.model.dao.AdsDao;
import com.example.raseedytask.adslist.model.remote.AdsServices;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import javax.inject.Singleton;

@Module
public class RepoModule {

    @Provides
    @Singleton
    public AdsRepo providesAdsRepo(Retrofit retrofit, AdsServices services, AdsDao dao) {
        return new AdsRepo(dao, services);
    }

}
