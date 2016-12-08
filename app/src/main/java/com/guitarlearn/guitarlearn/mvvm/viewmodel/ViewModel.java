package com.guitarlearn.guitarlearn.mvvm.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.guitarlearn.guitarlearn.mvvm.model.Item;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 12/8/2016.
 */
public abstract class ViewModel {

    public final ObservableInt isLoading;
    public final ObservableBoolean isRefreshing;
    public final ObservableBoolean isNextPageLoading;
    public int totalItems = 0;
    private ResponseListener responseListener;

    protected abstract void onDestroyView();

    public ViewModel() {
        isLoading = new ObservableInt(View.VISIBLE);
        isRefreshing = new ObservableBoolean(false);
        isNextPageLoading = new ObservableBoolean(false);
    }

    public ViewModel(ResponseListener listener) {
        this();
        responseListener = listener;
    }

    protected void setListener(ResponseListener listener) {
        responseListener = listener;
    }

    public interface ResponseListener<T> {
        void onResponse(T response);

        void onNextResponse(T response);

        void onNextError();

        void onError();

    }

    protected <T extends Item> void downloadFirstPage(Call<T> call) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {
                isRefreshing.set(false);
                isLoading.set(View.INVISIBLE);
                if (response.body() == null) {
                    responseListener.onError();
                    Log.e("TAG", "onResponse: "+response.message() );
                    return;
                }
                totalItems = response.body().total;
                if (response.isSuccessful()) {
                    responseListener.onResponse(response.body());
                } else {
                    responseListener.onError();
                    Log.e("TAG", "onResponse: else"+response.message() );
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                isRefreshing.set(false);
                isLoading.set(View.INVISIBLE);
                responseListener.onError();
                Log.e("TAG", "onFailure: "+t.getMessage() );
            }
        });
    }


    protected <T> void downloadNextPage(Call<T> call) {
        isNextPageLoading.set(true);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {
                isNextPageLoading.set(false);
                if (response.isSuccessful()) {
                    responseListener.onNextResponse(response.body());
                } else {
                    responseListener.onNextError();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                isNextPageLoading.set(false);
                responseListener.onNextError();
            }
        });
    }

    protected <T> void downloadData(Call<T> call) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {
                isRefreshing.set(false);
                isLoading.set(View.INVISIBLE);
                if (response.isSuccessful()) {
                    responseListener.onResponse(response.body());
                    Log.e("TAG", "onResponse: s"+response.body() );
                } else {
                    responseListener.onError();
                    Log.e("TAG", "onResponse e: "+response.body() );
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                isRefreshing.set(false);
                isLoading.set(View.INVISIBLE);
                responseListener.onError();
                Log.e("TAG", "onFailure: "+t.getMessage() );
            }
        });
    }

}

