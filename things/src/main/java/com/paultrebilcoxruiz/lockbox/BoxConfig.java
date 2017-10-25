package com.paultrebilcoxruiz.lockbox;

public class BoxConfig {

    private String combination;
    private boolean locked;
    private int orderNumber;

    public BoxConfig() {

    }

    public BoxConfig(String combination, boolean locked, int orderNumber) {
        this.combination = combination;
        this.locked = locked;
        this.orderNumber = orderNumber;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
