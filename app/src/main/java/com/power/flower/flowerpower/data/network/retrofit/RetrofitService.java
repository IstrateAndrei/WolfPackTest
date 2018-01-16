package com.power.flower.flowerpower.data.network.retrofit;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.power.flower.flowerpower.util.Constants;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Petre-pc on 1/15/2018.
 */

public final class RetrofitService {

    private Retrofit mRetrofit;

    private Retrofit getInstance()
    {
        if(mRetrofit == null)
        {
            mRetrofit = mBuilder.build();
        }
        return mRetrofit;
    }
    private HttpLoggingInterceptor getLoggingInterceptor()
    {
        HttpLoggingInterceptor mInterceptor = new HttpLoggingInterceptor();
        mInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return mInterceptor;
    }

    private Retrofit.Builder mBuilder = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                                                              .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.newThread()))
                                                              .addConverterFactory(GsonConverterFactory.create(new Gson()))
                                                              .client(getOkHttpClient());


    private synchronized OkHttpClient getOkHttpClient()
    {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                                                         .readTimeout(60, TimeUnit.SECONDS)
                                                         .connectTimeout(60, TimeUnit.SECONDS)
                                                         .addInterceptor(getLoggingInterceptor());
        return okBuilder.build();
    }

    public ApiRequest getService()
    {
        return getInstance().create(ApiRequest.class);
    }
}
