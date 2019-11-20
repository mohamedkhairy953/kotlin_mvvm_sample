package com.example.raseedytask.di;

import com.example.raseedytask.App;
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

public interface AppComponent {
   // void inject(@NotNull AdsViewModel adsViewModel);

    void inject(@NotNull MainActivity mainActivity);

    void inject(@NotNull AdsViewModel adsViewModel);

    void inject(App app);

}
