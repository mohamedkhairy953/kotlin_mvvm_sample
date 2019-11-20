package com.example.raseedytask.di.module;

import android.util.Log;
import com.example.raseedytask.BuildConfig;
import com.example.raseedytask.helpers.retrofit.LiveDataCallAdapterFactory;
import com.example.raseedytask.helpers.retrofit.MyServiceInterceptor;
import com.example.raseedytask.helpers.retrofit.NullOnEmptyConverterFactory;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.inject.Singleton;

@Module
public class NetModule {
    final private String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setPrettyPrinting();
        return gsonBuilder.create();
    }

    /**
     * @param myServiceInterceptor injected in MyServiceInterceptor class using constructor injection
     * @param interceptor
     * @return
     */
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(MyServiceInterceptor myServiceInterceptor, HttpLoggingInterceptor interceptor) {
        Log.i("NetModule", "Providing OkHttpClient");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(myServiceInterceptor);
        builder.addNetworkInterceptor(interceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(ScalarsConverterFactory.create()) //the ordering is importing, we must but ScalersConverter before Gson
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        if (BuildConfig.DEBUG)
            return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            return new HttpLoggingInterceptor();
    }
}
