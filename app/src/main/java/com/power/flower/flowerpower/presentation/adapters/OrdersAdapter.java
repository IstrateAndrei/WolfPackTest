package com.power.flower.flowerpower.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.power.flower.flowerpower.R;
import com.power.flower.flowerpower.data.models.OrderModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Petre-pc on 1/17/2018.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private ArrayList<OrderModel> mList;
    private Context mContext;
    private OnOrderItemClickListener mListner;

  public interface OnOrderItemClickListener{
        void onOrderClick(OrderModel model);
    }

    public OrdersAdapter(ArrayList<OrderModel> list,Context context, OnOrderItemClickListener listener)
    {
        this.mList = list;
        this.mContext = context;
        this.mListner = listener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout,parent,false);


        return new OrderViewHolder(v,mContext);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderModel currentItem = mList.get(position);

        holder.clientText.setText(currentItem.getDeliver_to());
        holder.priceText.setText(String.valueOf(currentItem.getPrice()));
        holder.bind(currentItem,mListner);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.client_text)
        TextView clientText;
        @BindView(R.id.price_text)
        TextView priceText;

        private Context mContext;

        OrderViewHolder(View itemView, Context context) {
            super(itemView);
            this.mContext = context;
            ButterKnife.bind(this, itemView);
        }

        public void bind(final OrderModel model, final OnOrderItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onOrderClick(model);
                }
            });
        }
    }

}
