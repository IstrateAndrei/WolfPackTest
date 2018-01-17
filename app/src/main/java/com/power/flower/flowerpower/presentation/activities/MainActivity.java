package com.power.flower.flowerpower.presentation.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.power.flower.flowerpower.R;
import com.power.flower.flowerpower.data.models.OrderModel;
import com.power.flower.flowerpower.data.models.OrdersModel;
import com.power.flower.flowerpower.presentation.adapters.OrdersAdapter;
import com.power.flower.flowerpower.presentation.fragments.OrderDetailFragment;
import com.power.flower.flowerpower.presentation.presenters.MainPresenter;
import com.power.flower.flowerpower.presentation.views.MainView;
import com.power.flower.flowerpower.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView, FragmentManager.OnBackStackChangedListener{

    @BindView(R.id.order_recycler) RecyclerView mRecycler;
    @BindView(R.id.exit_button) Button exitButton;
    @BindView(R.id.frame_container) FrameLayout mFrameLayout;
    @BindView(R.id.my_progress_bar) ProgressBar mProgressBar;
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        ButterKnife.bind(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        mPresenter = new MainPresenter(this);
        mPresenter.getOrders(Constants.AUTHORIZATION_STRING);

    }

    OrdersAdapter.OnOrderItemClickListener mListener = new OrdersAdapter.OnOrderItemClickListener() {
        @Override
        public void onOrderClick(OrderModel model) {
            goToDetailFragment(model);
        }
    };

    @Override
    public void displayOrders(OrdersModel orders) {

        if(!orders.getOrders().isEmpty())
        {
            mRecycler.setVisibility(View.VISIBLE);
            mRecycler.setLayoutManager(new LinearLayoutManager(this));
            OrdersAdapter mAdapter = new OrdersAdapter(orders.getOrders(),this,mListener);
            mRecycler.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @OnClick({R.id.exit_button})
    public void onButtonClick(View v)
    {
        switch (v.getId())
        {
            case R.id.exit_button:
                this.finish();
                break;
        }
    }

    public void goToDetailFragment(OrderModel model)
    {
        if(mFrameLayout.getVisibility() != View.VISIBLE)
        {
            mFrameLayout.setVisibility(View.VISIBLE);
            exitButton.setVisibility(View.GONE);
        }
        OrderDetailFragment mFragment = new OrderDetailFragment();
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("Order_item",model);
        mFragment.setArguments(mBundle);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container,mFragment).addToBackStack(OrderDetailFragment.class.getSimpleName()).commit();
    }

    @Override
    public void onBackStackChanged() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0)
        {
            mFrameLayout.setVisibility(View.GONE);
            exitButton.setVisibility(View.VISIBLE);
        }
    }
}

