package com.openroad.api.catalog.item.controller.dtos;

public class ItemCreateDTO {
    private int amount;

    private String observation;

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
