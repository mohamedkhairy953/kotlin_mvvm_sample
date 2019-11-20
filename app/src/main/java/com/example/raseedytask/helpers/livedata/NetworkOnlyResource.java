package com.example.raseedytask.helpers.livedata;

import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.example.raseedytask.Constants;
import com.example.raseedytask.helpers.core.AppExecutors;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;


/**
 * A generic class that can provide a resource backed by the network only .
 *
 * @param <ResultType>
 * @param <RequestType>
 */
public abstract class NetworkOnlyResource<ResultType, RequestType> {

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();
    private final AppExecutors appExecutors;
    private Gson gson;

    @MainThread
    public NetworkOnlyResource(AppExecutors appExecutors) {
        result.setValue(Resource.loading(null));
        this.appExecutors = appExecutors;
        this.gson = new GsonBuilder().create();
        fetchFromNetwork();
    }

    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        if (!(result.getValue() != null && result.getValue().equals(newValue))) {
            appExecutors.mainThread().execute(() -> result.setValue(newValue));
        }
    }

    private void fetchFromNetwork() {
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            //noinspection ConstantConditions
            if (response != null && response.isSuccessful()) {
                JsonElement jsonElement = gson.toJsonTree(response.body, Object.class);
                if (jsonElement.isJsonObject() && jsonElement.getAsJsonObject().has("success")) {
                    if (jsonElement.getAsJsonObject().get("success").getAsBoolean()) {
                        Log.d("NetworkOnlyResource", "success status is: " + jsonElement.getAsJsonObject().get("status").getAsInt());
                        setSuccessResource(response);
                    } else {
                        Log.d("NetworkOnlyResource", "fail status is: " + jsonElement.getAsJsonObject().get("status").getAsInt());
                        appExecutors.diskIO().execute(() ->
                                setValue(Resource.error(jsonElement.getAsJsonObject().get("message").getAsString()
                                        , response.getCode(), null)));
                    }
                } else
                    appExecutors.diskIO().execute(() -> setSuccessResource(response));
            } else {
                appExecutors.diskIO().execute(() -> {
                    RequestType requestType = processResponse(response);
                    ResultType resultType = processResult(requestType);
                    if (response != null) {
                        appExecutors.diskIO().execute(() ->
                                setValue(Resource.error(response.getErrorMessage(),
                                        response.getCode(), resultType)));
                    } else
                        appExecutors.diskIO().execute(() ->
                                setValue(Resource.error("Error, something happened",
                                        Constants.NetworkStatusCodes.CODE_500, resultType)));
                    onFetchFailed();
                });

            }
        });
    }

    private void setSuccessResource(ApiResponse<RequestType> response) {
        RequestType requestType = processResponse(response);
        ResultType resultType = processResult(requestType);
        setValue(Resource.success(resultType)
        );
    }

    protected void onFetchFailed() {
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }

    @WorkerThread
    protected RequestType processResponse(ApiResponse<RequestType> response) {
        return response.body;
    }

    @WorkerThread
    protected abstract ResultType processResult(@Nullable RequestType result);


    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();
}