package com.power.flower.flowerpower.data.network.retrofit;

import com.power.flower.flowerpower.data.models.OrdersModel;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Petre-pc on 1/15/2018.
 */

public interface ApiRequest {

    //here we place the REST operations

    @GET("/orders")
    Observable<OrdersModel> getOrders(
            @Header("Authorization") String auth);

}
