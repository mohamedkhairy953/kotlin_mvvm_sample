package com.example.raseedytask;

import com.example.raseedytask.adslist.view.MainActivity;
import com.example.raseedytask.adslist.viewmodel.AdsViewModel;
import com.example.raseedytask.di.module.*;
import dagger.Component;
import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

@Singleton
@Component(
        modules = {
                NetModule.class,
                DaoModule.class,
                ApiModule.class,
                AppModule.class,
                RepoModule.class
        }
)

public interface TestAppComponent {
    void inject(@NotNull BaseTest baseTest);
    // void inject(@NotNull AdsViewModel adsViewModel);


}
