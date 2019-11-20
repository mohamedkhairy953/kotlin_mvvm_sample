package com.example.raseedytask.helpers.retrofit;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;


/**
 * Interceptor which adds headers from shared preferences according to the added custom headers,
 * Authentication, languageCode and level headers by default.
 * <br>
 * when No-Authentication or Single-Language header is set to true add Authentication and multi
 * language headers from prefs
 */
@Singleton
public class MyServiceInterceptor implements Interceptor {

    private Request.Builder requestBuilder;

    @Inject
    MyServiceInterceptor() {
    }


    @NotNull
    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        requestBuilder = request.newBuilder();
        // todo no headers
        return chain.proceed(requestBuilder.build());
    }


}