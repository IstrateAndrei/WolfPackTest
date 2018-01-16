package com.power.flower.flowerpower.presentation.presenters;

import com.power.flower.flowerpower.data.models.OrdersModel;
import com.power.flower.flowerpower.data.network.retrofit.ApiRequest;
import com.power.flower.flowerpower.data.network.retrofit.RetrofitService;
import com.power.flower.flowerpower.presentation.views.MainView;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Petre-pc on 1/16/2018.
 */

interface MainPresenterView{

    void getOrders(String authString);
}

public class MainPresenter implements MainPresenterView {

    private MainView mView;

    public MainPresenter(MainView view)
    {
        this.mView = view;
    }

    @Override
    public void getOrders(String authString) {

        Flowable<OrdersModel> ordersFlowable = new RetrofitService().getService().getOrders(authString);
        ordersFlowable.subscribeOn(Schedulers.newThread())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(new Consumer<OrdersModel>() {
                          @Override
                          public void accept(OrdersModel ordersModel) throws Exception {
                            mView.displayOrders(ordersModel);
                          }
                      });
    }
}
