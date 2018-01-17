package com.power.flower.flowerpower.data.models;

import java.io.Serializable;

/**
 * Created by Petre-pc on 1/16/2018.
 */

public class OrderModel implements Serializable{

    private int id;
    private String description;
    private float price;
    private String deliver_to;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDeliver_to() {
        return deliver_to;
    }

    public void setDeliver_to(String deliver_to) {
        this.deliver_to = deliver_to;
    }
}
