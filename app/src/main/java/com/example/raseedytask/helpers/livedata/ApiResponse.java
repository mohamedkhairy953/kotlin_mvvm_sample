package com.example.raseedytask.helpers.livedata;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.raseedytask.App;
import com.example.raseedytask.Constants;
import com.example.raseedytask.R;
import com.example.raseedytask.helpers.utilities.NetworkUtils;
import retrofit2.Response;

import java.lang.ref.WeakReference;

/**
 * Wrapper helper class that wraps the result of the network calls
 * </br>
 * Provides easy access to response body and code
 *
 * @param <T>
 */
public class ApiResponse<T> {

    private static final String TAG = "ApiResponse";
    public final int code;
    @Nullable
    public final T body;

    @Nullable
    private final String errorMessage;

    private final boolean isSuccessful;

    private WeakReference<Context> contextWeakReference = new
            WeakReference<>(App.get().getApplicationContext());

    private Resources appResources = contextWeakReference.get().getResources();

    public ApiResponse(Throwable error) {
        code = Constants.NetworkStatusCodes.CODE_500;
        body = null;
        isSuccessful = false;
        Log.e(TAG, error.getMessage(), error);
        if (contextWeakReference.get() != null &&
                !NetworkUtils.isNetworkAvailable(contextWeakReference.get())) {
            errorMessage = appResources.getString(R.string.general_network_error);
        } else
            errorMessage = appResources.getString(R.string.no_response_from_server_text);
    }

    public ApiResponse(Response<T> response) {
        code = response.code();
        if (response.isSuccessful()) {
            body = response.body();
            errorMessage = null;
            isSuccessful = true;
            Log.d("LOGD", "ApiResponse: " + response.body());
        } else {
            isSuccessful = false;
            body = null;
            Log.d("LOGD", "ApiResponse: false");
            if (contextWeakReference.get() != null) {
                if (response.code() == Constants.NetworkStatusCodes.CODE_401) {
                    errorMessage = appResources.getString(R.string.general_invalid_credentials_error);
                } else {
                    errorMessage = appResources.getString(R.string.general_error);
                }
            } else
                errorMessage = appResources.getString(R.string.general_error);
        }
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public int getCode() {
        return code;
    }

    @Nullable
    public T getBody() {
        return body;
    }

}