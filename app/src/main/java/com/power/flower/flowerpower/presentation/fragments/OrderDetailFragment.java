package com.power.flower.flowerpower.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.power.flower.flowerpower.R;
import com.power.flower.flowerpower.data.models.OrderModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Petre-pc on 1/17/2018.
 */

public class OrderDetailFragment extends Fragment {

    @BindView(R.id.order_id_text) TextView orderId;
    @BindView(R.id.order_price_text) TextView orderPrice;
    @BindView(R.id.deliver_to_text) TextView orderDeliver;
    @BindView(R.id.order_description_text) TextView orderDescription;
    @BindView(R.id.back_button) Button backButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order_detail_fragment_layout,container,false);
        ButterKnife.bind(this,v);
        if(getArguments() != null)
        {
            OrderModel model = (OrderModel) getArguments().getSerializable("Order_item");
            assert model != null;
            orderId.setText(String.valueOf(model.getId()));
            orderPrice.setText(String.valueOf(model.getPrice()));
            orderDeliver.setText(model.getDeliver_to());
            orderDescription.setText(model.getDescription());
        }
        return v;
    }


    @OnClick({R.id.back_button})
    public void onButtonClicked(View v)
    {
        switch (v.getId())
        {
            case R.id.back_button:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }

}
